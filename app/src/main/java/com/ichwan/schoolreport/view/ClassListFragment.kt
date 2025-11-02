package com.ichwan.schoolreport.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ichwan.schoolreport.adapter.ClassListAdapter
import com.ichwan.schoolreport.databinding.FragmentClassListBinding
import com.ichwan.schoolreport.viewmodel.ClassViewModel

class ClassListFragment : Fragment() {

    private var _binding: FragmentClassListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ClassViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClassListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()

        // Load the class list when the fragment is created
        viewModel.loadClasses()

        // Setup listener for your FAB to open AddClassFragment
        binding.fabAddClass.setOnClickListener {
            val dialog = AddClassFragment.newInstance()
            dialog.show(childFragmentManager, AddClassFragment.TAG)
        }
    }

    private fun setupRecyclerView() {
        binding.rvClasses.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeViewModel() {
        viewModel.classList.observe(viewLifecycleOwner) { classList ->
            if (classList != null) {
                binding.rvClasses.adapter = ClassListAdapter(classList)
            } else {
                Toast.makeText(requireContext(), "No classes found", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.message.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = ClassListFragment()
    }
}
