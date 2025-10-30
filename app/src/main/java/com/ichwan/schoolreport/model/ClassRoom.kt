package com.ichwan.schoolreport.model

import kotlinx.serialization.Serializable

@Serializable
data class ClassRoom(var name: String, var teacher: String, var studentTotal: Int)
