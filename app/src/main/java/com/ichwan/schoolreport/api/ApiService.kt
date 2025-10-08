package com.ichwan.schoolreport.api

import com.ichwan.schoolreport.dto.LoginRequest
import com.ichwan.schoolreport.dto.LoginResponse
import com.ichwan.schoolreport.model.ActivityReport
import com.ichwan.schoolreport.model.CategoryActivity
import com.ichwan.schoolreport.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {
    @POST("auth/register")
    suspend fun register(@Body user: User): Response<Void>

    @PUT("auth/update")
    suspend fun updatePassword(@Body loginRequest: LoginRequest): Response<Void>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("/reports/create")
    suspend fun createReport(@Body activityReport: ActivityReport): Response<Void>

    @POST("/categories/create")
    suspend fun createCategory(@Body category: CategoryActivity): Response<Void>
}
