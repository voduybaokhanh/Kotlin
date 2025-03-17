package com.example.kotlin.DiemCong

fun main() {
    print("Nhap so nguyen duong n: ")
    val n = readLine()!!.toInt()
    var tongUoc = 0

    for (i in 1 until n) {
        if (n % i == 0) {
            tongUoc += i
        }
    }

    if (tongUoc == n) {
        println("$n la so hoan thien")
    } else {
        println("$n khong phai la so hoan thien")
    }
}