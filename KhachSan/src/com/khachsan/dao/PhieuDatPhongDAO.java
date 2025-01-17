package com.khachsan.dao;

import com.khachsan.entity.PhieuDatPhong;
import com.khachsan.utils.Jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhieuDatPhongDAO extends KhachSanDAO<PhieuDatPhong,String>{
    String INSERT_SQL = "INSERT INTO PhieuDatPhong(MaKH) VALUES(?)";
    String UPDATE_SQL = "UPDATE PhieuDatPhong SET MaKH = ? WHERE PhieuDatPhong = ?";
    String DELETE_SQL = "DELETE FROM PhieuDatPhong WHERE MaPDP = ?";
    String SELECT_ALL_SQL = "SELECT * FROM PhieuDatPhong ORDER BY NgayDatPhong DESC";
    String SELECT_BY_ID_SQL = "SELECT * FROM PhieuDatPhong WHERE MaPDP = ?";
    String SELECT_SQL_MaPhieuDatPhong_MaKhachHang = "SELECT * from PhieuDatPhong WHERE MaKH = ? AND NgayDatPhong BETWEEN DATEADD(MINUTE, -20, GETDATE()) AND DATEADD(MINUTE, 20, GETDATE())";
    @Override
    public void insert(PhieuDatPhong entity) {
        Jdbc.update(INSERT_SQL,entity.getMaKH());
    }

    @Override
    public void update(PhieuDatPhong entity) {
        Jdbc.update(UPDATE_SQL,entity.getMaKH(),entity.getMaPhieuDatPhong());
    }

    @Override
    public void delete(String id) {
        Jdbc.update(DELETE_SQL,id);
    }

    @Override
    public List<PhieuDatPhong> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public PhieuDatPhong selectById(String id) {
        List<PhieuDatPhong> list = this.selectBySql(SELECT_BY_ID_SQL,id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    
    public PhieuDatPhong select_maPhieuDatPhong_maKhachHang(String maKhachHang){
        List<PhieuDatPhong> list = this.selectBySql(SELECT_SQL_MaPhieuDatPhong_MaKhachHang,maKhachHang);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    
    @Override
    protected List<PhieuDatPhong> selectBySql(String sql, Object... args) {
        List<PhieuDatPhong> list = new ArrayList<PhieuDatPhong>();
        try {
            ResultSet rs = Jdbc.query(sql,args);
            while (rs.next()){
                PhieuDatPhong entity = new PhieuDatPhong();
                entity.setMaPhieuDatPhong(rs.getString("MaPDP"));
                entity.setMaKH(rs.getString("MaKH"));
                entity.setNgayDatPhong(rs.getTimestamp("NgayDatPhong"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
