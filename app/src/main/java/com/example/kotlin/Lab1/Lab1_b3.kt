package com.example.kotlin.Lab1

fun main() {
    var gioBatDau: Int
    var gioKetThuc: Int

    // Nhập giờ bắt đầu và giờ kết thúc
    while (true) {
        print("Nhap gio bat dau (7 - 23): ")
        gioBatDau = readLine()!!.toInt()
        print("Nhap gio ket thuc (7 - 23): ")
        gioKetThuc = readLine()!!.toInt()

        // Kiểm tra giờ hợp lệ
        if (gioBatDau in 7..23 && gioKetThuc in 7..23 && gioBatDau < gioKetThuc) break
        println("Gio nhap khong hop le. Vui long nhap lai!")
    }

    val thoiGianSuDung = gioKetThuc - gioBatDau
    var tongTien = 0

    // Tính tiền cho 2 giờ đầu tiên (20.000 VNĐ)
    if (thoiGianSuDung > 0) {
        val gioDau = minOf(thoiGianSuDung, 2)
        tongTien += gioDau * 20000
    }

    // Tính tiền cho các giờ tiếp theo (nếu có) với giá 10.000 VNĐ/giờ
    if (thoiGianSuDung > 2) {
        val gioTiepTheo = thoiGianSuDung - 2
        tongTien += gioTiepTheo * 10000
    }

    // Giảm giá 15% nếu bắt đầu từ thứ 4 trở đi
    val ngayTrongTuan = 4  // Giả sử là thứ 4 trở đi
    if (ngayTrongTuan >= 4) {
        tongTien = (tongTien * 0.85).toInt()
    }

    // Giảm thêm 10% nếu trong khung giờ Happy Hour (14 - 16)
    if (gioBatDau in 14..15) {
        tongTien = (tongTien * 0.90).toInt()
    }

    println("Tong tien thanh toan: $tongTien VND")
}
