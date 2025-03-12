package com.example.kotlin.Lab2

fun main() {
    while (true) {
        var nam: Int

        // Nhập năm, kiểm tra năm hợp lệ (>= 0)
        while (true) {
            print("Nhap nam (>= 0): ")
            nam = readLine()!!.toInt()
            if (nam >= 0) break
            println("Nam khong hop le, vui long nhap lai!")
        }

        // Kiểm tra năm nhuận
        val laNamNhuan = (nam % 4 == 0 && nam % 100 != 0) || (nam % 400 == 0)

        // In kết quả
        if (laNamNhuan) {
            println("Nam $nam la nam nhuan")
        } else {
            println("Nam $nam khong phai la nam nhuan")
        }

        // Hỏi người dùng có muốn tiếp tục hay không
        print("Ban co muon tiep tuc khong? (Y/N): ")
        val tiepTuc = readLine()!!.uppercase()
        if (tiepTuc != "Y") {
            println("Ket thuc chuong trinh. Tam biet!")
            break
        }
    }
}
