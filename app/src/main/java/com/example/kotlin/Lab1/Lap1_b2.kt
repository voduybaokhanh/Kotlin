package com.example.kotlin.Lab1

fun main() {
    val phiThueBao = 75000 // Phí thuê bao cố định

    print("Nhap so phut da su dung: ")
    val soPhut = readLine()!!.toInt()

    var tongTien = phiThueBao

    tongTien += when {
        soPhut <= 50 -> {
            soPhut * 700
        }

        soPhut <= 100 -> {
            50 * 700 + (soPhut - 50) * 500
        }

        else -> {
            50 * 700 + 50 * 500 + (soPhut - 100) * 300
        }
    }

    println("Tong cuoc dien thoai: $tongTien VND")
}
