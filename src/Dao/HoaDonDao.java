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
                + "	hd.NgayTao,\n"
                + "	hd.ThanhTien,\n"
                + "	hd.PTTT,\n"
                + "	hd.TrangThai\n"
                + "From HoaDon hd\n"
                + "join NhanVien nv on hd.ID_NV = nv.ID_NV\n"
                + "join KhachHang kh on kh.ID_KH = hd.ID_KH";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql); 
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                HoaDonView hoadon = new HoaDonView(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4),
                        resultSet.getDouble(5),
                        resultSet.getString(6),
                        resultSet.getInt(7)
                );
                hoaDons.add(hoadon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDons;
    }
}
