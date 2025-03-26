/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tuanb
 */
public class Loai {
    private int ID_Loai;
    private String TenLoai;
    private int TrangThai;

    public Loai() {
    }

    public Loai(int ID_Loai, String TenLoai, int TrangThai) {
        this.ID_Loai = ID_Loai;
        this.TenLoai = TenLoai;
        this.TrangThai = TrangThai;
    }

    public int getID_Loai() {
        return ID_Loai;
    }

    public void setID_Loai(int ID_Loai) {
        this.ID_Loai = ID_Loai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    public String toString(){
        return TenLoai;
    }
    
}
