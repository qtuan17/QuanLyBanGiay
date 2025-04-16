/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

public class ChiTietSanPhamView {

    private int idCTSP;
    private String maGiay;
    private String tenGiay;
    private String tenLoai;
    private String tenMau;
    private String tenSize;
    private int soLuong;
    private double giaTien;
    private int trangThai;

    public ChiTietSanPhamView(int idCTSP, String maGiay, String tenGiay, String tenLoai, String tenMau, String tenSize, int soLuong, double giaTien, int trangThai) {
        this.idCTSP = idCTSP;
        this.maGiay = maGiay;
        this.tenGiay = tenGiay;
        this.tenLoai = tenLoai;
        this.tenMau = tenMau;
        this.tenSize = tenSize;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.trangThai = trangThai;
    }

    public int getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(int idCTSP) {
        this.idCTSP = idCTSP;
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

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
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

    @Override
    public String toString() {
        return "ChiTietSanPhamViewModel{"
                + "idCTSP=" + idCTSP
                + ", maGiay='" + maGiay + '\''
                + ", tenGiay='" + tenGiay + '\''
                + ", tenLoai='" + tenLoai + '\''
                + ", tenMau='" + tenMau + '\''
                + ", tenSize='" + tenSize + '\''
                + ", soLuong=" + soLuong
                + ", giaTien=" + giaTien
                + ", trangThai=" + trangThai
                + '}';
    }
}
