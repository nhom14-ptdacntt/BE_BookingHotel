package com.bookingHotel.nhom14.repository.impl;

import com.bookingHotel.nhom14.entity.Room;
import com.bookingHotel.nhom14.repository.BaseRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author KhanhDzai - https://www.facebook.com/khanhdepzai.pro/
 */
@Repository
public interface RoomRepository extends BaseRepository<Room, Integer> {

    public Optional<Room> findByNumber(String number);

}
