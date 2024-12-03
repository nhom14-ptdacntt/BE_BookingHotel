package com.bookingHotel.nhom14.controller;

import com.bookingHotel.nhom14.dto.RoomTypeDTO;
import com.bookingHotel.nhom14.dto.response.ApiResponse;
import com.bookingHotel.nhom14.service.Impl.RoomTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")

public class RoomTypeController {
    @Autowired
    private RoomTypeServiceImpl roomTypeService;

    @PostMapping("/create_roomtype")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<RoomTypeDTO> createRoomType(@RequestBody RoomTypeDTO roomTypeDTO) {
        RoomTypeDTO roomTypeDTO1 = roomTypeService.create(roomTypeDTO);
        return ApiResponse.<RoomTypeDTO>builder()
                .result(roomTypeDTO1)
                .build();
    }

}
