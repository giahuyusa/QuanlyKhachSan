package com.khachsan.entity;

import java.sql.Timestamp;
import java.util.Date;

public class ChiTietPhieuDichVu {
    private String maDV;
    private int maPhieuDichVu;
    private int solanSuDung;
    private Timestamp ngaySuDung;

    public ChiTietPhieuDichVu() {
    }

    public ChiTietPhieuDichVu(String maDV, int maPhieuDichVu, int solanSuDung, Timestamp ngaySuDung) {
        this.maDV = maDV;
        this.maPhieuDichVu = maPhieuDichVu;
        this.solanSuDung = solanSuDung;
        this.ngaySuDung = ngaySuDung;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public int getMaPhieuDichVu() {
        return maPhieuDichVu;
    }

    public void setMaPhieuDichVu(int maPhieuDichVu) {
        this.maPhieuDichVu = maPhieuDichVu;
    }

    public int getSolanSuDung() {
        return solanSuDung;
    }

    public void setSolanSuDung(int solanSuDung) {
        this.solanSuDung = solanSuDung;
    }

    public Date getNgaySuDung() {
        return ngaySuDung;
    }

    public void setNgaySuDung(Timestamp ngaySuDung) {
        this.ngaySuDung = ngaySuDung;
    }
}
