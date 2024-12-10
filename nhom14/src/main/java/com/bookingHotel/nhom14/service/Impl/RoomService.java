/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingHotel.nhom14.service.Impl;

import static com.bookingHotel.nhom14.constant.ConstRoom.STATUS_BOOKED;
import static com.bookingHotel.nhom14.constant.ConstRoom.STATUS_OCCUPIED;
import com.bookingHotel.nhom14.dto.RoomDTO;
import com.bookingHotel.nhom14.entity.Room;
import com.bookingHotel.nhom14.exception.ApiException;
import com.bookingHotel.nhom14.mapper.RoomMapper;
import com.bookingHotel.nhom14.repository.impl.RoomRepository;
import com.bookingHotel.nhom14.repository.impl.RoomStatusRepository;
import com.bookingHotel.nhom14.service.IServiceFind;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author KhanhDzai - https://www.facebook.com/khanhdepzai.pro/
 */
@Service
public class RoomService implements IServiceFind<Room, Integer> {

    @Autowired
    private RoomRepository roomRepo;
    @Autowired
    private RoomStatusRepository roomStatusRepo;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<Room> findAll() {
        return roomRepo.findAll();
    }

    @Override
    public Room findById(Integer id) {
        return roomRepo.findById(id).orElse(null);
    }

    public Room findByNumber(Integer number) {
        return roomRepo.findByNumber(number).orElse(null);
    }

    public RoomDTO save(Room room) {
        return roomMapper.roomToRoomDTO(roomRepo.save(room));
    }

    public void deleteById(Integer id) {
        roomRepo.deleteById(id);
    }

    public void setRoomStatusAvailable(Room room) {
        room.setRoomStatus(roomStatusRepo.findById(1)
                .orElseThrow(
                        () -> new ApiException(ApiException.ERROR_EDIT, "Room status not found"))
        );
    }

    public void setRoomStatusOccupied(Room room) {
        room.setRoomStatus(roomStatusRepo.findById(2)
                .orElseThrow(
                        () -> new ApiException(ApiException.ERROR_EDIT, "Room status not found"))
        );
    }

    public void setRoomStatusBooked(Room room) {
        room.setRoomStatus(roomStatusRepo.findById(3)
                .orElseThrow(
                        () -> new ApiException(ApiException.ERROR_EDIT, "Room status not found"))
        );
    }

}
