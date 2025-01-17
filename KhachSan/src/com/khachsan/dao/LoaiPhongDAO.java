package com.khachsan.dao;

import com.khachsan.entity.LoaiPhong;
import com.khachsan.utils.Jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoaiPhongDAO extends KhachSanDAO<LoaiPhong,String> {
    String INSERT_SQL = "INSERT INTO LoaiPhong(MaLP,TenLoaiPhong) VALUES (?,?)";
    String UPDATE_SQL = "UPDATE LoaiPhong SET TenLoaiPhong = ? WHERE MaLP = ?";
    String DELETE_SQL = "DELETE FROM LoaiPhong WHERE MaLP = ?";
    String SELECT_ALL_SQL = "SELECT * FROM LoaiPhong";
    String SELECT_BY_ID_SQL = "SELECT * FROM LoaiPhong WHERE MaLP = ?";





    @Override
    public void insert(LoaiPhong entity) {
        Jdbc.update(INSERT_SQL,entity.getMaLP(),entity.getTenLoaiPhong());
    }

    @Override
    public void update(LoaiPhong entity) {
        Jdbc.update(UPDATE_SQL,entity.getTenLoaiPhong(),entity.getMaLP());
    }

    @Override
    public void delete(String id) {
        Jdbc.update(DELETE_SQL,id);
    }

    @Override
    public List<LoaiPhong> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public LoaiPhong selectById(String id) {
        List<LoaiPhong> list = this.selectBySql(SELECT_BY_ID_SQL,id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<LoaiPhong> selectBySql(String sql, Object... args) {
        List<LoaiPhong> list = new ArrayList<LoaiPhong>();
        try{
            ResultSet rs = Jdbc.query(sql, args);
            while (rs.next()){
                LoaiPhong entity = new LoaiPhong();
                entity.setMaLP(rs.getString("MaLP"));
                entity.setTenLoaiPhong(rs.getString("TenLoaiPhong"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
