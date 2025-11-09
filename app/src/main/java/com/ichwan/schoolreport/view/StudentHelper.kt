package com.ichwan.schoolreport.view

import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ichwan.schoolreport.adapter.QuestionActivityAdapter
import com.ichwan.schoolreport.databinding.ActivityStudentBinding
import com.ichwan.schoolreport.model.User
import com.ichwan.schoolreport.viewmodel.QuestionViewModel

class StudentHelper(
    private val activity: MainActivity,
    private val binding: ActivityStudentBinding,
    private val user: User
) {

    private lateinit var questionAdapter: QuestionActivityAdapter

    private val viewModel: QuestionViewModel by lazy {
        ViewModelProvider(activity)[QuestionViewModel::class.java]
    }

    init {
        setupView()
        setupObservers()
        viewModel.loadQuestionsByTarget(user.room)
    }

    private fun setupView() {
        binding.horizontalCalendar.setOnDateSelectListener { selectedDate ->
            Toast.makeText(
                activity, "Date: ${selectedDate.day} - ${selectedDate.month} - ${selectedDate.year}",
                Toast.LENGTH_SHORT
            ).show()
        }

        questionAdapter = QuestionActivityAdapter(emptyList(), user, activity.lifecycleScope)
        binding.listReport.adapter = questionAdapter
        binding.listReport.layoutManager = LinearLayoutManager(activity)
    }

    private fun setupObservers() {
        // Mengamati perubahan pada daftar pertanyaan dari ViewModel
        viewModel.questions.observe(activity) { questions ->
            questions?.let {

                questionAdapter.updateData(it)
            }
        }

        viewModel.message.observe(activity) { message ->
            if (message.isNotEmpty()) {
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
