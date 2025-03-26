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

/**
 *
 * @author tuanb
 */
public class LoginDao {

    public PreparedStatement preparedStatement = null;
    public ResultSet resultSet = null;
    public Connection connection = null;

    public LoginDao() throws Exception {
        connection = util.DBContext.getConnection();
    }

    public NhanVien Login(String username, String password) {
        String sql = "SELECT * FROM NhanVien WHERE Username = ? AND Password = ?";
        NhanVien nhanVien = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                nhanVien = new NhanVien(
                        resultSet.getInt("ID_NV"),
                        resultSet.getInt("ID_CV"),
                        resultSet.getString("HoTenNV"),
                        resultSet.getDate("NgaySinh"),
                        resultSet.getString("DiaChi"),
                        resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getInt("TrangThai")
                        
                );
                // de o day
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nhanVien;
    }

}
