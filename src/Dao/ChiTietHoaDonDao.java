/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.ChiTietHoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import viewModel.ChiTietSanPhamView;

/**
 *
 * @author tuanb
 */
public class ChiTietHoaDonDao {

    public PreparedStatement preparedStatement = null;
    public Connection connection = null;
    public ResultSet resultSet = null;

    public ChiTietHoaDonDao() throws Exception {
        connection = util.DBContext.getConnection();
    }

    public int insertChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        System.out.println("→ Gọi phương thức: insertChiTietHoaDon() - Thêm mới chi tiết hóa đơn");
        String sql = "INSERT INTO ChiTietHoaDon (ID_HD, ID_CTSP, SoLuong, DonGia, ThanhTien, TrangThai) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, chiTietHoaDon.getIdHD());
            ps.setInt(2, chiTietHoaDon.getIdCTSP());
            ps.setInt(3, chiTietHoaDon.getSoLuong());
            ps.setDouble(4, chiTietHoaDon.getDonGia());
            ps.setDouble(5, chiTietHoaDon.getThanhTien());
            ps.setInt(6, 1);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                return rows;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int insertOrUpdateChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        System.out.println("→ Gọi phương thức: insertOrUpdateChiTietHoaDon() - Thêm hoặc cập nhật chi tiết hóa đơn");
        String sqlCheck = "SELECT SoLuong FROM ChiTietHoaDon WHERE ID_HD = ? AND ID_CTSP = ? AND TrangThai = ?";
        try (PreparedStatement psCheck = connection.prepareStatement(sqlCheck)) {
            psCheck.setInt(1, chiTietHoaDon.getIdHD());
            psCheck.setInt(2, chiTietHoaDon.getIdCTSP());
            psCheck.setInt(3, 1);
            try (ResultSet rs = psCheck.executeQuery()) {
                if (rs.next()) {
                    int newQuantity = chiTietHoaDon.getSoLuong();
                    double thanhTienNew = newQuantity * chiTietHoaDon.getDonGia();

                    String sqlUpdate = "UPDATE ChiTietHoaDon SET SoLuong = ?, DonGia = ?, ThanhTien = ? WHERE ID_HD = ? AND ID_CTSP = ? AND TrangThai = ?";
                    try (PreparedStatement psUpdate = connection.prepareStatement(sqlUpdate)) {
                        psUpdate.setInt(1, newQuantity);
                        psUpdate.setDouble(2, chiTietHoaDon.getDonGia());
                        psUpdate.setDouble(3, thanhTienNew);
                        psUpdate.setInt(4, chiTietHoaDon.getIdHD());
                        psUpdate.setInt(5, chiTietHoaDon.getIdCTSP());
                        psUpdate.setInt(6, 1);
                        return psUpdate.executeUpdate();
                    }
                } else {
                    String sqlInsert = "INSERT INTO ChiTietHoaDon (ID_HD, ID_CTSP, SoLuong, DonGia, ThanhTien, TrangThai) VALUES (?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement psInsert = connection.prepareStatement(sqlInsert)) {
                        psInsert.setInt(1, chiTietHoaDon.getIdHD());
                        psInsert.setInt(2, chiTietHoaDon.getIdCTSP());
                        psInsert.setInt(3, chiTietHoaDon.getSoLuong());
                        psInsert.setDouble(4, chiTietHoaDon.getDonGia());
                        psInsert.setDouble(5, chiTietHoaDon.getSoLuong() * chiTietHoaDon.getDonGia());
                        psInsert.setInt(6, 1);
                        return psInsert.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<ChiTietSanPhamView> getChiTietSanPhamTheoHoaDon(int idHD) {
        System.out.println("→ Gọi phương thức: getChiTietSanPhamTheoHoaDon() với ID_HD = " + idHD);
        List<ChiTietSanPhamView> list = new ArrayList<>();
        String sql = """
            SELECT 
                ctsp.ID_CTSP,
                sp.MaGiay,
                sp.TenGiay,
                loai.TenLoai,
                mau.TenMau,
                size.TenSize,
                cthd.SoLuong,
                cthd.DonGia
            FROM ChiTietHoaDon cthd
            JOIN ChiTietSanPham ctsp ON cthd.ID_CTSP = ctsp.ID_CTSP
            JOIN SanPham sp ON ctsp.ID_SP = sp.ID_SP
            JOIN Loai loai ON sp.ID_Loai = loai.ID_Loai
            JOIN Mau mau ON ctsp.ID_Mau = mau.ID_Mau
            JOIN Size size ON ctsp.ID_Size = size.ID_Size
            WHERE cthd.ID_HD = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idHD);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ChiTietSanPhamView sp = new ChiTietSanPhamView(
                            rs.getInt("ID_CTSP"),
                            rs.getString("MaGiay"),
                            rs.getString("TenGiay"),
                            rs.getString("TenLoai"),
                            rs.getString("TenMau"),
                            rs.getString("TenSize"),
                            rs.getInt("SoLuong"),
                            rs.getDouble("DonGia"),
                            0
                    );
                    list.add(sp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public int getSoLuongExisting(int idHD, int idCTSP) {
        System.out.println("→ Gọi phương thức: getSoLuongExisting() với ID_HD = " + idHD + ", ID_CTSP = " + idCTSP);
        String sql = "SELECT SoLuong FROM ChiTietHoaDon WHERE ID_HD = ? AND ID_CTSP = ? AND TrangThai = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idHD);
            ps.setInt(2, idCTSP);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("SoLuong");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
