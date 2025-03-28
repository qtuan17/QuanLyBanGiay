/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Mau;
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
public class MauDao {
    public PreparedStatement preparedStatement = null;
    public ResultSet resultSet = null;
    public Connection connection = null;

    public MauDao() throws Exception {
        connection = util.DBContext.getConnection();
    }

    public List<Mau> findAll() {
        List<Mau> maus = new ArrayList<>();
        String sql = "SELECT * FROM Mau";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Mau mau = new Mau(
                        resultSet.getInt("ID_Mau"),
                        resultSet.getString("TenMau"),
                        resultSet.getInt("TrangThai")
                );
                maus.add(mau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maus;
    }
    public int create(Mau mau) {
        int rowedit = 0;
        String sql = "INSERT INTO Mau (TenMau, TrangThai) VALUES \n"
                + "(?, 1)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mau.getTenMau());
            rowedit = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowedit;

    }
    public int update(Mau mau) {
        int rowedit = 0;
        String sql = "UPDATE Mau set TenMau = ? WHERE ID_Mau = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mau.getTenMau());
            preparedStatement.setInt(2, mau.getIdMau());
            rowedit = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowedit;
    }
    public int delete(Mau mau) {
        int rowedit = 0;
        String sql = "UPDATE Mau set trangthai = ? WHERE ID_Mau = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, mau.getIdMau());
            rowedit = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowedit;
    }
    public int khoiphuc(Mau mau) {
        int khoiphuc = 0;
        String sql = "UPDATE Mau set trangthai = ? WHERE ID_Mau = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, mau.getIdMau());
            khoiphuc = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khoiphuc;
    }

}
