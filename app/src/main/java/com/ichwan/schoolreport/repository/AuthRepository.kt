package com.ichwan.schoolreport.repository

import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.api.RetrofitClient
import com.ichwan.schoolreport.model.LoginRequest
import com.ichwan.schoolreport.model.LoginResponse
import com.ichwan.schoolreport.model.User
import retrofit2.Response

class AuthRepository(private val apiService: ApiService = RetrofitClient.cleanApiService) {

    suspend fun register(user: User): Response<Void> = apiService.register(user)

    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> = apiService.login(loginRequest)

}