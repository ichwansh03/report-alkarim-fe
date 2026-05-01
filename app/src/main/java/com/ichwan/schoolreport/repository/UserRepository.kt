package com.ichwan.schoolreport.repository

import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.core.RetrofitClientWrapper
import com.ichwan.schoolreport.model.ApiResponse
import com.ichwan.schoolreport.model.User
import retrofit2.Response

class UserRepository(private val apiService: ApiService = RetrofitClientWrapper.apiService) {

    suspend fun updateUser(id: Long, user: User): Response<ApiResponse<User>> =
        apiService.updateUser(id, user)

    suspend fun getUserByClassAndRoles(classValue: String, roles: String): Response<ApiResponse<List<User>>> =
        apiService.getUsersByClassAndRoles(classValue, roles)

    suspend fun getUserByRegnumber(regnumber: String): Response<ApiResponse<User>> =
        apiService.getUserByRegNumber(regnumber)

    suspend fun getUserByRole(role: String): Response<ApiResponse<List<User>>> =
        apiService.getUsersByRole(role)

    suspend fun deleteUser(id: Long): Response<ApiResponse<Void>> =
        apiService.deleteUser(id)

    suspend fun getAllUsers(page: Int, size: Int): Response<ApiResponse<List<User>>> =
        apiService.getAllUsers(page, size)
}
