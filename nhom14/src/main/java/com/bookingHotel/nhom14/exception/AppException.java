package com.bookingHotel.nhom14.exception;

import lombok.Getter;

public class AppException extends RuntimeException {

    @Getter
    private ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
