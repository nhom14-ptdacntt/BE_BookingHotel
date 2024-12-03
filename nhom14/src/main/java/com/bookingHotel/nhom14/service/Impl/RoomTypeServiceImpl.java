package com.bookingHotel.nhom14.service.Impl;

import com.bookingHotel.nhom14.dto.RoomTypeDTO;
import com.bookingHotel.nhom14.entity.RoomType;
import com.bookingHotel.nhom14.mapper.RoomTypeMapper;
import com.bookingHotel.nhom14.repository.RoomTypeRepository;
import com.bookingHotel.nhom14.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private RoomTypeMapper roomTypeMapper;
    @Override
    public RoomTypeDTO create(RoomTypeDTO dto) {
        RoomType roomType = roomTypeMapper.mapToRoomType(dto);
        RoomType savedRoomType = roomTypeRepository.save(roomType);
        return roomTypeMapper.mapToRoomTypeDTO(savedRoomType);
    }

    @Override
    public List<RoomTypeDTO> getAll() {
        return null;
    }

    @Override
    public RoomTypeDTO getById(int id) {
        return null;
    }

    @Override
    public RoomTypeDTO update(int id, RoomTypeDTO dto) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
