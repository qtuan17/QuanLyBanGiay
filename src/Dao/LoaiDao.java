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
    
    public LoaiDao() throws Exception{
        connection = util.DBContext.getConnection();
    }
    
    public List<Loai> findAll(){
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
    public int create(Loai loai){
        int rowedit = 0;
        String sql = "INSERT INTO Loai (TenLoai, TrangThai) VALUES \n"
                + "(?, 1)";
        try {
            preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(1, loai.getTenLoai());
            rowedit = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowedit;
    }
    public int update(Loai loai){
        int rowedit = 0;
        String sql = "UPDATE Loai set Tenloai = ? WHERE ID_Loai = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, loai.getTenLoai());
            preparedStatement.setInt(2, loai.getID_Loai());
            rowedit = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowedit;
    }
    public int delete(Loai loai){
        int rowedit = 0;
        String sql = "UPDATE Loai set trangthai = ? WHERE ID_Loai = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, loai.getID_Loai());
            rowedit = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowedit;
    }
    public int khoiphuc(Loai loai){
        int rowedit = 0;
        String sql = "UPDATE Loai set trangthai = ? WHERE ID_Loai = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, loai.getID_Loai());
            rowedit = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowedit;
    }
}
