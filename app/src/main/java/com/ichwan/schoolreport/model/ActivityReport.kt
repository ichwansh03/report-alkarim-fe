package com.ichwan.schoolreport.model

import kotlinx.serialization.Serializable

@Serializable
data class ActivityReport(var nip: String, var category: String, var question: String, var action: Boolean, var score: String)
