package com.ichwan.schoolreport.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ichwan.schoolreport.api.ApiClient
import com.ichwan.schoolreport.core.AlkarimApp
import com.ichwan.schoolreport.model.ActivityReport
import kotlinx.coroutines.launch

class ReportViewModel(app: AlkarimApp) : AndroidViewModel(app) {

    private val apiService by lazy { ApiClient(app.applicationContext).instance }
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun addReports(report: ActivityReport) {
        viewModelScope.launch {
            try {
                val response = apiService.createReport(report)
                if (response.isSuccessful) {
                    _message.value = "Report added successfully"
                } else {
                    _message.value = "Failed to add report: ${response.message()}"
                }
            } catch (e: Exception) {
                _message.value = "An error occurred: ${e.message}"
            }
        }
    }
}