package com.bookingHotel.nhom14.dto;

import lombok.Data;

@Data
public class BookingDTO {

    private int roomNumber;
    private String customerName;
    private String customerPhoneNumber;
    /**
     * Cái này chỉ gửi cho client
     */
    private String status;

}
