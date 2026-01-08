package com.ichwan.schoolreport.api

import android.content.Context
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient(private val context: Context) {

    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080/"
    }
    private val tokenDataStore = TokenDataStore(context)

    private val loggingInterceptor = HttpLoggingInterceptor { message ->
        Log.d("API_PAYLOAD", message)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val unauthenticatedRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val cleanApiService: ApiService by lazy {
        unauthenticatedRetrofit.create(ApiService::class.java)
    }

    private val tokenAuthenticator by lazy {
        TokenAuthenticator(cleanApiService, tokenDataStore)
    }

    private val authenticatedRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(AuthInterceptor(tokenDataStore))
                    .addInterceptor(loggingInterceptor)
                    .authenticator(tokenAuthenticator)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        authenticatedRetrofit.create(ApiService::class.java)
    }

}