package com.ichwan.schoolreport

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ichwan.schoolreport.databinding.ActivityStudentBinding
import com.ichwan.schoolreport.databinding.ActivityTeacherBinding

class MainActivity : AppCompatActivity() {

    private lateinit var student: ActivityStudentBinding
    private lateinit var teacher: ActivityTeacherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val regNumber = intent.getStringExtra("regnumber")
        if (regNumber?.equals("123") == true) setContentView(R.layout.activity_student)
        else setContentView(R.layout.activity_teacher)
    }
}
