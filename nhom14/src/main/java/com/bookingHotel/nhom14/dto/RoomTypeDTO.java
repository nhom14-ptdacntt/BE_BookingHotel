package com.bookingHotel.nhom14.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class RoomTypeDTO {
    private int roomTypeID;
    private String roomTypeName;
    private String description;
}
