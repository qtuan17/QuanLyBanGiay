/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.ChucVu;
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
public class ChucVuDao {

    public PreparedStatement preparedStatement = null;
    public Connection connection = null;
    public ResultSet resultSet = null;

    public ChucVuDao() throws Exception {
        connection = util.DBContext.getConnection();
    }

    public List<ChucVu> findAll() {
        List<ChucVu> lstCV = new ArrayList<>();
        String sql = "SELECT * FROM ChucVu";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                ChucVu chucVu = new ChucVu(
                        resultSet.getInt("ID_CV"),
                        resultSet.getString("TenChucVu"),
                        resultSet.getString("MoTa"),
                        resultSet.getInt("TrangThai")
                );
                lstCV.add(chucVu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstCV;
    }
    
    public int create(ChucVu chucVu) {
    int rowedit = 0;
    String sql = "INSERT INTO ChucVu (TenChucVu, MoTa, TrangThai) VALUES (?, ?, 1)";
    try {
        System.out.println("Tên chức vụ: " + chucVu.getTenChucVu());
        System.out.println("Mô tả chức vụ: " + chucVu.getMoTa());  // In mô tả để kiểm tra
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, chucVu.getTenChucVu());
        preparedStatement.setString(2, chucVu.getMoTa());
        rowedit = preparedStatement.executeUpdate();
        System.out.println("Rows affected: " + rowedit);  // In số dòng bị ảnh hưởng
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return rowedit;
}



}
