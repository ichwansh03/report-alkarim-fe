package com.ichwan.schoolreport.view

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ichwan.schoolreport.adapter.QuestionActivityAdapter
import com.ichwan.schoolreport.api.ApiClient
import com.ichwan.schoolreport.databinding.ActivityStudentBinding
import com.ichwan.schoolreport.model.User
import kotlinx.coroutines.launch

class StudentActivity(
    private val activity: MainActivity,
    private val binding: ActivityStudentBinding,
    private val user: User
) {

    init {
        setupView()
    }

    fun setupView() {
        binding.horizontalCalendar.setOnDateSelectListener { selectedDate ->
            Toast.makeText(
                activity, "Date: ${selectedDate.day} - ${selectedDate.month} - ${selectedDate.year}",
                Toast.LENGTH_SHORT
            ).show()
        }

        activity.lifecycleScope.launch {
            try {
                val response = ApiClient.instance.getQuestionsByTarget(user.room)
                if (response.isSuccessful) {
                    response.body()?.let {
                        val adapter = QuestionActivityAdapter(it, user, activity.lifecycleScope)
                        binding.listReport.adapter = adapter
                        binding.listReport.layoutManager = LinearLayoutManager(activity)
                    }
                }
                else {
                    Toast.makeText(activity, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }
}
