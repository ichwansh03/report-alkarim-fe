package com.ichwan.schoolreport.repository

import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.api.RetrofitClient

class CategoryRepository(private val apiService: ApiService = RetrofitClient.apiService) {

    suspend fun getCategory() = apiService.getCategory()

}