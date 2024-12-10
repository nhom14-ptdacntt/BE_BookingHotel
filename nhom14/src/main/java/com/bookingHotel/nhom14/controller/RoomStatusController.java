/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingHotel.nhom14.controller;

import com.bookingHotel.nhom14.core.util.Logger;
import com.bookingHotel.nhom14.dto.response.ApiResponse;
import com.bookingHotel.nhom14.entity.RoomStatus;
import com.bookingHotel.nhom14.repository.impl.RoomStatusRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author KhanhDzai - https://www.facebook.com/khanhdepzai.pro/
 */
@RestController
@RequestMapping("/api/roomstatus")
public class RoomStatusController {

    @Autowired
    private RoomStatusRepository roomStatusRepo;

    @GetMapping()
    public ApiResponse getAllRoomStatus() {
        try {
            return ApiResponse.<List<RoomStatus>>builder()
                    .result(roomStatusRepo.findAll())
                    .build();
        } catch (Exception e) {
            Logger.DebugLogic("ERROR getAllRoomStatus ? ", e);
            return ApiResponse.<Object>builder()
                    .result(null)
                    .build();
        }
    }

}
