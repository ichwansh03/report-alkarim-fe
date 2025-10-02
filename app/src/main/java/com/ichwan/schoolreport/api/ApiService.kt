package com.ichwan.schoolreport.api

import com.ichwan.schoolreport.model.LoginResponse
import com.ichwan.schoolreport.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/register")
    fun register(@Body user: User): Call<Void>

    @POST("auth/login")
    fun login(@Body user: User): Call<LoginResponse>
}
