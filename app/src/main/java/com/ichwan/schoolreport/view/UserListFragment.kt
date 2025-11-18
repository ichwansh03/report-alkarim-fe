package com.ichwan.schoolreport.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ichwan.schoolreport.adapter.ListStudentAdapter
import com.ichwan.schoolreport.databinding.FragmentUserListBinding
import com.ichwan.schoolreport.viewmodel.UserViewModel
import androidx.fragment.app.viewModels
class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!
    private val role by lazy { arguments?.getString(ARG_ROLE) ?: "" }
    private val viewModel: UserViewModel by viewModels()

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

        viewModel.users.observe(viewLifecycleOwner) { users ->
            adapter.updateData(users ?: emptyList())
        }

        viewModel.message.observe(viewLifecycleOwner) { msg ->
            if (!msg.isNullOrEmpty()) {
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            }
        }

        if (role.isNotBlank()) {
            viewModel.loadUserByRoles(role)
        } else {
            Toast.makeText(requireContext(), "Role not provided", Toast.LENGTH_SHORT).show()
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