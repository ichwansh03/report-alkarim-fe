package com.ichwan.schoolreport.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: Int,
    val name: String,
    val regnumber: String,
    val clsroom: String?,
    val gender: String,
    val roles: String,
    val createdAt: String,
    val updatedAt: String
)