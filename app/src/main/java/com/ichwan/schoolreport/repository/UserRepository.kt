package com.ichwan.schoolreport.repository

import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.api.RetrofitClient
import com.ichwan.schoolreport.model.LoginRequest
import com.ichwan.schoolreport.model.LoginResponse
import com.ichwan.schoolreport.model.RefreshTokenResponse
import com.ichwan.schoolreport.model.User
import retrofit2.Response

class UserRepository(private val apiService: ApiService = RetrofitClient.apiService) {
    suspend fun register(user: User): Response<RefreshTokenResponse> = apiService.register(user)

    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> = apiService.login(loginRequest)

    suspend fun getUserByClassAndRoles(classValue: String, roles: String): Response<List<User>> =
        apiService.getUsersByClassAndRoles(classValue, roles)

    suspend fun getUserByRole(role: String): Response<List<User>> = apiService.getUsersByRole(role)

    suspend fun getUserByRegnumber(regnumber: String): Response<User> = apiService.getUserByRegNumber(regnumber)

}