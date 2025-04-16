/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import java.util.Date;

public class HoaDonView {

    private int idHd;
    private String hoTenNV;
    private String hoTenKH;
    private String sdt;
    private String diaChi;
    private Date ngayTao;
    private double thanhTien;
    private int trangThai;

    public HoaDonView(int idHd, String hoTenNV, String hoTenKH, Date ngayTao, double thanhTien, int trangThai) {
        this.idHd = idHd;
        this.hoTenNV = hoTenNV;
        this.hoTenKH = hoTenKH;
        this.ngayTao = ngayTao;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
    }

    public HoaDonView(int idHd, String hoTenNV, String hoTenKH, String sdt, String diaChi, Date ngayTao, double thanhTien, int trangThai) {
        this.idHd = idHd;
        this.hoTenNV = hoTenNV;
        this.hoTenKH = hoTenKH;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.ngayTao = ngayTao;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
    }

    public int getIdHd() {
        return idHd;
    }

    public void setIdHd(int idHd) {
        this.idHd = idHd;
    }

    public String getHoTenNV() {
        return hoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        this.hoTenNV = hoTenNV;
    }

    public String getHoTenKH() {
        return hoTenKH;
    }

    public void setHoTenKH(String hoTenKH) {
        this.hoTenKH = hoTenKH;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String trangThaiToString(int trangThai) {
        if (trangThai == 0) {
            return "Chưa Thanh Toán";
        } else if (trangThai == 1) {
            return "Đã Thanh Toán";
        } else if (trangThai == 2) {
            return "Đã Huỷ";
        } else if (trangThai == 3) {
            return "Đang Chờ";
        } else {
            return "Đang Cập Nhật";
        }
    }

    @Override
    public String toString() {
        return "HoaDonViewModel{"
                + "idHd=" + idHd
                + ", hoTenNV='" + hoTenNV + '\''
                + ", hoTenKH='" + hoTenKH + '\''
                + ", sdt='" + sdt + '\''
                + ", diaChi='" + diaChi + '\''
                + ", ngayTao='" + ngayTao + '\''
                + ", thanhTien=" + thanhTien
                + ", trangThai='" + trangThai + '\''
                + '}';
    }
}
