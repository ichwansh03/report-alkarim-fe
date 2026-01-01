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
            val refreshToken = tokenDataStore.getRefreshToken() ?: return null

            return try {
                val refreshResponse = cleanApiService.refreshToken(
                    mapOf("refreshToken" to refreshToken)
                ).execute()

                if (refreshResponse.isSuccessful) {
                    val newTokens = refreshResponse.body() ?: return null

                    runBlocking { tokenDataStore.saveTokens(newTokens.accessToken, newTokens.refreshToken) }

                    response.request.newBuilder()
                        .header("Authorization", "Bearer ${newTokens.accessToken}")
                        .build()
                } else {
                    handleAuthFailure(refreshResponse.code())
                    null
                }
            } catch (e: Exception) {
                handleAuthFailure(0)
                e.printStackTrace()
                null
            }
        }
    }

    private fun handleAuthFailure(responseCode: Int) {
        if (responseCode == 401 || responseCode == 403 || responseCode == 0) {
            runBlocking { tokenDataStore.clearTokens() }
        }
    }
}