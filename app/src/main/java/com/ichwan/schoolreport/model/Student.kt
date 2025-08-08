package com.ichwan.schoolreport.model

import kotlinx.serialization.Serializable

@Serializable
data class Student(val name: String, val nisn: String, val className: String, val gender: String)
