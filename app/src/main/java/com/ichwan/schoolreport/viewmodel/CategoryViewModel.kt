package com.ichwan.schoolreport.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ichwan.schoolreport.model.CategoryActivity
import com.ichwan.schoolreport.repository.CategoryRepository
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: CategoryRepository = CategoryRepository()) : ViewModel() {

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
                val response = repository.getCategory()
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
