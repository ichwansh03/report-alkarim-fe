package com.ichwan.schoolreport.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ichwan.schoolreport.model.LoginRequest
import com.ichwan.schoolreport.model.User
import com.ichwan.schoolreport.model.UserRole
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

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    fun registerUser(user: User) {
        viewModelScope.launch {
            try {
                val response = repository.register(user)
                if (response.isSuccessful) {
                    _message.value = "User registered successfully"
                } else {
                    _message.value = "Failed to register user: ${response.message()}"
                }
            } catch (e: Exception) {
                _message.value = "An error occurred: ${e.message}"
            }
        }
    }

    fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {
                val response = repository.login(loginRequest)
                if (response.isSuccessful) {
                    _loginSuccess.value = true
                    _message.value = "Login successful"
                } else {
                    _loginSuccess.value = false
                    _message.value = "Login failed: ${response.message()}"
                }
            } catch (e: Exception) {
                _loginSuccess.value = false
                _message.value = "An error occurred: ${e.message}"
            }
        }
    }

    private suspend fun fetchUsers(request: suspend () -> Response<List<User>>) {
        try {
            val response = withContext(Dispatchers.IO) { request() }
            if (response.isSuccessful) {
                _users.postValue(response.body())
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
                if (response.isSuccessful) {
                    _message.value = "User found"
                    _user.value = response.body()
                } else {
                    _message.value = "User not found"
                    _user.value = null
                }
            } catch (e: Exception) {
                _user.value = null
                _message.value = "An error occurred: ${e.message}"
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