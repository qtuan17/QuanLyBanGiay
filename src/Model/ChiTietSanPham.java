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
    private int soLuong;
    private double giaTien;
    private int trangThai;

    public ChiTietSanPham(int idCTSP, int idSP, int idMau, int idSize, int soLuong, double giaTien, int trangThai) {
        this.idCTSP = idCTSP;
        this.idSP = idSP;
        this.idMau = idMau;
        this.idSize = idSize;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.trangThai = trangThai;
    }

    public ChiTietSanPham(int idSP, int idMau, int idSize, int soLuong, double giaTien, int trangThai) {
        this.idSP = idSP;
        this.idMau = idMau;
        this.idSize = idSize;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.trangThai = trangThai;
    }
    
    public ChiTietSanPham() {
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
               ", SoLuong=" + soLuong + ", GiaTien=" + giaTien + ", TrangThai=" + trangThai + "]";
    }
}

