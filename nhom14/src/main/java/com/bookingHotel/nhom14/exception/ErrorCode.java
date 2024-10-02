package com.bookingHotel.nhom14.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1001,"User is existed"),
    EMAIL_EXISTED(1002,"Email is existed"),
    USER_NOT_EXISTED(1003,"User is not exist"),
    EMAIL_NOT_EXISTED(1004,"Email is not exist"),
    PASSWORD_FAILED(1005,"Password is not exist"),

    UNAUTHENTICATED(1006,"User is not authenticated")
    ;


    private int code;
    private String message;
}
