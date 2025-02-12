package com.bookingHotel.nhom14.repository.impl;

import com.bookingHotel.nhom14.entity.BookingStatus;
import com.bookingHotel.nhom14.repository.BaseRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author KhanhDzai - https://www.facebook.com/khanhdepzai.pro/
 */
@Repository
public interface BookingStatusRepository extends BaseRepository<BookingStatus, Integer> {

    public Optional<BookingStatus> findByName(String name);
}
