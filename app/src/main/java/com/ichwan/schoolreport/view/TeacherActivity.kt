package com.ichwan.schoolreport.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ichwan.schoolreport.adapter.ListStudentAdapter
import com.ichwan.schoolreport.api.ApiClient
import com.ichwan.schoolreport.databinding.ActivityTeacherBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeacherActivity(private val activity: AppCompatActivity, private val binding: ActivityTeacherBinding, private val userClass: String?) {

    init {
        setupUI()
        getStudents()
    }

    private fun setupUI(){
        binding.addQuestionFab.setOnClickListener {
            activity.startActivity(Intent(activity, AddQuestionActivity::class.java))
        }
    }

    private fun getStudents(){
        activity.lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.instance.getUsersByClassAndRoles(userClass ?: "", "student")
                if (response.isSuccessful){
                    val students = response.body() ?: emptyList()
                    withContext(Dispatchers.Main){
                        val adapter = ListStudentAdapter(students)
                        binding.listStudentRv.layoutManager = LinearLayoutManager(activity)
                        binding.listStudentRv.adapter = adapter
                    }
                }
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}