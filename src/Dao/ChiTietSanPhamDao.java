/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.ChiTietSanPham;
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
public class ChiTietSanPhamDao {

    public PreparedStatement preparedStatement = null;
    public Connection connection = null;
    public ResultSet resultSet = null;

    public ChiTietSanPhamDao() throws Exception {
        connection = util.DBContext.getConnection();
    }

    public List<ChiTietSanPham> findAll() {
        List<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        String sql = "SELECT \n" +
"		ctsp.ID_CTSP,\n" +
"		sp.TenGiay,\n" +
"		m.TenMau,\n" +
"		s.TenSize,\n" +
"		l.TenLoai,\n" +
"		ctsp.SoLuong,\n" +
"		ctsp.GiaTien,\n" +
"		ctsp.HinhAnh,\n" +
"		ctsp.GhiChu,\n" +
"		ctsp.TrangThai  \n" +
"	FROM ChiTietSanPham ctsp\n" +
"	JOIN SanPham sp on ctsp.ID_SP = sp.ID_SP\n" +
"	JOIN Mau m on ctsp.ID_Mau = m.ID_Mau\n" +
"	JOIN Size s on ctsp.ID_Size = s.ID_Size\n" +
"	JOIN Loai l on ctsp.ID_Loai = l.ID_Loai";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham(
                        resultSet.getInt("ID_CTSP"),
                        resultSet.getString("TenGiay"),
                        resultSet.getString("TenMau"),
                        resultSet.getString("TenSize"),
                        resultSet.getString("TenLoai"),
                        resultSet.getInt("SoLuong"),
                        resultSet.getFloat("GiaTien"),
                        resultSet.getString("HinhAnh"),
                        resultSet.getString("GhiChu"),
                        resultSet.getInt("TrangThai")
                );
                chiTietSanPhams.add(chiTietSanPham);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }
}
