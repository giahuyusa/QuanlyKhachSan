CREATE DATABASE KhachSan7;
USE KhachSan7;


-- Bảng Nhân viên
CREATE TABLE NhanVien(
    ID INT IDENTITY(1,1),
    MaNV NVARCHAR(7) PRIMARY KEY DEFAULT 'TEMPnv',
    HoTen NVARCHAR(30) NOT NULL,
    VaiTro BIT NOT NULL DEFAULT 0,
    GioiTinh BIT NOT NULL DEFAULT 1,
    DiaChi NVARCHAR(100) NOT NULL,
    SDT NVARCHAR(10) NOT NULL,
    Luong FLOAT NOT NULL,
    MatKhau NVARCHAR(30) NOT NULL
);

-- Bảng Loại phòng
CREATE TABLE LoaiPhong(
    MaLP NVARCHAR(5) NOT NULL PRIMARY KEY,
    TenLoaiPhong NVARCHAR(30) NOT NULL
);

-- Bảng Phòng
CREATE TABLE Phong(
    MaPhong NVARCHAR(5) NOT NULL PRIMARY KEY,
    MaLP NVARCHAR(5) NOT NULL,
    MoTa NVARCHAR(100) NOT NULL,
    DonGia FLOAT NOT NULL CHECK (DonGia >= 0),
    FOREIGN KEY (MaLP) REFERENCES LoaiPhong(MaLP)
);

-- Bảng Dịch vụ
CREATE TABLE DichVu(
    MaDV NVARCHAR(5) NOT NULL PRIMARY KEY,
    TenDV NVARCHAR(30) NOT NULL,
    DonGia FLOAT NOT NULL CHECK (DonGia >= 0),
    MoTa NVARCHAR(100) NOT NULL
);

-- Bảng Khách hàng

CREATE TABLE KhachHang(
    ID INT IDENTITY(1,1),
    MaKH NVARCHAR(5) PRIMARY KEY DEFAULT 'TEMP',
    HoTen NVARCHAR(30) NOT NULL,
    CCCD NVARCHAR(12) NOT NULL,
    SDT NVARCHAR(10) NOT NULL,
    DiaChi NVARCHAR(50) NOT NULL,
    GioiTinh BIT DEFAULT 1
);

-- Bảng Phiếu đặt phòng
CREATE TABLE PhieuDatPhong(
	ID INT IDENTITY(1,1),
    MaPDP NVARCHAR(7) PRIMARY KEY DEFAULT 'TEMPpdp',
    MaKH NVARCHAR(5) NOT NULL,
    NgayDatPhong DATETIME2 NOT NULL DEFAULT GETDATE(),
    FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH) ON DELETE NO ACTION ON UPDATE CASCADE,
);


-- Bảng Phiếu dịch vụ
CREATE TABLE PhieuDichVu(
    MaPDV INT PRIMARY KEY IDENTITY(1,1),
	MaPhong NVARCHAR(5) NOT NULL,
	MaPDP NVARCHAR(7) NOT NULL,
	NgaySuDung DATETIME2 NOT NULL DEFAULT GETDATE(),
	FOREIGN KEY (MaPhong) REFERENCES Phong(MaPhong) ON DELETE NO ACTION ON UPDATE CASCADE,
	FOREIGN KEY (MaPDP) REFERENCES PhieuDatPhong(MaPDP) ON DELETE NO ACTION ON UPDATE CASCADE,
);

-- Bảng Phiếu hóa đơn
CREATE TABLE PhieuHoaDon(
    MaPHD INT PRIMARY KEY IDENTITY(1,1),
    MaNV NVARCHAR(7) NOT NULL,
    MaPDP NVARCHAR(7) NOT NULL,
	MaPhong NVARCHAR(5) NOT NULL,
    NgayLap DATETIME2 NOT NULL DEFAULT GETDATE(),
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV) ON DELETE NO ACTION ON UPDATE CASCADE,
	FOREIGN KEY (MaPDP) REFERENCES PhieuDatPhong(MaPDP) ON DELETE NO ACTION ON UPDATE CASCADE,
	FOREIGN KEY (MaPhong) REFERENCES Phong(MaPhong) ON DELETE NO ACTION ON UPDATE CASCADE,
);

