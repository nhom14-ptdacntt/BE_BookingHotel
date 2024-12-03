package com.bookingHotel.nhom14.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1001,"User is existed", HttpStatus.CONFLICT),
    EMAIL_EXISTED(1002,"Email is existed", HttpStatus.CONFLICT),
    USER_NOT_EXISTED(1003,"User is not exist", HttpStatus.NOT_FOUND),
    EMAIL_NOT_EXISTED(1004,"Email is not exist", HttpStatus.NOT_FOUND),
    PASSWORD_FAILED(1005,"Password is not exist", HttpStatus.BAD_REQUEST),

    UNAUTHENTICATED(1006,"User is not authenticated", HttpStatus.UNAUTHORIZED)
    ;


    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
