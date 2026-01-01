package com.ichwan.schoolreport.repository

import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.api.RetrofitClient
import com.ichwan.schoolreport.core.RetrofitClientWrapper
import com.ichwan.schoolreport.model.ClassRoom
import com.ichwan.schoolreport.model.User
import retrofit2.Response

class ClassRepository(private val apiService: ApiService = RetrofitClientWrapper.apiService) {

    suspend fun getClass() = apiService.getClass()

    suspend fun getUserByRole(role: String): Response<List<User>> = apiService.getUsersByRole(role)

    suspend fun createClass(classRoom: ClassRoom): Response<Void> =
        apiService.createClass(classRoom)

}