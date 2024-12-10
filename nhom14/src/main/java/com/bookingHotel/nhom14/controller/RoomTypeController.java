/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingHotel.nhom14.controller;

import com.bookingHotel.nhom14.core.util.Logger;
import com.bookingHotel.nhom14.dto.response.ApiResponse;
import com.bookingHotel.nhom14.entity.RoomType;
import com.bookingHotel.nhom14.service.Impl.RoomService;
import com.bookingHotel.nhom14.service.Impl.RoomTypeService;
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
@RequestMapping("/api/roomtype")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping()
    public ApiResponse getAllRoomType() {
        try {
            return ApiResponse.<List<RoomType>>builder()
                    .result(roomTypeService.findAll())
                    .build();
        } catch (Exception e) {
            Logger.DebugLogic("ERROR getAllRoomType ? ", e);
            return ApiResponse.<Object>builder()
                    .result(null)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ApiResponse getRoomTypeNameById() {
        try {
            return ApiResponse.<List<RoomType>>builder()
                    .result(roomTypeService.findAll())
                    .build();
        } catch (Exception e) {
            Logger.DebugLogic("ERROR getAllRoomType ? ", e);
            return ApiResponse.<Object>builder()
                    .result(null)
                    .build();
        }
    }

}
