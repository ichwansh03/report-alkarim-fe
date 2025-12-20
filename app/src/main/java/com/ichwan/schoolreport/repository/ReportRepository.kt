package com.ichwan.schoolreport.repository

import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.api.RetrofitClient
import com.ichwan.schoolreport.model.ActivityReport

class ReportRepository(private val apiService: ApiService = RetrofitClient.apiService) {

    suspend fun createReport(report: ActivityReport) = apiService.createReport(report)
}
