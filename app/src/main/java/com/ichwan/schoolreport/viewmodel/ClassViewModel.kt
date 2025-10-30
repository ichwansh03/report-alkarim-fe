package com.ichwan.schoolreport.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ichwan.schoolreport.api.ApiClient
import com.ichwan.schoolreport.model.ClassRoom
import com.ichwan.schoolreport.model.User
import kotlinx.coroutines.launch
import kotlin.getValue

class ClassViewModel : ViewModel() {

    private val apiService by lazy { ApiClient.instance }

    private val _teacherList = MutableLiveData<List<User>?>()
    val teacherList: LiveData<List<User>?> = _teacherList
    private val _creationSuccess = MutableLiveData<Boolean>()
    val creationSuccess: LiveData<Boolean> = _creationSuccess

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    init {
        loadTeachers()
    }

    private fun loadTeachers() {
        viewModelScope.launch {
            try {
                val response = apiService.getUsersByRole("TEACHER")
                if (response.isSuccessful) {
                    _teacherList.postValue(response.body())
                } else {
                    _message.postValue("Error fetching teachers: ${response.message()}")
                    _teacherList.postValue(null)
                }
            } catch (e: Exception) {
                _message.postValue("Network Error: ${e.message}")
                _teacherList.postValue(null)
            }
        }
    }

    fun onAddClassClicked(className: String, teacherName: String?) {
        if (className.isBlank()) {
            _message.value = "Class name cannot be empty"
            return
        }
        if (teacherName == null) {
            _message.value = "Please select a homeroom teacher"
            return
        }

        viewModelScope.launch {
            try {
                // studentTotal is 0 because this is a new class
                val newClass = ClassRoom(name = className, teacher = teacherName, studentTotal = 0)
                val response = apiService.createClass(newClass)
                if (response.isSuccessful) {
                    _message.postValue("Class '$className' created successfully")
                    _creationSuccess.postValue(true)
                } else {
                    _message.postValue("Failed to create class: ${response.errorBody()?.string()}")
                    _creationSuccess.postValue(false)
                }
            } catch (e: Exception) {
                _message.postValue("Network Error: ${e.message}")
                _creationSuccess.postValue(false)
            }
        }
    }
}
