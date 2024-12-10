/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingHotel.nhom14.constant;

import java.util.List;

/**
 *
 * @author KhanhDzai - https://www.facebook.com/khanhdepzai.pro/
 */
public class ConstRoom {

    public static final List<String> //
            ROOM_TYPE_DEFAULT = List.of("SINGLE", "DOUBLE", "SINGLE_VIP", "DOUBLE_VIP"),
            ROOM_STATUS_DEFAULT = List.of("AVAIABLE", "OCCUPIED", "BOOKED");

    public static boolean isRoomAvaiable(String statusName) {
        return statusName.equals("AVAIABLE");
    }

    public static boolean isRoomOccupied(String statusName) {
        return statusName.equals("OCCUPIED");
    }

    public static boolean isRoomBooked(String statusName) {
        return statusName.equals("BOOKED");
    }

}