-- Bảng ChiTietPhieuDatPhong
CREATE TABLE ChiTietPhieuDatPhong (
	MaPhong NVARCHAR(5) NOT NULL,
	MaPDP NVARCHAR(7) NOT NULL,
	NgayNhanPhong DATETIME2 NOT NULL DEFAULT GETDATE(),
	NgayTraPhong DATETIME2 NOT NULL,
	TinhTrang NVARCHAR(30) NOT NULL DEFAULT N'Chưa Thanh toán',
	PRIMARY KEY (MaPhong, MaPDP),
	FOREIGN KEY (MaPhong) REFERENCES Phong(MaPhong) ON DELETE NO ACTION ON UPDATE CASCADE,
	FOREIGN KEY (MaPDP) REFERENCES PhieuDatPhong(MaPDP) ON DELETE NO ACTION ON UPDATE CASCADE,
);

-- Bảng ChiTietPhieuDichVu
CREATE TABLE ChiTietPhieuDichVu(
	MaPDV INT NOT NULL,
	MaDV NVARCHAR(5) NOT NULL,
	SoLanSuDung INT NOT NULL,
	NgaySuDung DATETIME2 NOT NULL DEFAULT GETDATE(),
	PRIMARY KEY (MaPDV, MaDV),
	FOREIGN KEY (MaPDV) REFERENCES PhieuDichVu(MaPDV) ON DELETE NO ACTION ON UPDATE CASCADE,
	FOREIGN KEY (MaDV) REFERENCES DichVu(MaDV) ON DELETE NO ACTION ON UPDATE CASCADE,
);

INSERT INTO NhanVien(MaNV, HoTen, VaiTro, GioiTinh, DiaChi, SDT, Luong, MatKhau) 
VALUES
('PS42080', N'Phạm Nguyễn Duy Phước', 0, 1, N'Phạm Văn Đồng, Bình Chánh, Hồ Chí Minh', '0397093580', 1100, '0320'),
('PS42088', N'Trần Nguyễn Gia Huy', 0, 1, N'Phạm Văn Đồng, Bình Thạnh, Hồ Chí Minh', '0329571615', 1500, '0420'),
('PS42184', N'Trần Đức Hiền', 0, 1, N'Phường 5, Gò Vấp, Hồ Chí Minh', '0812529537', 1000, '2005'),
('PS42215', N'Phạm Hữu Ý', 1, 1, N'Nguyễn Ảnh Thủ, Quận 12, Hồ Chí Minh', '0987770530', 1300, '2003'),
('PS42218', N'Nguyễn Hoàng Tuấn Anh', 1, 1, N'Lê Thị Hà, Hóc Môn, Hồ Chí Minh', '0377518992', 1200, '0520');


-- Thêm dữ liệu vào bảng LoaiPhong
INSERT INTO LoaiPhong (MaLP, TenLoaiPhong)
VALUES 
('LP001', N'Phòng đơn'),
('LP002', N'Phòng đôi'),
('LP003', N'Phòng VIP'),
('LP004', N'Phòng gia đình'),
('LP005', N'Phòng suite'),
('LP006', N'Phòng tiêu chuẩn'),
('LP007', N'Phòng deluxe'),
('LP008', N'Phòng superior'),
('LP009', N'Phòng hạng sang'),
('LP010', N'Phòng penthouse');

-- Thêm dữ liệu vào bảng Phong
INSERT INTO Phong (MaPhong, MaLP, MoTa, DonGia)
VALUES 
('P001', 'LP001', N'Phòng đơn, diện tích 20m2, có TV và điều hòa', 500000),
('P002', 'LP002', N'Phòng đôi, diện tích 30m2, có TV, điều hòa và minibar', 800000),
('P003', 'LP003', N'Phòng VIP, diện tích 40m2, có TV, điều hòa, minibar và phòng tắm riêng', 1200000),
('P004', 'LP004', N'Phòng gia đình, diện tích 50m2, có TV, điều hòa, minibar và phòng tắm riêng', 1500000),
('P005', 'LP005', N'Phòng suite, diện tích 60m2, có TV, điều hòa, minibar và phòng tắm riêng', 1800000),
('P006', 'LP006', N'Phòng tiêu chuẩn, diện tích 25m2, có TV và điều hòa', 550000),
('P007', 'LP007', N'Phòng deluxe, diện tích 35m2, có TV, điều hòa, minibar và phòng tắm riêng', 850000),
('P008', 'LP008', N'Phòng superior, diện tích 45m2, có TV, điều hòa, minibar và phòng tắm riêng', 1300000),
('P009', 'LP009', N'Phòng hạng sang, diện tích 55m2, có TV, điều hòa, minibar và phòng tắm riêng', 1600000),
('P010', 'LP010', N'Phòng penthouse, diện tích 70m2, có TV, điều hòa, minibar và phòng tắm riêng', 2000000);

