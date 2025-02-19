# Hotel Booking System

Giới thiệu

Hotel Booking System là một ứng dụng đặt phòng khách sạn dành cho lễ tân, được xây dựng bằng Java Swing và Spring Framework. Ứng dụng hỗ trợ quản lý phòng, đặt phòng, xử lý thanh toán và tích hợp tính năng gửi mã OTP qua email khi quên mật khẩu.

Tính năng chính

Quản lý phòng: Thêm, sửa, xóa phòng khách sạn.

Đặt phòng: Xác nhận đặt phòng và cập nhật trạng thái phòng.

Quản lý khách hàng: Lưu thông tin khách hàng và lịch sử đặt phòng.

Thanh toán: Hỗ trợ các phương thức thanh toán khác nhau.

Khôi phục mật khẩu: Gửi mã OTP qua email khi quên mật khẩu.

Báo cáo và thống kê: Hiển thị doanh thu và tình trạng phòng.

Công nghệ sử dụng

Frontend: Java Swing

Backend: Spring Framework (Spring Boot, Spring Security, Spring Data JPA)

Cơ sở dữ liệu: MySQL

Gửi email: JavaMail API

## Tổng quát giao diện
[Giao diện login](./loginView.png)

- Người dùng có thể đăng nhập, đăng kí và quên mật khẩu với chức năng get mã OTP

[Giao diện chính của ứng dụng](./dashboardview.png)

Với các chức năng quản lí phòng, quản lí khách hàngm, quản lí nhân viên(phân quyền ADMIN), thanh toán, thống kê,... Hỗ trợ người dùng trong việc quản lí khách sạn.

[Đặt phòng](./bookingRoom.png)
Click vào đặt phòng, ứng dụng sẽ hiển thị ra form đăng kí phòng cho khách hàng.
[Form đăng kí phòng](./Screenshot%202025-02-19%20173803.png)
Nhập số điện thoại của khách hàng để tìm kiếm khách đã có thông tin ở khách sạn hoặc click vào thêm mới khách hàng. Bấm xác nhận để đặt phòng.

Ngoài ra còn rất nhiều tính năng trong ứng dụng cho người dùng trải nghiệm.

## Các tính năng
- Đăng nhập/Đăng kí/ Quên mật khẩu
- Thêm/Sửa/xóa phòng
- Thanh toán/Thống kê
- Quản lí khách hàng (Thêm/Sửa/Xóa)
- Quản lí nhân viên (Thêm/Sửa/xóa/Phân quyền)
- Thông tin cá nhân
##  Mô hình thiết kế cơ sở dữ liệu

[](./database_diagram.png)

