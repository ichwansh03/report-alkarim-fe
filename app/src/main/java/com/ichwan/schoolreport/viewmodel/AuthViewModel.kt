package com.ichwan.schoolreport.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ichwan.schoolreport.model.LoginRequest
import com.ichwan.schoolreport.model.User
import com.ichwan.schoolreport.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository = AuthRepository()) : ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

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
}