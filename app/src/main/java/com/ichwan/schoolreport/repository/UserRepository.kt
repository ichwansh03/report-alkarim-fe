package com.ichwan.schoolreport.repository

import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.core.RetrofitClientWrapper
import com.ichwan.schoolreport.model.BaseResponse
import com.ichwan.schoolreport.model.User
import retrofit2.Response

class UserRepository(private val apiService: ApiService = RetrofitClientWrapper.apiService) {

    suspend fun updateUser(id: Long, user: User): Response<BaseResponse<User>> =
        apiService.updateUser(id, user)

    suspend fun getUserByClassAndRoles(classValue: String, roles: String): Response<BaseResponse<List<User>>> =
        apiService.getUsersByClassAndRoles(classValue, roles)

    suspend fun getUserByRegnumber(regnumber: String): Response<BaseResponse<User>> =
        apiService.getUserByRegNumber(regnumber)

    suspend fun getUserByRole(role: String): Response<BaseResponse<List<User>>> =
        apiService.getUsersByRole(role)

    suspend fun deleteUser(id: Long): Response<BaseResponse<Void>> =
        apiService.deleteUser(id)

    suspend fun getAllUsers(page: Int, size: Int): Response<BaseResponse<List<User>>> =
        apiService.getAllUsers(page, size)
}