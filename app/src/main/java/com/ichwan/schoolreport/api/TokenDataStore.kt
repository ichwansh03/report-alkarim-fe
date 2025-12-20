package com.ichwan.schoolreport.api

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

//property delegate to keep an instance only, not duplicate
val Context.dataStore : DataStore<Preferences> by preferencesDataStore("tokens")

class TokenDataStore(private val context: Context) {

    //avoid memory leak
    private val dataStore = context.dataStore

    //initialize key data store
    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }

    // each changes from token, all collected accessToken or refreshToken's would be update automatically
    suspend fun saveTokens(accessToken: String, refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken
            preferences[REFRESH_TOKEN] = refreshToken
        }
    }

    val accessToken: Flow<String?> = dataStore.data.map { prefs -> prefs[ACCESS_TOKEN] }
    val refreshToken: Flow<String?> = dataStore.data.map { prefs -> prefs[REFRESH_TOKEN] }

    suspend fun getAccessToken(): String? = accessToken.first()
    suspend fun getRefreshToken(): String? = refreshToken.first()

    suspend fun clearTokens() {
        dataStore.edit { it.clear() }
    }
}