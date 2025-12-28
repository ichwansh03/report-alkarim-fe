package com.ichwan.schoolreport.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ichwan.schoolreport.model.ActivityReport
import com.ichwan.schoolreport.repository.ReportRepository
import kotlinx.coroutines.launch

class ReportViewModel(private val repository: ReportRepository = ReportRepository()) : ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun addReports(report: ActivityReport) {
        viewModelScope.launch {
            try {
                val response = repository.createReport(report)
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