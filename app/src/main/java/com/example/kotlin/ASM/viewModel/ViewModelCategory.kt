package com.example.kotlin.ASM.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.ASM.dataClass.Category
import com.example.kotlin.ASM.services.RetrofitInstance
import kotlinx.coroutines.launch

class ViewModelCategory : ViewModel() {
    private val setListCategory = mutableStateOf<List<Category>>(emptyList())
    val getListCategory: State<List<Category>> = setListCategory

    fun getListCategoryViewModel() {
        viewModelScope.launch {
            try {
                setListCategory.value = RetrofitInstance.api.ListCategory()
            } catch (e: Exception) {
                // Xử lý lỗi nếu cần
                Log.d("=======", e.toString())
            }
        }
    }
}