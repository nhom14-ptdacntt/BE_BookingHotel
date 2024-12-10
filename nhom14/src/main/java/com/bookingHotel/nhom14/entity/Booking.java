/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingHotel.nhom14.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author KhanhDzai - https://www.facebook.com/khanhdepzai.pro/
 */
@Table(name = "bookings")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false)
    private String customerPhoneNumber;
    @OneToOne
    @JoinColumn(name = "room", unique = true)
    private Room room;
    @ManyToOne
    @JoinColumn(name = "status")
    private BookingStatus status;

    /**
     * Key: status.name - Value: System.currentimemilies()
     */
    @ElementCollection
    @CollectionTable(
            name = "booking_time_change_status", // Tên bảng phụ
            joinColumns = @JoinColumn(name = "id") // Tên cột liên kết
    )
    @MapKeyColumn(name = "status_name") // Cột cho key
    @Column(name = "last_time_millis") // Cột cho value
    private Map<String, Long> timeChangeStatus = new HashMap<>();

    public void setStatus(BookingStatus status) {
        this.status = status;
        this.timeChangeStatus.put(status.getName(), System.currentTimeMillis());
    }

}
