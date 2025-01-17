package com.khachsan.dao;

import com.khachsan.entity.KhachHang;
import com.khachsan.utils.Jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO extends KhachSanDAO<KhachHang,String>{
    String INSERT_SQL = "INSERT INTO KhachHang(HoTen, CCCD, SDT, DiaChi, GioiTinh) VALUES (?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE KhachHang SET HoTen = ?, CCCD = ?,SDT = ? ,DiaChi = ?, GioiTinh = ? WHERE MaKH = ?";
    String DELETE_SQL = "DELETE FROM KhachHang WHERE MaKH = ?";
    String SELECT_ALL_SQL = "SELECT * FROM KhachHang";
    String SELECT_BY_ID_SQL = "SELECT * FROM KhachHang WHERE MaKH = ?";
    String SELECT_BY_HOTEN_SODIENTHOAI_SQL = "SELECT * FROM KhachHang WHERE HoTen LIKE ? AND SDT LIKE ?";
    @Override
    public void insert(KhachHang entity) {
        Jdbc.update(INSERT_SQL,entity.getHoTen(),
                               entity.getCanCuocCongDan(),
                               entity.getSoDienThoai(),
                               entity.getDiaChi(),
                               entity.getGioiTinh());
    }

    @Override
    public void update(KhachHang entity) {
        Jdbc.update(UPDATE_SQL, entity.getHoTen(),
                                entity.getCanCuocCongDan(),
                                entity.getSoDienThoai(),
                                entity.getDiaChi(),
                                entity.getGioiTinh(),
                                entity.getMaKH());
    }

    @Override
    public void delete(String id) {
        Jdbc.update(DELETE_SQL,id);
    }

    @Override
    public List<KhachHang> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public KhachHang selectById(String id) {
        List<KhachHang> list = this.selectBySql(SELECT_BY_ID_SQL,id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    public List<KhachHang> selectByHoTen_SoDienThoai(String hoten, String sodienthoai){
        List<KhachHang> list = this.selectBySql(SELECT_BY_HOTEN_SODIENTHOAI_SQL,"%" + hoten + "%","%" + sodienthoai + "%");
        if(list.isEmpty()){
            return null;
        }
        return list;
    }
    @Override
    protected List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<KhachHang>();
        try{
            ResultSet rs = Jdbc.query(sql, args);
            while (rs.next()){
                KhachHang entity = new KhachHang();
                entity.setMaKH(rs.getString("MaKH"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setCanCuocCongDan(rs.getString("CCCD"));
                entity.setSoDienThoai(rs.getString("SDT"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
