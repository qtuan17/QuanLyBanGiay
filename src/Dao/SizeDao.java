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
    public int create(Size size) {
        int rowedit = 0;
        String sql = "INSERT INTO Size (TenSize, TrangThai) VALUES \n"
                + "(?, 1)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, size.getTenSize());
            rowedit = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowedit;

    }
    public int update(Size size) {
        int rowedit = 0;
        String sql = "UPDATE Size set TenSize = ? WHERE ID_Size = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, size.getTenSize());
            preparedStatement.setInt(2, size.getID_Size());
            rowedit = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowedit;
    }
    public int delete(Size size) {
        int rowedit = 0;
        String sql = "UPDATE Size set trangthai = ? WHERE ID_Size = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, size.getID_Size());
            rowedit = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowedit;
    }
    public int khoiphuc(Size size) {
        int khoiphuc = 0;
        String sql = "UPDATE Size set trangthai = ? WHERE ID_Size = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, size.getID_Size());
            khoiphuc = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khoiphuc;
    }
}
