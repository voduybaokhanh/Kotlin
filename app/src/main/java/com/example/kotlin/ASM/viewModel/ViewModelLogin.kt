package com.example.kotlin.ASM.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.ASM.dataClass.Account
import com.example.kotlin.ASM.dataClass.LoginRequest
import com.example.kotlin.ASM.dataClass.LoginResponse
import com.example.kotlin.ASM.services.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelLogin : ViewModel() {

    private val _login = MutableLiveData<LoginResponse>()
    val login: LiveData<LoginResponse> = _login
    
    fun loginViewModel(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {
                val response: Response<LoginResponse> = RetrofitInstance.api.login(loginRequest)
                if (response.isSuccessful) {
                    response.body()?.let { 
                        _login.value = it
                    } ?: run {
                        // Nếu body là null, tạo một đối tượng LoginResponse với success = false
                        _login.value = LoginResponse(
                            success = false,
                            token = "",
                            data = Account("", "", "")
                        )
                    }
                } else {
                    // Xử lý lỗi response
                    Log.d("=======", "Error: ${response.code()} - ${response.message()}")
                    _login.value = LoginResponse(
                        success = false,
                        token = "",
                        data = Account("", "", "")
                    )
                }
            } catch (e: Exception) {
                // Xử lý lỗi kết nối
                Log.d("=======", e.toString())
                _login.value = LoginResponse(
                    success = false,
                    token = "",
                    data = Account("", "", "")
                )
            }
        }
    }
}
