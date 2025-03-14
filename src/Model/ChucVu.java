/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tuanb
 */
public class ChucVu {
    private int ID_CV;
    private String TenChucVu;
    private String MoTa;
    private int TrangThai;

    public ChucVu() {
    }

    public ChucVu(int ID_CV, String TenChucVu, String MoTa, int TrangThai) {
        this.ID_CV = ID_CV;
        this.TenChucVu = TenChucVu;
        this.MoTa = MoTa;
        this.TrangThai = TrangThai;
    }

    public int getID_CV() {
        return ID_CV;
    }

    public void setID_CV(int ID_CV) {
        this.ID_CV = ID_CV;
    }

    public String getTenChucVu() {
        return TenChucVu;
    }

    public void setTenChucVu(String TenChucVu) {
        this.TenChucVu = TenChucVu;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
}
