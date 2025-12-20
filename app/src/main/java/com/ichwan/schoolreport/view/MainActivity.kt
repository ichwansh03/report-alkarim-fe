package com.ichwan.schoolreport.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.ichwan.schoolreport.api.RetrofitClient
import com.ichwan.schoolreport.databinding.ActivityAdminBinding
import com.ichwan.schoolreport.databinding.ActivityStudentBinding
import com.ichwan.schoolreport.databinding.ActivityTeacherBinding
import com.ichwan.schoolreport.viewmodel.QuestionViewModel
import com.ichwan.schoolreport.viewmodel.ReportViewModel
import com.ichwan.schoolreport.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private val questionViewModel: QuestionViewModel by viewModels()
    private val reportViewModel: ReportViewModel by viewModels()

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
                val response = RetrofitClient.apiService.getUserByRegNumber(regNumber)
                if (response.isSuccessful) {
                    val user = response.body()
                    if (user != null) {
                        when (user.roles) {
                            "STUDENT" -> {
                                val binding = ActivityStudentBinding.inflate(layoutInflater)
                                setContentView(binding.root)
                                StudentHelper(this@MainActivity, binding, user, questionViewModel, reportViewModel)
                            }
                            "TEACHER" -> {
                                val binding = ActivityTeacherBinding.inflate(layoutInflater)
                                setContentView(binding.root)
                                TeacherHelper(this@MainActivity, userViewModel, user)
                            }
                            "ADMINISTRATOR" -> {
                                val binding = ActivityAdminBinding.inflate(layoutInflater)
                                setContentView(binding.root)
                                AdminHelper(this@MainActivity)
                                val viewPager = binding.viewPager
                                val tabLayout = binding.tabLayout
                                viewPager.adapter = AdminHelper(this@MainActivity).adapter
                                // The mediator now sets the title for the third tab
                                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                                    tab.text = when (position) {
                                        0 -> "Siswa"
                                        1 -> "Guru"
                                        else -> "Kelas" // Title for the new tab
                                    }
                                }.attach()
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
