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
    private Date ngayTao;
    private double thanhTien;
    private String pttt;      // Phương thức thanh toán
    private int trangThai;

    // Constructor
    public HoaDonView(int idHd, String hoTenNV, String hoTenKH, Date ngayTao, double thanhTien, String pttt, int trangThai) {
        this.idHd = idHd;
        this.hoTenNV = hoTenNV;
        this.hoTenKH = hoTenKH;
        this.ngayTao = ngayTao;
        this.thanhTien = thanhTien;
        this.pttt = pttt;
        this.trangThai = trangThai;
    }

    // Getters and Setters
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

    public String getPttt() {
        return pttt;
    }

    public void setPttt(String pttt) {
        this.pttt = pttt;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    public String trangThaiToString(int trangThai){
        if(trangThai ==1){
            return "Chưa Thanh Toán";
        }else if(trangThai == 2){
            return "Đã Thanh Toán";
        }else if(trangThai == 3){
            return "Đã Huỷ";
        }else{
            return "Đang Cập Nhật";
        }
    }
    @Override
    public String toString() {
        return "HoaDonViewModel{" +
                "idHd=" + idHd +
                ", hoTenNV='" + hoTenNV + '\'' +
                ", hoTenKH='" + hoTenKH + '\'' +
                ", ngayTao='" + ngayTao + '\'' +
                ", thanhTien=" + thanhTien +
                ", pttt='" + pttt + '\'' +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}

