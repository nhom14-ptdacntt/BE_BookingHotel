package com.bookingHotel.nhom14.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "rooms")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;
    private String roomNumber;
    private int bed;
    private int bath;
    private String description;
    private double price;
    private String image;
    private String status;

    @ManyToOne
    @JoinColumn(name = "room_typeid")
    private RoomType roomTypeID;
}
