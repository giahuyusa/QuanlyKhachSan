package com.khachsan.entity;

public class KhachHang {
    private String maKH;
    private String hoTen;
    private String canCuocCongDan;
    private String soDienThoai;
    private String diaChi;
    private Boolean gioiTinh;

    public KhachHang() {
    }

    public KhachHang(String maKH, String hoTen, String canCuocCongDan, String soDienThoai, String diaChi, Boolean gioiTinh) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.canCuocCongDan = canCuocCongDan;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getCanCuocCongDan() {
        return canCuocCongDan;
    }

    public void setCanCuocCongDan(String canCuocCongDan) {
        this.canCuocCongDan = canCuocCongDan;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
}
