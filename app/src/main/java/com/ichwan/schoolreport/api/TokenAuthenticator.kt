package com.ichwan.schoolreport.api

import com.ichwan.schoolreport.model.RefreshTokenRequest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val apiService: ApiService,
    private val tokenDataStore: TokenDataStore
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        if (responseCount(response) >= 2) return null

        val refreshToken = runBlocking { tokenDataStore.refreshToken.first() } ?: return null

        val newTokens = runBlocking {
            try {
                apiService.refreshToken(RefreshTokenRequest(refreshToken))
            } catch (e: Exception) {
                null
            }
        } ?: return null

        runBlocking {
            tokenDataStore.saveTokens(newTokens.accessToken, newTokens.refreshToken)
        }

        return response.request.newBuilder().header("Authorization", "Bearer ${newTokens.accessToken}").build()
    }

    private fun responseCount(response: Response?): Int {
        var count = 1
        var r = response
        while (r?.priorResponse != null) {
            count++
            r = r.priorResponse
        }

        return count
    }
}