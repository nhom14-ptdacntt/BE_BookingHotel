package com.bookingHotel.nhom14.dto;

import com.bookingHotel.nhom14.entity.RoomType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class RoomDTO {
    private int roomId;
    private String roomNumber;
    private int bed;
    private int bath;
    private String description;
    private double price;
    private String image;
    private String status;
    private int roomTypeID;
}
