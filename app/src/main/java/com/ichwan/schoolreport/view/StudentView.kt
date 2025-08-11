package com.ichwan.schoolreport.view

import androidx.recyclerview.widget.LinearLayoutManager
import com.ichwan.schoolreport.adapter.ActivityStudentAdapter
import com.ichwan.schoolreport.databinding.ActivityStudentBinding
import com.ichwan.schoolreport.model.ActivityReport
import com.ichwan.schoolreport.model.CategoryActivity

class StudentView {

    companion object {
        fun callActivityList(binding: ActivityStudentBinding) {
            val prays = arrayOf(
                ActivityReport("123","Ibadah","Sholat Shubuh",false),
                ActivityReport("123","Ibadah","Sholat Dzuhur",false),
                ActivityReport("123","Ibadah","Sholat Ashar",false),
                ActivityReport("123","Ibadah","Sholat Maghrib",false),
                ActivityReport("123","Ibadah","Sholat Isya",false)
            )

            val helper = arrayOf(
                ActivityReport("123","Membantu Orang Tua","Menyapu",false),
                ActivityReport("123","Membantu Orang Tua","Membersihkan Tempat Tidur",false)
            )

            val categories = arrayOf(
                CategoryActivity("Ibadah", prays),
                CategoryActivity("Membantu Orang Tua", helper)
            )

            binding.listReport.layoutManager = LinearLayoutManager(binding.root.context)
            binding.listReport.setHasFixedSize(true)
            binding.listReport.adapter = ActivityStudentAdapter(categories)

        }
    }
}