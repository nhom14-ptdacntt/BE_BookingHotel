package com.bookingHotel.nhom14.controller;

import com.bookingHotel.nhom14.dto.RoomDTO;
import com.bookingHotel.nhom14.dto.RoomTypeDTO;
import com.bookingHotel.nhom14.dto.response.ApiResponse;
import com.bookingHotel.nhom14.service.Impl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RoomController {
    @Autowired
    private RoomServiceImpl roomService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add_room")
    public ApiResponse<RoomDTO> createRoom(@RequestBody RoomDTO roomDTO) {
        RoomDTO roomDTO1 = roomService.create(roomDTO);
        return ApiResponse.<RoomDTO>builder()
                .result(roomDTO1)
                .build();
    }


}
