package com.ichwan.schoolreport.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ichwan.schoolreport.databinding.ActivityStudentBinding
import com.ichwan.schoolreport.databinding.ActivityTeacherBinding

class MainActivity : AppCompatActivity() {

    private lateinit var student: ActivityStudentBinding
    private lateinit var teacher: ActivityTeacherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val regNumber = intent.getStringExtra("regnumber")
        if (regNumber?.equals("123") == true) {
            student = ActivityStudentBinding.inflate(layoutInflater)
            setContentView(student.root)
            StudentView.callActivityList(student)
            student.horizontalCalendar.setOnDateSelectListener{ selectedDate ->
                Toast.makeText(applicationContext, "Date: ${selectedDate.day} - ${selectedDate.month} - ${selectedDate.year}",
                    Toast.LENGTH_SHORT).show()

            }
        }
        else {
            teacher = ActivityTeacherBinding.inflate(layoutInflater)
            setContentView(teacher.root)
            TeacherView.callStudentList(teacher)
            teacher.addQuestionFab.setOnClickListener{
                val intent = Intent(this, AddQuestionActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
