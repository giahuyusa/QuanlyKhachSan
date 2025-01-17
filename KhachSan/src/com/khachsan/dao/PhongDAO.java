package com.khachsan.dao;

import com.khachsan.entity.Phong;
import com.khachsan.utils.Jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhongDAO extends KhachSanDAO<Phong,String>{
    String INSERT_SQL = "INSERT INTO Phong(MaPhong, MaLP, MoTa, DonGia) VALUES (?,?,?,?)";
    String UPDATE_SQL = "UPDATE Phong SET MaLP = ?, MoTa = ?, DonGia = ? WHERE MaPhong = ?";
    String DELETE_SQL = "DELETE FROM Phong WHERE MaPhong = ?";
    String SELECT_ALL_SQL_Phong_Date_LoaiPhong = "SELECT p.MaPhong, p.MaLP, p.MoTa, p.DonGia\n" +
                                                    "FROM Phong p\n" +
                                                    "LEFT JOIN ChiTietPhieuDatPhong ct\n" +
                                                    "    ON p.MaPhong = ct.MaPhong\n" +
                                                    "    AND ct.NgayTraPhong BETWEEN CAST(? AS DATETIME) \n" +
                                                    "                            AND DATEADD(DAY, 7, CAST(? AS DATETIME))\n" +
                                                    "WHERE ct.MaPhong IS NULL \n" +
                                                    "    AND p.MaLP = ?";
    String SELECT_BY_ID_SQL = "SELECT * FROM PHONG WHERE MaPhong";

    private List<Object[]> getListOfArray(String sql,String[] cols,Object ... args){
        try{
            List<Object[]> list = new ArrayList<>();
            // mang chua
            ResultSet rs = Jdbc.query(sql, args);

            while(rs.next()){
                Object[] vals = new Object[cols.length];
                for(int i = 0;i < cols.length;i++){
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }
    
    public List<Object[]> get_ALL_SQL_Phong_Date_LoaiPhong(Date ngayDatPhong, String loaiPhong) {
        String sql = "CALL SELECT_ALL_SQL_Phong_Date_LoaiPhong(?, ?)";
        String[] cols = {"MaPhong"};
        return this.getListOfArray(sql, cols, ngayDatPhong, loaiPhong);
    }

    public List<Phong> get_SELECT_ALL_SQL_Phong_Date_LoaiPhong(String ngayDatPhong,String ngayDatPhong1, String loaiPhong){
        return this.selectBySql(SELECT_ALL_SQL_Phong_Date_LoaiPhong, ngayDatPhong, ngayDatPhong1, loaiPhong);
    }



    @Override
    public void insert(Phong entity) {
        Jdbc.update(INSERT_SQL,entity.getMaPhong(),
                               entity.getMaLP(),
                               entity.getMota(),
                               entity.getDonGia());
    }

    @Override
    public void update(Phong entity) {
        Jdbc.update(UPDATE_SQL,entity.getMaLP(),
                               entity.getMota(),
                               entity.getDonGia(),
                               entity.getMaPhong());
    }

    @Override
    public void delete(String id) {
        Jdbc.update(DELETE_SQL,id);
    }

    @Override
    public List<Phong> selectAll() {
        return null;
    }

    @Override
    public Phong selectById(String id) {
        List<Phong> list = this.selectBySql(SELECT_BY_ID_SQL,id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<Phong> selectBySql(String sql, Object... args) {
        List<Phong> list = new ArrayList<>();
        ResultSet rs = Jdbc.query(sql,args);
        try{
            while (rs.next()){
                Phong entity = new Phong();
                entity.setMaPhong(rs.getString("MaPhong"));
                entity.setMaLP(rs.getString("MaLP"));
                entity.setMota(rs.getString("MoTa"));
                entity.setDonGia(rs.getDouble("DonGia"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

   
}
