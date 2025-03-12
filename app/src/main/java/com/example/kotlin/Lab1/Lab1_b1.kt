package com.example.kotlin.Lab1

fun main() {
    print("Nhap diem kiem tra 15 phut: ")
    val diem15Phut = readLine()!!.toDouble()

    print("Nhap diem giua ky: ")
    val diemGiuaKy = readLine()!!.toDouble()

    print("Nhap diem cuoi kỳ: ")
    val diemCuoiKy = readLine()!!.toDouble()

    // Tính điểm trung bình
    val dtb = (diem15Phut + diemGiuaKy * 2 + diemCuoiKy * 3) / 6

    // Xác định loại học lực
    val xepLoai = when {
        dtb >= 9.5 -> "Xuat sac"
        dtb in 8.0..9.4 -> "Gioi"
        dtb in 6.0..7.9 -> "Kha"
        dtb in 4.0..5.9 -> "Trung binh"
        else -> "Yếu"
    }

    println("Diem trung binh: %.2f".format(dtb))
    println("Xep loai hoc luc: $xepLoai")
}
