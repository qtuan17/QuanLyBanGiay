    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Loai {
    private int idLoai;
    private String tenLoai;
    private int trangThai;

    public Loai() {
    }

    public Loai(int idLoai, String tenLoai, int trangThai) {
        this.idLoai = idLoai;
        this.tenLoai = tenLoai;
        this.trangThai = trangThai;
    }

    // Getters and Setters
    public int getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(int idLoai) {
        this.idLoai = idLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
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
        return tenLoai;
    }
}

