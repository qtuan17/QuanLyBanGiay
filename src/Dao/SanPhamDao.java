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
    
}
