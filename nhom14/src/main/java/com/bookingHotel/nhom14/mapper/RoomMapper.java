package com.bookingHotel.nhom14.mapper;

import com.bookingHotel.nhom14.dto.RoomDTO;
import com.bookingHotel.nhom14.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    /**
     *
     * @param room
     * @return
     */
    @Mapping(source = "roomType.id", target = "roomTypeId")
    @Mapping(source = "roomStatus.id", target = "roomStatusId")
    RoomDTO roomToRoomDTO(Room room);

}
