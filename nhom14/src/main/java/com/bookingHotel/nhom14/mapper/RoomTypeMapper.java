package com.bookingHotel.nhom14.mapper;

import com.bookingHotel.nhom14.dto.RoomTypeDTO;
import com.bookingHotel.nhom14.entity.RoomType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomTypeMapper {

    RoomType mapToRoomType(RoomTypeDTO roomTypeDTO);

    RoomTypeDTO mapToRoomTypeDTO(RoomType roomType);

}
