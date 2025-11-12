package com.ichwan.schoolreport.core

import android.app.Application
import com.ichwan.schoolreport.api.ApiClient
import com.ichwan.schoolreport.api.TokenDataStore

// for initialize data store and avoid memory leak
// this class will be called before anything else and always life even app closed
class AlkarimApp : Application() {

    lateinit var apiClient: ApiClient
        private set

    override fun onCreate() {
        super.onCreate()
        apiClient = ApiClient(applicationContext)
    }
}