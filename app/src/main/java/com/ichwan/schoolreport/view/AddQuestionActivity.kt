package com.ichwan.schoolreport.view

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ichwan.schoolreport.core.AlkarimApp
import com.ichwan.schoolreport.databinding.ActivityAddQuestionBinding
import com.ichwan.schoolreport.model.Question
import com.ichwan.schoolreport.viewmodel.CategoryViewModel
import kotlinx.coroutines.launch

class AddQuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddQuestionBinding
    private val categoryViewModel: CategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()

        binding.addCategoryBtn.setOnClickListener {
            val addCategoryFragment = AddCategoryFragment()
            addCategoryFragment.show(supportFragmentManager, "AddCategoryFragment")
        }

        binding.saveBtn.setOnClickListener {
            // Logic to save the question remains the same
            handleSaveQuestion()
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh categories every time the activity is resumed
        categoryViewModel.loadCategories()
    }

    private fun setupObservers() {
        categoryViewModel.categories.observe(this) { categories ->
            val categoryNames = categories.map { it.category }
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.categorySp.adapter = adapter
        }

        categoryViewModel.message.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleSaveQuestion() {
        val category = binding.categorySp.selectedItem.toString()
        val questionText = binding.questionEt.text.toString().trim()
        val target = binding.classSp.selectedItem.toString()
        val option = when {
            binding.checklistRb.isChecked -> "Checkbox"
            binding.freeTextRb.isChecked -> "Text"
            else -> ""
        }

        if (questionText.isEmpty()) {
            Toast.makeText(this, "Question cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        if (option.isEmpty()) {
            Toast.makeText(this, "Please select an answer type", Toast.LENGTH_SHORT).show()
            return
        }

        val question = Question(
            quest = questionText,
            category = category,
            target = target,
            option = option
        )

        lifecycleScope.launch {
            try {
                val response = (application as AlkarimApp).apiClient.instance.createQuestion(question)
                if (response.isSuccessful) {
                    Toast.makeText(this@AddQuestionActivity, "Question created successfully", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@AddQuestionActivity, "Failed to create question: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@AddQuestionActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
