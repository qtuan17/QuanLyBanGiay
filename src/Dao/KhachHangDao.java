/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.KhachHang;
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
public class KhachHangDao {

    public PreparedStatement preparedStatement = null;
    public Connection connection = null;
    public ResultSet resultSet = null;

    public KhachHangDao() throws Exception {
        connection = util.DBContext.getConnection();
    }

    public List<KhachHang> findAll() {
        List<KhachHang> lstNV = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                KhachHang khachHang = new KhachHang(
                        resultSet.getInt("ID_KH"),
                        resultSet.getString("HoTenKH"),
                        resultSet.getString("SDT"),
                        resultSet.getString("DiaChi"),
                        resultSet.getInt("TrangThai")
                );
                lstNV.add(khachHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstNV;
    }

    public int create(KhachHang khachHang) {
        int generatedId = -1;
        String sql = "INSERT INTO KhachHang (HoTenKH, SDT, DiaChi, TrangThai) OUTPUT inserted.ID_KH VALUES (?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, khachHang.getHoTenKH());
            preparedStatement.setString(2, khachHang.getSdt());
            preparedStatement.setString(3, khachHang.getDiaChi());
            preparedStatement.setInt(4, khachHang.getTrangThai());
            ResultSet generatedKeys = preparedStatement.executeQuery();
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    public KhachHang findKhachHangByPhone(String sdt) {
        String sql = "SELECT * FROM KhachHang WHERE SDT = ?";
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, sdt);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new KhachHang(
                        resultSet.getInt("ID_KH"),
                        resultSet.getString("HoTenKH"),
                        resultSet.getString("SDT"),
                        resultSet.getString("DiaChi"),
                        resultSet.getInt("TrangThai")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
