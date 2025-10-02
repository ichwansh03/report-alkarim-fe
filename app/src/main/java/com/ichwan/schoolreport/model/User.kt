package com.ichwan.schoolreport.model

import kotlinx.serialization.Serializable

@Serializable
data class User(val name: String, val regnumber: String, val room: String, val gender: String, val password: String)
