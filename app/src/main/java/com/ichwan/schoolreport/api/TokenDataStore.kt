package com.ichwan.schoolreport.api

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//property delegate to keep an instance only, not duplicate
val Context.dataStore by preferencesDataStore("auth_prefs")

class TokenDataStore(private val context: Context) {

    //avoid memory leak
    private val appContext = context.applicationContext

    //initialize key data store
    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }

    // each changes from token, all collected accessToken or refreshToken's would be update automatically
    val accessToken: Flow<String?> = appContext.dataStore.data.map { prefs -> prefs[ACCESS_TOKEN] }
    val refreshToken: Flow<String?> = appContext.dataStore.data.map { prefs -> prefs[REFRESH_TOKEN] }

    suspend fun saveTokens(access: String?, refresh: String?) {
        context.dataStore.edit { prefs ->
            if (access != null) prefs[ACCESS_TOKEN] = access
            if (refresh != null) prefs[REFRESH_TOKEN] = refresh
        }
    }

    //user logout
    suspend fun clearTokens() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}