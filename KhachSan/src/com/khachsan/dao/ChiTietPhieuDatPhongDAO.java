package com.khachsan.dao;

import com.khachsan.entity.ChiTietPhieuDatPhong;
import com.khachsan.entity.PhieuDatPhong;
import com.khachsan.utils.Jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChiTietPhieuDatPhongDAO extends KhachSanDAO<ChiTietPhieuDatPhong,String>{
    String INSERT_SQL = "INSERT INTO ChiTietPhieuDatPhong(MaPDP, MaPhong, NgayNhanPhong, NgayTraPhong) VALUES (?,?,?,?)";
    String UPDATE_SQL = "UPDATE ChiTietPhieuDatPhong SET NgayNhanPhong = ?, NgayTraPhong = ? WHERE MaPDP = ? AND MaPhong = ?";
    String UPDATE_SQL_TinhTrang = "UPDATE ChiTietPhieuDatPhong SET TinhTrang = ? WHERE MaPDP = ? AND MaPhong = ?";
    String DELETE_SQL = "DELETE FROM ChiTietPhieuDatPhong WHERE MaPDP = ? AND MaPhong = ?";
    String SELECT_ALL_SQL = "SELECT * FROM ChiTietPhieuDatPhong WHERE MaPDP = ?";
    String SELECT_ALL_SQL_MaPhieuDatPhong_Trong = "SELECT * FROM ChiTietPhieuDatPhong WHERE MaPDP = ? AND TinhTrang = N'Chưa Thanh toán'";
    String SELECT_ALL_SQL_MaPhieuDatPhong_Da = "SELECT * FROM ChiTietPhieuDatPhong WHERE MaPDP = ? AND TinhTrang = N'Thanh toán'";
    String SELECT_BY_MaPhieuDatPhong_MaPhong = "SELECT * FROM ChiTietPhieuDatPhong WHERE MaPDP = ? AND MaPhong = ?";





    @Override
    public void insert(ChiTietPhieuDatPhong entity) {
        Jdbc.update(INSERT_SQL,entity.getMaPhieuDatPhong(),
                               entity.getMaPhong(),
                               entity.getNgayNhanPhong(),
                               entity.getNgayTraPhong());
    }

    @Override
    public void update(ChiTietPhieuDatPhong entity) {
        Jdbc.update(UPDATE_SQL,entity.getNgayNhanPhong(),
                               entity.getNgayTraPhong(),
                               entity.getMaPhieuDatPhong(),
                               entity.getMaPhong());
    }
    public void updatetinhTrang(ChiTietPhieuDatPhong entity){
        Jdbc.update(UPDATE_SQL_TinhTrang, entity.getTrangThai(), 
                                          entity.getMaPhieuDatPhong(), 
                                          entity.getMaPhong());
    }
    @Override
    public void delete(String key) {

    }
    public void delete_MaPhieuDatPhong_MaPhong(String maPhieuDatPhong,String maPhong) {
        Jdbc.update(DELETE_SQL , maPhieuDatPhong, maPhong);
    }
    @Override
    public List<ChiTietPhieuDatPhong> selectAll() {
        return null;
    }
    public List<ChiTietPhieuDatPhong> selectAllmaPhieuDatPhong(String maPhieuDatPhong){
        return this.selectBySql(SELECT_ALL_SQL, maPhieuDatPhong);
    }
    public List<ChiTietPhieuDatPhong> selectAll_MaPhieuDatPhong_Trong(String maPhieuDatPhong) {
        return this.selectBySql(SELECT_ALL_SQL_MaPhieuDatPhong_Trong,maPhieuDatPhong);
    }
    public List<ChiTietPhieuDatPhong> selectAll_MaPhieuDatPhong_Da(String maPhieuDatPhong){
        return this.selectBySql(SELECT_ALL_SQL_MaPhieuDatPhong_Da,maPhieuDatPhong);
    }
    public ChiTietPhieuDatPhong select_MaPhieuDatPhong_MaPhong(String maPhieuDatPhong, String maPhong) {
        List<ChiTietPhieuDatPhong> list = this.selectBySql(SELECT_BY_MaPhieuDatPhong_MaPhong,maPhieuDatPhong,maPhong);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    
    @Override
    public ChiTietPhieuDatPhong selectById(String key) {
        return null;
    }

    @Override
    protected List<ChiTietPhieuDatPhong> selectBySql(String sql, Object... args) {
        List<ChiTietPhieuDatPhong> list = new ArrayList<ChiTietPhieuDatPhong>();
        try{
            ResultSet rs = Jdbc.query(sql,args);
            while (rs.next()){
                ChiTietPhieuDatPhong entity = new ChiTietPhieuDatPhong();
                entity.setMaPhieuDatPhong(rs.getString("MaPDP"));
                entity.setMaPhong(rs.getString("MaPhong"));
                entity.setNgayNhanPhong(rs.getTimestamp("NgayNhanPhong"));
                entity.setNgayTraPhong(rs.getTimestamp("NgayTraPhong"));
                entity.setTrangThai(rs.getString("TinhTrang"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch(Exception e){
            throw new RuntimeException();
        }
    }
    //
    private List<Object[]> getListOfArray(String sql, String[] cols, Object...args){
        try{
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = Jdbc.query(sql,args);
            while (rs.next()){
                Object[] vals = new Object[cols.length];
                for(int i = 0; i < cols.length ; i++){
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public List<Object[]> getChiTietPhieuDatPhongDa1(String maPhong, String maPhieuDatPhong){
    String sql = "{CALL sp_ChiTietPhieuDatPhongDa1(?,?)}";
    String[] cols = {"MaPhong", "DonGia", "NgayNhanPhong", "NgayTraPhong", "ThanhTien", "MaPDP", "HoTen", "SDT", "MaNV", "MaPHD"};
    return this.getListOfArray(sql, cols, maPhong, maPhieuDatPhong);
}
}
