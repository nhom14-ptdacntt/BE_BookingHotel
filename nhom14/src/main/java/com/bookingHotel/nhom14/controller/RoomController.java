/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingHotel.nhom14.controller;

import com.bookingHotel.nhom14.dto.RoomDTO;
import com.bookingHotel.nhom14.dto.response.ApiResponse;
import com.bookingHotel.nhom14.entity.Room;
import com.bookingHotel.nhom14.exception.ApiException;
import com.bookingHotel.nhom14.exception.AppException;
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
        return ApiResponse.<List<Room>>builder()
                .result(roomService.findAll())
                .build();
    }

    @PostMapping("/edit/{id}")
    public ApiResponse editRoom(@RequestBody RoomDTO roomDTO) {

        var room = roomService.findById(roomDTO.getId());
        if (room == null) {
            throw new ApiException(ApiException.ERROR_FIND, "not found room id: " + roomDTO.getId());
        }

        var roomType = roomTypeService.findById(roomDTO.getRoomTypeId());
        if (roomType == null) {
            throw new ApiException(ApiException.ERROR_FIND, "not found room type id: " + roomDTO.getRoomTypeId());
        }

        room.setNumber(roomDTO.getRoomNumber());
        room.setRoomType(roomType);
        room.setPrice(roomDTO.getPrice());

        roomDTO = roomService.save(room);

        return ApiResponse.<RoomDTO>builder()
                .result(roomDTO)
                .build();
    }

}
