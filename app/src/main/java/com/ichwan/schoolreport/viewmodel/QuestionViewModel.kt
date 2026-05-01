package com.ichwan.schoolreport.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ichwan.schoolreport.model.Question
import com.ichwan.schoolreport.repository.QuestionRepository
import kotlinx.coroutines.launch

class QuestionViewModel(private val repository: QuestionRepository = QuestionRepository()) : ViewModel() {

    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> = _questions

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun loadQuestionsByTarget(target: String) {
        viewModelScope.launch {
            try {
                val response = repository.getQuestionsByTarget(target)
                if (response.isSuccessful && response.body() != null) {
                    _questions.value = response.body()?.data ?: emptyList()
                } else {
                    _message.value = "Failed to load questions: ${response.message()}"
                }
            } catch (e: Exception) {
                _message.value = "An error occurred: ${e.message}"
            }
        }
    }
}
