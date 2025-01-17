package com.khachsan.entity;

import java.sql.Timestamp;
import java.util.Date;

public class PhieuDatPhong {
    private String maPhieuDatPhong;
    private String maKH;
    private Timestamp ngayDatPhong;

    public PhieuDatPhong() {
        
    }

    public PhieuDatPhong(String maPhieuDatPhong, String maKH, Timestamp ngayDatPhong) {
        this.maPhieuDatPhong = maPhieuDatPhong;
        this.maKH = maKH;
        this.ngayDatPhong = ngayDatPhong;
    }

    public String getMaPhieuDatPhong() {
        return maPhieuDatPhong;
    }

    public void setMaPhieuDatPhong(String maPhieuDatPhong) {
        this.maPhieuDatPhong = maPhieuDatPhong;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public Date getNgayDatPhong() {
        return ngayDatPhong;
    }

    public void setNgayDatPhong(Timestamp ngayDatPhong) {
        this.ngayDatPhong = ngayDatPhong;
    }
}
