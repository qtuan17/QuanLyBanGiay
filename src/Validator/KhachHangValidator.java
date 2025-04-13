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
    
    public static KhachHang validatorKhachHang(KhachHang khachHang){
        if(khachHang.getHoTenKH().isEmpty()){
            JOptionPane.showMessageDialog(null, "Họ Tên Khách Hàng Không Được Để Trống","Thông Báo Lỗi",JOptionPane.ERROR_MESSAGE);
            return null;
        }else if(khachHang.getDiaChi().isEmpty()){
            JOptionPane.showMessageDialog(null, "Địa Chỉ Khách Hàng Không Được Để Trống","Thông Báo Lỗi",JOptionPane.ERROR_MESSAGE);
            return null;
        }else if(khachHang.getSdt().isEmpty()){
            JOptionPane.showMessageDialog(null, "Số Điện Thoại Khách Hàng Không Được Để Trống","Thông Báo Lỗi",JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return khachHang;
    }
}
