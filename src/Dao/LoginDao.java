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
import util.SessionLogin;

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

    public NhanVien Login(String sdt, String password) {
        String sql = "SELECT * FROM NhanVien WHERE SDT = ? AND Password = ?";
        NhanVien nhanVien = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sdt);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                nhanVien = new NhanVien(
                        resultSet.getInt("ID_NV"),
                        resultSet.getString("HoTenNV"),
                        resultSet.getDate("NgaySinh"),
                        resultSet.getString("DiaChi"),
                        resultSet.getString("SDT"),
                        resultSet.getString("Password"),
                        resultSet.getInt("TrangThai"),
                        resultSet.getBoolean("Role")
                );
                // de o day
                SessionLogin.setNhanVienLogin(nhanVien);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nhanVien;
    }

}
