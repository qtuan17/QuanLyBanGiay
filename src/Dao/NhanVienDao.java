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
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {                
                NhanVien nhanVien = new NhanVien(
                        resultSet.getInt("ID_NV"),
                        resultSet.getInt("ID_CV"),
                        resultSet.getString("HoTenNV"),
                        resultSet.getDate("NgaySinh"),
                        resultSet.getInt("CCCD"),
                        resultSet.getString("DiaChi"),
                        resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getInt("TrangThai")
                );
                lstNV.add(nhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstNV;
    }
}

