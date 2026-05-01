package com.ichwan.schoolreport.model

import com.ichwan.schoolreport.util.AnswerType
import kotlinx.serialization.Serializable

@Serializable
data class Question(
    var id: Long? = null,
    var question: String,
    var category: String,
    var target: String,
    var options: AnswerType
)
