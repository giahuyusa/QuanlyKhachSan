package com.khachsan.entity;

public class LoaiPhong {
    private String maLP;
    private String tenLoaiPhong;

    public LoaiPhong() {
    }
@Override
public String toString(){
    return this.tenLoaiPhong;
    // lấy tên để hiển thị trong JComboBox
}
@Override
public boolean equals(Object obj){
    LoaiPhong other = (LoaiPhong) obj;
    // lấy mã để so sánh 2 chuyên đề
    return other.maLP.equals(this.getMaLP());
}
    public LoaiPhong(String maLP, String tenLoaiPhong) {
        this.maLP = maLP;
        this.tenLoaiPhong = tenLoaiPhong;
    }

    public String getMaLP() {
        return maLP;
    }

    public void setMaLP(String maLP) {
        this.maLP = maLP;
    }

    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    public void setTenLoaiPhong(String tenLoaiPhong) {
        this.tenLoaiPhong = tenLoaiPhong;
    }
}
