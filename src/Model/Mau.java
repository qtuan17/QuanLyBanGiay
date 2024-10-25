/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tuanb
 */
public class Mau {
    private int ID_Mau;
    private String MaMau;
    private String TenMau;
    private int TrangThai;

    public Mau() {
    }

    public Mau(int ID_Mau, String MaMau, String TenMau, int TrangThai) {
        this.ID_Mau = ID_Mau;
        this.MaMau = MaMau;
        this.TenMau = TenMau;
        this.TrangThai = TrangThai;
    }

    public int getID_Mau() {
        return ID_Mau;
    }

    public void setID_Mau(int ID_Mau) {
        this.ID_Mau = ID_Mau;
    }

    public String getMaMau() {
        return MaMau;
    }

    public void setMaMau(String MaMau) {
        this.MaMau = MaMau;
    }

    public String getTenMau() {
        return TenMau;
    }

    public void setTenMau(String TenMau) {
        this.TenMau = TenMau;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    
    
    
}
