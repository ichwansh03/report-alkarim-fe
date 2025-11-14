package com.ichwan.schoolreport.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ichwan.schoolreport.core.AlkarimApp
import com.ichwan.schoolreport.model.User
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val apiService = (application as AlkarimApp).apiClient.instance

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun loadUsersByClassAndRoles(classValue: String, roles: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getUsersByClassAndRoles(classValue, roles)
                if (response.isSuccessful) {
                    _users.postValue(response.body())
                } else {
                    _message.postValue("Failed to load users: ${response.message()}")
                }
            } catch (e: Exception) {
                _message.postValue("An error occurred: ${e.message}")
            }
        }
    }
}
