/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Mau {

    private int idMau;
    private String tenMau;
    private int trangThai;

    public Mau() {
    }

    public Mau(int idMau, String tenMau, int trangThai) {
        this.idMau = idMau;
        this.tenMau = tenMau;
        this.trangThai = trangThai;
    }

    // Getters and Setters
    public int getIdMau() {
        return idMau;
    }

    public void setIdMau(int idMau) {
        this.idMau = idMau;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    // toString() method to display information
    @Override
    public String toString() {
        return tenMau;
    }
}
