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

    @POST("api/v1/auths/register")
    suspend fun register(@Body user: User): Response<ApiResponse<User>>

    @POST("api/v1/auths/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<ApiResponse<LoginResponse>>

    @POST("api/v1/auths/refresh")
    fun refreshToken(@Body body: Map<String, String>): retrofit2.Call<RefreshTokenResponse>

    // User Endpoints
    @PUT("api/v1/users/update/{id}")
    suspend fun updateUser(@Path("id") id: Long, @Body user: User): Response<ApiResponse<User>>

    @GET("api/v1/users/class/{class}/roles/{roles}")
    suspend fun getUsersByClassAndRoles(
        @Path("class") classValue: String,
        @Path("roles") roles: String
    ): Response<ApiResponse<List<User>>>

    @GET("api/v1/users/{regnumber}")
    suspend fun getUserByRegNumber(@Path("regnumber") regNumber: String): Response<ApiResponse<User>>

    @GET("api/v1/users/roles/{roles}")
    suspend fun getUsersByRole(@Path("roles") roles: String): Response<ApiResponse<List<User>>>

    @DELETE("api/v1/users/delete/{id}")
    suspend fun deleteUser(@Path("id") id: Long): Response<ApiResponse<Void>>

    @GET("api/v1/users")
    suspend fun getAllUsers(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ApiResponse<List<User>>>

    // Report Endpoints
    @POST("api/v1/reports/create")
    suspend fun createReport(@Body activityReport: ActivityReport): Response<ApiResponse<Void>>

    @GET("api/v1/reports/regnumber/{regnumber}")
    suspend fun getReportsByRegnumber(@Path("regnumber") regnumber: String): Response<ApiResponse<List<ActivityReport>>>

    @GET("api/v1/reports/name/{name}")
    suspend fun getReportsByUserName(@Path("name") name: String): Response<ApiResponse<List<ActivityReport>>>

    @PUT("api/v1/reports/update/{id}")
    suspend fun updateReport(@Path("id") id: Long, @Body activityReport: ActivityReport): Response<ApiResponse<ActivityReport>>

    @DELETE("api/v1/reports/delete/{id}")
    suspend fun deleteReport(@Path("id") id: Long): Response<ApiResponse<Void>>

    @GET("api/v1/reports")
    suspend fun getAllReports(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ApiResponse<List<ActivityReport>>>

    // Question Endpoints
    @POST("api/v1/questions/create")
    suspend fun createQuestion(@Body question: Question): Response<ApiResponse<Question>>

    @GET("api/v1/questions/target/{target}")
    suspend fun getQuestionsByTarget(@Path("target") target: String): Response<ApiResponse<List<Question>>>

    @GET("api/v1/questions/category/{category}")
    suspend fun getQuestionsByCategory(@Path("category") category: String): Response<ApiResponse<List<Question>>>

    @GET("api/v1/questions/category/{category}/target/{target}")
    suspend fun getQuestionsByCategoryAndTarget(
        @Path("category") category: String,
        @Path("target") target: String
    ): Response<ApiResponse<List<Question>>>

    @PUT("api/v1/questions/update/{id}")
    suspend fun updateQuestion(@Path("id") id: Long, @Body question: Question): Response<ApiResponse<Question>>

    @DELETE("api/v1/questions/delete/{id}")
    suspend fun deleteQuestion(@Path("id") id: Long): Response<ApiResponse<Void>>

    @GET("api/v1/questions")
    suspend fun getAllQuestions(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ApiResponse<List<Question>>>

    // ClassRoom Endpoints
    @GET("api/v1/classes/teacher/{teacherName}")
    suspend fun getClassRoomByTeacherName(@Path("teacherName") teacherName: String): Response<ApiResponse<List<ClassRoom>>>

    @POST("api/v1/classes/create")
    suspend fun createClassRoom(@Body classRoom: ClassRoom): Response<ApiResponse<ClassRoom>>

    @PUT("api/v1/classes/update/{id}")
    suspend fun updateClassRoom(@Path("id") id: Long, @Body classRoom: ClassRoom): Response<ApiResponse<ClassRoom>>

    @DELETE("api/v1/classes/delete/{id}")
    suspend fun deleteClassRoom(@Path("id") id: Long): Response<ApiResponse<Void>>

    @GET("api/v1/classes")
    suspend fun getAllClassRoom(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ApiResponse<List<ClassRoom>>>

    // Category Endpoints
    @GET("api/v1/categories")
    suspend fun getCategory(): Response<ApiResponse<List<CategoryActivity>>>

    @POST("api/v1/categories/create")
    suspend fun createCategory(@Body category: CategoryActivity): Response<ApiResponse<Void>>

    @DELETE("/api/v1/categories/delete/{id}")
    suspend fun deleteCategory(@Path("id") id: Long): Response<ApiResponse<Void>>
}
