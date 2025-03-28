/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

public class NhanVien {

    private int idNV;
    private int idCV;
    private String hoTenNV;
    private Date ngaySinh;
    private String diaChi;
    private String sdt;
    private String password;
    private int trangThai;
    private boolean role;

    public NhanVien(int idNV, int idCV, String hoTenNV, Date ngaySinh, String diaChi, String sdt, String password, int trangThai) {
        this.idNV = idNV;
        this.idCV = idCV;
        this.hoTenNV = hoTenNV;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.password = password;
        this.trangThai = trangThai;
        this.role = role;
    }

    public NhanVien() {
    }
    
    // Getters and Setters
    public int getIdNV() {
        return idNV;
    }

    public void setIdNV(int idNV) {
        this.idNV = idNV;
    }

    public int getIdCV() {
        return idCV;
    }

    public void setIdCV(int idCV) {
        this.idCV = idCV;
    }

    public String getHoTenNV() {
        return hoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        this.hoTenNV = hoTenNV;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    // toString() method to display information
    @Override
    public String toString() {
        return "NhanVien [ID_NV=" + idNV + ", ID_CV=" + idCV + ", HoTenNV=" + hoTenNV + ", NgaySinh=" + ngaySinh
                + ", DiaChi=" + diaChi + ", SDT=" + sdt + ", Password=" + password + ", TrangThai=" + trangThai
                + ", Role=" + role + "]";
    }
}
