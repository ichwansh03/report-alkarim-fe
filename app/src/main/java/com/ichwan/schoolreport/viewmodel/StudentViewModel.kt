package com.ichwan.schoolreport.viewmodel

import androidx.compose.ui.util.fastMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ichwan.schoolreport.util.ConstantData
import com.ichwan.schoolreport.model.Student
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.launch
import java.util.Collections

class StudentViewModel : ViewModel() {

    fun fetchNisn(onResult: (List<String>?) -> Unit) {
        viewModelScope.launch{
            try {
                val student = ConstantData.supabasePostgrest
                    .from("students")
                    .select(columns = Columns.raw("nisn"))
                    .decodeList<Student>()

                val nisnList = student.fastMap { it.nisn }
                onResult(nisnList)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(Collections.emptyList())
            }
        }
    }
}