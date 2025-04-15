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

    private val _login = MutableLiveData<LoginResponse?>()
    val login: LiveData<LoginResponse?> = _login
    
    // Biến để theo dõi trạng thái đang gọi API
    private var isLoggingIn = false
    
    fun loginViewModel(loginRequest: LoginRequest) {
        // Nếu đang trong quá trình đăng nhập, không gọi API lần nữa
        if (isLoggingIn) return
        
        // Log thông tin request để debug
        Log.d("LoginViewModel", "Attempting login with: Email=${loginRequest.Email}, Password=${loginRequest.Password}")
        
        isLoggingIn = true
        
        viewModelScope.launch {
            try {
                Log.d("LoginViewModel", "Sending login request...")
                val response: Response<LoginResponse> = RetrofitInstance.api.login(loginRequest)
                
                Log.d("LoginViewModel", "Response code: ${response.code()}")
                Log.d("LoginViewModel", "Response message: ${response.message()}")
                
                if (response.isSuccessful) {
                    Log.d("LoginViewModel", "Login successful")
                    response.body()?.let { 
                        Log.d("LoginViewModel", "Response body: success=${it.success}, token=${it.token}")
                        _login.value = it
                    } ?: run {
                        Log.d("LoginViewModel", "Response body is null")
                        // Nếu body là null, tạo một đối tượng LoginResponse với success = false
                        _login.value = LoginResponse(
                            success = false,
                            token = "",
                            data = Account(Email = "", FullName = "", Password = "")
                        )
                    }
                } else {
                    // Xử lý lỗi response
                    Log.d("LoginViewModel", "Login failed: ${response.code()} - ${response.message()}")
                    
                    // Thử đọc error body
                    val errorBody = response.errorBody()?.string()
                    Log.d("LoginViewModel", "Error body: $errorBody")
                    
                    _login.value = LoginResponse(
                        success = false,
                        token = "",
                        data = Account(Email = "", FullName = "", Password = "")
                    )
                }
            } catch (e: Exception) {
                // Xử lý lỗi kết nối
                Log.d("LoginViewModel", "Exception during login: ${e.message}")
                Log.d("LoginViewModel", "Stack trace: ${e.stackTraceToString()}")
                
                _login.value = LoginResponse(
                    success = false,
                    token = "",
                    data = Account(Email = "", FullName = "", Password = "")
                )
            } finally {
                isLoggingIn = false
            }
        }
    }
    
    // Hàm để reset trạng thái login sau khi xử lý xong
    fun resetLoginState() {
        _login.value = null
    }
}
