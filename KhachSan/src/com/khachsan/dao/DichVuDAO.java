package com.khachsan.dao;

import com.khachsan.utils.Jdbc;
import com.khachsan.utils.MsgBox;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DichVuDAO {

    // Câu lệnh SQL để chọn bảng phiếu dịch vụ
    private static final String SELECT_TABLE = 
        "SELECT PhieuDichVu.MaPhong, PhieuDichVu.MaPDV, SUM(ChiTietPhieuDichVu.SoLanSuDung * DichVu.DonGia) AS TongTien "
        + "FROM DichVu "
        + "JOIN ChiTietPhieuDichVu ON ChiTietPhieuDichVu.MaDV = DichVu.MaDV "
        + "JOIN PhieuDichVu ON PhieuDichVu.MaPDV = ChiTietPhieuDichVu.MaPDV "
        + "WHERE PhieuDichVu.MaPhong = ? AND PhieuDichVu.MaPDP = ? "
        + "GROUP BY PhieuDichVu.MaPhong, PhieuDichVu.MaPDV";

    // Câu lệnh SQL để chèn phiếu dịch vụ mới
    private static final String INSERT_PDV = 
        "INSERT INTO PhieuDichVu (MaPhong, MaPDP) OUTPUT INSERTED.MaPDV VALUES (?, ?)";

    // Câu lệnh SQL để kiểm tra dịch vụ đã sử dụng
    private static final String CHECK_SERVICE_USAGE = 
        "SELECT COUNT(*) FROM ChiTietPhieuDichVu WHERE MaPDV = ?";

    // Câu lệnh SQL để xóa phiếu dịch vụ
    private static final String DELETE_PDV = 
        "DELETE FROM PhieuDichVu WHERE MaPDV = ?";

    // Câu lệnh SQL để xóa dịch vụ khỏi phiếu dịch vụ
    private static final String DELETE_SERVICE_FROM_VOUCHER = 
        "DELETE FROM ChiTietPhieuDichVu WHERE MaPDV = ? AND MaDV = ?";

    // Câu lệnh SQL để lấy danh sách dịch vụ
    private static final String SELECT_LIST_DV = 
        "SELECT TenDV FROM DichVu";

    // Câu lệnh SQL để chèn dịch vụ vào phiếu dịch vụ
    private static final String INSERT_SERVICE_INTO_VOUCHER = 
        "INSERT INTO ChiTietPhieuDichVu (MaPDV, MaDV, SoLanSuDung) VALUES (?, ?, ?)";

    // Câu lệnh SQL để lấy thông tin dịch vụ
    private static final String SELECT_SERVICE_INFO = 
        "SELECT TenDV, DonGia FROM DichVu WHERE MaDV = ?";

    // Câu lệnh SQL để lấy danh sách dịch vụ cho phiếu dịch vụ
    private static final String SELECT_SERVICES_FOR_VOUCHER = 
        "SELECT d.TenDV, d.DonGia, c.SoLanSuDung, (d.DonGia * c.SoLanSuDung) AS ThanhTien "
        + "FROM ChiTietPhieuDichVu c "
        + "JOIN DichVu d ON c.MaDV = d.MaDV "
        + "WHERE c.MaPDV = ?";

    // Câu lệnh SQL để lấy đơn giá dịch vụ
    private static final String GET_SERVICE_PRICE = 
        "SELECT DonGia FROM DichVu WHERE MaDV = ?";

    // Câu lệnh SQL để lấy mã dịch vụ từ tên dịch vụ
    private static final String GET_MADV_BY_TEN = 
        "SELECT MaDV FROM DichVu WHERE TenDV = ?";

    // Câu lệnh SQL để cập nhật số lượng dịch vụ
    private static final String UPDATE_SERVICE_QUANTITY = 
        "UPDATE ChiTietPhieuDichVu SET SoLanSuDung = ? WHERE MaPDV = ? AND MaDV = ?";

    public List<Object[]> selectTable(String maPhong, String maPDP) {
        List<Object[]> list = new ArrayList<>();
        try (ResultSet rs = Jdbc.query(SELECT_TABLE, maPhong, maPDP)) {
            while (rs.next()) {
                list.add(new Object[]{
                        rs.getString("MaPhong"),
                        rs.getString("MaPDV"),
                        rs.getDouble("TongTien")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            MsgBox.alert(null, "Lỗi khi lấy danh sách phiếu dịch vụ: " + e.getMessage());
        }
        return list;
    }

    public String insertPhieuDichVu(String maPhong, String maPDP) {
        String maPDV = null;
        try (ResultSet rs = Jdbc.query(INSERT_PDV, maPhong, maPDP)) {
            if (rs.next()) {
                maPDV = rs.getString("MaPDV");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            MsgBox.alert(null, "Lỗi khi chèn phiếu dịch vụ mới: " + e.getMessage());
        }
        return maPDV;
    }

    public boolean isServiceUsed(String maPDV) {
        try (ResultSet rs = Jdbc.query(CHECK_SERVICE_USAGE, maPDV)) {
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            MsgBox.alert(null, "Lỗi khi kiểm tra dịch vụ đã sử dụng: " + e.getMessage());
        }
        return false;
    }

    public boolean deletePhieuDichVu(String maPDV) {
        try {
            int rowsAffected = Jdbc.update(DELETE_PDV, maPDV);
            return rowsAffected > 0;
        } catch (RuntimeException e) {
            e.printStackTrace();
            MsgBox.alert(null, "Lỗi khi xóa phiếu dịch vụ: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteServiceFromVoucher(String maPDV, String maDV) {
        try {
            int rowsAffected = Jdbc.update(DELETE_SERVICE_FROM_VOUCHER, maPDV, maDV);
            return rowsAffected > 0;
        } catch (RuntimeException e) {
            e.printStackTrace();
            MsgBox.alert(null, "Lỗi khi xóa dịch vụ khỏi phiếu dịch vụ: " + e.getMessage());
        }
        return false;
    }

    public List<String> selectListDV() {
        List<String> list = new ArrayList<>();
        try (ResultSet rs = Jdbc.query(SELECT_LIST_DV)) {
            while (rs.next()) {
                list.add(rs.getString("TenDV"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            MsgBox.alert(null, "Lỗi khi lấy danh sách dịch vụ: " + e.getMessage());
        }
        return list;
    }

    public boolean insertServiceIntoVoucher(String maPDV, String tenDV, int soLuong) throws SQLException {
        try (ResultSet rs = Jdbc.query(GET_MADV_BY_TEN, tenDV)) {
            if (rs.next()) {
                String maDV = rs.getString("MaDV");
                int rowsAffected = Jdbc.update(INSERT_SERVICE_INTO_VOUCHER, maPDV, maDV, soLuong);
                return rowsAffected > 0;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            MsgBox.alert(null, "Lỗi khi chèn dịch vụ vào phiếu dịch vụ: " + e.getMessage());
        }
        return false;
    }

    public Object[] getServiceInfo(String maDV) {
        try (ResultSet rs = Jdbc.query(SELECT_SERVICE_INFO, maDV)) {
            if (rs.next()) {
                return new Object[]{
                    rs.getString("TenDV"),
                    rs.getDouble("DonGia")
                };
            }
        } catch (SQLException e) {
            e.printStackTrace();
            MsgBox.alert(null, "Lỗi khi lấy thông tin dịch vụ: " + e.getMessage());
        }
        return null;
    }

    public List<Object[]> selectServicesForVoucher(String maPDV) {
        List<Object[]> services = new ArrayList<>();
        try (ResultSet rs = Jdbc.query(SELECT_SERVICES_FOR_VOUCHER, maPDV)) {
            while (rs.next()) {
                services.add(new Object[]{
                    rs.getString("TenDV"),
                    rs.getDouble("DonGia"),
                    rs.getInt("SoLanSuDung"),
                    rs.getDouble("ThanhTien")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            MsgBox.alert(null, "Lỗi khi lấy danh sách dịch vụ cho phiếu dịch vụ: " + e.getMessage());
        }
        return services;
    }

    public double getServicePrice(String maDV) {
        try (ResultSet rs = Jdbc.query(GET_SERVICE_PRICE, maDV)) {
            if (rs.next()) {
                return rs.getDouble("DonGia");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            MsgBox.alert(null, "Lỗi khi lấy đơn giá dịch vụ: " + e.getMessage());
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
            MsgBox.alert(null, "Lỗi khi lấy mã dịch vụ từ tên dịch vụ: " + e.getMessage());
        }
        return null;
    }

    public boolean updateServiceQuantity(String maPDV, String maDV, int soLuong) {
        try {
            int rowsAffected = Jdbc.update(UPDATE_SERVICE_QUANTITY, soLuong, maPDV, maDV);
            return rowsAffected > 0;
        } catch (RuntimeException e) {
            e.printStackTrace();
            MsgBox.alert(null, "Lỗi khi cập nhật số lượng dịch vụ: " + e.getMessage());
        }
        return false;
    }
}