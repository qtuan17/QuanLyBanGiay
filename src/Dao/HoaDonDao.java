/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.ChiTietHoaDon;
import Model.ChiTietSanPham;
import Model.HoaDon;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import viewModel.HoaDonView;
import viewModel.ThongKe;

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

    public List<HoaDonView> findAllHoaDon() {
        System.out.println("→ Gọi phương thức: findAllHoaDon() - Lấy danh sách toàn bộ hóa đơn");
        List<HoaDonView> hoaDons = new ArrayList<>();
        String sql = """
            SELECT hd.ID_HD, nv.HoTenNV, kh.HoTenKH, kh.SDT, kh.DiaChi, hd.NgayTao, hd.ThanhTien, hd.TrangThai
            FROM HoaDon hd
            JOIN NhanVien nv ON hd.ID_NV = nv.ID_NV
            JOIN KhachHang kh ON kh.ID_KH = hd.ID_KH
        """;

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

    public List<ChiTietHoaDon> findCTHDByIDHD(int idHD) {
        System.out.println("→ Gọi phương thức: findCTHDByIDHD() với ID_HD = " + idHD);
        List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();
        String sql = """
            SELECT 
                cthd.ID_CTHD,
                cthd.ID_HD,
                cthd.ID_CTSP,
                sp.TenGiay AS TenSP,
                cthd.SoLuong,
                cthd.DonGia,
                cthd.ThanhTien,
                cthd.TrangThai
            FROM ChiTietHoaDon cthd
            JOIN ChiTietSanPham ctsp ON cthd.ID_CTSP = ctsp.ID_CTSP
            JOIN SanPham sp ON ctsp.ID_SP = sp.ID_SP
            WHERE cthd.ID_HD = ?
        """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idHD);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ChiTietHoaDon chiTiet = new ChiTietHoaDon(
                            resultSet.getInt("ID_CTHD"),
                            resultSet.getInt("ID_HD"),
                            resultSet.getInt("ID_CTSP"),
                            resultSet.getString("TenSP"),
                            resultSet.getInt("SoLuong"),
                            resultSet.getDouble("DonGia"),
                            resultSet.getDouble("ThanhTien"),
                            resultSet.getInt("TrangThai")
                    );
                    chiTietHoaDons.add(chiTiet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chiTietHoaDons;
    }

    public int insert(HoaDon hoaDon) {
        System.out.println("→ Gọi phương thức: insert() - Thêm hóa đơn mới");
        String sql = "INSERT INTO HoaDon (ID_NV, ID_KH, ThanhTien, NgayTao, TrangThai) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, hoaDon.getIdNV());
            ps.setInt(2, hoaDon.getIdKH());
            ps.setDouble(3, 0);
            ps.setDate(4, Date.valueOf(LocalDate.now()));
            ps.setInt(5, 3);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int thanhToan(HoaDon hoaDon) {
        System.out.println("→ Gọi phương thức: thanhToan() - Cập nhật thanh toán cho hóa đơn");
        String sql = "UPDATE HoaDon SET ThanhTien = ?, TrangThai = ? WHERE ID_HD = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, hoaDon.getThanhTien());
            ps.setInt(2, 1);
            ps.setInt(3, hoaDon.getIdHD());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int setHoaDonCho(HoaDon hoaDon) {
        System.out.println("→ Gọi phương thức: setHoaDonCho() - Đặt trạng thái hóa đơn chờ");
        String sql = "UPDATE HoaDon SET ThanhTien = ?, TrangThai = ? WHERE ID_HD = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, hoaDon.getThanhTien());
            ps.setInt(2, 3);
            ps.setInt(3, hoaDon.getIdHD());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean huyHoaDon(int idHD) {
        System.out.println("→ Gọi phương thức: huyHoaDon() với ID_HD = " + idHD);
        String sql = "UPDATE HoaDon SET TrangThai = 2 WHERE ID_HD = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idHD);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void rollbackSanPhamTrongHoaDon(int idHD) {
        System.out.println("→ Gọi phương thức: rollbackSanPhamTrongHoaDon() với ID_HD = " + idHD);
        String sql = "SELECT ID_CTSP, SoLuong FROM ChiTietHoaDon WHERE ID_HD = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idHD);
            ResultSet rs = ps.executeQuery();

            ChiTietSanPhamDao ctspDao = new ChiTietSanPhamDao();

            while (rs.next()) {
                int idCTSP = rs.getInt("ID_CTSP");
                int soLuongTra = rs.getInt("SoLuong");

                int soLuongHienTai = ctspDao.getSoLuongById(idCTSP);
                int soLuongMoi = soLuongHienTai + soLuongTra;

                ChiTietSanPham ctsp = new ChiTietSanPham();
                ctsp.setIdCTSP(idCTSP);
                ctsp.setSoLuong(soLuongMoi);

                ctspDao.updateSoLuong(ctsp);

                if (soLuongMoi > 0) {
                    ctspDao.updateTrangThaiConHang(idCTSP);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ThongKe thongKeTheoKhoangNgay(LocalDate tuNgay, LocalDate denNgay) {
        System.out.println("→ Gọi phương thức: thongKeTheoKhoangNgay()");
        String sql = """
            SELECT COUNT(*) AS SoLuongHD, SUM(ThanhTien) AS TongTien
            FROM HoaDon
            WHERE NgayTao >= ? AND NgayTao <= ? AND TrangThai = 1
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(tuNgay));
            ps.setDate(2, java.sql.Date.valueOf(denNgay));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new ThongKe(rs.getInt("SoLuongHD"), rs.getDouble("TongTien"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ThongKe(0, 0);
    }

    public Map<String, Double> getDoanhThuTheoTungThangTrongNam() {
        System.out.println("→ Gọi phương thức: getDoanhThuTheoTungThangTrongNam()");
        Map<String, Double> data = new LinkedHashMap<>();
        String sql = """
            SELECT FORMAT(NgayTao, 'MM/yyyy') AS Thang, SUM(ThanhTien) AS TongTien
            FROM HoaDon
            WHERE NgayTao >= DATEADD(MONTH, -11, GETDATE()) AND TrangThai = 1
            GROUP BY FORMAT(NgayTao, 'MM/yyyy')
            ORDER BY Thang
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                data.put(rs.getString("Thang"), rs.getDouble("TongTien"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ThongKe thongKeTheoNhanVienVaKhoangNgay(int idNV, LocalDate tuNgay, LocalDate denNgay) {
        System.out.println("→ Gọi phương thức: thongKeTheoNhanVienVaKhoangNgay() với ID_NV = " + idNV);
        String sql = """
            SELECT COUNT(*) AS SoHoaDon, SUM(ThanhTien) AS TongDoanhThu
            FROM HoaDon
            WHERE ID_NV = ? AND NgayTao BETWEEN ? AND ?
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idNV);
            ps.setDate(2, Date.valueOf(tuNgay));
            ps.setDate(3, Date.valueOf(denNgay));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ThongKe(rs.getInt("SoHoaDon"), rs.getDouble("TongDoanhThu"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ThongKe(0, 0);
    }
}
