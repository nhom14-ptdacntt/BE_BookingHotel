package com.bookingHotel.nhom14.service;

import com.bookingHotel.nhom14.dto.UsersDTO;
import com.bookingHotel.nhom14.entity.Users;

import java.util.List;

public interface UserService {
    UsersDTO createUser(UsersDTO usersDTO);
    List<UsersDTO> getAllUsers();
    UsersDTO updateUser(int id, UsersDTO usersDTO);
    UsersDTO getUserById(int id);
    UsersDTO getMyInfo();
    void deleteUser(int id);
}
