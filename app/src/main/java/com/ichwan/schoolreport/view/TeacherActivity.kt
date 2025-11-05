package com.ichwan.schoolreport.view

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.ichwan.schoolreport.adapter.ListStudentAdapter
import com.ichwan.schoolreport.api.ApiClient
import com.ichwan.schoolreport.databinding.ActivityTeacherBinding
import com.ichwan.schoolreport.model.User
import com.ichwan.schoolreport.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class TeacherActivity(
    private val activity: MainActivity,
    private val binding: ActivityTeacherBinding,
    private val viewModel: UserViewModel,
    private val room: String
) {

    init {
        setUp()
    }

    fun setUp(){
        activity.lifecycleScope.launch {
            try {
                viewModel.loadUsersByClassAndRoles(room, "STUDENT", activity, binding)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
