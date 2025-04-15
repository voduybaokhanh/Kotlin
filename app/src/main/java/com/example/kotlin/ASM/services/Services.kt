package com.example.kotlin.ASM.services

import com.example.kotlin.ASM.dataClass.Account
import com.example.kotlin.ASM.dataClass.LoginRequest
import com.example.kotlin.ASM.dataClass.LoginResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

// Interface định nghĩa API
interface ApiService {
    // API đăng ký
    @POST("api/accounts/register")
    suspend fun register(@Body account: Account): Response<LoginResponse>

    // API đăng nhập
    @POST("api/accounts/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

}


// Object để khởi tạo Retrofit
object RetrofitInstance {
    private const val BASE_URL = "http://192.168.1.15:3000/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