-- Thêm dữ liệu vào bảng DichVu
INSERT INTO DichVu (MaDV, TenDV, DonGia, MoTa)
VALUES 
('DV001', N'Giặt ủi', 50000, N'Giặt và ủi quần áo'),
('DV002', N'Bữa sáng', 100000, N'Bữa sáng buffet'),
('DV003', N'Đưa đón sân bay', 200000, N'Dịch vụ đưa đón từ sân bay đến khách sạn và ngược lại'),
('DV004', N'Spa', 300000, N'Dịch vụ spa và massage thư giãn'),
('DV005', N'Bể bơi', 150000, N'Sử dụng bể bơi của khách sạn'),
('DV006', N'Phòng gym', 120000, N'Sử dụng phòng gym của khách sạn'),
('DV007', N'Nhà hàng', 400000, N'Dịch vụ ăn uống tại nhà hàng của khách sạn'),
('DV008', N'Bar', 350000, N'Dịch vụ tại quầy bar của khách sạn'),
('DV009', N'Thẩm mỹ viện', 500000, N'Dịch vụ thẩm mỹ và làm đẹp'),
('DV010', N'Karaoke', 250000, N'Dịch vụ karaoke');

-- Thêm dữ liệu vào bảng KhachHang
INSERT INTO KhachHang (MaKH, HoTen, CCCD, SDT, DiaChi, GioiTinh)
VALUES 
('KH001', N'Trần Văn Công', '123456789012', '0912345678', N'789 Đường Nam Kỳ Khởi Nghĩa, Quận 3, TP. HCM', 1),
('KH002', N'Phạm Thị Đông', '098765432109', '0987654321', N'012 Đường Lê Hồng Phong, Quận 10, TP. HCM', 0),
('KH003', N'Nguyễn Văn Toàn', '234567890123', '0912345679', N'345 Đường Điện Biên Phủ, Quận 10, TP. HCM', 1),
('KH004', N'Hoàng Thị Thủy Tiên', '345678901234', '0987654322', N'678 Đường Phan Xích Long, Quận Phú Nhuận, TP. HCM', 0),
('KH005', N'Lê Văn Giang', '456789012345', '0912345680', N'910 Đường Nguyễn Văn Trỗi, Quận Phú Nhuận, TP. HCM', 1),
('KH006', N'Vũ Thị Hồng Ngọc', '567890123456', '0987654323', N'112 Đường Trường Sa, Quận Bình Thạnh, TP. HCM', 0),
('KH007', N'Đặng Văn Tài', '678901234567', '0912345681', N'213 Đường Hoàng Văn Thụ, Quận Tân Bình, TP. HCM', 1),
('KH008', N'Ngô Thị Kim Yến', '789012345678', '0987654324', N'314 Đường Cộng Hòa, Quận Tân Bình, TP. HCM', 0),
('KH009', N'Bùi Văn Lộc', '890123456789', '0912345682', N'415 Đường Võ Thị Sáu, Quận 3, TP. HCM', 1),
('KH010', N'Phan Thị Minh Thư', '901234567890', '0987654325', N'516 Đường Cách Mạng Tháng 8, Quận 3, TP. HCM', 0);


-- Thêm dữ liệu vào bảng PhieuDatPhong
INSERT INTO PhieuDatPhong (MaPDP, MaKH, NgayDatPhong)
VALUES 
('PDP0001', 'KH001', '2024-07-24 12:00:00'),
('PDP0002', 'KH002', '2024-07-25 14:00:00'),
('PDP0003', 'KH003', '2024-07-26 16:00:00'),
('PDP0004', 'KH004', '2024-07-27 18:00:00'),
('PDP0005', 'KH005', '2024-07-28 20:00:00'),
('PDP0006', 'KH006', '2024-07-29 22:00:00'),
('PDP0007', 'KH007', '2024-07-30 08:00:00'),
('PDP0008', 'KH008', '2024-07-31 10:00:00'),
('PDP0009', 'KH009', '2024-08-01 12:00:00'),
('PDP0010', 'KH010', '2024-08-02 14:00:00');

