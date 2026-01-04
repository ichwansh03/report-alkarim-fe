package com.ichwan.schoolreport.repository

import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.core.RetrofitClientWrapper
import com.ichwan.schoolreport.model.ActivityReport

class ReportRepository(private val apiService: ApiService = RetrofitClientWrapper.apiService) {

    suspend fun createReport(report: ActivityReport) = apiService.createReport(report)
}
