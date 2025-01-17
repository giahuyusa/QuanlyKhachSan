/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khachsan.utils;

import com.khachsan.entity.NhanVien;

/**
 *
 * @author nguye
 */
public class Auth {
    // duy tri user
    public static NhanVien user = null;
    public static void clear(){
        Auth.user = null;
    }
    // xac nhan nhan vien
    public static boolean isLogin(){
        return Auth.user != null;
    }

    public static boolean isManager(){
        return Auth.isLogin()&&user.getVaiTro();
    }
}
