/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Loai;
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
public class LoaiDao {

    public PreparedStatement preparedStatement = null;
    public ResultSet resultSet = null;
    public Connection connection = null;

    public LoaiDao() throws Exception {
        connection = util.DBContext.getConnection();
    }

    public List<Loai> findAllLoai() {
        System.out.println("→ Gọi phương thức: findAllLoai() - Lấy danh sách tất cả loại sản phẩm");
        List<Loai> loais = new ArrayList<>();
        String sql = "SELECT * FROM Loai";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Loai loai = new Loai(
                        resultSet.getInt("ID_Loai"),
                        resultSet.getString("TenLoai"),
                        resultSet.getInt("TrangThai")
                );
                loais.add(loai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loais;
    }

    public int create(Loai loai) {
        System.out.println("→ Gọi phương thức: create() - Thêm mới loại sản phẩm: " + loai.getTenLoai());
        int rowedit = 0;
        String sql = "INSERT INTO Loai (TenLoai, TrangThai) VALUES (?, 1)";
        try {
            preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(1, loai.getTenLoai());
            rowedit = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowedit;
    }

    public int update(Loai loai) {
        System.out.println("→ Gọi phương thức: update() - Cập nhật tên loại sản phẩm ID = " + loai.getIdLoai());
        int rowedit = 0;
        String sql = "UPDATE Loai SET TenLoai = ? WHERE ID_Loai = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, loai.getTenLoai());
            preparedStatement.setInt(2, loai.getIdLoai());
            rowedit = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowedit;
    }

    public int delete(Loai loai) {
        System.out.println("→ Gọi phương thức: delete() - Ẩn loại sản phẩm ID = " + loai.getIdLoai());
        int rowedit = 0;
        String sql = "UPDATE Loai SET TrangThai = ? WHERE ID_Loai = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, loai.getIdLoai());
            rowedit = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowedit;
    }

    public int khoiphuc(Loai loai) {
        System.out.println("→ Gọi phương thức: khoiphuc() - Khôi phục loại sản phẩm ID = " + loai.getIdLoai());
        int rowedit = 0;
        String sql = "UPDATE Loai SET TrangThai = ? WHERE ID_Loai = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, loai.getIdLoai());
            rowedit = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowedit;
    }

    public Loai findById(int id) {
        System.out.println("→ Gọi phương thức: findById() - Tìm loại sản phẩm theo ID = " + id);
        String sql = "SELECT * FROM Loai WHERE ID_Loai = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Loai(
                        rs.getInt("ID_Loai"),
                        rs.getString("TenLoai"),
                        rs.getInt("TrangThai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
