package com.ichwan.schoolreport.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ichwan.schoolreport.api.ApiClient
import com.ichwan.schoolreport.databinding.ActivityAddQuestionBinding
import com.ichwan.schoolreport.model.ActivityReport
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
            saveReport()
        }
    }

    private fun saveReport() {
        val category = binding.categorySp.selectedItem.toString()
        val question = binding.questionEt.text.toString()
        val isChecklist = binding.checklistRb.isChecked

        // Assuming 'nip' and 'score' are not collected from this screen.
        // You'll need to provide actual values for these.
        val nip = ""
        val score = ""

        if (question.isEmpty()) {
            Toast.makeText(this, "Question cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        val report = ActivityReport(
            nip = nip,
            category = category,
            question = question,
            answer = "",
            score = score
        )

        lifecycleScope.launch {
            try {
                val response = ApiClient.instance.createReport(report)
                if (response.isSuccessful) {
                    Toast.makeText(this@AddQuestionActivity, "Report saved successfully", Toast.LENGTH_SHORT).show()
                    finish() // Close the activity after saving
                } else {
                    Toast.makeText(this@AddQuestionActivity, "Failed to save report: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@AddQuestionActivity, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}