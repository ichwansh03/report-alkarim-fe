package com.ichwan.schoolreport.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ichwan.schoolreport.api.ApiClient
import com.ichwan.schoolreport.databinding.ActivityStudentBinding
import com.ichwan.schoolreport.databinding.ActivityTeacherBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val regNumber = intent.getStringExtra("regnumber")

        if (regNumber.isNullOrEmpty()) {
            Toast.makeText(this, "Registration number not found.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        lifecycleScope.launch {
            try {
                val response = ApiClient.instance.getUserByRegNumber(regNumber)
                if (response.isSuccessful) {
                    val roles = response.body()?.roles
                    if (roles != null) {
                        when (roles) {
                            "student" -> {
                                val binding = ActivityStudentBinding.inflate(layoutInflater)
                                setContentView(binding.root)
                                val controller = StudentController(this@MainActivity, binding)
                                controller.setupView()
                            }
                            "teacher" -> {
                                val binding = ActivityTeacherBinding.inflate(layoutInflater)
                                setContentView(binding.root)
                                val controller = TeacherController(this@MainActivity, binding)
                                controller.setupView()
                            }
                            else -> {
                                Toast.makeText(applicationContext, "Unknown role: $roles", Toast.LENGTH_SHORT).show()
                                finish()
                            }
                        }
                    } else {
                        Toast.makeText(applicationContext, "User not found", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                } else {
                    Toast.makeText(applicationContext, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "Failure: ${e.message}", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
