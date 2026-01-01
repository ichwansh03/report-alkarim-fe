package com.ichwan.schoolreport.core

import android.content.Context
import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.api.RetrofitClient

object RetrofitClientWrapper {
    lateinit var apiService: ApiService
    lateinit var cleanApiService: ApiService

    fun init(context: Context) {
        val client = RetrofitClient(context)
        apiService = client.apiService
        cleanApiService = client.cleanApiService
    }
}