/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.HoaDon;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author tuanb
 */
public class HoaDonDao {
    public PreparedStatement preparedStatement = null;
    public Connection connection = null;
    public ResultSet resultSet = null;
    
    public HoaDonDao() throws Exception {
        connection = util.DBContext.getConnection();
    }
    
    public List<HoaDon> findAll() {
        List<HoaDon> hoaDons = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {                
                HoaDon hoaDon = new HoaDon(
                        resultSet.getInt("ID_HD"),
                        resultSet.getInt("ID_NV"),
                        resultSet.getInt("ThanhTien"),
                        resultSet.getString("PTTT"),
                        resultSet.getString("NgayTao"),
                        resultSet.getInt("TrangThai")
                );
                hoaDons.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDons;
    }
}

