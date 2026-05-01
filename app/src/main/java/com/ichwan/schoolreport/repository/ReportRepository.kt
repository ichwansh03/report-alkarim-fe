package com.ichwan.schoolreport.repository

import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.core.RetrofitClientWrapper
import com.ichwan.schoolreport.model.ActivityReport
import com.ichwan.schoolreport.model.ApiResponse
import retrofit2.Response

class ReportRepository(private val apiService: ApiService = RetrofitClientWrapper.apiService) {

    suspend fun createReport(report: ActivityReport): Response<ApiResponse<Void>> =
        apiService.createReport(report)

    suspend fun getReportsByRegnumber(regnumber: String): Response<ApiResponse<List<ActivityReport>>> =
        apiService.getReportsByRegnumber(regnumber)

    suspend fun getReportsByUserName(name: String): Response<ApiResponse<List<ActivityReport>>> =
        apiService.getReportsByUserName(name)

    suspend fun updateReport(id: Long, report: ActivityReport): Response<ApiResponse<ActivityReport>> =
        apiService.updateReport(id, report)

    suspend fun deleteReport(id: Long): Response<ApiResponse<Void>> =
        apiService.deleteReport(id)

    suspend fun getAllReports(page: Int, size: Int): Response<ApiResponse<List<ActivityReport>>> =
        apiService.getAllReports(page, size)
}
