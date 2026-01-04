package com.ichwan.schoolreport.repository

import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.core.RetrofitClientWrapper

class QuestionRepository(private val apiService: ApiService = RetrofitClientWrapper.apiService) {

    suspend fun getQuestionByTarget(target: String) = apiService.getQuestionsByTarget(target)
}