package com.example.kotlin.Lab2

import kotlin.math.pow

fun main() {
    print("Nhap so nguyen n > 0: ")
    val n = readLine()!!.toInt()

    // a. Xuat ra cac so tu 1 den n
    println("a. Cac so tu 1 den $n:")
    for (i in 1..n) {
        print("$i ")
    }
    println()

    // b. Xuat ra cac so chan tu 1 den n
    println("b. Cac so chan tu 1 den $n:")
    for (i in 1..n) {
        if (i % 2 == 0) print("$i ")
    }
    println()

    // c. Xuat ra cac so le khong chia het cho 3 tu 1 den n
    println("c. Cac so le khong chia het cho 3 tu 1 den $n:")
    for (i in 1..n) {
        if (i % 2 != 0 && i % 3 != 0) print("$i ")
    }
    println()

    // d. Tinh cac bieu thuc
    // S1 = 1 + 2 + ... + n
    val s1 = (1..n).sum()
    println("S1 = $s1")

    // S2 = -1 + 2 - 3 + 4 - ... + (-1)^n * n
    var s2 = 0
    for (i in 1..n) {
        s2 += if (i % 2 == 0) i else -i
    }
    println("S2 = $s2")

    // S3 = 1/2 + 2/3 + 3/4 + ... + n/(n+1)
    var s3 = 0.0
    for (i in 1..n) {
        s3 += i.toDouble() / (i + 1)
    }
    println("S3 = $s3")

    // S4 = x^n (x la so thuc)
    print("Nhap so thuc x: ")
    val x = readLine()!!.toDouble()
    val s4 = x.pow(n)
    println("S4 = $s4")

    // e. Tinh tong cac chu so cua n
    var sumDigits = 0
    var temp = n
    while (temp > 0) {
        sumDigits += temp % 10
        temp /= 10
    }
    println("Tong cac chu so cua $n = $sumDigits")
}
