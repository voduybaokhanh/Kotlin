package com.example.kotlin.ASM.dataClass

// Sử dụng tên trường chính xác như backend mong đợi
data class Account(
    val Email: String,
    val FullName: String,
    val Password: String
)

// Sử dụng tên trường chính xác như backend mong đợi
data class LoginRequest(
    val Email: String,
    val Password: String
)

data class LoginResponse(
    val success: Boolean,
    val token: String,
    val data: Account
)

// Sử dụng tên trường chính xác như backend mong đợi
data class ReqRegister(
    val Email: String,
    val Password: String,
    val FullName: String
)

data class ResRegister(
    val success: Boolean,
    val message: String
)