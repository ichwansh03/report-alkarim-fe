package com.ichwan.schoolreport.repository

import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.core.RetrofitClientWrapper
import com.ichwan.schoolreport.model.BaseResponse
import com.ichwan.schoolreport.model.ClassRoom
import retrofit2.Response

class ClassRepository(private val apiService: ApiService = RetrofitClientWrapper.apiService) {

    suspend fun getClassRoomByTeacherName(teacherName: String): Response<BaseResponse<List<ClassRoom>>> =
        apiService.getClassRoomByTeacherName(teacherName)

    suspend fun createClassRoom(classRoom: ClassRoom): Response<BaseResponse<ClassRoom>> =
        apiService.createClassRoom(classRoom)

    suspend fun updateClassRoom(id: Long, classRoom: ClassRoom): Response<BaseResponse<ClassRoom>> =
        apiService.updateClassRoom(id, classRoom)

    suspend fun deleteClassRoom(id: Long): Response<BaseResponse<Void>> =
        apiService.deleteClassRoom(id)

    suspend fun getAllClassRoom(page: Int, size: Int): Response<BaseResponse<List<ClassRoom>>> =
        apiService.getAllClassRoom(page, size)
}