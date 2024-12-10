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
public class ConstBooking {

    // Các trạng thái đặt phòng
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_CONFIRMED = "CONFIRMED";
    public static final String STATUS_CANCELLED = "CANCELLED";
    public static final String STATUS_FINISHED = "FINISHED";

    // Danh sách các trạng thái mặc định
    public static final List<String> BOOKING_STATUS_DEFAULT = List.of(STATUS_PENDING,
            STATUS_CONFIRMED,
            STATUS_CANCELLED,
            STATUS_FINISHED
    );

    public static boolean isPending(String statusName) {
        return statusName.equals(STATUS_PENDING);
    }

    public static boolean isConfirmed(String statusName) {
        return statusName.equals(STATUS_CONFIRMED);
    }

    public static boolean isCancelled(String statusName) {
        return statusName.equals(STATUS_CANCELLED);
    }

    public static boolean isFinished(String statusName) {
        return statusName.equals(STATUS_FINISHED);
    }
}
