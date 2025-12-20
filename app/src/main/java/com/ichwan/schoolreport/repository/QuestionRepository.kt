package com.ichwan.schoolreport.repository

import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.api.RetrofitClient

class QuestionRepository(private val apiService: ApiService = RetrofitClient.apiService) {

    suspend fun getQuestionByTarget(target: String) = apiService.getQuestionsByTarget(target)
}