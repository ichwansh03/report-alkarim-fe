package com.ichwan.schoolreport.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.ichwan.schoolreport.api.ApiClient
import com.ichwan.schoolreport.core.AlkarimApp
import com.ichwan.schoolreport.databinding.FragmentAddCategoryBinding
import com.ichwan.schoolreport.model.CategoryActivity
import kotlinx.coroutines.launch

class AddCategoryFragment : DialogFragment() {

    private lateinit var binding: FragmentAddCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveCategoryBtn.setOnClickListener {
            val categoryName = binding.categoryNameEt.text.toString().trim()
            if (categoryName.isNotEmpty()) {
                val category = CategoryActivity(categoryName)
                lifecycleScope.launch {
                    try {
                        val response = (requireContext().applicationContext as AlkarimApp).apiClient.instance.createCategory(category)
                        if (response.isSuccessful) {
                            Toast.makeText(requireContext(), "Category saved successfully", Toast.LENGTH_SHORT).show()
                            dismiss()
                        } else {
                            Toast.makeText(requireContext(), "Failed to add category: ${response.message()}", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Category name cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
