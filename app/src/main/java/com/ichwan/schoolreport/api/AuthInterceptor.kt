package com.ichwan.schoolreport.api

import com.ichwan.schoolreport.core.AlkarimApp
import com.ichwan.schoolreport.model.RefreshTokenRequest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor (private val app: AlkarimApp) : Interceptor {

    private var token: String? = null

    fun setToken(token: String?) {
        this.token = token
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        token?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(requestBuilder.build())
    }
}
