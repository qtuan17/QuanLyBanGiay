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
import viewModel.ChiTietSanPhamView;

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

    public List<ChiTietSanPhamView> findAll() {
        List<ChiTietSanPhamView> chiTietSanPhams = new ArrayList<>();
        String sql = "SELECT \n"
                + "	ctsp.ID_CTSP, \n"
                + "	sp.MaGiay, \n"
                + "	sp.TenGiay, \n"
                + "	l.TenLoai,\n"
                + "	m.TenMau,\n"
                + "	s.TenSize,\n"
                + "	ctsp.SoLuong, \n"
                + "	ctsp.GiaTien, \n"
                + "	ctsp.HinhAnh,\n"
                + "	ctsp.TrangThai\n"
                + "FROM ChiTietSanPham ctsp\n"
                + "JOIN SanPham sp on ctsp.ID_SP = sp.ID_SP\n"
                + "JOIN Loai l on l.ID_Loai = ctsp.ID_Loai\n"
                + "JOIN Mau m on m.ID_Mau = ctsp.ID_Mau\n"
                + "JOIN size s on s.ID_Size = ctsp.ID_Size";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ChiTietSanPhamView ctsp = new ChiTietSanPhamView(
                        resultSet.getInt(1), 
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getString(9),
                        resultSet.getInt(10)
                );
                chiTietSanPhams.add(ctsp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }

    public boolean checkConHang(int idSanPham) {
        String query = "SELECT * FROM ChiTietSua WHERE ID_CTSP = ? and SoLuong > 0";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, idSanPham);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("looix check gio hang: " + e);
            return false;
        }
    }
}
