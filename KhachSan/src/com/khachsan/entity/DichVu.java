package com.khachsan.entity;

public class DichVu {
    private String maDV;
    private String tenDV;
    private Double donGia;
    private String moTa;

    public DichVu() {
    }

    public DichVu(String maDV, String tenDV, Double donGia, String moTa) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.donGia = donGia;
        this.moTa = moTa;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
