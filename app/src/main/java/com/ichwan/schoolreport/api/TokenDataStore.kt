package com.ichwan.schoolreport.api

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

//property delegate to keep an instance only, not duplicate
val Context.dataStore : DataStore<Preferences> by preferencesDataStore("tokens")

class TokenDataStore(private val context: Context) {

    //avoid memory leak
    private val dataStore = context.applicationContext.dataStore

    //initialize key data store
    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }

    //in-memory cache for token
    private var cacheAccess: String? = null
    private var cacheRefresh: String? = null

    init {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val prefs = dataStore.data.first()
                cacheAccess = prefs[ACCESS_TOKEN]
                cacheRefresh = prefs[REFRESH_TOKEN]
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // each changes from token, all collected accessToken or refreshToken's would be update automatically
    suspend fun saveTokens(accessToken: String, refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken
            preferences[REFRESH_TOKEN] = refreshToken
        }

        cacheAccess = accessToken
        cacheRefresh = refreshToken
    }

    suspend fun clearTokens() {
        dataStore.edit { it.clear() }
        cacheAccess = null
        cacheRefresh = null
    }

    fun getAccessToken(): String? = cacheAccess
    fun getRefreshToken(): String? = cacheRefresh
}