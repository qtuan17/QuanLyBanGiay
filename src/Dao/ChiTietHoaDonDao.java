/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.ChiTietHoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tuanb
 */
public class ChiTietHoaDonDao {

    public PreparedStatement preparedStatement = null;
    public Connection connection = null;
    public ResultSet resultSet = null;

    public ChiTietHoaDonDao() throws Exception {
        connection = util.DBContext.getConnection();
    }

    public int insertChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        String sql = "INSERT INTO ChiTietHoaDon (ID_HD, ID_CTSP, SoLuong, DonGia, ThanhTien, TrangThai) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, chiTietHoaDon.getIdHD()); 
            ps.setInt(2, chiTietHoaDon.getIdCTSP());                  
            ps.setInt(3, chiTietHoaDon.getSoLuong());
            ps.setDouble(4, chiTietHoaDon.getDonGia());
            ps.setDouble(5, chiTietHoaDon.getThanhTien());
            ps.setInt(6, 1);                           
            int rows = ps.executeUpdate();
            if (rows > 0) {
                return rows;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
