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
                    val user = response.body()
                    if (user != null) {
                        when (user.roles) {
                            "STUDENT" -> {
                                val binding = ActivityStudentBinding.inflate(layoutInflater)
                                setContentView(binding.root)
                                StudentHelper(this@MainActivity, binding, user)
                            }
                            "TEACHER" -> {
                                val binding = ActivityTeacherBinding.inflate(layoutInflater)
                                setContentView(binding.root)
                                TeacherHelper(this@MainActivity, binding, user.room)
                            }
                            else -> {
                                Toast.makeText(applicationContext, "Unknown role: ${user.roles}", Toast.LENGTH_SHORT).show()
                                finish()
                            }
                        }
                    } else {
                        Toast.makeText(applicationContext, "User not found", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                } else {
                    Toast.makeText(applicationContext, "Error fetching user data: ${response.message()}", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "Network request failed: ${e.message}", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
