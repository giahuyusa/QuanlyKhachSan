package com.khachsan.dao;

import com.khachsan.entity.PhieuHoaDon;
import com.khachsan.utils.Jdbc;

import java.awt.geom.RectangularShape;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhieuHoaDonDAO extends KhachSanDAO<PhieuHoaDon,String>{

    String INSERT_SQL = "INSERT INTO PhieuHoaDon(MaNV, MaPDP, MaPhong) VALUES (?,?,?)";
    String UPDATE_SQl = "UPDATE PhieuHoaDon SET MaPDP = ? WHERE MaPHD = ?";
    String DELETE_SQL = "DELETE FROM PhieuHoaDon WHERE MaPDP = ?";
    String SELECT_ALL_SQL = "SELECT * FROM PhieuHoaDon";
    String SELECT_BY_ID_SQL = "SELECT * FROM PhieuHoaDon WHERE MaPDP = ?";
    @Override
    public void insert(PhieuHoaDon entity) {
        Jdbc.update(INSERT_SQL,entity.getMaNV(),
                               entity.getMaPhieuDatPhong(),
                               entity.getMaPhong()
                               );
    }

    @Override
    public void update(PhieuHoaDon entity) {
    }

    @Override
    public void delete(String id) {
        Jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<PhieuHoaDon> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public PhieuHoaDon selectById(String id) {
        List<PhieuHoaDon> list = this.selectBySql(SELECT_BY_ID_SQL,id);
        if (list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<PhieuHoaDon> selectBySql(String sql, Object... args) {
        List<PhieuHoaDon> list = new ArrayList<>();
        try{
            ResultSet rs = Jdbc.query(sql, args);
            while (rs.next()){
                PhieuHoaDon entity = new PhieuHoaDon();
                entity.setMaPhieuHoaDon(rs.getInt("MaPHD"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setMaPhieuDatPhong(rs.getString("MaPDP"));
                entity.setNgayLap(rs.getTimestamp("NgayLap"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
