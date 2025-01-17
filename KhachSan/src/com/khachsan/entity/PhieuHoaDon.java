package com.khachsan.entity;

import java.sql.Timestamp;
import java.util.Date;

public class PhieuHoaDon {
    private int maPhieuHoaDon;
    private String maNV;
    private String maPhieuDatPhong;
    private String maPhong;
    private Timestamp ngayLap;

    public PhieuHoaDon() {
    }

    public PhieuHoaDon(int maPhieuHoaDon, String maNV, String maPhieuDatPhong, String maPhong, Timestamp ngayLap) {
        this.maPhieuHoaDon = maPhieuHoaDon;
        this.maNV = maNV;
        this.maPhieuDatPhong = maPhieuDatPhong;
        this.maPhong = maPhong;
        this.ngayLap = ngayLap;
    }

    public int getMaPhieuHoaDon() {
        return maPhieuHoaDon;
    }

    public void setMaPhieuHoaDon(int maPhieuHoaDon) {
        this.maPhieuHoaDon = maPhieuHoaDon;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaPhieuDatPhong() {
        return maPhieuDatPhong;
    }

    public void setMaPhieuDatPhong(String maPhieuDatPhong) {
        this.maPhieuDatPhong = maPhieuDatPhong;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Timestamp ngayLap) {
        this.ngayLap = ngayLap;
    }
    
    
}
