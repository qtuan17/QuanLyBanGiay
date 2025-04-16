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
        System.out.println("→ Gọi phương thức: findAll() - Lấy danh sách tất cả chi tiết sản phẩm");
        List<ChiTietSanPhamView> chiTietSanPhams = new ArrayList<>();
        String sql = """
            SELECT 
                ctsp.ID_CTSP, 
                sp.MaGiay, 
                sp.TenGiay, 
                l.TenLoai,
                m.TenMau,
                s.TenSize,
                ctsp.SoLuong, 
                ctsp.GiaTien,
                ctsp.TrangThai
            FROM ChiTietSanPham ctsp
            JOIN SanPham sp ON ctsp.ID_SP = sp.ID_SP
            JOIN Loai l ON l.ID_Loai = sp.ID_Loai
            JOIN Mau m ON m.ID_Mau = ctsp.ID_Mau
            JOIN Size s ON s.ID_Size = ctsp.ID_Size
        """;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idCTSP = resultSet.getInt(1);
                int soLuong = resultSet.getInt(7);

                updateTrangThaiTheoSoLuong(idCTSP);

                ChiTietSanPhamView ctsp = new ChiTietSanPhamView(
                        idCTSP,
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        soLuong,
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

    public boolean addChiTietSanPham(ChiTietSanPham chiTietSanPham) {
        System.out.println("→ Gọi phương thức: addChiTietSanPham() - Thêm hoặc cập nhật chi tiết sản phẩm");
        try {
            String checkSql = "SELECT ID_CTSP, SoLuong FROM ChiTietSanPham WHERE ID_SP = ? AND ID_Mau = ? AND ID_Size = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);
            checkStmt.setInt(1, chiTietSanPham.getIdSP());
            checkStmt.setInt(2, chiTietSanPham.getIdMau());
            checkStmt.setInt(3, chiTietSanPham.getIdSize());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int idCTSP = rs.getInt("ID_CTSP");
                int currentSoLuong = rs.getInt("SoLuong");
                int newSoLuong = currentSoLuong + chiTietSanPham.getSoLuong();

                chiTietSanPham.setIdCTSP(idCTSP);
                chiTietSanPham.setSoLuong(newSoLuong);

                return updateSoLuongVaGiaTien(chiTietSanPham) > 0;
            } else {
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

    public int updateSoLuongVaGiaTien(ChiTietSanPham chiTietSanPham) {
        System.out.println("→ Gọi phương thức: updateSoLuongVaGiaTien()");
        String sql = "UPDATE ChiTietSanPham SET SoLuong = ?, GiaTien = ? WHERE ID_CTSP = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, chiTietSanPham.getSoLuong());
            ps.setDouble(2, chiTietSanPham.getGiaTien());
            ps.setInt(3, chiTietSanPham.getIdCTSP());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateSoLuong(ChiTietSanPham chiTietSanPham) {
        System.out.println("→ Gọi phương thức: updateSoLuong()");
        String sql = "UPDATE ChiTietSanPham SET SoLuong = ? WHERE ID_CTSP = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, chiTietSanPham.getSoLuong());
            ps.setInt(2, chiTietSanPham.getIdCTSP());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSoLuongById(int idCTSP) {
        System.out.println("→ Gọi phương thức: getSoLuongById() với ID_CTSP = " + idCTSP);
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
        return -1;
    }

    public boolean updateTrangThaiTheoSoLuong(int idCTSP) {
        System.out.println("→ Gọi phương thức: updateTrangThaiTheoSoLuong() với ID_CTSP = " + idCTSP);
        String selectSql = "SELECT SoLuong FROM ChiTietSanPham WHERE ID_CTSP = ?";
        String updateSql = "UPDATE ChiTietSanPham SET TrangThai = ? WHERE ID_CTSP = ?";

        try (PreparedStatement selectStmt = connection.prepareStatement(selectSql)) {
            selectStmt.setInt(1, idCTSP);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int soLuong = rs.getInt("SoLuong");
                int newTrangThai = (soLuong > 0) ? 1 : 0;

                try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                    updateStmt.setInt(1, newTrangThai);
                    updateStmt.setInt(2, idCTSP);
                    return updateStmt.executeUpdate() > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateTrangThaiConHang(int idCTSP) {
        System.out.println("→ Gọi phương thức: updateTrangThaiConHang() với ID_CTSP = " + idCTSP);
        String sql = "UPDATE ChiTietSanPham SET TrangThai = 1 WHERE ID_CTSP = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCTSP);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ChiTietSanPham findBySP_Mau_Size(int idSP, int idMau, int idSize) {
        System.out.println("→ Gọi phương thức: findBySP_Mau_Size() với SP = " + idSP + ", Mau = " + idMau + ", Size = " + idSize);
        String sql = "SELECT ID_CTSP, SoLuong, GiaTien, TrangThai FROM ChiTietSanPham WHERE ID_SP = ? AND ID_Mau = ? AND ID_Size = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idSP);
            ps.setInt(2, idMau);
            ps.setInt(3, idSize);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                ctsp.setIdCTSP(rs.getInt("ID_CTSP"));
                ctsp.setIdSP(idSP);
                ctsp.setIdMau(idMau);
                ctsp.setIdSize(idSize);
                ctsp.setSoLuong(rs.getInt("SoLuong"));
                ctsp.setGiaTien(rs.getDouble("GiaTien"));
                ctsp.setTrangThai(rs.getInt("TrangThai"));
                return ctsp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
