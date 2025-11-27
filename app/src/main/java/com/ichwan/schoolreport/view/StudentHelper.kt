package com.ichwan.schoolreport.view

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ichwan.schoolreport.adapter.QuestionActivityAdapter
import com.ichwan.schoolreport.databinding.ActivityStudentBinding
import com.ichwan.schoolreport.model.User
import com.ichwan.schoolreport.viewmodel.QuestionViewModel
import com.ichwan.schoolreport.viewmodel.ReportViewModel

class StudentHelper(
    private val activity: MainActivity,
    private val binding: ActivityStudentBinding,
    private val user: User,
    private val questionViewModel: QuestionViewModel,
    private val reportViewModel: ReportViewModel
) {

    private lateinit var questionAdapter: QuestionActivityAdapter

    init {
        setupView()
        setupObservers()
        questionViewModel.loadQuestionsByTarget(user.room)
    }

    private fun setupView() {
        binding.horizontalCalendar.setOnDateSelectListener { selectedDate ->
            Toast.makeText(
                activity, "Date: ${selectedDate.day} - ${selectedDate.month} - ${selectedDate.year}",
                Toast.LENGTH_SHORT
            ).show()
        }

        questionAdapter = QuestionActivityAdapter(emptyList(), user, reportViewModel, activity.lifecycleScope)
        binding.listReport.adapter = questionAdapter
        binding.listReport.layoutManager = LinearLayoutManager(activity)
    }

    private fun setupObservers() {
        questionViewModel.questions.observe(activity) { questions ->
            questions?.let {
                questionAdapter.updateData(it)
            }
        }

        questionViewModel.message.observe(activity) { message ->
            if (message.isNotEmpty()) {
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
