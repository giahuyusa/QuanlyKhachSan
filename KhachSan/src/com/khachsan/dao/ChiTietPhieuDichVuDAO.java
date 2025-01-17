package com.khachsan.dao;

import com.khachsan.entity.ChiTietPhieuDichVu;
import com.khachsan.utils.Jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChiTietPhieuDichVuDAO extends KhachSanDAO<ChiTietPhieuDichVu,String> {
    String INSERT_SQL = "INSERT INTO ChiTietPhieuDichVu(MaDV, SoLanSuDung) VALUES (?,?)";
    String UPDATE_SQL = "UPDATE ChiTietPhieuDichVu SET SoLanSuDung = ? WHERE MaPDV = ?";
    String DELETE_SQL = "DELETE FROM ChiTietPhieuDichVu  WHERE MaPDV = ? AND MaDV = ?";
    String SELECT_ALL_SQL = "SELECT * FROM ChiTietPhieuDichVu WHERE MaPDV = ?";
    String SELECT_ALL_SQL_MaPhieuDatPhong_Trong = "";
    String SELECT_ALL_SQL_MaPhieuDatPhong_Da = "";
    String SELECT_BY_MaPhieuDatPhong_MaPhong = "SELECT * FROM ChiTietPhieuDichVu WHERE MaPDV = ? AND MaDV = ?";


    
    private static final String SELECT_TABLE = "SELECT PhieuDichVu.MaPhong, PhieuDichVu.MaPDV, SUM(ChiTietPhieuDichVu.SoLanSuDung * DichVu.DonGia) AS TongTien "
            + "FROM DichVu "
            + "JOIN ChiTietPhieuDichVu ON ChiTietPhieuDichVu.MaDV = DichVu.MaDV "
            + "JOIN PhieuDichVu ON PhieuDichVu.MaPDV = ChiTietPhieuDichVu.MaPDV "
            + "WHERE PhieuDichVu.MaPhong = ? AND PhieuDichVu.MaPDP = ? "
            + "GROUP BY PhieuDichVu.MaPhong, PhieuDichVu.MaPDV";

    private static final String INSERT_PDV = "INSERT INTO PhieuDichVu (MaPhong, MaPDP) OUTPUT INSERTED.MaPDV VALUES (?, ?)";
    private static final String CHECK_SERVICE_USAGE = "SELECT COUNT(*) FROM ChiTietPhieuDichVu WHERE MaPDV = ?";
    private static final String DELETE_PDV = "DELETE FROM PhieuDichVu WHERE MaPDV = ?";
    private static final String DELETE_SERVICE_FROM_VOUCHER = "DELETE FROM ChiTietPhieuDichVu WHERE MaPDV = ? AND MaDV = ?";
    private static final String GET_MADV = "SELECT MaDV FROM DichVu WHERE TenDV = ?";
    private static final String INSERT_SERVICE = "INSERT INTO ChiTietPhieuDichVu (MaPDV, MaDV, SoLanSuDung) VALUES (?, ?, ?)";
    private static final String SELECT_SERVICE_INFO = "SELECT TenDV, DonGia FROM DichVu WHERE MaDV = ?";
    private static final String SELECT_SERVICES_FOR_VOUCHER = "SELECT d.TenDV, d.DonGia, c.SoLanSuDung, (d.DonGia * c.SoLanSuDung) AS ThanhTien "
            + "FROM ChiTietPhieuDichVu c "
            + "JOIN DichVu d ON c.MaDV = d.MaDV "
            + "WHERE c.MaPDV = ?";
    private static final String GET_SERVICE_PRICE = "SELECT DonGia FROM DichVu WHERE MaDV = ?";
    private static final String GET_MADV_BY_TEN = "SELECT MaDV FROM DichVu WHERE TenDV = ?";
    private static final String UPDATE_SERVICE_QUANTITY = "UPDATE ChiTietPhieuDichVu SET SoLanSuDung = ? WHERE MaPDV = ? AND MaDV = ?";
    private static final String SELECT_LIST_DV = "SELECT TenDV FROM DichVu";

    public List<Object[]> selectTable(String maPhong, String maPDP) {
        return getListOfArray(SELECT_TABLE, new String[]{"MaPhong", "MaPDV", "TongTien"}, maPhong, maPDP);
    }

    public String insertPhieuDichVu(String maPhong, String maPDP) {
        try (ResultSet rs = Jdbc.query(INSERT_PDV, maPhong, maPDP)) {
            if (rs.next()) {
                return rs.getString("MaPDV");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isServiceUsed(String maPDV) {
        try (ResultSet rs = Jdbc.query(CHECK_SERVICE_USAGE, maPDV)) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePhieuDichVu(String maPDV) {
        return Jdbc.update(DELETE_PDV, maPDV) > 0;
    }

    public boolean deleteServiceFromVoucher(String maPDV, String maDV) {
        return Jdbc.update(DELETE_SERVICE_FROM_VOUCHER, maPDV, maDV) > 0;
    }

    public List<String> selectListDV() {
        List<String> list = new ArrayList<>();
        try (ResultSet rs = Jdbc.query(SELECT_LIST_DV)) {
            while (rs.next()) {
                list.add(rs.getString("TenDV"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertServiceIntoVoucher(String maPDV, String tenDV, int soLuong) {
        try (ResultSet rs = Jdbc.query(GET_MADV, tenDV)) {
            if (rs.next()) {
                String maDV = rs.getString("MaDV");
                return Jdbc.update(INSERT_SERVICE, maPDV, maDV, soLuong) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Object[] getServiceInfo(String maDV) {
        try (ResultSet rs = Jdbc.query(SELECT_SERVICE_INFO, maDV)) {
            if (rs.next()) {
                return new Object[]{rs.getString("TenDV"), rs.getDouble("DonGia")};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Object[]> selectServicesForVoucher(String maPDV) {
        return getListOfArray(SELECT_SERVICES_FOR_VOUCHER, new String[]{"TenDV", "DonGia", "SoLanSuDung", "ThanhTien"}, maPDV);
    }

    public double getServicePrice(String maDV) {
        try (ResultSet rs = Jdbc.query(GET_SERVICE_PRICE, maDV)) {
            if (rs.next()) {
                return rs.getDouble("DonGia");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getMaDVByTenDV(String tenDV) {
        try (ResultSet rs = Jdbc.query(GET_MADV_BY_TEN, tenDV)) {
            if (rs.next()) {
                return rs.getString("MaDV");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateServiceQuantity(String maPDV, String maDV, int soLuongMoi) {
        return Jdbc.update(UPDATE_SERVICE_QUANTITY, soLuongMoi, maPDV, maDV) > 0;
    }




    @Override
    public void insert(ChiTietPhieuDichVu entity) {
        Jdbc.update(INSERT_SQL,entity.getMaDV(),
                               entity.getSolanSuDung());
    }

    @Override
    public void update(ChiTietPhieuDichVu entity) {
        Jdbc.update(UPDATE_SQL,entity.getSolanSuDung(),
                               entity.getMaPhieuDichVu());
    }

    @Override
    public void delete(String key) {

    }

    @Override
    public List<ChiTietPhieuDichVu> selectAll() {
        return null;
    }

    @Override
    public ChiTietPhieuDichVu selectById(String key) {
        return null;
    }

    @Override
    protected List<ChiTietPhieuDichVu> selectBySql(String sql, Object... args) {
        List<ChiTietPhieuDichVu> list = new ArrayList<ChiTietPhieuDichVu>();
        try{
            ResultSet rs = Jdbc.query(sql, args);
            while(rs.next()){
                ChiTietPhieuDichVu entity = new ChiTietPhieuDichVu();
                entity.setMaPhieuDichVu(rs.getInt("MaPDV"));
                entity.setMaDV(rs.getString("MaDV"));
                entity.setSolanSuDung(rs.getInt("SoLanSuDung"));
                entity.setNgaySuDung(rs.getTimestamp("NgaySuDung"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
        }catch(Exception e){
            throw new RuntimeException();
        }
        return list;
    }
    public List<Object[]> getChiTietPhieuDichVu(String maPhong, String maPhieuDatPhong){
        String sql = "{CALL sp_ChiTietPhieuDichVu(?,?)}";
        String[] cols = {"TenDV","DonGia","SoLanSuDung","ThanhTien"};
        return this.getListOfArray(sql, cols, maPhong, maPhieuDatPhong);
    }
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

}
