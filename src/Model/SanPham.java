/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tuanb
 */
public class SanPham {
    private int ID_SP;
    private String MaGiay;
    private String TenGiay;
    private int ID_Loai;
    private int TrangThai;

    public SanPham() {
    }

    public SanPham(int ID_SP, String MaGiay, String TenGiay, int ID_Loai, int TrangThai) {
        this.ID_SP = ID_SP;
        this.MaGiay = MaGiay;
        this.TenGiay = TenGiay;
        this.ID_Loai = ID_Loai;
        this.TrangThai = TrangThai;
    }

    public int getID_SP() {
        return ID_SP;
    }

    public void setID_SP(int ID_SP) {
        this.ID_SP = ID_SP;
    }

    public String getMaGiay() {
        return MaGiay;
    }

    public void setMaGiay(String MaGiay) {
        this.MaGiay = MaGiay;
    }

    public String getTenGiay() {
        return TenGiay;
    }

    public void setTenGiay(String TenGiay) {
        this.TenGiay = TenGiay;
    }

    public int getID_Loai() {
        return ID_Loai;
    }

    public void setID_Loai(int ID_Loai) {
        this.ID_Loai = ID_Loai;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
    
    
}
