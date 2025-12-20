package com.ichwan.schoolreport.api

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    private const val BASE_URL = "http://10.0.2.2:8080/"
    private lateinit var tokenDataStore: TokenDataStore

    fun init(context: Context) {
        tokenDataStore = TokenDataStore(context.applicationContext)
    }

    private val retrofit : Retrofit by lazy {
        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val cleanApiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    private val authRetrofit: Retrofit by lazy {
        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val authClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenDataStore))
            .addInterceptor(logging)
            .authenticator(TokenAuthenticator(cleanApiService, tokenDataStore))
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(authClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        authRetrofit.create(ApiService::class.java)
    }
}