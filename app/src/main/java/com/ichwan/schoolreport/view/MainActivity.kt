package com.ichwan.schoolreport.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ichwan.schoolreport.databinding.ActivityStudentBinding
import com.ichwan.schoolreport.databinding.ActivityTeacherBinding
import com.ichwan.schoolreport.BuildConfig
import com.ichwan.schoolreport.model.Student
import com.ichwan.schoolreport.model.Teacher
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient

import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var student: ActivityStudentBinding
    private lateinit var teacher: ActivityTeacherBinding
    private lateinit var supabase: SupabaseClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supabase = createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_ANON_KEY
        ) {
            install(Postgrest)
        }

        fetchStudents()
        fetchTeachers()

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

    private fun fetchStudents() {
        CoroutineScope(Dispatchers.IO).launch() {
            try {
                val students = supabase.from("student").select().decodeList<Student>()
                Log.d("MainActivity", "Students: $students")
            } catch (e: Exception) {
                Log.e("MainActivity", "Error fetching students: ${e.message}")
            }
        }
    }

    private fun fetchTeachers() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val teachers = supabase.from("teacher").select().decodeList<Teacher>()
                Log.d("MainActivity", "Teachers: $teachers")
            } catch (e: Exception) {
                Log.e("MainActivity", "Error fetching teachers: ${e.message}")
            }
        }
    }
}
