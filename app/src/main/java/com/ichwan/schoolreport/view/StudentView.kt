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
                ActivityReport("123","Ibadah","Sholat Shubuh",false, "0"),
                ActivityReport("123","Ibadah","Sholat Dzuhur",false, "0"),
                ActivityReport("123","Ibadah","Sholat Ashar",false, "0"),
                ActivityReport("123","Ibadah","Sholat Maghrib",false, "0"),
                ActivityReport("123","Ibadah","Sholat Isya",false, "0")
            )

            val helper = arrayOf(
                ActivityReport("123","Membantu Orang Tua","Menyapu",false, "0"),
                ActivityReport("123","Membantu Orang Tua","Membersihkan Tempat Tidur",false, "0")
            )

            val categories = arrayOf(
                CategoryActivity("Ibadah", prays, "0"),
                CategoryActivity("Membantu Orang Tua", helper, "0")
            )

            binding.listReport.layoutManager = LinearLayoutManager(binding.root.context)
            binding.listReport.setHasFixedSize(true)
            binding.listReport.adapter = ActivityStudentAdapter(categories)

        }
    }
}