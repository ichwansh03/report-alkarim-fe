package com.ichwan.schoolreport.api

import com.ichwan.schoolreport.model.LoginRequest
import com.ichwan.schoolreport.model.LoginResponse
import com.ichwan.schoolreport.model.ActivityReport
import com.ichwan.schoolreport.model.CategoryActivity
import com.ichwan.schoolreport.model.Question
import com.ichwan.schoolreport.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("auth/register")
    suspend fun register(@Body user: User): Response<Void>

    @PUT("auth/update")
    suspend fun updatePassword(@Body loginRequest: LoginRequest): Response<Void>

    @GET("auth/class/{class}/roles/{roles}")
    suspend fun getUsersByClassAndRoles(
        @Path("class") classValue: String,
        @Path("roles") roles: String
    ): Response<List<User>>

    @GET("auth/user/{regnumber}")
    suspend fun getUserByRegNumber(@Path("regnumber") regNumber: String): Response<User>

    @GET("auth/roles/{role}")
    suspend fun getUsersByRole(@Path("role") role: String): Response<List<User>>

    @PUT("auth/user/{regnumber}")
    suspend fun updateUser(@Path("regnumber") regNumber: String, @Body user: User): Response<Void>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("/reports/create")
    suspend fun createReport(@Body activityReport: ActivityReport): Response<Void>

    @POST("/categories/create")
    suspend fun createCategory(@Body category: CategoryActivity): Response<Void>

    @POST("/questions/create")
    suspend fun createQuestion(@Body question: Question): Response<Void>

    @GET("questions/target/{target}")
    suspend fun getQuestionsByTarget(@Path("target") target: String): Response<List<Question>>

    @GET("questions/category/{category}")
    suspend fun getQuestionsByCategory(@Path("category") category: String): Response<List<Question>>

    @GET("questions/category/{category}/target/{target}")
    suspend fun getQuestionsByCategoryAndTarget(
        @Path("category") category: String,
        @Path("target") target: String
    ): Response<List<Question>>
}
