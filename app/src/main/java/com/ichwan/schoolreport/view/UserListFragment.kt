package com.ichwan.schoolreport.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ichwan.schoolreport.adapter.ListStudentAdapter
import com.ichwan.schoolreport.api.ApiClient
import com.ichwan.schoolreport.databinding.FragmentUserListBinding
import kotlinx.coroutines.launch

class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!
    private val role by lazy { arguments?.getString(ARG_ROLE) ?: "" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ListStudentAdapter(requireContext(), mutableListOf()) { user ->
            val intent = Intent(requireContext(), EditUserActivity::class.java)
            intent.putExtra("regnumber", user.regnumber)
            startActivity(intent)
        }

        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUsers.adapter = adapter

        lifecycleScope.launch {
            try {
                val response = ApiClient(requireContext().applicationContext).instance.getUsersByRole(role)
                if (response.isSuccessful) {
                    adapter.updateData(response.body() ?: emptyList())
                }
                else {
                    Toast.makeText(requireContext(), "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_ROLE = "role"

        fun newInstance(role: String): UserListFragment {
            val fragment = UserListFragment()
            val args = Bundle()
            args.putString(ARG_ROLE, role)
            fragment.arguments = args
            return fragment
        }
    }
}