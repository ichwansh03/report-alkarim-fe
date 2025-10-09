package com.ichwan.schoolreport.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(val regnumber: String, val password: String)
