package com.bookingHotel.nhom14.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "rooms")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    @Column(nullable = false, unique = true)
    private String number;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private RoomType roomType;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private RoomStatus roomStatus;

}
