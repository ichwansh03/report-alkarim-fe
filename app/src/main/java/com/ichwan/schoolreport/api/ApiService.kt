package com.ichwan.schoolreport.api

import com.ichwan.schoolreport.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // Auth Endpoints
    @GET("auth/test")
    suspend fun testAuth(): Response<ApiResponse<String>>

    @POST("auth/updatePassword")
    suspend fun updatePassword(@Body loginRequest: LoginRequest): Response<ApiResponse<Void>>

    @POST("auth/register")
    suspend fun register(@Body user: User): Response<ApiResponse<User>>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<ApiResponse<LoginResponse>>

    @POST("auth/refresh")
    fun refreshToken(@Body body: Map<String, String>): retrofit2.Call<RefreshTokenResponse>

    // User Endpoints
    @PUT("user/update/{id}")
    suspend fun updateUser(@Path("id") id: Long, @Body user: User): Response<ApiResponse<User>>

    @GET("user/class/{class}/roles/{roles}")
    suspend fun getUsersByClassAndRoles(
        @Path("class") classValue: String,
        @Path("roles") roles: String
    ): Response<ApiResponse<List<User>>>

    @GET("user/{regnumber}")
    suspend fun getUserByRegNumber(@Path("regnumber") regNumber: String): Response<ApiResponse<User>>

    @GET("user/roles/{roles}")
    suspend fun getUsersByRole(@Path("roles") roles: String): Response<ApiResponse<List<User>>>

    @DELETE("user/delete/{id}")
    suspend fun deleteUser(@Path("id") id: Long): Response<ApiResponse<Void>>

    @GET("user")
    suspend fun getAllUsers(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ApiResponse<List<User>>>

    // Report Endpoints
    @POST("reports/create")
    suspend fun createReport(@Body activityReport: ActivityReport): Response<ApiResponse<Void>>

    @GET("reports/regnumber/{regnumber}")
    suspend fun getReportsByRegnumber(@Path("regnumber") regnumber: String): Response<ApiResponse<List<ActivityReport>>>

    @GET("reports/name/{name}")
    suspend fun getReportsByUserName(@Path("name") name: String): Response<ApiResponse<List<ActivityReport>>>

    @PUT("reports/update/{id}")
    suspend fun updateReport(@Path("id") id: Long, @Body activityReport: ActivityReport): Response<ApiResponse<ActivityReport>>

    @DELETE("reports/delete/{id}")
    suspend fun deleteReport(@Path("id") id: Long): Response<ApiResponse<Void>>

    @GET("reports")
    suspend fun getAllReports(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ApiResponse<List<ActivityReport>>>

    // Question Endpoints
    @POST("questions/create")
    suspend fun createQuestion(@Body question: Question): Response<ApiResponse<Question>>

    @GET("questions/target/{target}")
    suspend fun getQuestionsByTarget(@Path("target") target: String): Response<ApiResponse<List<Question>>>

    @GET("questions/category/{category}")
    suspend fun getQuestionsByCategory(@Path("category") category: String): Response<ApiResponse<List<Question>>>

    @GET("questions/category/{category}/target/{target}")
    suspend fun getQuestionsByCategoryAndTarget(
        @Path("category") category: String,
        @Path("target") target: String
    ): Response<ApiResponse<List<Question>>>

    @PUT("questions/update/{id}")
    suspend fun updateQuestion(@Path("id") id: Long, @Body question: Question): Response<ApiResponse<Question>>

    @DELETE("questions/delete/{id}")
    suspend fun deleteQuestion(@Path("id") id: Long): Response<ApiResponse<Void>>

    @GET("questions")
    suspend fun getAllQuestions(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ApiResponse<List<Question>>>

    // ClassRoom Endpoints
    @GET("class/teacher/{teacherName}")
    suspend fun getClassRoomByTeacherName(@Path("teacherName") teacherName: String): Response<ApiResponse<List<ClassRoom>>>

    @POST("class/create")
    suspend fun createClassRoom(@Body classRoom: ClassRoom): Response<ApiResponse<ClassRoom>>

    @PUT("class/update/{id}")
    suspend fun updateClassRoom(@Path("id") id: Long, @Body classRoom: ClassRoom): Response<ApiResponse<ClassRoom>>

    @DELETE("class/delete/{id}")
    suspend fun deleteClassRoom(@Path("id") id: Long): Response<ApiResponse<Void>>

    @GET("class")
    suspend fun getAllClassRoom(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ApiResponse<List<ClassRoom>>>

    // Category Endpoints
    @GET("category")
    suspend fun getCategory(): Response<ApiResponse<List<CategoryActivity>>>

    @POST("category/create")
    suspend fun createCategory(@Body category: CategoryActivity): Response<ApiResponse<Void>>

    @DELETE("category/delete/{id}")
    suspend fun deleteCategory(@Path("id") id: Long): Response<ApiResponse<Void>>
}
