package com.example.kotlin.DiemCong

fun main() {
    print("Nhap mot so nguyen: ")
    val so = readLine()!!
    val daoNguoc = so.reversed()

    if (so == daoNguoc) {
        println("$so la so doi xung")
    } else {
        println("$so khong phai la so doi xung")
    }
}