package com.bookingHotel.nhom14.entity;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "roomtypes")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomTypeID;
    private String roomTypeName;
    private String description;
}
