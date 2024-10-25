/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tuanb
 */
public class Size {
    private int ID_Size;
    private String MaSize;
    private String TenSize;
    private int TrangThai;

    public Size() {
    }

    public Size(int ID_Size, String MaSize, String TenSize, int TrangThai) {
        this.ID_Size = ID_Size;
        this.MaSize = MaSize;
        this.TenSize = TenSize;
        this.TrangThai = TrangThai;
    }

    public int getID_Size() {
        return ID_Size;
    }

    public void setID_Size(int ID_Size) {
        this.ID_Size = ID_Size;
    }

    public String getMaSize() {
        return MaSize;
    }

    public void setMaSize(String MaSize) {
        this.MaSize = MaSize;
    }

    public String getTenSize() {
        return TenSize;
    }

    public void setTenSize(String TenSize) {
        this.TenSize = TenSize;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
