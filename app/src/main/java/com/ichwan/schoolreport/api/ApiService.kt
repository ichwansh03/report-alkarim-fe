package com.ichwan.schoolreport.api

import com.ichwan.schoolreport.model.ActivityReport
import com.ichwan.schoolreport.model.BaseResponse
import com.ichwan.schoolreport.model.CategoryActivity
import com.ichwan.schoolreport.model.ClassRoom
import com.ichwan.schoolreport.model.LoginRequest
import com.ichwan.schoolreport.model.LoginResponse
import com.ichwan.schoolreport.model.Question
import com.ichwan.schoolreport.model.RefreshTokenResponse
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

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<BaseResponse<LoginResponse>>

    @PUT("auth/update")
    suspend fun updatePassword(@Body loginRequest: LoginRequest): Response<Void>

    @GET("user/class/{class}/roles/{roles}")
    suspend fun getUsersByClassAndRoles(
        @Path("class") classValue: String,
        @Path("roles") roles: String
    ): Response<List<User>>

    @GET("user/{regnumber}")
    suspend fun getUserByRegNumber(@Path("regnumber") regNumber: String): Response<User>

    @GET("user/roles/{role}")
    suspend fun getUsersByRole(@Path("role") role: String): Response<List<User>>

    @PUT("auth/user/{regnumber}")
    suspend fun updateUser(@Path("regnumber") regNumber: String, @Body user: User): Response<Void>

    @POST("/reports/create")
    suspend fun createReport(@Body activityReport: ActivityReport): Response<Void>

    @GET("category")
    suspend fun getCategory(): Response<List<CategoryActivity>>

    @POST("/category/create")
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

    @GET("class")
    suspend fun getClass(): Response<List<ClassRoom>>

    @GET("class/{teacherName}")
    suspend fun getClassByTeacher(@Path("teacherName") teacherName: String): Response<List<ClassRoom>>

    @POST("class/create")
    suspend fun createClass(@Body classRoom: ClassRoom): Response<Void>

    @POST("api/auth/refresh")
    fun refreshToken(@Body body: Map<String, String>): retrofit2.Call<RefreshTokenResponse>
}
