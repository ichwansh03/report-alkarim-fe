package com.ichwan.schoolreport.util

import android.util.Log
import com.ichwan.schoolreport.BuildConfig
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

class ConstantData {

    companion object {
        const val SUPABASE_URL = BuildConfig.SUPABASE_URL
        const val SUPABASE_KEY = BuildConfig.SUPABASE_ANON_KEY
        var supabasePostgrest = createSupabaseClient(
            supabaseUrl = SUPABASE_URL,
            supabaseKey = SUPABASE_KEY
        ) {
            install(Postgrest)

        }

        init {
            Log.d("ConstantData", "SUPABASE_URL: ${SUPABASE_URL}")
            Log.d("ConstantData", "SUPABASE_KEY: ${SUPABASE_KEY}")
            Log.d("ConstantData", "supabasePostgrest: ${supabasePostgrest}")
        }
    }
}