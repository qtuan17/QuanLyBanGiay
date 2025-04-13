/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class ChiTietHoaDon {
    private int idCTHD;
    private int idHD;
    private int idCTSP;
    private int soLuong;
    private double donGia;
    private double thanhTien;
    private int trangThai;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int idCTHD, int idHD, int idCTSP, int soLuong, double donGia, double thanhTien, int trangThai) {
        this.idCTHD = idCTHD;
        this.idHD = idHD;
        this.idCTSP = idCTSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    

    // Getters and Setters
    public int getIdCTHD() {
        return idCTHD;
    }

    public void setIdCTHD(int idCTHD) {
        this.idCTHD = idCTHD;
    }

    public int getIdHD() {
        return idHD;
    }

    public void setIdHD(int idHD) {
        this.idHD = idHD;
    }

    public int getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(int idCTSP) {
        this.idCTSP = idCTSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    // toString() method to display information
    @Override
    public String toString() {
        return "ChiTietHoaDon [ID_CTHD=" + idCTHD + ", ID_HD=" + idHD + ", ID_CTSP=" + idCTSP + ", SoLuong=" + soLuong +
               ", DonGia=" + donGia + ", ThanhTien=" + thanhTien + ", TrangThai=" + trangThai + "]";
    }
}

