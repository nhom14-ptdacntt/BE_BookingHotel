package com.bookingHotel.nhom14.mapper;

import com.bookingHotel.nhom14.dto.ManagersDTO;
import com.bookingHotel.nhom14.entity.Managers;

public class ManagersMapper {

    public static ManagersDTO mapToManagersDTO(Managers managers) {
        return new ManagersDTO(
                managers.getManagerId(),
                managers.getUserName(),
                managers.getPassword(),
                managers.getFullName(),
                managers.getEmail(),
                managers.getPhone(),
                managers.getGender(),
                managers.getImage()
        );
    }

    public static Managers mapToManagers(ManagersDTO managersDTO) {
        return new Managers(
                managersDTO.getManagerId(),
                managersDTO.getUserName(),
                managersDTO.getPassword(),
                managersDTO.getFullName(),
                managersDTO.getEmail(),
                managersDTO.getPhone(),
                managersDTO.getGender(),
                managersDTO.getImage()
        );
    }
}
