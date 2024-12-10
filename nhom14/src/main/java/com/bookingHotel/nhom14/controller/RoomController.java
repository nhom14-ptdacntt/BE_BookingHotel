/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingHotel.nhom14.controller;

import com.bookingHotel.nhom14.dto.RoomDTO;
import com.bookingHotel.nhom14.dto.response.ApiResponse;
import com.bookingHotel.nhom14.entity.Room;
import com.bookingHotel.nhom14.exception.ApiException;
import com.bookingHotel.nhom14.repository.impl.RoomStatusRepository;
import com.bookingHotel.nhom14.service.Impl.RoomService;
import com.bookingHotel.nhom14.service.Impl.RoomTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private RoomStatusRepository roomStatusRepo;

    @Autowired
    private RoomService roomService;

    @GetMapping()
    public ApiResponse getAllRoom() {
        return ApiResponse.<List<Room>>builder()
                .result(roomService.findAll())
                .build();
    }

    @PostMapping("/edit/{id}")
    public ApiResponse editRoom(@RequestBody RoomDTO roomDTO, @RequestParam int id) {

        var room = roomService.findById(id);
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

    @PostMapping("/create")
    public ApiResponse createRoom(@RequestBody RoomDTO roomDTO) {

        var roomType = roomTypeService.findById(roomDTO.getRoomTypeId());
        if (roomType == null) {
            throw new ApiException(ApiException.ERROR_CREATE, "not found room type id: " + roomDTO.getRoomTypeId());
        }

        var room = new Room();
        room.setNumber(roomDTO.getRoomNumber());
        room.setPrice(roomDTO.getPrice());
        room.setRoomType(roomType);
        room.setRoomStatus(roomStatusRepo.findById(1)
                .orElseThrow(
                        () -> new ApiException(ApiException.ERROR_CREATE, "Room status not found"))
        );

        roomDTO = roomService.save(room);

        return ApiResponse.<RoomDTO>builder()
                .result(roomDTO)
                .build();
    }

}
