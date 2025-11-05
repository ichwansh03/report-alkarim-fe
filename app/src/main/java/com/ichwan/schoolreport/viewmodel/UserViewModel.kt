package com.ichwan.schoolreport.viewmodel

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ichwan.schoolreport.adapter.ListStudentAdapter
import com.ichwan.schoolreport.api.ApiClient
import com.ichwan.schoolreport.databinding.ActivityTeacherBinding
import com.ichwan.schoolreport.model.User
import com.ichwan.schoolreport.view.DetailStudentActivity
import com.ichwan.schoolreport.view.MainActivity
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val apiService by lazy { ApiClient.instance }
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun loadUsersByClassAndRoles(classValue: String, roles: String, activity: MainActivity, binding: ActivityTeacherBinding) {
        viewModelScope.launch {
            try {
                val response = apiService.getUsersByClassAndRoles(classValue, roles)
                if (response.isSuccessful) {
                    _users.postValue(response.body())
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
                    _message.postValue("Failed to load users: ${response.message()}")
                }
            } catch (e: Exception) {
                _message.postValue("An error occurred: ${e.message}")
            }
        }
    }
}