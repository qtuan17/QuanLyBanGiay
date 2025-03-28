/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class KhachHang {
    private int idKH;
    private String hoTenKH;
    private String sdt;
    private String diaChi;
    private int trangThai;

    public KhachHang(int idKH, String hoTenKH, String sdt, String diaChi, int trangThai) {
        this.idKH = idKH;
        this.hoTenKH = hoTenKH;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
    }

    // Getters and Setters
    public int getIdKH() {
        return idKH;
    }

    public void setIdKH(int idKH) {
        this.idKH = idKH;
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

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    // toString() method to display information
    @Override
    public String toString() {
        return "KhachHang [ID_KH=" + idKH + ", HoTenKH=" + hoTenKH + ", SDT=" + sdt + ", DiaChi=" + diaChi + 
               ", TrangThai=" + trangThai + "]";
    }
}

