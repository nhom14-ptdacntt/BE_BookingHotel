/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingHotel.nhom14.service.Impl;

import com.bookingHotel.nhom14.entity.Room;
import com.bookingHotel.nhom14.entity.RoomType;
import com.bookingHotel.nhom14.repository.impl.RoomTypeRepository;
import com.bookingHotel.nhom14.service.IServiceFind;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author KhanhDzai - https://www.facebook.com/khanhdepzai.pro/
 */
@Service
public class RoomTypeService implements IServiceFind<RoomType, Integer> {

    @Autowired
    private RoomTypeRepository roomTypeRepo;

    @Override
    public List<RoomType> findAll() {
        return roomTypeRepo.findAll();
    }

    @Override
    public RoomType findById(Integer id) {
        return roomTypeRepo.findById(id).orElse(null);
    }

}
