package com.khachsan.entity;

import java.sql.Timestamp;
import java.util.Date;

public class PhieuDichVu {
    private int maPhieuDichVu;
    private Timestamp ngaySuDung;
    private String maPhieuDatPhong;
    private String maPhong;

    public PhieuDichVu() {
    }

    public PhieuDichVu(int maPhieuDichVu, Timestamp ngaySuDung, String maPhieuDatPhong, String maPhong) {
        this.maPhieuDichVu = maPhieuDichVu;
        this.ngaySuDung = ngaySuDung;
        this.maPhieuDatPhong = maPhieuDatPhong;
        this.maPhong = maPhong;
    }

    public int getMaPhieuDichVu() {
        return maPhieuDichVu;
    }

    public void setMaPhieuDichVu(int maPhieuDichVu) {
        this.maPhieuDichVu = maPhieuDichVu;
    }

    public Date getNgaySuDung() {
        return ngaySuDung;
    }

    public void setNgaySuDung(Timestamp ngaySuDung) {
        this.ngaySuDung = ngaySuDung;
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
}
