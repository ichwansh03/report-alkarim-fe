package com.ichwan.schoolreport.repository

import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.core.RetrofitClientWrapper
import com.ichwan.schoolreport.model.ApiResponse
import com.ichwan.schoolreport.model.LoginRequest
import com.ichwan.schoolreport.model.LoginResponse
import com.ichwan.schoolreport.model.User
import retrofit2.Response

class AuthRepository(private val apiService: ApiService = RetrofitClientWrapper.cleanApiService) {

    suspend fun register(user: User): Response<ApiResponse<User>> = apiService.register(user)

    suspend fun login(loginRequest: LoginRequest): Response<ApiResponse<LoginResponse>> = apiService.login(loginRequest)

    suspend fun testAuth(): Response<ApiResponse<String>> = apiService.testAuth()

}