-- Thêm dữ liệu vào bảng PhieuDichVu
INSERT INTO PhieuDichVu (MaPhong, MaPDP, NgaySuDung)
VALUES 
('P001', 'PDP0001', '2024-07-25 09:00:00'),
('P002', 'PDP0002', '2024-07-26 10:00:00'),
('P003', 'PDP0003', '2024-07-27 11:00:00'),
('P004', 'PDP0004', '2024-07-28 12:00:00'),
('P005', 'PDP0005', '2024-07-29 13:00:00'),
('P006', 'PDP0006', '2024-07-30 14:00:00'),
('P007', 'PDP0007', '2024-07-31 15:00:00'),
('P008', 'PDP0008', '2024-08-01 16:00:00'),
('P009', 'PDP0009', '2024-08-02 17:00:00'),
('P010', 'PDP0010', '2024-08-03 18:00:00');


INSERT INTO PhieuHoaDon (MaNV, MaPDP, MaPhong, NgayLap)
VALUES 
-- Phiếu đặt phòng PDP0001 do nhân viên PS42218 lập, đặt 3 phòng P001, P002, P003
('PS42218', 'PDP0001', 'P001', '2024-07-25 18:00:00'),
('PS42218', 'PDP0001', 'P002', '2024-07-25 18:00:00'),
('PS42218', 'PDP0001', 'P003', '2024-07-25 18:00:00'),

-- Phiếu đặt phòng PDP0002 do nhân viên PS42184 lập, đặt 2 phòng P002, P004
('PS42184', 'PDP0002', 'P002', '2024-07-26 19:00:00'),
('PS42184', 'PDP0002', 'P004', '2024-07-26 19:00:00'),

-- Phiếu đặt phòng PDP0003 do nhân viên PS42080 lập, đặt 2 phòng P003, P005
('PS42080', 'PDP0003', 'P003', '2024-07-27 20:00:00'),
('PS42080', 'PDP0003', 'P005', '2024-07-27 20:00:00'),

-- Phiếu đặt phòng PDP0004 do nhân viên PS42215 lập, đặt 2 phòng P004, P006
('PS42215', 'PDP0004', 'P004', '2024-07-28 21:00:00'),
('PS42215', 'PDP0004', 'P006', '2024-07-28 21:00:00'),

-- Phiếu đặt phòng PDP0005 do nhân viên PS42088 lập, đặt 2 phòng P005, P007
('PS42088', 'PDP0005', 'P005', '2024-07-29 22:00:00'),
('PS42088', 'PDP0005', 'P007', '2024-07-29 22:00:00'),

-- Phiếu đặt phòng PDP0006 do nhân viên PS42218 lập, đặt 2 phòng P006, P008
('PS42218', 'PDP0006', 'P006', '2024-07-30 23:00:00'),
('PS42218', 'PDP0006', 'P008', '2024-07-30 23:00:00'),

-- Phiếu đặt phòng PDP0007 do nhân viên PS42184 lập, đặt 2 phòng P007, P009
('PS42184', 'PDP0007', 'P007', '2024-07-31 08:00:00'),
('PS42184', 'PDP0007', 'P009', '2024-07-31 08:00:00'),

-- Phiếu đặt phòng PDP0008 do nhân viên PS42080 lập, đặt 2 phòng P008, P010
('PS42080', 'PDP0008', 'P008', '2024-08-01 09:00:00'),
('PS42080', 'PDP0008', 'P010', '2024-08-01 09:00:00'),

-- Phiếu đặt phòng PDP0009 do nhân viên PS42215 lập, đặt 2 phòng P009, P001
('PS42215', 'PDP0009', 'P009', '2024-08-02 10:00:00'),
('PS42215', 'PDP0009', 'P001', '2024-08-02 10:00:00'),

-- Phiếu đặt phòng PDP0010 do nhân viên PS42088 lập, đặt 2 phòng P010, P002
('PS42088', 'PDP0010', 'P010', '2024-08-03 11:00:00'),
('PS42088', 'PDP0010', 'P002', '2024-08-03 11:00:00');


