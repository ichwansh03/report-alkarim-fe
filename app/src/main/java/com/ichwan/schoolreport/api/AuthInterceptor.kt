package com.ichwan.schoolreport.api

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenDataStore: TokenDataStore) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request()
        val accessToken = tokenDataStore.getAccessToken() ?: return chain.proceed(requestBuilder)

        val authorizeRequest = requestBuilder.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()

        return chain.proceed(authorizeRequest)
    }
}
