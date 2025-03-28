/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class ChiTietSanPham {
    private int idCTSP;
    private int idSP;
    private int idMau;
    private int idSize;
    private int idLoai;
    private int soLuong;
    private double giaTien;
    private String hinhAnh;
    private String ghiChu;
    private int trangThai;

    public ChiTietSanPham(int idCTSP, int idSP, int idMau, int idSize, int idLoai, int soLuong, double giaTien, String hinhAnh, String ghiChu, int trangThai) {
        this.idCTSP = idCTSP;
        this.idSP = idSP;
        this.idMau = idMau;
        this.idSize = idSize;
        this.idLoai = idLoai;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.hinhAnh = hinhAnh;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    // Getters and Setters
    public int getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(int idCTSP) {
        this.idCTSP = idCTSP;
    }

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public int getIdMau() {
        return idMau;
    }

    public void setIdMau(int idMau) {
        this.idMau = idMau;
    }

    public int getIdSize() {
        return idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    public int getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(int idLoai) {
        this.idLoai = idLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
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
        return "ChiTietSanPham [ID_CTSP=" + idCTSP + ", ID_SP=" + idSP + ", ID_Mau=" + idMau + ", ID_Size=" + idSize + 
               ", ID_Loai=" + idLoai + ", SoLuong=" + soLuong + ", GiaTien=" + giaTien + ", HinhAnh=" + hinhAnh + 
               ", GhiChu=" + ghiChu + ", TrangThai=" + trangThai + "]";
    }
}

