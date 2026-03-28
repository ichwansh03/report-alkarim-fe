package com.ichwan.schoolreport.core

import android.content.Context
import com.ichwan.schoolreport.api.ApiService
import com.ichwan.schoolreport.api.RetrofitClient

object RetrofitClientWrapper {
    lateinit var apiService: ApiService
    lateinit var cleanApiService: ApiService

    fun init(context: Context) {
        // ✅ Use applicationContext to avoid memory leaks
        val client = RetrofitClient(context.applicationContext)
        apiService = client.apiService
        cleanApiService = client.cleanApiService
    }

}