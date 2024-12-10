/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingHotel.nhom14.exception;

import lombok.Getter;

/**
 *
 * @author KhanhDzai - https://www.facebook.com/khanhdepzai.pro/
 */
public class ApiException extends RuntimeException {

    public final static int ERROR_UNKNOW = 500,
            ERROR_FIND = 501,
            ERROR_EDIT = 502,
            ERROR_DELETE = 503,
            ERROR_CREATE = 504;

    @Getter
    private int errorCode = ERROR_UNKNOW;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
