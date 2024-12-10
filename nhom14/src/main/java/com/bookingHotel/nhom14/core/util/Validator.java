/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingHotel.nhom14.core.util;

/**
 *
 * @author KhanhDzai - https://www.facebook.com/khanhdepzai.pro/
 */
public class Validator {

    public static boolean isInvalidPhoneNumber(String phoneNumber) {
        // Biểu thức chính quy để kiểm tra số điện thoại Việt Nam
        String regex = "^(0|\\+84)(3[2-9]|5[6|8|9]|7[0|6-9]|8[1-5]|9[0-9])[0-9]{7}$";
        return !(phoneNumber != null && phoneNumber.matches(regex));
    }

}
