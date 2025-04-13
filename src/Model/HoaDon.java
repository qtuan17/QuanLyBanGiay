/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;



public class HoaDon {
    private int idHD;
    private int idNV;
    private int idKH;
    private double thanhTien;
    private String ngayTao;
    private int trangThai;

    public HoaDon() {
    }
    

    public HoaDon(int idHD, int idNV, int idKH, String tenSP, double giaTien, double thanhTien, String ngayTao, int trangThai) {
        this.idHD = idHD;
        this.idNV = idNV;
        this.idKH = idKH;
        this.thanhTien = thanhTien;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

    public HoaDon(int idNV, int idKH, double thanhTien, String ngayTao, int trangThai) {
        this.idNV = idNV;
        this.idKH = idKH;
        this.thanhTien = thanhTien;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

    // Getters and Setters
    public int getIdHD() {
        return idHD;
    }

    public void setIdHD(int idHD) {
        this.idHD = idHD;
    }

    public int getIdNV() {
        return idNV;
    }

    public void setIdNV(int idNV) {
        this.idNV = idNV;
    }

    public int getIdKH() {
        return idKH;
    }

    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

        public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    // toString() method to display information
    @Override
    public String toString() {
        return "HoaDon [ID_HD=" + idHD + ", ID_NV=" + idNV + ", ID_KH=" + idKH + ", ThanhTien=" + thanhTien + ", NgayTao=" + ngayTao + ", TrangThai=" + trangThai + "]";
    }
}


