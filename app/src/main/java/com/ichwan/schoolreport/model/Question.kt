package com.ichwan.schoolreport.model

import kotlinx.serialization.Serializable

@Serializable
data class Question(var quest: String, var category: String, var target: String, var option: String)