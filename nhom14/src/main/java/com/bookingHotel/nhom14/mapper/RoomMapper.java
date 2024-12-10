package com.bookingHotel.nhom14.mapper;

import com.bookingHotel.nhom14.dto.RoomDTO;
import com.bookingHotel.nhom14.entity.Room;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    // Ánh xạ từ Room sang RoomDTO
    @Mapping(source = "roomType.id", target = "roomTypeId") // Ánh xạ roomType.name sang roomTypeName
    RoomDTO roomToRoomDTO(Room room);

    // Phương thức sẽ chạy sau khi ánh xạ xong
    @AfterMapping
    default void setRoomTypeName(@MappingTarget RoomDTO roomDTO, Room room) {
        if (room.getRoomType() != null) {
            roomDTO.setRoomTypeId(room.getRoomType().getId());
        }
    }

}
