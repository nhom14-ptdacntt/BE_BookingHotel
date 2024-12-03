package com.bookingHotel.nhom14.mapper;

import com.bookingHotel.nhom14.dto.RoomDTO;
import com.bookingHotel.nhom14.entity.Room;
import com.bookingHotel.nhom14.entity.RoomType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    Room mapToRoom(RoomDTO roomDTO);
    RoomDTO mapToRoomDTO(Room room);

    // Thêm phương thức ánh xạ tùy chỉnh
    default RoomType map(int roomTypeID) {
        RoomType roomType = new RoomType();
        roomType.setRoomTypeID(roomTypeID);
        // Bạn có thể thiết lập các thuộc tính khác của RoomType nếu cần
        return roomType;
    }

    // Phương thức ánh xạ từ RoomType sang int (để MapStruct có thể ánh xạ đúng)
    default int map(RoomType roomType) {
        return roomType.getRoomTypeID(); // Trả về roomTypeID từ đối tượng RoomType
    }
}
