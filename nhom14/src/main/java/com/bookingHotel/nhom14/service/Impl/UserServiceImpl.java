package com.bookingHotel.nhom14.service.Impl;

import com.bookingHotel.nhom14.core.util.Logger;
import com.bookingHotel.nhom14.dto.UsersDTO;
import com.bookingHotel.nhom14.entity.Users;
import com.bookingHotel.nhom14.enums.Role;
import com.bookingHotel.nhom14.exception.AppException;
import com.bookingHotel.nhom14.exception.ErrorCode;
import com.bookingHotel.nhom14.mapper.UserMapper;
import com.bookingHotel.nhom14.repository.UserRepository;
import com.bookingHotel.nhom14.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;


    @Override
    public UsersDTO createUser(UsersDTO usersDTO) {
        Users user = userMapper.mapToUsers(usersDTO);
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<String> roles = new HashSet<>(){{

            add(Role.CUSTOMER.name());

        }};
        user.setRoles(roles);

         Users savedUser= userRepository.save(user);

        return userMapper.mapToUsersDTO(savedUser);
    };

    @Override
    public List<UsersDTO> getAllUsers() {
        return null;
    }

    @Override
    public UsersDTO updateUser(int id, UsersDTO usersDTO) {
        return null;
    }

    @Override
    public UsersDTO getUserById(int id) {
        return null;
    }

    @Override
    public UsersDTO getMyInfo() {
        return null;
    }

    @Override
    public void deleteUser(int id) {

    }
}
