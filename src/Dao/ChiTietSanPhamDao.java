/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.ChiTietSanPham;
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
public class ChiTietSanPhamDao {

    public PreparedStatement preparedStatement = null;
    public Connection connection = null;
    public ResultSet resultSet = null;

    public ChiTietSanPhamDao() throws Exception {
        connection = util.DBContext.getConnection();
    }

    public List<ChiTietSanPhamView> findAll() {
        List<ChiTietSanPhamView> chiTietSanPhams = new ArrayList<>();
        String sql = "SELECT \n"
                + "    ctsp.ID_CTSP, \n"
                + "    sp.MaGiay, \n"
                + "    sp.TenGiay, \n"
                + "    l.TenLoai,\n"
                + "    m.TenMau,\n"
                + "    s.TenSize,\n"
                + "    ctsp.SoLuong, \n"
                + "    ctsp.GiaTien,\n"
                + "    ctsp.TrangThai\n"
                + "FROM ChiTietSanPham ctsp\n"
                + "JOIN SanPham sp ON ctsp.ID_SP = sp.ID_SP\n"
                + "JOIN Loai l ON l.ID_Loai = sp.ID_Loai\n"
                + "JOIN Mau m ON m.ID_Mau = ctsp.ID_Mau\n"
                + "JOIN Size s ON s.ID_Size = ctsp.ID_Size;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ChiTietSanPhamView ctsp = new ChiTietSanPhamView(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9)
                );
                chiTietSanPhams.add(ctsp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }

//    public boolean checkConHang(int idSanPham) {
//        String query = "SELECT * FROM ChiTietSua WHERE ID_CTSP = ? and SoLuong > 0";
//        try {
//            PreparedStatement pstmt = connection.prepareStatement(query);
//            pstmt.setInt(1, idSanPham);
//            ResultSet result = pstmt.executeQuery();
//            while (result.next()) {
//                return true;
//            }
//            return false;
//        } catch (Exception e) {
//            System.out.println("looix check gio hang: " + e);
//            return false;
//        }
//    }
    public boolean addChiTietSanPham(ChiTietSanPham chiTietSanPham) {
        try {
            // 1. Kiểm tra sản phẩm đã tồn tại chưa (theo ID_SP, ID_Mau, ID_Size)
            String checkSql = "SELECT ID_CTSP, SoLuong FROM ChiTietSanPham WHERE ID_SP = ? AND ID_Mau = ? AND ID_Size = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);
            checkStmt.setInt(1, chiTietSanPham.getIdSP());
            checkStmt.setInt(2, chiTietSanPham.getIdMau());
            checkStmt.setInt(3, chiTietSanPham.getIdSize());

            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // 2. Nếu tồn tại -> cập nhật số lượng
                int idCTSP = rs.getInt("ID_CTSP");
                int currentSoLuong = rs.getInt("SoLuong");
                int newSoLuong = currentSoLuong + chiTietSanPham.getSoLuong();

                chiTietSanPham.setIdCTSP(idCTSP);
                chiTietSanPham.setSoLuong(newSoLuong);
                return updateSoLuong(chiTietSanPham) > 0;
            } else {
                // 3. Nếu không tồn tại -> thêm mới
                String insertSql = "INSERT INTO ChiTietSanPham (ID_SP, ID_Mau, ID_Size, SoLuong, GiaTien, TrangThai) VALUES (?, ?, ?, ?, ?, 1)";
                PreparedStatement insertStmt = connection.prepareStatement(insertSql);
                insertStmt.setInt(1, chiTietSanPham.getIdSP());
                insertStmt.setInt(2, chiTietSanPham.getIdMau());
                insertStmt.setInt(3, chiTietSanPham.getIdSize());
                insertStmt.setInt(4, chiTietSanPham.getSoLuong());
                insertStmt.setDouble(5, chiTietSanPham.getGiaTien());

                return insertStmt.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int updateSoLuong(ChiTietSanPham chiTietSanPham) {
        String sql = "UPDATE ChiTietSanPham SET SoLuong = ? WHERE ID_CTSP = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, chiTietSanPham.getSoLuong());
            ps.setInt(2, chiTietSanPham.getIdCTSP());
            return ps.executeUpdate(); // Trả về số dòng bị ảnh hưởng
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSoLuongById(int idCTSP) {
        String sql = "SELECT SoLuong FROM ChiTietSanPham WHERE ID_CTSP = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCTSP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("SoLuong");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu có lỗi hoặc không tìm thấy
    }

}
