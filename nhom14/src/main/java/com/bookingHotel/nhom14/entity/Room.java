package com.bookingHotel.nhom14.entity;

import com.bookingHotel.nhom14.dto.RoomDTO;
import jakarta.persistence.*;
import java.sql.Timestamp;
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
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private int number;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private RoomType roomType;

    @Column(nullable = false)
    private double price;

    private Timestamp checkInDate;

    private Timestamp checkOutDate;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private RoomStatus roomStatus;

}
