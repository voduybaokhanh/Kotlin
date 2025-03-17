package com.example.kotlin.DiemCong

fun main() {
    print("Nhap so nguyen duong n: ")
    val n = readLine()!!.toInt()
    var a = 0
    var b = 1

    print("Day Fibonacci tu 1 den $n: ")
    while (a <= n) {
        print("$a ")
        val temp = a + b
        a = b
        b = temp
    }
    println()
}