package com.ichwan.schoolreport.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ichwan.schoolreport.api.ApiClient
import com.ichwan.schoolreport.core.AlkarimApp
import com.ichwan.schoolreport.model.Question
import kotlinx.coroutines.launch

class QuestionViewModel(app: AlkarimApp) : AndroidViewModel(app) {

    private val apiService by lazy { ApiClient(app.applicationContext).instance }
    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> = _questions

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun loadQuestionsByTarget(target: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getQuestionsByTarget(target)
                if (response.isSuccessful) {
                    _questions.postValue(response.body())
                } else {
                    _message.postValue("Failed to load questions: ${response.message()}")
                }
            } catch (e: Exception) {
                _message.postValue("An error occurred: ${e.message}")
            }
        }
    }
}
