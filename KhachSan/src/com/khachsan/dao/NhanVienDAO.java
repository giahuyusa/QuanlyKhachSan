package com.khachsan.dao;

import com.khachsan.entity.NhanVien;
import com.khachsan.utils.Jdbc;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO extends KhachSanDAO<NhanVien,String>{
    String INSERT_SQL = "INSERT INTO NhanVien(HoTen, VaiTro, GioiTinh, DiaChi, SDT, Luong, MatKhau) VALUES (?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE NhanVien SET HoTen = ?, VaiTro = ?, GioiTinh = ?, DiaChi = ?, SDT = ?, Luong = ?, MatKhau = ? WHERE MaNV = ?";
    String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV = ?";
    String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNV = ?";
    @Override
    public void insert(NhanVien entity) {
        Jdbc.update(INSERT_SQL,entity.getHoTen(),entity.getVaiTro(), entity.getGioiTinh(), entity.getDiaChi(), entity.getSoDienThoai(), entity.getLuong(), entity.getMatKhau());
    }

    @Override
    public void update(NhanVien entity) {
        Jdbc.update(UPDATE_SQL,entity.getHoTen(),entity.getVaiTro(),entity.getGioiTinh(),entity.getDiaChi(), entity.getSoDienThoai(),entity.getLuong(), entity.getMatKhau(), entity.getMaNV());
    }

    @Override
    public void delete(String id) {
        Jdbc.update(DELETE_SQL,id);
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = this.selectBySql(SELECT_BY_ID_SQL,id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        try{
            ResultSet rs = Jdbc.query(sql, args);
            while (rs.next()){
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setSoDienThoai(rs.getString("SDT"));
                entity.setLuong(rs.getDouble("Luong"));
                entity.setMatKhau(rs.getString("MatKhau"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
