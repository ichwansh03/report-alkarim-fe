package com.ichwan.schoolreport.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val name: String,
    val clsroom: String?,
    val gender: String,
    val regnumber: String,
    val roles: String,
    val createdAt: String,
    val updatedAt: String
)