/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class SanPham {
    private int idSP;
    private String maGiay;
    private String tenGiay;
    private int idLoai;
    private int trangThai;

    public SanPham(int idSP, String maGiay, String tenGiay, int idLoai, int trangThai) {
        this.idSP = idSP;
        this.maGiay = maGiay;
        this.tenGiay = tenGiay;
        this.idLoai = idLoai;
        this.trangThai = trangThai;
    }

    // Getters and Setters
    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public String getMaGiay() {
        return maGiay;
    }

    public void setMaGiay(String maGiay) {
        this.maGiay = maGiay;
    }

    public String getTenGiay() {
        return tenGiay;
    }

    public void setTenGiay(String tenGiay) {
        this.tenGiay = tenGiay;
    }

    public int getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(int idLoai) {
        this.idLoai = idLoai;
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
        return tenGiay;
    }
}

