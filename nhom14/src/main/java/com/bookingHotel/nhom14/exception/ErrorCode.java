package com.bookingHotel.nhom14.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1001,"User is existed"),
    EMAIL_EXISTED(1002,"Email is existed"),

    UNAUTHENTICATED(1003,"User is not authenticated")
    ;


    private int code;
    private String message;
}
