package com.ichwan.schoolreport.view

import androidx.recyclerview.widget.LinearLayoutManager
import com.ichwan.schoolreport.adapter.ListStudentAdapter
import com.ichwan.schoolreport.adapter.ReportStudentCategoryAdapter
import com.ichwan.schoolreport.databinding.ActivityDetailStudentBinding
import com.ichwan.schoolreport.databinding.ActivityTeacherBinding
import com.ichwan.schoolreport.model.ActivityReport
import com.ichwan.schoolreport.model.CategoryActivity
import com.ichwan.schoolreport.model.Student

class TeacherView {

    companion object {
        fun callStudentList(binding: ActivityTeacherBinding) {
            val students = arrayOf(
                Student("Ichwan Sholihin","123422","VII Abu Bakar","Laki"),
                Student("Joko","123421","VII Abu Bakar","Laki"),
                Student("Agus Tina","123420","VII Abu Bakar","Perempuan")
            )

            binding.listStudentRv.layoutManager = LinearLayoutManager(binding.root.context)
            binding.listStudentRv.adapter = ListStudentAdapter(students)
            binding.listStudentRv.setHasFixedSize(true)
        }

        fun callStudentReport(binding: ActivityDetailStudentBinding) {
            val prays = arrayOf(
                ActivityReport("123","Ibadah","Sholat Shubuh",false, "7x"),
                ActivityReport("123","Ibadah","Sholat Dzuhur",false, "7x"),
                ActivityReport("123","Ibadah","Sholat Ashar",false, "7x"),
                ActivityReport("123","Ibadah","Sholat Maghrib",false, "7x"),
                ActivityReport("123","Ibadah","Sholat Isya",false, "7x")
            )

            val helper = arrayOf(
                ActivityReport("123","Membantu Orang Tua","Menyapu",false, "3x"),
                ActivityReport("123","Membantu Orang Tua","Membersihkan Tempat Tidur",false, "2x")
            )

            val categories = arrayOf(
                CategoryActivity("Ibadah", prays, "A"),
                CategoryActivity("Membantu Orang Tua", helper, "B")
            )

            binding.reportWeeklyRv.layoutManager = LinearLayoutManager(binding.root.context)
            binding.reportWeeklyRv.adapter = ReportStudentCategoryAdapter(categories)
            binding.reportWeeklyRv.setHasFixedSize(true)
        }
    }
}