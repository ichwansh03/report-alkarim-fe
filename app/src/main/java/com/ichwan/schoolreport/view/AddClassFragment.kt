package com.ichwan.schoolreport.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ichwan.schoolreport.databinding.FragmentAddClassBinding
import com.ichwan.schoolreport.model.User
import com.ichwan.schoolreport.viewmodel.ClassViewModel

class AddClassFragment : Fragment() {

    private var _binding: FragmentAddClassBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ClassViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddClassBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        viewModel.teacherList.observe(viewLifecycleOwner) { teachers ->
            if (teachers != null) {
                setupTeacherSpinner(teachers)
            } else {
                // Handle the case where teacher list could not be loaded
                Toast.makeText(context, "Failed to load teachers", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.message.observe(viewLifecycleOwner) { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }

        viewModel.creationSuccess.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                // Navigate back or clear fields after successful creation
                parentFragmentManager.popBackStack()
            }
        }
    }

    private fun setupTeacherSpinner(teachers: List<User>) {
        val teacherNames = teachers.map { it.name } // Extract names
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, teacherNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.rolesSpinner.adapter = adapter
    }

    private fun setupClickListeners() {
        binding.upsertBtn.setOnClickListener {
            val className = binding.nameEditText.text.toString().trim()
            val selectedTeacherName = if (binding.rolesSpinner.selectedItemPosition >= 0) {
                binding.rolesSpinner.selectedItem as? String
            } else {
                null
            }

            viewModel.onAddClassClicked(className, selectedTeacherName)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
