package com.ichwan.schoolreport.view

import android.widget.Toast
import com.ichwan.schoolreport.databinding.ActivityStudentBinding

class StudentController(
    private val activity: MainActivity,
    private val binding: ActivityStudentBinding
) {
    fun setupView() {
        binding.horizontalCalendar.setOnDateSelectListener { selectedDate ->
            Toast.makeText(
                activity, "Date: ${selectedDate.day} - ${selectedDate.month} - ${selectedDate.year}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
