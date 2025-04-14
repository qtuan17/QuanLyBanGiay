/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tuanb
 */
public class NhanVienDao {

    public PreparedStatement preparedStatement = null;
    public Connection connection = null;
    public ResultSet resultSet = null;

    public NhanVienDao() throws Exception {
        connection = util.DBContext.getConnection();
    }

    public List<NhanVien> findAll() {
        List<NhanVien> lstNV = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                NhanVien nhanVien = new NhanVien(
                        resultSet.getInt("ID_NV"),
                        resultSet.getString("HoTenNV"),
                        resultSet.getDate("NgaySinh"),
                        resultSet.getString("DiaChi"),
                        resultSet.getString("sdt"),
                        resultSet.getString("Password"),
                        resultSet.getInt("TrangThai"),
                        resultSet.getBoolean("Role")
                );
                lstNV.add(nhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstNV;
    }

    public int create(NhanVien nhanVien) {
        int add = 0;
        String sql = "INSERT INTO NhanVien (HoTenNV, NgaySinh, DiaChi, SDT, Password, TrangThai, Role) VALUES (?, ?, ?, ?, ?, 1, 0)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nhanVien.getHoTenNV());

            java.sql.Date sqlDate = null;
            if (nhanVien.getNgaySinh() != null) {
                sqlDate = new java.sql.Date(nhanVien.getNgaySinh().getTime());
            }
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setString(3, nhanVien.getDiaChi());
            preparedStatement.setString(4, nhanVien.getSdt());
            preparedStatement.setString(5, nhanVien.getPassword());

            add = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return add;
    }

    public NhanVien findBySdt(String sdt) {
        String sql = "SELECT * FROM NhanVien WHERE SDT = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, sdt);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setIdNV(rs.getInt("ID_NV"));
                nv.setHoTenNV(rs.getString("HoTenNV"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSdt(rs.getString("SDT"));
                nv.setPassword(rs.getString("Password"));
                nv.setTrangThai(rs.getInt("TrangThai"));
                return nv;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
