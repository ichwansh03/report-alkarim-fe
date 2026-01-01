package com.ichwan.schoolreport.repository

import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.api.RetrofitClient
import com.ichwan.schoolreport.core.RetrofitClientWrapper

class CategoryRepository(private val apiService: ApiService = RetrofitClientWrapper.apiService) {

    suspend fun getCategory() = apiService.getCategory()

}