/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;



public class HoaDon {
    private int idHD;
    private int idNV;
    private int idKH;
    private String tenSP;
    private double giaTien;
    private double thanhTien;
    private String pttt;
    private String ngayTao;
    private int trangThai;

    public HoaDon() {
    }
    

    public HoaDon(int idHD, int idNV, int idKH, String tenSP, double giaTien, double thanhTien, String pttt, String ngayTao, int trangThai) {
        this.idHD = idHD;
        this.idNV = idNV;
        this.idKH = idKH;
        this.tenSP = tenSP;
        this.giaTien = giaTien;
        this.thanhTien = thanhTien;
        this.pttt = pttt;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

    public HoaDon(int idHD, int idNV, double thanhTien, String pttt, String ngayTao, int trangThai) {
        this.idHD = idHD;
        this.idNV = idNV;
        this.thanhTien = thanhTien;
        this.pttt = pttt;
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

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
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
        return "HoaDon [ID_HD=" + idHD + ", ID_NV=" + idNV + ", ID_KH=" + idKH + ", TenSP=" + tenSP + ", GiaTien=" + giaTien +
               ", ThanhTien=" + thanhTien + ", PTTT=" + pttt + ", NgayTao=" + ngayTao + ", TrangThai=" + trangThai + "]";
    }
}


