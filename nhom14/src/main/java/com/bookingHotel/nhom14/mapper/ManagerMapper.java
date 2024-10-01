package com.bookingHotel.nhom14.mapper;

import com.bookingHotel.nhom14.dto.ManagersDTO;
import com.bookingHotel.nhom14.entity.Managers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    ManagersDTO mapToManagerDTO(Managers managers);
    Managers mapToManager(ManagersDTO managersDTO);
}
