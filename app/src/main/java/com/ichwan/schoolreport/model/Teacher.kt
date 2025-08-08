package com.ichwan.schoolreport.model

import kotlinx.serialization.Serializable

@Serializable
data class Teacher(val name: String, val nip: String, val room: String, val gender: String)
