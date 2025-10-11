package com.ichwan.schoolreport.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ichwan.schoolreport.api.ApiClient
import com.ichwan.schoolreport.databinding.ActivityAddQuestionBinding
import com.ichwan.schoolreport.model.Question
import kotlinx.coroutines.launch

class AddQuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addCategoryBtn.setOnClickListener {
            val addCategoryFragment = AddCategoryFragment()
            addCategoryFragment.show(supportFragmentManager, "AddCategoryFragment")
        }

        binding.saveBtn.setOnClickListener {
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
                return@setOnClickListener
            }

            if (option.isEmpty()) {
                Toast.makeText(this, "Please select an answer type", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val question = Question(
                quest = questionText,
                category = category,
                target = target,
                option = option
            )

            lifecycleScope.launch {
                try {
                    val response = ApiClient.instance.createQuestion(question)
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
}
