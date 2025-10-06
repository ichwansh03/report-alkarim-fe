package com.ichwan.schoolreport.api

import com.ichwan.schoolreport.dto.LoginRequest
import com.ichwan.schoolreport.dto.LoginResponse
import com.ichwan.schoolreport.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/register")
    suspend fun register(@Body user: User): Response<Void>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}
