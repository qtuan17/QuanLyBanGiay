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

    public List<Size> findAllSize() {
        System.out.println("→ Gọi phương thức: findAllSize() - Lấy danh sách toàn bộ size");
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
        System.out.println("→ Gọi phương thức: create() - Thêm size mới: " + size.getTenSize());
        int rowedit = 0;
        String sql = "INSERT INTO Size (TenSize, TrangThai) VALUES (?, 1)";
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
        System.out.println("→ Gọi phương thức: update() - Cập nhật size ID = " + size.getIdSize());
        int rowedit = 0;
        String sql = "UPDATE Size SET TenSize = ? WHERE ID_Size = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, size.getTenSize());
            preparedStatement.setInt(2, size.getIdSize());
            rowedit = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowedit;
    }

    public int delete(Size size) {
        System.out.println("→ Gọi phương thức: delete() - Ẩn size ID = " + size.getIdSize());
        int rowedit = 0;
        String sql = "UPDATE Size SET TrangThai = ? WHERE ID_Size = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, size.getIdSize());
            rowedit = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowedit;
    }

    public int khoiphuc(Size size) {
        System.out.println("→ Gọi phương thức: khoiphuc() - Khôi phục size ID = " + size.getIdSize());
        int khoiphuc = 0;
        String sql = "UPDATE Size SET TrangThai = ? WHERE ID_Size = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, size.getIdSize());
            khoiphuc = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khoiphuc;
    }
}
