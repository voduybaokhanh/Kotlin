package com.example.kotlin.ASM.services

import android.util.Log
import com.example.kotlin.ASM.dataClass.Account
import com.example.kotlin.ASM.dataClass.LoginRequest
import com.example.kotlin.ASM.dataClass.LoginResponse
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    // Đảm bảo IP này là chính xác và có thể truy cập từ thiết bị Android
    private const val BASE_URL = "http://192.168.1.15:3000/"

    // Thêm log để kiểm tra URL
    init {
        Log.d("RetrofitInstance", "Using BASE_URL: $BASE_URL")
    }

    private const val TAG = "RetrofitInstance"

    // Tạo logging interceptor để xem request và response
    private val loggingInterceptor = HttpLoggingInterceptor { message ->
        Log.d(TAG, message)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Tạo OkHttpClient với logging interceptor
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    // Tạo Gson với pretty printing để dễ đọc
    private val gson = GsonBuilder()
        .setPrettyPrinting()
        .setLenient() // Cho phép JSON không chuẩn
        .create()

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }
}
