package com.bookingHotel.nhom14.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roomtypes")
@Data
@NoArgsConstructor
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public RoomType(String name) {
        this.name = name;
    }

}
