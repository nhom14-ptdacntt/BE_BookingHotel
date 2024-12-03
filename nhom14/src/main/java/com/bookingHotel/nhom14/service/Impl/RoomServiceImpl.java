package com.bookingHotel.nhom14.service.Impl;

import com.bookingHotel.nhom14.dto.RoomDTO;
import com.bookingHotel.nhom14.entity.Room;
import com.bookingHotel.nhom14.mapper.RoomMapper;
import com.bookingHotel.nhom14.repository.RoomRepository;
import com.bookingHotel.nhom14.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public RoomDTO create(RoomDTO dto) {
        Room room = roomMapper.mapToRoom(dto);
        Room savedRoom = roomRepository.save(room);
        return roomMapper.mapToRoomDTO(savedRoom);
    }

    @Override
    public List<RoomDTO> getAll() {
        return null;
    }

    @Override
    public RoomDTO getById(int id) {
        return null;
    }

    @Override
    public RoomDTO update(int id, RoomDTO dto) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
