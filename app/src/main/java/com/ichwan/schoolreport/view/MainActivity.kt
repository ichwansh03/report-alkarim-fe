package com.ichwan.schoolreport.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ichwan.schoolreport.R
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

        setContentView(R.layout.activity_loading)

        val regNumber = intent.getStringExtra("regnumber")
        val role = intent.getStringExtra("role")

        if (regNumber.isNullOrEmpty()) {
            Toast.makeText(this, "Registration number not found.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        if (role.isNullOrEmpty()) {
            Toast.makeText(this, "User role not found.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        Log.i("MainActivity", "onCreate: user role from login: $role")

        // Load user data for additional information needed by helpers
        userViewModel.loadUserByRegnumber(regNumber)
        
        userViewModel.user.observe(this) { user ->
            Log.d("MainActivity", "onCreate: user: $user")
            
            if (user != null) {
                // Use role from login response to determine layout
                when (role) {
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
                        val adminHelper = AdminHelper(this@MainActivity, binding)
                        adminHelper.setupTabs()
                    }
                    else -> {
                        Toast.makeText(this, "Role unknown: $role", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Error loading user data", Toast.LENGTH_LONG).show()
            }
        }
    }
}
