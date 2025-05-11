# ASM - Furniture Shopping App

## Tổng quan

ASM là một ứng dụng mua sắm nội thất được phát triển bằng Kotlin và Jetpack Compose. Ứng dụng này
cung cấp trải nghiệm mua sắm trực tuyến đầy đủ với các tính năng như đăng nhập, đăng ký, duyệt sản
phẩm, giỏ hàng, thanh toán và quản lý tài khoản.

## Cấu trúc dự án

Dự án được tổ chức thành các package sau:

```
com.example.kotlin.ASM/
├── data/                  # Quản lý dữ liệu ứng dụng
│   └── CartDataStore.kt   # Lưu trữ và quản lý dữ liệu giỏ hàng
├── dataClass/             # Các lớp dữ liệu
│   └── DataClass.kt       # Định nghĩa các model dữ liệu (Account, LoginRequest, v.v.)
├── navigation/            # Quản lý điều hướng
│   └── LocalNavController.kt # Cung cấp NavController cho toàn bộ ứng dụng
├── screen/                # Các màn hình UI
│   ├── Boarding.kt        # Màn hình giới thiệu
│   ├── BottomNavigation.kt # Thanh điều hướng dưới cùng
│   ├── CartScreen.kt      # Màn hình giỏ hàng
│   ├── CheckOutScreen.kt  # Màn hình thanh toán
│   ├── CongratsScreen.kt  # Màn hình xác nhận đặt hàng thành công
│   ├── HomeNavigationScreen.kt # Màn hình điều hướng chính
│   ├── HomeScreen.kt      # Màn hình trang chủ
│   ├── Login.kt           # Màn hình đăng nhập
│   ├── NotificationScreen.kt # Màn hình thông báo
│   ├── ProductDetailScreen.kt # Màn hình chi tiết sản phẩm
│   ├── ProfileScreen.kt   # Màn hình hồ sơ người dùng
│   └── Signup.kt          # Màn hình đăng ký
├── services/              # Dịch vụ API
│   └── Services.kt        # Cấu hình và định nghĩa API
└── viewModel/             # Các ViewModel
    ├── ViewModelLogin.kt  # ViewModel cho màn hình đăng nhập
    └── ViewModelSignup.kt # ViewModel cho màn hình đăng ký
```

## Tính năng chính

### 1. Xác thực người dùng

- **Đăng nhập**: Cho phép người dùng đăng nhập bằng email và mật khẩu
- **Đăng ký**: Cho phép người dùng tạo tài khoản mới
- **Đăng xuất**: Cho phép người dùng đăng xuất khỏi ứng dụng

### 2. Duyệt sản phẩm

- **Trang chủ**: Hiển thị các sản phẩm nổi bật và danh mục
- **Chi tiết sản phẩm**: Hiển thị thông tin chi tiết về sản phẩm
- **Tìm kiếm**: Cho phép người dùng tìm kiếm sản phẩm

### 3. Giỏ hàng và thanh toán

- **Giỏ hàng**: Quản lý các sản phẩm đã thêm vào giỏ hàng
- **Cập nhật số lượng**: Tăng/giảm số lượng sản phẩm trong giỏ hàng
- **Xóa sản phẩm**: Xóa sản phẩm khỏi giỏ hàng
- **Thanh toán**: Hoàn tất quá trình mua hàng
- **Mã giảm giá**: Hỗ trợ nhập mã giảm giá

### 4. Quản lý tài khoản

- **Hồ sơ người dùng**: Xem và cập nhật thông tin cá nhân
- **Thông báo**: Xem các thông báo từ ứng dụng

## Luồng điều hướng

1. **Luồng xác thực**:
    - Màn hình giới thiệu (Boarding) → Đăng nhập (Login) / Đăng ký (Signup) → Trang chủ (Home)

2. **Luồng mua sắm**:
    - Trang chủ (Home) → Chi tiết sản phẩm (ProductDetail) → Giỏ hàng (Cart) → Thanh toán (
      Checkout) → Xác nhận (Congrats)

3. **Điều hướng chính**:
    - Thanh điều hướng dưới cùng cho phép chuyển đổi giữa: Trang chủ, Giỏ hàng, Thông báo và Hồ sơ

## Kiến trúc ứng dụng

Ứng dụng được xây dựng theo mô hình MVVM (Model-View-ViewModel):

- **Model**: Các lớp dữ liệu trong package `dataClass`
- **View**: Các màn hình UI trong package `screen`
- **ViewModel**: Các lớp ViewModel trong package `viewModel`
- **Repository**: Dịch vụ API trong package `services`

## Công nghệ sử dụng

- **Kotlin**: Ngôn ngữ lập trình chính
- **Jetpack Compose**: Framework UI hiện đại dựa trên Kotlin
- **Navigation Compose**: Quản lý điều hướng giữa các màn hình
- **Retrofit**: Thực hiện các cuộc gọi API
- **OkHttp**: Client HTTP cho Retrofit
- **Gson**: Chuyển đổi JSON sang đối tượng Kotlin

## Hướng dẫn sử dụng

### Cài đặt

1. Clone dự án từ repository
2. Mở dự án trong Android Studio
3. Đảm bảo bạn có các phiên bản SDK và các thư viện cần thiết
4. Chạy ứng dụng trên thiết bị hoặc máy ảo

### Cấu hình API

Để kết nối với backend, cập nhật URL trong file `Services.kt`:

```kotlin
private const val BASE_URL = "http://your-api-url/"
```

## Đóng góp

Nếu bạn muốn đóng góp vào dự án, vui lòng tạo pull request hoặc báo cáo các vấn đề trong phần
Issues.

## Tác giả

[Võ Duy Bảo Khánh] - [khanhvo908@gmail.com]

## Giấy phép

Dự án này được cấp phép theo [Loại giấy phép] - xem file LICENSE để biết thêm chi tiết.
