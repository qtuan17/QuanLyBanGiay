/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Size;
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
public class SizeDao {

    public PreparedStatement preparedStatement = null;
    public ResultSet resultSet = null;
    public Connection connection = null;

    public SizeDao() throws Exception {
        connection = util.DBContext.getConnection();
    }

    public List<Size> findAll() {
        List<Size> sizes = new ArrayList<>();
        String sql = "SELECT * FROM Size";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Size size = new Size(
                        resultSet.getInt("ID_Size"),
                        resultSet.getString("MaSize"),
                        resultSet.getString("TenSize"),
                        resultSet.getInt("TrangThai")
                );
                sizes.add(size);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sizes;
    }
}
