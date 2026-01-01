package com.ichwan.schoolreport.repository

import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.api.RetrofitClient
import com.ichwan.schoolreport.core.RetrofitClientWrapper
import com.ichwan.schoolreport.model.User
import retrofit2.Response

class UserRepository(private val apiService: ApiService = RetrofitClientWrapper.apiService) {

    suspend fun getUserByClassAndRoles(classValue: String, roles: String): Response<List<User>> =
        apiService.getUsersByClassAndRoles(classValue, roles)

    suspend fun getUserByRole(role: String): Response<List<User>> = apiService.getUsersByRole(role)

    suspend fun getUserByRegnumber(regnumber: String): Response<User> = apiService.getUserByRegNumber(regnumber)

}