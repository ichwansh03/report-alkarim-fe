package com.ichwan.schoolreport.api

import com.ichwan.schoolreport.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // Auth Endpoints
    @GET("api/v1/auths/test")
    suspend fun testAuth(): Response<BaseResponse<String>>

    @POST("api/v1/auths/register")
    suspend fun register(@Body user: User): Response<BaseResponse<User>>

    @POST("api/v1/auths/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<BaseResponse<LoginResponse>>

    @POST("api/v1/auths/refresh")
    fun refreshToken(@Body body: Map<String, String>): retrofit2.Call<RefreshTokenResponse>

    // User Endpoints
    @PUT("api/v1/users/update/{id}")
    suspend fun updateUser(@Path("id") id: Long, @Body user: User): Response<BaseResponse<User>>

    @GET("api/v1/users/class/{class}/roles/{roles}")
    suspend fun getUsersByClassAndRoles(
        @Path("class") classValue: String,
        @Path("roles") roles: String
    ): Response<BaseResponse<List<User>>>

    @GET("api/v1/users/{regnumber}")
    suspend fun getUserByRegNumber(@Path("regnumber") regNumber: String): Response<BaseResponse<User>>

    @GET("api/v1/users/roles/{roles}")
    suspend fun getUsersByRole(@Path("roles") roles: String): Response<BaseResponse<List<User>>>

    @DELETE("api/v1/users/delete/{id}")
    suspend fun deleteUser(@Path("id") id: Long): Response<BaseResponse<Void>>

    @GET("api/v1/users")
    suspend fun getAllUsers(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<BaseResponse<List<User>>>

    // Report Endpoints
    @POST("api/v1/reports/create")
    suspend fun createReport(@Body activityReport: ActivityReport): Response<BaseResponse<Void>>

    @GET("api/v1/reports/regnumber/{regnumber}")
    suspend fun getReportsByRegnumber(@Path("regnumber") regnumber: String): Response<BaseResponse<List<ActivityReport>>>

    @GET("api/v1/reports/name/{name}")
    suspend fun getReportsByUserName(@Path("name") name: String): Response<BaseResponse<List<ActivityReport>>>

    @PUT("api/v1/reports/update/{id}")
    suspend fun updateReport(@Path("id") id: Long, @Body activityReport: ActivityReport): Response<BaseResponse<ActivityReport>>

    @DELETE("api/v1/reports/delete/{id}")
    suspend fun deleteReport(@Path("id") id: Long): Response<BaseResponse<Void>>

    @GET("api/v1/reports")
    suspend fun getAllReports(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<BaseResponse<List<ActivityReport>>>

    // Question Endpoints
    @POST("api/v1/questions/create")
    suspend fun createQuestion(@Body question: Question): Response<BaseResponse<Question>>

    @GET("api/v1/questions/target/{target}")
    suspend fun getQuestionsByTarget(@Path("target") target: String): Response<BaseResponse<List<Question>>>

    @GET("api/v1/questions/category/{category}")
    suspend fun getQuestionsByCategory(@Path("category") category: String): Response<BaseResponse<List<Question>>>

    @GET("api/v1/questions/category/{category}/target/{target}")
    suspend fun getQuestionsByCategoryAndTarget(
        @Path("category") category: String,
        @Path("target") target: String
    ): Response<BaseResponse<List<Question>>>

    @PUT("api/v1/questions/update/{id}")
    suspend fun updateQuestion(@Path("id") id: Long, @Body question: Question): Response<BaseResponse<Question>>

    @DELETE("api/v1/questions/delete/{id}")
    suspend fun deleteQuestion(@Path("id") id: Long): Response<BaseResponse<Void>>

    @GET("api/v1/questions")
    suspend fun getAllQuestions(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<BaseResponse<List<Question>>>

    // ClassRoom Endpoints
    @GET("api/v1/classes/teacher/{teacherName}")
    suspend fun getClassRoomByTeacherName(@Path("teacherName") teacherName: String): Response<BaseResponse<List<ClassRoom>>>

    @POST("api/v1/classes/create")
    suspend fun createClassRoom(@Body classRoom: ClassRoom): Response<BaseResponse<ClassRoom>>

    @PUT("api/v1/classes/update/{id}")
    suspend fun updateClassRoom(@Path("id") id: Long, @Body classRoom: ClassRoom): Response<BaseResponse<ClassRoom>>

    @DELETE("api/v1/classes/delete/{id}")
    suspend fun deleteClassRoom(@Path("id") id: Long): Response<BaseResponse<Void>>

    @GET("api/v1/classes")
    suspend fun getAllClassRoom(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<BaseResponse<List<ClassRoom>>>

    // Category Endpoints (Legacy/Awaiting Update)
    @GET("category")
    suspend fun getCategory(): Response<List<CategoryActivity>>

    @POST("/category/create")
    suspend fun createCategory(@Body category: CategoryActivity): Response<Void>
}
