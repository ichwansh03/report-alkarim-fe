package com.ichwan.schoolreport.view

import androidx.lifecycle.lifecycleScope
import com.ichwan.schoolreport.model.User
import com.ichwan.schoolreport.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class TeacherHelper(
    private val activity: MainActivity,
    private val viewModel: UserViewModel,
    private val user: User
) {


    init {
        setUp()
    }

    fun setUp(){
        activity.lifecycleScope.launch {
            try {
                viewModel.loadUsersByClassAndRoles(user.clsroom, "STUDENT")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
