package com.khachsan.dao;

import com.khachsan.entity.PhieuDichVu;
import com.khachsan.utils.Jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhieuDichVuDAO extends KhachSanDAO<PhieuDichVu,String>{
    String INSERT_SQL = "INSERT INTO PhieuDichVu(MaPDP, MaPhong) VALUES (?,?)";
    String UPDATE_SQL = "";
    String DELETE_SQL = "DELETE FROM PhieuDichVu WHERE MaPDP = ? AND MaPhong = ?";
    String SELECT_ALL_SQL_MaPhieuDatPhong = "SELECT * FROM PhieuDichVu WHERE MaPDP = ?";
    String SELECT_MaPhieuDatPhong_MaPhong = "SELECT * FROM PhieuDichVu WHERE MaPDP = ? AND MaPhong = ?";

    @Override
    public void insert(PhieuDichVu entity) {
        Jdbc.update(INSERT_SQL,entity.getMaPhieuDatPhong(),
                               entity.getMaPhong());
    }

    @Override
    public void update(PhieuDichVu entity) {

    }

    @Override
    public void delete(String key) {

    }
    public void delete_MaPhieuDatPhong_MaPhong(String maPhieuDatPhong, String maPhong) {
        Jdbc.update(DELETE_SQL,maPhieuDatPhong,maPhong);
    }
    @Override
    public List<PhieuDichVu> selectAll() {
        return null;
    }

    public List<PhieuDichVu> selectAll_MaPhieuDatPhong(String maPhieuDatPhong) {
        return this.selectBySql(SELECT_ALL_SQL_MaPhieuDatPhong,maPhieuDatPhong);
    }
    @Override
    public PhieuDichVu selectById(String key) {
        return null;
    }

    public PhieuDichVu select_MaPhieuDatPhong_MaPhong(String maPhieuDatPhong, String maPhong) {
        List<PhieuDichVu> list = this.selectBySql(SELECT_MaPhieuDatPhong_MaPhong,maPhieuDatPhong,maPhong);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);

    }

    @Override
    protected List<PhieuDichVu> selectBySql(String sql, Object... args) {
        List<PhieuDichVu> list = new ArrayList<PhieuDichVu>();
        try{
            ResultSet rs = Jdbc.query(sql,args);
            while (rs.next()){
                PhieuDichVu entity = new PhieuDichVu();
                entity.setMaPhieuDichVu(rs.getInt("MaPDV"));
                entity.setMaPhieuDatPhong(rs.getString("MaPDP"));
                entity.setMaPhong(rs.getString("MaPhong"));
                entity.setNgaySuDung(rs.getTimestamp("NgaySuDung"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