INSERT INTO ChiTietPhieuDatPhong (MaPhong, MaPDP, NgayNhanPhong, NgayTraPhong, TinhTrang)
VALUES 
-- PDP0001 đặt nhiều phòng
('P001', 'PDP0001', '2024-07-25 14:00:00', '2024-07-26 12:00:00', N'Thanh toán'),
('P002', 'PDP0001', '2024-07-25 14:30:00', '2024-07-26 12:30:00', N'Thanh toán'),
-- PDP0002 đặt nhiều phòng
('P002', 'PDP0002', '2024-07-26 14:00:00', '2024-07-27 12:00:00', N'Chưa Thanh toán'),
('P003', 'PDP0002', '2024-07-26 14:30:00', '2024-07-27 12:30:00', N'Chưa Thanh toán'),
-- PDP0003 đặt nhiều phòng
('P003', 'PDP0003', '2024-07-27 14:00:00', '2024-07-28 12:00:00', N'Thanh toán'),
('P004', 'PDP0003', '2024-07-27 14:30:00', '2024-07-28 12:30:00', N'Thanh toán'),
-- PDP0004 đặt nhiều phòng
('P004', 'PDP0004', '2024-07-28 14:00:00', '2024-07-29 12:00:00', N'Chưa Thanh toán'),
('P005', 'PDP0004', '2024-07-28 14:30:00', '2024-07-29 12:30:00', N'Chưa Thanh toán'),
-- PDP0005 đặt nhiều phòng
('P005', 'PDP0005', '2024-07-29 14:00:00', '2024-07-30 12:00:00', N'Chưa Thanh toán'),
('P006', 'PDP0005', '2024-07-29 14:30:00', '2024-07-30 12:30:00', N'Chưa Thanh toán'),
-- PDP0006 đặt nhiều phòng
('P006', 'PDP0006', '2024-07-30 14:00:00', '2024-07-31 12:00:00', N'Thanh toán'),
('P007', 'PDP0006', '2024-07-30 14:30:00', '2024-07-31 12:30:00', N'Thanh toán'),
-- PDP0007 đặt nhiều phòng
('P007', 'PDP0007', '2024-07-31 14:00:00', '2024-08-01 12:00:00', N'Chưa Thanh toán'),
('P008', 'PDP0007', '2024-07-31 14:30:00', '2024-08-01 12:30:00', N'Chưa Thanh toán'),
-- PDP0008 đặt nhiều phòng
('P008', 'PDP0008', '2024-08-01 14:00:00', '2024-08-02 12:00:00', N'Thanh toán'),
('P009', 'PDP0008', '2024-08-01 14:30:00', '2024-08-02 12:30:00', N'Thanh toán'),
-- PDP0009 đặt nhiều phòng
('P009', 'PDP0009', '2024-08-02 14:00:00', '2024-08-03 12:00:00', N'Chưa Thanh toán'),
('P010', 'PDP0009', '2024-08-02 14:30:00', '2024-08-03 12:30:00', N'Chưa Thanh toán'),
-- PDP0010 đặt nhiều phòng
('P010', 'PDP0010', '2024-08-03 14:00:00', '2024-08-04 12:00:00', N'Thanh toán'),
('P001', 'PDP0010', '2024-08-03 14:30:00', '2024-08-04 12:30:00', N'Thanh toán');


