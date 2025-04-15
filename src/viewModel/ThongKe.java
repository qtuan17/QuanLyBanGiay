/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

/**
 *
 * @author tuanb
 */
public class ThongKe {

    private int soHoaDon;
    private double tongDoanhThu;

    public ThongKe(int soHoaDon, double tongDoanhThu) {
        this.soHoaDon = soHoaDon;
        this.tongDoanhThu = tongDoanhThu;
    }

    public int getSoHoaDon() {
        return soHoaDon;
    }

    public double getTongDoanhThu() {
        return tongDoanhThu;
    }
}
