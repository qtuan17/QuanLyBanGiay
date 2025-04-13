/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.HoaDon;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import viewModel.HoaDonView;

/**
 *
 * @author tuanb
 */
public class HoaDonDao {

    public PreparedStatement preparedStatement = null;
    public Connection connection = null;
    public ResultSet resultSet = null;

    public HoaDonDao() throws Exception {
        connection = util.DBContext.getConnection();
    }

    public List<HoaDonView> findAll() {
        List<HoaDonView> hoaDons = new ArrayList<>();
        String sql = ""
                + "Select\n"
                + "	hd.ID_HD,\n"
                + "	nv.HoTenNV,\n"
                + "	kh.HoTenKH,\n"
                + "	kh.SDT,\n"
                + "	kh.DiaChi,\n"
                + "	hd.NgayTao,\n"
                + "	hd.ThanhTien,\n"
                + "	hd.TrangThai\n"
                + "From HoaDon hd\n"
                + "join NhanVien nv on hd.ID_NV = nv.ID_NV\n"
                + "join KhachHang kh on kh.ID_KH = hd.ID_KH";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                HoaDonView hoadon = new HoaDonView(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getDouble(7),
                        resultSet.getInt(8)
                );
                hoaDons.add(hoadon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDons;
    }

    public int insert(HoaDon hoaDon) {
        String sql = "INSERT INTO HoaDon (ID_NV, ID_KH, ThanhTien, NgayTao, TrangThai) VALUES (?, ?, ?, ?, ?)";
        System.out.println(hoaDon.toString());
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, hoaDon.getIdNV());
            ps.setInt(2, hoaDon.getIdKH());
            ps.setDouble(3, 0);
            ps.setDate(4, Date.valueOf(LocalDate.now()));
            ps.setInt(5, 1);
            int affectedRows = ps.executeUpdate();
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // insert thất bại
    }

    public int thanhToan(HoaDon hoaDon) {
        String sql = "UPDATE HoaDon SET ThanhTien = ?, TrangThai = ? WHERE ID_HD = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, hoaDon.getThanhTien());
            ps.setInt(2, 2);
            ps.setInt(3, hoaDon.getIdHD());
            return ps.executeUpdate(); // Trả về số dòng bị ảnh hưởng
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
