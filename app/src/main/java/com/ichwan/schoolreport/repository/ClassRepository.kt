package com.ichwan.schoolreport.repository

import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.core.RetrofitClientWrapper
import com.ichwan.schoolreport.model.ApiResponse
import com.ichwan.schoolreport.model.ClassRoom
import retrofit2.Response

class ClassRepository(private val apiService: ApiService = RetrofitClientWrapper.apiService) {

    suspend fun getClassRoomByTeacherName(teacherName: String): Response<ApiResponse<List<ClassRoom>>> =
        apiService.getClassRoomByTeacherName(teacherName)

    suspend fun createClassRoom(classRoom: ClassRoom): Response<ApiResponse<ClassRoom>> =
        apiService.createClassRoom(classRoom)

    suspend fun updateClassRoom(id: Long, classRoom: ClassRoom): Response<ApiResponse<ClassRoom>> =
        apiService.updateClassRoom(id, classRoom)

    suspend fun deleteClassRoom(id: Long): Response<ApiResponse<Void>> =
        apiService.deleteClassRoom(id)

    suspend fun getAllClassRoom(page: Int, size: Int): Response<ApiResponse<List<ClassRoom>>> =
        apiService.getAllClassRoom(page, size)
}
