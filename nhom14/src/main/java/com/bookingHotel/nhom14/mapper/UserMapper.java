package com.bookingHotel.nhom14.mapper;


import com.bookingHotel.nhom14.dto.UsersDTO;
import com.bookingHotel.nhom14.entity.Users;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.HashSet;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users mapToUsers(UsersDTO usersDTO);
    UsersDTO mapToUsersDTO(Users users);
    @AfterMapping
    default void mapRoles(Users users, @MappingTarget UsersDTO usersDTO) {
        if (users.getRoles() != null) {
            usersDTO.setRoles(new HashSet<>(users.getRoles()));
        }
    }
}
