package com.example.kotlin.ASM.dataClass

data class Account(
    val email: String,
    val fullName: String,
    val password: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val success: Boolean,
    val token: String,
    val data: Account
)

data class ReqRegister(
    val email: String,
    val password: String,
    val fullName: String
)

data class ResRegister(
    val success: Boolean,
    val message: String
)