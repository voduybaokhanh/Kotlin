package com.example.kotlin.Lab2

fun main() {
    print("Nhap thang:  ")
    val thang = readLine()!!.toInt()
    when (thang) {
        1, 2, 3 -> println("Thang $thang la quy 1 trong nam")
        4, 5, 6 -> println("Thang $thang la quy 2 trong nam")
        7, 8, 9 -> println("Thang $thang la quy 3 trong nam")
        10, 11, 12 -> println("Thang $thang la quy 4 trong nam")
        else -> println("Thang khong hop le")
    }
}