package com.ichwan.schoolreport.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.ichwan.schoolreport.databinding.ActivityAdminBinding
import com.ichwan.schoolreport.databinding.ActivityStudentBinding
import com.ichwan.schoolreport.databinding.ActivityTeacherBinding
import com.ichwan.schoolreport.viewmodel.QuestionViewModel
import com.ichwan.schoolreport.viewmodel.ReportViewModel
import com.ichwan.schoolreport.viewmodel.UserViewModel

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

        userViewModel.loadUserByRegnumber(regNumber)
        userViewModel.user.observe(this) { user ->
            Log.i("MainActivity", "onCreate: user roles: ${user?.roles}")
            when (user?.roles) {
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
            }
        }
    }
}
