/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingHotel.nhom14.controller;

import com.bookingHotel.nhom14.core.util.Logger;
import com.bookingHotel.nhom14.dto.RoomDTO;
import com.bookingHotel.nhom14.dto.response.ApiResponse;
import com.bookingHotel.nhom14.entity.Room;
import com.bookingHotel.nhom14.service.Impl.RoomService;
import com.bookingHotel.nhom14.service.Impl.RoomTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author KhanhDzai - https://www.facebook.com/khanhdepzai.pro/
 */
@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private RoomService roomService;

    @GetMapping()
    public ApiResponse getAllRoom() {
        try {
            return ApiResponse.<List<Room>>builder()
                    .result(roomService.findAll())
                    .build();
        } catch (Exception e) {
            Logger.DebugLogic("ERROR getAllRoom ? ", e);
            return ApiResponse.<Object>builder()
                    .result(null)
                    .build();
        }
    }

    @PostMapping("/edit/{id}")
    public ApiResponse editRoom(@RequestBody RoomDTO roomDTO) {
        try {

            var room = roomService.findById(roomDTO.getId());
            if (room == null) {
                throw new RuntimeException("not found room id: " + roomDTO.getId());
            }

            return ApiResponse.<RoomDTO>builder()
                    .result(roomDTO)
                    .build();
        } catch (Exception e) {
            Logger.DebugLogic("ERROR editRoom ? ", e);
            return ApiResponse.<Object>builder()
                    .result(null)
                    .build();
        }
    }

}
