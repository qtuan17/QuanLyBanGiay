/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import Model.NhanVien;

/**
 *
 * @author tuanb
 */
public class SessionLogin {
    private static NhanVien nhanVienLogin = null;
    public static void setNhanVienLogin(NhanVien nhanVien){
        nhanVienLogin = nhanVien;
    }
    public static NhanVien getNhanVienLogin(){
        return nhanVienLogin;
    }
    public static boolean isLogin(){
        if(nhanVienLogin == null){
            return false;
        }
        return true;
    }
    public static void logout(){
        nhanVienLogin = null;
    }
}
