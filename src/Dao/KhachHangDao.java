/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.KhachHang;
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
public class KhachHangDao {
    public PreparedStatement preparedStatement = null;
    public Connection connection = null;
    public ResultSet resultSet = null;
    
    public KhachHangDao() throws Exception {
        connection = util.DBContext.getConnection();
    }
    
    public List<KhachHang> findAll() {
        List<KhachHang> lstNV = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {                
                KhachHang khachHang = new KhachHang(
                        resultSet.getInt("ID_KH"),
                        resultSet.getString("HoTenKH"),
                        resultSet.getInt("SDT"),
                        resultSet.getString("DiaChi"),
                        resultSet.getInt("TrangThai")
                );
                lstNV.add(khachHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstNV;
    }
}
