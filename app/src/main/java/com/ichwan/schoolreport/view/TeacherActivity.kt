package com.ichwan.schoolreport.view

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.ichwan.schoolreport.adapter.ListStudentAdapter
import com.ichwan.schoolreport.api.ApiClient
import com.ichwan.schoolreport.databinding.ActivityTeacherBinding
import com.ichwan.schoolreport.model.User
import kotlinx.coroutines.launch

class TeacherActivity(
    private val activity: MainActivity,
    private val binding: ActivityTeacherBinding,
    private val room: String
) {

    init {
        setUp()
    }

    fun setUp(){
        activity.lifecycleScope.launch {
            try {
                val response = ApiClient.instance.getUsersByClassAndRoles(room, "STUDENT")
                if (response.isSuccessful) {
                    val users = response.body()
                    if (!users.isNullOrEmpty()) {
                        val adapter = ListStudentAdapter(activity, users.toMutableList()) { user ->
                            val intent = Intent(activity, DetailStudentActivity::class.java)
                            intent.putExtra("name", user.name)
                            activity.startActivity(intent)
                        }
                        binding.listStudentRv.adapter = adapter
                    }
                } else {
                    Toast.makeText(activity, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
