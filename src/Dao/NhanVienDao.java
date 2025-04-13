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
import java.sql.Date;

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
        String sql = "INSERT INTO NhanVien (HoTenNV, NgaySinh, CCCD, DiaChi, Username, Password, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(3, nhanVien.getHoTenNV());
            java.sql.Date sqlDate = nhanVien.getNgaySinh() != null
                    ? new java.sql.Date(nhanVien.getNgaySinh().getTime())
                    : null;
            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setString(5, nhanVien.getDiaChi());
            preparedStatement.setString(6, nhanVien.getSdt());
            preparedStatement.setString(7, nhanVien.getPassword());
            preparedStatement.setInt(8, nhanVien.getTrangThai());

            add = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return add;
    }

}
