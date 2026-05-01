package com.ichwan.schoolreport.repository

import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.core.RetrofitClientWrapper
import com.ichwan.schoolreport.model.ApiResponse
import com.ichwan.schoolreport.model.CategoryActivity
import retrofit2.Response

class CategoryRepository(private val apiService: ApiService = RetrofitClientWrapper.apiService) {

    suspend fun getCategory(): Response<ApiResponse<List<CategoryActivity>>> =
        apiService.getCategory()

    suspend fun createCategory(category: CategoryActivity): Response<ApiResponse<Void>> =
        apiService.createCategory(category)
}
