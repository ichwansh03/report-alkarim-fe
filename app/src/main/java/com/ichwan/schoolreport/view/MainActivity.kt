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

        // 1. Tampilkan Loading Screen segera saat aplikasi dibuka
        setContentView(R.layout.activity_loading)

        val regNumber = intent.getStringExtra("regnumber")

        if (regNumber.isNullOrEmpty()) {
            Toast.makeText(this, "Registration number not found.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Amati pesan error dari ViewModel (misal: gagal koneksi)
        userViewModel.message.observe(this) { msg ->
            if (msg != null && (msg.contains("Failed") || msg.contains("error"))) {
                Toast.makeText(this, "Error loading user: $msg", Toast.LENGTH_LONG).show()
                // Opsional: Redirect kembali ke login atau tampilkan tombol retry
            }
        }

        userViewModel.loadUserByRegnumber(regNumber)
        
        userViewModel.user.observe(this) { user ->
            Log.i("MainActivity", "onCreate: user roles: ${user?.roles}")
            Log.d("MainActivity", "onCreate: user: $user")
            
            if (user != null) {
                // 2. Data User ditemukan, ganti layout sesuai Role
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
                        // Gunakan metode baru yang lebih bersih
                        val adminHelper = AdminHelper(this@MainActivity, binding)
                        adminHelper.setupTabs()
                    }
                    else -> {
                        Toast.makeText(this, "Role unknown: ${user.roles}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                 // User null (mungkin masih loading atau gagal)
                 // Jika loading sudah selesai tapi user tetap null, Anda mungkin perlu menangani kasus ini.
            }
        }
    }
}
