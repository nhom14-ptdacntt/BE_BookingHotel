package com.bookingHotel.nhom14.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RoomDTO {

    private int id;
    private String roomNumber;
    private int roomTypeId;
    private double price;
    private String checkInDate;
    private String checkOutDate;
    private String status;
}
