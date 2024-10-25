/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tuanb
 */
public class ChiTietSanPham {
    private int ID_CTSP;
    private String TenGiay;
    private int ID_SP;
    private int ID_Mau;
    private int ID_Size;
    private int ID_Loai;
    private String TenMau;
    private String TenSize;
    private String TenLoai;
    private int SoLuong;
    private float GiaTien;
    private String HinhAnh;
    private String GhiChu;
    private int TrangThai;
    
    
    public ChiTietSanPham() {
    }

    public ChiTietSanPham(int ID_CTSP,String TenGiay, String TenMau, String TenSize, String TenLoai, int SoLuong, float GiaTien, String HinhAnh, String GhiChu, int TrangThai) {
        this.ID_CTSP = ID_CTSP;
        this.TenGiay= TenGiay;
        this.TenMau = TenMau;
        this.TenSize = TenSize;
        this.TenLoai = TenLoai;
        this.SoLuong = SoLuong;
        this.GiaTien = GiaTien;
        this.HinhAnh = HinhAnh;
        this.GhiChu = GhiChu;
        this.TrangThai = TrangThai;
    }

    

    
    
    public ChiTietSanPham(int ID_CTSP, int ID_SP, int ID_Mau, int ID_Size, int ID_Loai, int SoLuong, float GiaTien, String HinhAnh, String GhiChu, int TrangThai) {
        this.ID_CTSP = ID_CTSP;
        this.ID_SP = ID_SP;
        this.ID_Mau = ID_Mau;
        this.ID_Size = ID_Size;
        this.ID_Loai = ID_Loai;
        this.SoLuong = SoLuong;
        this.GiaTien = GiaTien;
        this.HinhAnh = HinhAnh;
        this.GhiChu = GhiChu;
        this.TrangThai = TrangThai;
    }

    public int getID_CTSP() {
        return ID_CTSP;
    }

    public void setID_CTSP(int ID_CTSP) {
        this.ID_CTSP = ID_CTSP;
    }

    public String getTenGiay() {
        return TenGiay;
    }

    public void setTenGiay(String TenGiay) {
        this.TenGiay = TenGiay;
    }

    public int getID_SP() {
        return ID_SP;
    }

    public void setID_SP(int ID_SP) {
        this.ID_SP = ID_SP;
    }

    public int getID_Mau() {
        return ID_Mau;
    }

    public void setID_Mau(int ID_Mau) {
        this.ID_Mau = ID_Mau;
    }

    public int getID_Size() {
        return ID_Size;
    }

    public void setID_Size(int ID_Size) {
        this.ID_Size = ID_Size;
    }

    public int getID_Loai() {
        return ID_Loai;
    }

    public void setID_Loai(int ID_Loai) {
        this.ID_Loai = ID_Loai;
    }

    public String getTenMau() {
        return TenMau;
    }

    public void setTenMau(String TenMau) {
        this.TenMau = TenMau;
    }

    public String getTenSize() {
        return TenSize;
    }

    public void setTenSize(String TenSize) {
        this.TenSize = TenSize;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(float GiaTien) {
        this.GiaTien = GiaTien;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    
    
    
}
