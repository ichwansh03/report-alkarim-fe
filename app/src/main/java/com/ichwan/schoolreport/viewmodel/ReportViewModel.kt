package com.ichwan.schoolreport.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ichwan.schoolreport.core.AlkarimApp
import com.ichwan.schoolreport.model.ActivityReport
import kotlinx.coroutines.launch

class ReportViewModel(application: Application) : AndroidViewModel(application) {

    private val apiService by lazy {
        getApplication<AlkarimApp>().apiClient.instance
    }
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