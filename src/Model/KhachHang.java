/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tuanb
 */
public class KhachHang {
    private int ID_KH;
    private String HoTenKH;
    private int SDT;
    private String DiaChi;
    private int TrangThai;

    public KhachHang() {
    }

    public KhachHang(int ID_KH, String HoTenKH, int SDT, String DiaChi, int TrangThai) {
        this.ID_KH = ID_KH;
        this.HoTenKH = HoTenKH;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
        this.TrangThai = TrangThai;
    }

    public int getID_KH() {
        return ID_KH;
    }

    public void setID_KH(int ID_KH) {
        this.ID_KH = ID_KH;
    }

    public String getHoTenKH() {
        return HoTenKH;
    }

    public void setHoTenKH(String HoTenKH) {
        this.HoTenKH = HoTenKH;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
}
