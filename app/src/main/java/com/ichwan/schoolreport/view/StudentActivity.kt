package com.ichwan.schoolreport.view

import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ichwan.schoolreport.adapter.QuestionActivityAdapter
import com.ichwan.schoolreport.api.ApiClient
import com.ichwan.schoolreport.databinding.ActivityStudentBinding
import com.ichwan.schoolreport.model.User
import com.ichwan.schoolreport.viewmodel.QuestionViewModel
import kotlinx.coroutines.launch

class StudentActivity(
    private val activity: MainActivity,
    private val binding: ActivityStudentBinding,
    private val user: User,
    private val viewModel: QuestionViewModel,
    private val lifecycleOwner: LifecycleOwner
) {

    private lateinit var questionAdapter: QuestionActivityAdapter

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

        questionAdapter = QuestionActivityAdapter(emptyList(), user, lifecycleOwner.lifecycleScope)
        binding.listReport.adapter = questionAdapter
        binding.listReport.layoutManager = LinearLayoutManager(activity)
    }

    private fun setupObservers() {
        // Mengamati perubahan pada daftar pertanyaan dari ViewModel
        viewModel.questions.observe(lifecycleOwner) { questions ->
            questions?.let {

                questionAdapter.updateData(it)
            }
        }

        viewModel.message.observe(lifecycleOwner) { message ->
            if (message.isNotEmpty()) {
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