INSERT INTO ChiTietPhieuDichVu (MaPDV, MaDV, SoLanSuDung, NgaySuDung)
VALUES 
-- Phòng P001 sử dụng nhiều dịch vụ
(1, 'DV001', 2, '2024-07-25 09:00:00'),
(1, 'DV002', 1, '2024-07-25 09:15:00'),
(1, 'DV003', 1, '2024-07-25 09:30:00'),
-- Phòng P002 sử dụng nhiều dịch vụ
(2, 'DV002', 1, '2024-07-26 10:00:00'),
(2, 'DV004', 1, '2024-07-26 10:30:00'),
(2, 'DV005', 2, '2024-07-26 11:00:00'),
-- Phòng P003 sử dụng nhiều dịch vụ
(3, 'DV003', 1, '2024-07-27 11:00:00'),
(3, 'DV006', 1, '2024-07-27 11:15:00'),
(3, 'DV007', 1, '2024-07-27 11:30:00'),
-- Phòng P004 sử dụng nhiều dịch vụ
(4, 'DV004', 1, '2024-07-28 12:00:00'),
(4, 'DV008', 1, '2024-07-28 12:15:00'),
(4, 'DV009', 1, '2024-07-28 12:30:00'),
-- Phòng P005 sử dụng nhiều dịch vụ
(5, 'DV005', 2, '2024-07-29 13:00:00'),
(5, 'DV010', 2, '2024-07-29 13:15:00'),
(5, 'DV001', 2, '2024-07-29 13:30:00'),
-- Phòng P006 sử dụng nhiều dịch vụ
(6, 'DV006', 1, '2024-07-30 14:00:00'),
(6, 'DV002', 1, '2024-07-30 14:15:00'),
(6, 'DV003', 1, '2024-07-30 14:30:00'),
-- Phòng P007 sử dụng nhiều dịch vụ
(7, 'DV007', 1, '2024-07-31 15:00:00'),
(7, 'DV004', 1, '2024-07-31 15:15:00'),
(7, 'DV005', 2, '2024-07-31 15:30:00'),
-- Phòng P008 sử dụng nhiều dịch vụ
(8, 'DV008', 1, '2024-08-01 16:00:00'),
(8, 'DV003', 1, '2024-08-01 16:15:00'),
(8, 'DV009', 1, '2024-08-01 16:30:00'),
-- Phòng P009 sử dụng nhiều dịch vụ
(9, 'DV009', 1, '2024-08-02 17:00:00'),
(9, 'DV002', 1, '2024-08-02 17:15:00'),
(9, 'DV001', 2, '2024-08-02 17:30:00'),
-- Phòng P010 sử dụng nhiều dịch vụ
(10, 'DV010', 2, '2024-08-03 18:00:00'),
(10, 'DV004', 1, '2024-08-03 18:15:00'),
(10, 'DV007', 1, '2024-08-03 18:30:00');


-- store procedure
Drop Procedure sp_ChiTietPhieuDatPhongDa1
CREATE PROC sp_ChiTietPhieuDatPhongDa1(@maPhong NVARCHAR(4), @maPhieuDatPhong NVARCHAR(7))
AS
BEGIN
		SELECT		
			
            ct.MaPhong, 
			p.DonGia, 
			ct.NgayNhanPhong, 
			ct.NgayTraPhong,
			DATEDIFF(DAY,ct.NgayNhanPhong,ct.NgayTraPhong) * p.DonGia as 'ThanhTien',
			ct.MaPDP,
			kh.HoTen,
            kh.SDT,
			hd.MaNV,
			hd.MaPHD
		FROM KhachHang kh 
			INNER JOIN PhieuDatPhong pd ON kh.MaKH = pd.MaKH 
			INNER JOIN ChiTietPhieuDatPhong ct ON pd.MaPDP = ct.MaPDP
			INNER JOIN PhieuHoaDon hd ON ct.MaPDP = hd.MaPDP AND ct.MaPhong = hd.MaPhong
			INNER JOIN Phong p ON ct.MaPhong = p.MaPhong
		WHERE ct.MaPDP = @maPhieuDatPhong AND ct.MaPhong = @maPhong
END;


CREATE PROC sp_ChiTietPhieuDichVu(@maPhong NVARCHAR(4), @maPhieuDatPhong NVARCHAR(7))
AS 
BEGIN
    -- Bắt đầu một giao dịch
    BEGIN TRANSACTION;

    BEGIN TRY
        -- Lựa chọn các chi tiết phiếu dịch vụ dựa trên mã phòng và mã phiếu đặt phòng
        SELECT 
            dv.TenDV, 
            dv.DonGia, 
            ct.SoLanSuDung, 
            (dv.DonGia * ct.SoLanSuDung) AS ThanhTien
        FROM PhieuDichVu p 
        INNER JOIN ChiTietPhieuDichVu ct ON p.MaPDV = ct.MaPDV 
        INNER JOIN DichVu dv ON ct.MaDV = dv.MaDV
        WHERE p.MaPhong = @maPhong AND p.MaPDP = @maPhieuDatPhong;

        -- Hoàn tất giao dịch nếu không có lỗi
        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        -- Rollback nếu có lỗi
        ROLLBACK TRANSACTION;

        -- Trả lại lỗi chi tiết
        DECLARE @ErrorMessage NVARCHAR(4000);
        DECLARE @ErrorSeverity INT;
        DECLARE @ErrorState INT;

        SELECT 
            @ErrorMessage = ERROR_MESSAGE(),
            @ErrorSeverity = ERROR_SEVERITY(),
            @ErrorState = ERROR_STATE();

        -- Ném ra lỗi
        RAISERROR (@ErrorMessage, @ErrorSeverity, @ErrorState);
    END CATCH
