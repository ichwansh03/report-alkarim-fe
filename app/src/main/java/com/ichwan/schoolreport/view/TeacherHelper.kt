package com.ichwan.schoolreport.view

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ichwan.schoolreport.databinding.ActivityTeacherBinding
import com.ichwan.schoolreport.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import kotlin.getValue

class TeacherHelper(
    private val activity: MainActivity,
    private val room: String
) {

    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(activity)[UserViewModel::class.java]
    }

    init {
        setUp()
    }

    fun setUp(){
        activity.lifecycleScope.launch {
            try {
                viewModel.loadUsersByClassAndRoles(room, "STUDENT")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
