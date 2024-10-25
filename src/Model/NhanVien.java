/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;


/**
 *
 * @author tuanb
 */
public class NhanVien {
    private int ID_NV;
    private int ID_CV;
    private String HoTenNV;
    private Date Ngaysinh;
    private int CCCD;
    private String DiaChi;
    private String Username;
    private String Password;
    private int TrangThai;

    public NhanVien() {
    }

    public NhanVien(int ID_NV, int ID_CV, String HoTenNV, Date Ngaysinh, int CCCD, String DiaChi, String Username, String Password, int TrangThai) {
        this.ID_NV = ID_NV;
        this.ID_CV = ID_CV;
        this.HoTenNV = HoTenNV;
        this.Ngaysinh = Ngaysinh;
        this.CCCD = CCCD;
        this.DiaChi = DiaChi;
        this.Username = Username;
        this.Password = Password;
        this.TrangThai = TrangThai;
    }

    public int getID_NV() {
        return ID_NV;
    }

    public void setID_NV(int ID_NV) {
        this.ID_NV = ID_NV;
    }

    public int getID_CV() {
        return ID_CV;
    }

    public void setID_CV(int ID_CV) {
        this.ID_CV = ID_CV;
    }

    public String getHoTenNV() {
        return HoTenNV;
    }

    public void setHoTenNV(String HoTenNV) {
        this.HoTenNV = HoTenNV;
    }

    public Date getNgaysinh() {
        return Ngaysinh;
    }

    public void setNgaysinh(Date Ngaysinh) {
        this.Ngaysinh = Ngaysinh;
    }

    public int getCCCD() {
        return CCCD;
    }

    public void setCCCD(int CCCD) {
        this.CCCD = CCCD;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    
    
    
}
