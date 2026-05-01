package com.ichwan.schoolreport.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ichwan.schoolreport.api.TokenDataStore
import com.ichwan.schoolreport.model.LoginRequest
import com.ichwan.schoolreport.model.User
import com.ichwan.schoolreport.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AuthRepository()
    private val tokenDataStore = TokenDataStore(application)

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    private val _userRole = MutableLiveData<String>()
    val userRole: LiveData<String> = _userRole

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
                if (response.isSuccessful && response.body() != null) {
                    val loginResponse = response.body()!!

                    val token = loginResponse.data?.token
                    if (token != null) {
                        tokenDataStore.saveTokens(
                            accessToken = token,
                            refreshToken = token // replace when backend returns a real refresh token
                        )
                        _userRole.value = loginResponse.data.user.roles.name
                        _message.value = "Login successful"
                        _loginSuccess.value = true
                    } else {
                        _message.value = "Login failed: No token received"
                        _loginSuccess.value = false
                    }
                } else {
                    _message.value = "Login failed: ${response.message()}"
                    _loginSuccess.value = false
                }
            } catch (e: Exception) {
                _message.value = "An error occurred: ${e.message}"
                _loginSuccess.value = false
            }
        }
    }
}
