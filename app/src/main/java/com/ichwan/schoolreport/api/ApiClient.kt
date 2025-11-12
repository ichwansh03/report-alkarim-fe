package com.ichwan.schoolreport.api

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient(context: Context) {

    private val BASE_URL = "http://10.0.2.2:8080/"

    // 1. Buat DataStore untuk mengakses token
    private val tokenDataStore = TokenDataStore(context)

    // 2. Buat instance Retrofit "bersih" (tanpa authenticator)
    // Ini HANYA untuk digunakan di dalam TokenAuthenticator
    private val cleanApiService: ApiService by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    // 3. Buat TokenAuthenticator, berikan ApiService yang "bersih" dan DataStore
    private val tokenAuthenticator = TokenAuthenticator(cleanApiService, tokenDataStore)

    // 4. Buat AuthInterceptor, berikan DataStore
    private val authInterceptor = AuthInterceptor(tokenDataStore)

    // 5. Buat OkHttpClient UTAMA yang digunakan seluruh aplikasi
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .authenticator(tokenAuthenticator) // DAFTARKAN AUTHENTICATOR DI SINI
        .build()

    // 6. Buat instance Retrofit UTAMA
    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
