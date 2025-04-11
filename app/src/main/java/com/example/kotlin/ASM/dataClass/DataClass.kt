package com.example.kotlin.ASM.dataClass

data class ReqRegister(
    val email: String,
    val password: String,
    val fullname: String
)

data class ResRegister(
    val status: Boolean,
    val message: String
)

data class Category(
    val idCate: Int,
    val nameCate: String,
    val image: String
)