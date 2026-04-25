package com.ichwan.schoolreport.repository

import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.core.RetrofitClientWrapper
import com.ichwan.schoolreport.model.BaseResponse
import com.ichwan.schoolreport.model.Question
import retrofit2.Response

class QuestionRepository(private val apiService: ApiService = RetrofitClientWrapper.apiService) {

    suspend fun createQuestion(question: Question): Response<BaseResponse<Question>> =
        apiService.createQuestion(question)

    suspend fun getQuestionsByTarget(target: String): Response<BaseResponse<List<Question>>> =
        apiService.getQuestionsByTarget(target)

    suspend fun getQuestionsByCategory(category: String): Response<BaseResponse<List<Question>>> =
        apiService.getQuestionsByCategory(category)

    suspend fun getQuestionsByCategoryAndTarget(category: String, target: String): Response<BaseResponse<List<Question>>> =
        apiService.getQuestionsByCategoryAndTarget(category, target)

    suspend fun updateQuestion(id: Long, question: Question): Response<BaseResponse<Question>> =
        apiService.updateQuestion(id, question)

    suspend fun deleteQuestion(id: Long): Response<BaseResponse<Void>> =
        apiService.deleteQuestion(id)

    suspend fun getAllQuestions(page: Int, size: Int): Response<BaseResponse<List<Question>>> =
        apiService.getAllQuestions(page, size)
}