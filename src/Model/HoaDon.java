/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;



/**
 *
 * @author tuanb
 */
public class HoaDon {
    private int ID_HD;
    private int ID_NV;
    private int ID_KH;
    private String TenSP;
    private float GiaTien;
    private float ThanhTien;
    private String PTTT;
    private String NgayTao;
    private int TrangThai;

    public HoaDon() {
    }

    public HoaDon(int ID_HD, int ID_NV, int ID_KH, String TenSP, float GiaTien, float ThanhTien, String PTTT, String NgayTao, int TrangThai) {
        this.ID_HD = ID_HD;
        this.ID_NV = ID_NV;
        this.ID_KH = ID_KH;
        this.TenSP = TenSP;
        this.GiaTien = GiaTien;
        this.ThanhTien = ThanhTien;
        this.PTTT = PTTT;
        this.NgayTao = NgayTao;
        this.TrangThai = TrangThai;
    }

    public HoaDon(int ID_HD, int ID_NV, float ThanhTien, String PTTT, String NgayTao, int TrangThai) {
        this.ID_HD = ID_HD;
        this.ID_NV = ID_NV;
        this.ThanhTien = ThanhTien;
        this.PTTT = PTTT;
        this.NgayTao = NgayTao;
        this.TrangThai = TrangThai;
    }

    public int getID_HD() {
        return ID_HD;
    }

    public void setID_HD(int ID_HD) {
        this.ID_HD = ID_HD;
    }

    public int getID_NV() {
        return ID_NV;
    }

    public void setID_NV(int ID_NV) {
        this.ID_NV = ID_NV;
    }

    public int getID_KH() {
        return ID_KH;
    }

    public void setID_KH(int ID_KH) {
        this.ID_KH = ID_KH;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public float getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(float GiaTien) {
        this.GiaTien = GiaTien;
    }

    public float getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(float ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public String getPTTT() {
        return PTTT;
    }

    public void setPTTT(String PTTT) {
        this.PTTT = PTTT;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
}
