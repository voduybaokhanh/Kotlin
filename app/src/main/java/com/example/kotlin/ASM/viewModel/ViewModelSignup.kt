package com.example.kotlin.ASM.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.ASM.dataClass.Account
import com.example.kotlin.ASM.dataClass.LoginResponse
import com.example.kotlin.ASM.services.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelSignup : ViewModel() {

    private val _signup = MutableLiveData<LoginResponse?>()
    val signup: LiveData<LoginResponse?> = _signup
    
    // Biến để theo dõi trạng thái đang gọi API
    private var isRegistering = false
    
    fun signupViewModel(account: Account) {
        // Nếu đang trong quá trình đăng ký, không gọi API lần nữa
        if (isRegistering) return
        
        // Log thông tin request để debug
        Log.d("SignupViewModel", "Attempting signup with: Email=${account.Email}, FullName=${account.FullName}")
        
        isRegistering = true
        
        viewModelScope.launch {
            try {
                Log.d("SignupViewModel", "Sending signup request...")
                val response: Response<LoginResponse> = RetrofitInstance.api.register(account)
                
                Log.d("SignupViewModel", "Response code: ${response.code()}")
                Log.d("SignupViewModel", "Response message: ${response.message()}")
                
                if (response.isSuccessful) {
                    Log.d("SignupViewModel", "Signup successful")
                    response.body()?.let { 
                        Log.d("SignupViewModel", "Response body: success=${it.success}, token=${it.token}")
                        _signup.value = it
                    } ?: run {
                        Log.d("SignupViewModel", "Response body is null")
                        // Nếu body là null, tạo một đối tượng LoginResponse với success = false
                        _signup.value = LoginResponse(
                            success = false,
                            token = "",
                            data = Account(Email = "", FullName = "", Password = "")
                        )
                    }
                } else {
                    // Xử lý lỗi response
                    Log.d("SignupViewModel", "Signup failed: ${response.code()} - ${response.message()}")
                    
                    // Thử đọc error body
                    val errorBody = response.errorBody()?.string()
                    Log.d("SignupViewModel", "Error body: $errorBody")
                    
                    _signup.value = LoginResponse(
                        success = false,
                        token = "",
                        data = Account(Email = "", FullName = "", Password = "")
                    )
                }
            } catch (e: Exception) {
                // Xử lý lỗi kết nối
                Log.d("SignupViewModel", "Exception during signup: ${e.message}")
                Log.d("SignupViewModel", "Stack trace: ${e.stackTraceToString()}")
                
                _signup.value = LoginResponse(
                    success = false,
                    token = "",
                    data = Account(Email = "", FullName = "", Password = "")
                )
            } finally {
                isRegistering = false
            }
        }
    }
    
    // Hàm để reset trạng thái signup sau khi xử lý xong
    fun resetSignupState() {
        _signup.value = null
    }
}