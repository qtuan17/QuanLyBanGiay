/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validator;

import Model.KhachHang;
import javax.swing.JOptionPane;

/**
 *
 * @author tuanb
 */
public class KhachHangValidator {

    public static KhachHang validatorKhachHang(KhachHang khachHang) {
        if (khachHang.getHoTenKH().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Họ Tên Khách Hàng Không Được Để Trống", "Thông Báo Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        } else if (khachHang.getDiaChi().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Địa Chỉ Khách Hàng Không Được Để Trống", "Thông Báo Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        } else if (khachHang.getSdt().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Số Điện Thoại Khách Hàng Không Được Để Trống", "Thông Báo Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return khachHang;
    }

    public static String validatorSDT(String sdt) {
        // Kiểm tra độ dài
        if (sdt.length() != 10 && sdt.length() != 11) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải có 10 hoặc 11 chữ số", "Thông Báo Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Kiểm tra có phải toàn số không
        if (!sdt.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại chỉ được chứa chữ số", "Thông Báo Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Kiểm tra đầu số (tuỳ chọn)
        if (!sdt.startsWith("03") && !sdt.startsWith("05") && !sdt.startsWith("07")
                && !sdt.startsWith("08") && !sdt.startsWith("09")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không đúng định dạng đầu số", "Thông Báo Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Nếu qua hết thì hợp lệ
        return sdt;
    }
}
