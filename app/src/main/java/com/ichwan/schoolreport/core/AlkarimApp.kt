package com.ichwan.schoolreport.core

import android.app.Application

// for initialize data store and avoid memory leak
// this class will be called before anything else and always life even app closed
class AlkarimApp : Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitClientWrapper.init(this)
    }

}