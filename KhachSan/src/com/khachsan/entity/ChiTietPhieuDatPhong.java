package com.khachsan.entity;

import java.sql.Timestamp;
import java.util.Date;

public class ChiTietPhieuDatPhong {
    private String maPhong;
    private String maPhieuDatPhong;
    private Timestamp ngayNhanPhong;
    private Timestamp ngayTraPhong;
    private String TrangThai;

    public ChiTietPhieuDatPhong() {
    }

    public ChiTietPhieuDatPhong(String maPhong, String maPhieuDatPhong, Timestamp ngayNhanPhong, Timestamp ngayTraPhong, String trangThai) {
        this.maPhong = maPhong;
        this.maPhieuDatPhong = maPhieuDatPhong;
        this.ngayNhanPhong = ngayNhanPhong;
        this.ngayTraPhong = ngayTraPhong;
        TrangThai = trangThai;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaPhieuDatPhong() {
        return maPhieuDatPhong;
    }

    public void setMaPhieuDatPhong(String maPhieuDatPhong) {
        this.maPhieuDatPhong = maPhieuDatPhong;
    }

    public Date getNgayNhanPhong() {
        return ngayNhanPhong;
    }

    public void setNgayNhanPhong(Timestamp ngayNhanPhong) {
        this.ngayNhanPhong = ngayNhanPhong;
    }

    public Date getNgayTraPhong() {
        return ngayTraPhong;
    }

    public void setNgayTraPhong(Timestamp ngayTraPhong) {
        this.ngayTraPhong = ngayTraPhong;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}
