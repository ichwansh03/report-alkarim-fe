package com.ichwan.schoolreport.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ichwan.schoolreport.api.ApiClient
import com.ichwan.schoolreport.core.AlkarimApp
import com.ichwan.schoolreport.model.CategoryActivity
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val apiService by lazy {
        getApplication<AlkarimApp>().apiClient.instance
    }
    private val _categories = MutableLiveData<List<CategoryActivity>>()
    val categories: LiveData<List<CategoryActivity>> = _categories

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    init {
        loadCategories()
    }

    fun loadCategories() {
        viewModelScope.launch {
            try {
                val response = apiService.getCategory()
                if (response.isSuccessful && response.body() != null) {
                    _categories.value = response.body()
                } else {
                    _message.value = "Failed to load categories: ${response.message()}"
                }
            } catch (e: Exception) {
                _message.value = "An error occurred: ${e.message}"
            }
        }
    }
}
