package com.example.kotlin.ASM.services

import com.example.kotlin.ASM.dataClass.Category
import com.example.kotlin.ASM.dataClass.ReqRegister
import com.example.kotlin.ASM.dataClass.ResRegister
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

// Interface định nghĩa API
interface ApiService {
    @POST("register.php")
    suspend fun register(@Body reqRegister: ReqRegister): ResRegister

    @GET("list-category.php")
    suspend fun ListCategory(): List<Category>

}

// Object để khởi tạo Retrofit
object RetrofitInstance {
    private const val BASE_URL = "https://fpoly-learn.io.vn/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}