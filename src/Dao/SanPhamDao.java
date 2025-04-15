/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.SanPham;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 *
 * @author tuanb
 */
public class SanPhamDao {

    public PreparedStatement preparedStatement = null;
    public ResultSet resultSet = null;
    public Connection connection = null;

    public SanPhamDao() throws Exception {
        connection = util.DBContext.getConnection();
    }

    public List<SanPham> findAll() {
        List<SanPham> sanphams = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SanPham sanpham = new SanPham(
                        resultSet.getInt("ID_SP"),
                        resultSet.getString("MaGiay"),
                        resultSet.getString("TenGiay"),
                        resultSet.getInt("ID_Loai"),
                        resultSet.getInt("TrangThai")
                );
                sanphams.add(sanpham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanphams;
    }

    public int create(SanPham sp) {
        int result = 0;
        String sql = "INSERT INTO SanPham (MaGiay, TenGiay, ID_Loai, TrangThai) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, sp.getMaGiay());
            ps.setString(2, sp.getTenGiay());
            ps.setInt(3, sp.getIdLoai());
            ps.setInt(4, sp.getTrangThai());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(SanPham sp) {
        String sql = "UPDATE SanPham SET MaGiay = ?, TenGiay = ?, ID_Loai = ?, TrangThai = ? WHERE ID_SP = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, sp.getMaGiay());
            ps.setString(2, sp.getTenGiay());
            ps.setInt(3, sp.getIdLoai());
            ps.setInt(4, sp.getTrangThai());
            ps.setInt(5, sp.getIdSP());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public SanPham findByMaGiay(String maGiay) {
        String sql = "SELECT * FROM SanPham WHERE MaGiay = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, maGiay);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdSP(rs.getInt("ID_SP"));
                sp.setMaGiay(rs.getString("MaGiay"));
                sp.setTenGiay(rs.getString("TenGiay"));
                sp.setIdLoai(rs.getInt("ID_Loai"));
                sp.setTrangThai(rs.getInt("TrangThai"));
                return sp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
