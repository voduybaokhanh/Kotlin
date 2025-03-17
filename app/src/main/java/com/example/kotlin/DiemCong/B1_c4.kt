package com.example.kotlin.DiemCong

fun main() {
    print("Nhap so a: ")
    val a = readLine()!!
    print("Nhap so b: ")
    val b = readLine()!!

    if (a.contains(b)) {
        println("So $b co chua trong so $a")
    } else {
        println("So $b khong co chua trong so $a")
    }
}