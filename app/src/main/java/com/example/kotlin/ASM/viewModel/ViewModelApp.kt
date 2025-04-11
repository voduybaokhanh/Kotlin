package com.example.kotlin.ASM.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.ASM.dataClass.ReqRegister
import com.example.kotlin.ASM.dataClass.ResRegister
import com.example.kotlin.ASM.services.RetrofitInstance
import kotlinx.coroutines.launch

class ViewModelApp : ViewModel() {
    private val _register = mutableStateOf<ResRegister?>(null)
    val register: State<ResRegister?> = _register

    fun registerViewModel(reqRegister: ReqRegister) {
        viewModelScope.launch {
            try {
                _register.value = RetrofitInstance.api.register(reqRegister)
            } catch (e: Exception) {
                // Xử lý lỗi nếu cần
                Log.d("=======", e.toString())
            }
        }
    }
}