package com.khachsan.entity;

public class Phong {
    private String maPhong;
    private String maLP;
    private String mota;
    private Double donGia;
public String toString(){
    return this.maPhong;
    // lấy tên để hiển thị trong JComboBox
}
    public Phong() {
    }

    public Phong(String maPhong, String maLP, String mota, Double donGia) {
        this.maPhong = maPhong;
        this.maLP = maLP;
        this.mota = mota;
        this.donGia = donGia;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaLP() {
        return maLP;
    }

    public void setMaLP(String maLP) {
        this.maLP = maLP;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }
}
