package com.ichwan.schoolreport.model

import com.ichwan.schoolreport.util.UserRole
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val name: String,
    val clsroom: String?,
    val gender: String,
    val regnumber: String,
    val roles: UserRole,
    val createdAt: String,
    val updatedAt: String
)
