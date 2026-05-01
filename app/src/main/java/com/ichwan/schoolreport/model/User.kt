package com.ichwan.schoolreport.model

import com.ichwan.schoolreport.util.UserRole
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long? = null,
    val name: String,
    val regnumber: String,
    val clsroom: String,
    val gender: String,
    val roles: UserRole,
    val password: String
)
