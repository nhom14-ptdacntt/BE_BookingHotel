package com.bookingHotel.nhom14.controller;

import com.bookingHotel.nhom14.dto.UsersDTO;
import com.bookingHotel.nhom14.dto.response.ApiResponse;
import com.bookingHotel.nhom14.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/create_user")
    public ApiResponse<UsersDTO> createUser(@RequestBody UsersDTO usersDTO) {
        UsersDTO usersDTO1 = userServiceImpl.createUser(usersDTO);
        return ApiResponse.<UsersDTO>builder()
                .result(usersDTO1)
                .build();
    }
}
