/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khachsan.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author nguye
 */
public class XDate {
    // Chuyển đổi String thành Date
    public static Date toDate(String date, String pattern) {
        try {
            SimpleDateFormat formater = new SimpleDateFormat(pattern);
            return formater.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    // Chuyển đổi Date thành String
    public static String toString(Date date, String pattern) {
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        return formater.format(date);
    }
}

