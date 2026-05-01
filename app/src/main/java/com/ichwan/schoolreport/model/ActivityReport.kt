package com.ichwan.schoolreport.model

import kotlinx.serialization.Serializable

@Serializable
data class ActivityReport(
    var id: Long? = null,
    var category: String,
    var content: String,
    var userId: Long? = null,
    var regnumber: String? = null,
    var score: String,
    var answer: String
)
