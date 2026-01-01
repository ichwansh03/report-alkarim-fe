package com.ichwan.schoolreport.api

import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val cleanApiService: ApiService,
    private val tokenDataStore: TokenDataStore
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(this) {
            val refreshToken = runBlocking { tokenDataStore.getRefreshToken() } ?: return null

            try {
                val refreshCall = cleanApiService.refreshToken(mapOf("refreshToken" to refreshToken))
                val refreshResponse = refreshCall.execute()

                if (!refreshResponse.isSuccessful) {
                    if (refreshResponse.code() == 401 || refreshResponse.code() == 403) {
                        runBlocking { tokenDataStore.clearTokens() }  // Wrap di runBlocking
                        // Optional: Trigger logout event di sini (misal via LiveData atau callback)
                    }
                    return null
                }

                val tokenResponse = refreshResponse.body() ?: return null

                runBlocking {
                    tokenDataStore.saveTokens(tokenResponse.accessToken, tokenResponse.refreshToken)
                }

                return response.request.newBuilder()
                    .header("Authorization", "Bearer ${tokenResponse.accessToken}")
                    .build()
            } catch (e: Exception) {
                return null
            }
        }
    }
}