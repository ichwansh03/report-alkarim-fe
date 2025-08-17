package com.ichwan.schoolreport.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryActivity(var category: String, var questions: Array<ActivityReport>, var mark: String)