END;

-- trigger

CREATE TRIGGER trAfterInsertNhanVien
ON NhanVien
AFTER INSERT
AS
BEGIN
    DECLARE @ID INT;
    DECLARE @newMaNV NVARCHAR(10); -- Adjust length if needed

    -- Retrieve the ID just inserted
    SELECT @ID = ID FROM INSERTED;

    -- Generate the new MaNV with the ID just received
    SET @newMaNV = 'PS' + RIGHT('00000' + CAST(42219 + @ID AS NVARCHAR), 5);

    -- Update the MaNV in the NhanVien table
    UPDATE NhanVien 
    SET MaNV = @newMaNV
    WHERE ID = @ID;
END;

--

go

CREATE TRIGGER trAfterInsertKhachHang
ON KhachHang
AFTER INSERT
AS
BEGIN
	DECLARE @ID INT;
	DECLARE @newMaKH NVARCHAR(10); --Adjust lenght if needed

	-- Retrieve the ID just inserted
	SELECT @ID = ID FROM inserted;

	-- Generate the new MaKH with the ID just received
	SET @newMaKH = 'KH' + RIGHT('00000' + CAST(010 + @ID AS NVARCHAR),3);

	-- Update the MaNV in the NhanVien table
	UPDATE KhachHang
	SET MaKH = @newMaKH
	WHERE ID = @ID;
END;

go

CREATE Trigger trAfterInsertPhieuDatPhong
ON PhieuDatPhong
AFTER INSERT
AS
BEGIN
	DECLARE @ID INT;
	DECLARE @newMaPhieuDatPhong NVARCHAR(10);

	SELECT @ID = ID FROM inserted;
	SET @newMaPhieuDatPhong = 'PDP' + RIGHT('00000' + CAST(0010 + @ID AS NVARCHAR),4)

	UPDATE PhieuDatPhong
	SET MaPDP = @newMaPhieuDatPhong
	WHERE ID = @ID;
END;


-- Tính doanhthuPhong
CREATE PROCEDURE sp_CalculateDoanhThuPhong
    @Month INT,
    @Year INT
AS
BEGIN
    -- Tính tổng doanh thu theo phòng
    SELECT
        SUM(DATEDIFF(DAY, ct.NgayNhanPhong, ct.NgayTraPhong) * p.DonGia) AS TongDoanhThu
    FROM ChiTietPhieuDatPhong ct
    JOIN Phong p ON ct.MaPhong = p.MaPhong
    WHERE YEAR(ct.NgayNhanPhong) = @Year
    AND MONTH(ct.NgayNhanPhong) = @Month
    AND ct.TinhTrang = N'Thanh toán';
END;
--tính doanhthudicVu
CREATE PROCEDURE sp_TinhDoanhThuDichVu
    @Month INT,
    @Year INT
AS
BEGIN
    -- Tính doanh thu dịch vụ cho tháng và năm được chỉ định
    SELECT 
        SUM(D.DonGia * C.SoLanSuDung) AS DoanhThuDichVu
    FROM 
        ChiTietPhieuDichVu C
    JOIN 
        DichVu D ON C.MaDV = D.MaDV
    JOIN 
        PhieuDichVu P ON C.MaPDV = P.MaPDV
    WHERE 
        YEAR(P.NgaySuDung) = @Year AND MONTH(P.NgaySuDung) = @Month;
END;
 EXEC sp_TinhDoanhThuDichVu @Year = 2024, @Month = 7;
--Tính số lượng Phòng đặt
CREATE PROCEDURE soluongphongDat
      @Month INT,
    @Year INT
AS
BEGIN
    SELECT COUNT(*) AS SoLuongPhongDat
    FROM PhieuDatPhong
    WHERE MONTH(NgayDatPhong) = @Month AND YEAR(NgayDatPhong) = @Year;
END;
EXEC soluongphongDat @Year = 2024, @Month = 7;

select getDate ();