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
}
