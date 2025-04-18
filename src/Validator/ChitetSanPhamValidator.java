/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validator;

import Model.ChiTietSanPham;
import javax.swing.JOptionPane;

/**
 *
 * @author tuanb
 */
public class ChitetSanPhamValidator {

    public static ChiTietSanPham validateChiTietSanPham(ChiTietSanPham ctsp) {
        if (ctsp == null) {
            JOptionPane.showMessageDialog(null, "Chi tiết sản phẩm không được null.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (ctsp.getIdSP() <= 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (ctsp.getIdMau() <= 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn màu sắc hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (ctsp.getIdSize() <= 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn size hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (ctsp.getSoLuong() < 0) {
            JOptionPane.showMessageDialog(null, "Số lượng không được âm hoặc bỏ trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (ctsp.getGiaTien() < 0) {
            JOptionPane.showMessageDialog(null, "Giá tiền không được âm hoặc bỏ trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return ctsp;
    }

}
