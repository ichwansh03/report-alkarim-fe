package com.ichwan.schoolreport.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val regnumber: String,
    val token: String
)
