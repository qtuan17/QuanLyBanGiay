/*fghyjuikol;p'
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validator;

import Model.NhanVien;
import javax.swing.JOptionPane;

/**
 *
 * @author tuanb
 */
public class NhanVienValidator {

    public static NhanVien validateNhanVien(NhanVien nv) {
    if (nv == null) {
        JOptionPane.showMessageDialog(null, "Đối tượng nhân viên không được null.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    // Họ tên
    if (nv.getHoTenNV() == null || nv.getHoTenNV().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Họ tên không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    // Ngày sinh
    if (nv.getNgaySinh() == null) {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày sinh.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    // Địa chỉ
    if (nv.getDiaChi() == null || nv.getDiaChi().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    // Số điện thoại
    String sdt = nv.getSdt();
    if (sdt == null || sdt.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return null;
    }
    sdt = sdt.trim();
    if (!sdt.matches("\\d{10}")) {
        JOptionPane.showMessageDialog(null, "Số điện thoại phải gồm đúng 10 chữ số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    // Mật khẩu
    if (nv.getPassword() == null || nv.getPassword().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    // Hợp lệ → chuẩn hoá và return
    nv.setSdt(sdt); // nếu cần chuẩn hoá lại SDT
    return nv;
}

}
