package com.ichwan.schoolreport.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ichwan.schoolreport.model.ApiResponse
import com.ichwan.schoolreport.model.User
import com.ichwan.schoolreport.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserViewModel(private val repository: UserRepository = UserRepository()) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user


    private suspend fun fetchUsers(request: suspend () -> Response<ApiResponse<List<User>>>) {
        try {
            val response = withContext(Dispatchers.IO) { request() }
            if (response.isSuccessful) {
                val data = response.body()?.data
                if (data != null) {
                    _users.postValue(data)
                } else {
                    _message.postValue("No data found")
                }
            } else {
                _message.postValue("Failed to load users: ${response.message()}")
            }
        } catch (t: Throwable) {
            _message.postValue("An error occurred: ${t.message ?: "Unknown error"}")
        }
    }

    fun loadUserByRegnumber(regnumber: String) {
        viewModelScope.launch {
            try {
                val response = repository.getUserByRegnumber(regnumber)
                Log.i("UserViewModel", "loadUserByRegnumber: $response")
                if (response.isSuccessful) {
                    _message.value = "User found"
                    _user.value = response.body()?.data
                    Log.i("UserViewModel", "loadUserByRegnumber: success response")
                } else {
                    _message.value = "User not found"
                    _user.value = null
                    Log.i("UserViewModel", "loadUserByRegnumber: failed response")
                }
            } catch (e: Exception) {
                _user.value = null
                _message.value = "An error occurred: ${e.message}"
                Log.i("UserViewModel", "loadUserByRegnumber: error response ${e.message}")
            }
        }
    }

    fun loadUsersByClassAndRoles(classValue: String, roles: String) {
        viewModelScope.launch {
            fetchUsers { repository.getUserByClassAndRoles(classValue, roles) }
        }
    }

    fun loadUserByRoles(roles: String) {
        viewModelScope.launch {
            fetchUsers { repository.getUserByRole(roles) }
        }
    }
}
