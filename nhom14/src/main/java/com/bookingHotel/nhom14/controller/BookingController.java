/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingHotel.nhom14.controller;

import com.bookingHotel.nhom14.constant.ConstBooking;
import com.bookingHotel.nhom14.constant.ConstRoom;
import com.bookingHotel.nhom14.core.util.Logger;
import com.bookingHotel.nhom14.core.util.Validator;
import com.bookingHotel.nhom14.dto.BookingDTO;
import com.bookingHotel.nhom14.dto.response.ApiResponse;
import com.bookingHotel.nhom14.entity.Booking;
import com.bookingHotel.nhom14.entity.Room;
import com.bookingHotel.nhom14.exception.ApiException;
import com.bookingHotel.nhom14.repository.impl.BookingRepository;
import com.bookingHotel.nhom14.repository.impl.BookingStatusRepository;
import com.bookingHotel.nhom14.service.Impl.RoomService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author KhanhDzai - https://www.facebook.com/khanhdepzai.pro/
 */
@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private BookingRepository bookingRepo;
    @Autowired
    private BookingStatusRepository bookingStatusRepo;

    @GetMapping()
    public ApiResponse getAll() {
        return ApiResponse.<List<Booking>>builder()
                .result(bookingRepo.findAll())
                .build();
    }

    private void validateCustomerName(String customerName) {
        if (customerName == null || customerName.isEmpty() || customerName.isBlank()) {
            throw new ApiException(ApiException.ERROR_CREATE, "Customer name cannot be empty or blank");
        }
    }

    private void validateCustomerPhoneNumber(String customerPhoneNumber) {

        if (customerPhoneNumber == null || customerPhoneNumber.isEmpty() || customerPhoneNumber.isBlank()) {
            throw new ApiException(ApiException.ERROR_CREATE, "Customer phone number cannot be empty or blank");
        }

        if (Validator.isInvalidPhoneNumber(customerPhoneNumber)) {
            throw new ApiException(ApiException.ERROR_CREATE, "Customer phone number is invalid");
        }
    }

    @PostMapping("/create")
    public synchronized ApiResponse create(@RequestBody BookingDTO bookingDTO) {
        var customerName = bookingDTO.getCustomerName();
        this.validateCustomerName(customerName);

        String customerPhoneNumber = bookingDTO.getCustomerPhoneNumber();
        this.validateCustomerPhoneNumber(customerPhoneNumber);

        var room = roomService.findByNumber(bookingDTO.getRoomNumber());
        if (room == null) {
            throw new ApiException(ApiException.ERROR_CREATE, "Not found room number");
        }
        if (!ConstRoom.isRoomAvaiable(room.getRoomStatus().getName())) {
            throw new ApiException(ApiException.ERROR_CREATE, "Room is not avaiable : " + room.getRoomStatus().getName());
        }

        var booking = new Booking();
        booking.setCustomerName(customerName);
        booking.setCustomerPhoneNumber(customerPhoneNumber);
        booking.setRoom(room);
        booking.setStatus(bookingStatusRepo.findById(1)
                .orElseThrow(
                        () -> new ApiException(ApiException.ERROR_CREATE, "Booking status not found"))
        );

        roomService.setRoomStatusBooked(room);
        roomService.save(room);
        booking = bookingRepo.save(booking);

        bookingDTO.setCustomerName(booking.getCustomerName());
        bookingDTO.setCustomerPhoneNumber(booking.getCustomerPhoneNumber());
        bookingDTO.setRoomNumber(booking.getRoom().getNumber());
        bookingDTO.setStatus(booking.getStatus().getName());

        return ApiResponse.<Object>builder()
                .result(bookingDTO)
                .build();
    }

    @PostMapping("/edit/{id}")
    public synchronized ApiResponse edit(@RequestBody BookingDTO bookingDTO, @PathVariable int id) {
        var customerName = bookingDTO.getCustomerName();
        this.validateCustomerName(customerName);

        String customerPhoneNumber = bookingDTO.getCustomerPhoneNumber();
        this.validateCustomerPhoneNumber(customerPhoneNumber);

        var room = roomService.findByNumber(bookingDTO.getRoomNumber());
        if (room == null) {
            throw new ApiException(ApiException.ERROR_CREATE, "Not found room number");
        }

        var booking = bookingRepo.findById(id)
                .orElseThrow(
                        () -> new ApiException(ApiException.ERROR_CREATE, "Booking not found")
                );

        if (ConstBooking.isFinished(booking.getStatus().getName())
                || ConstBooking.isCancelled(booking.getStatus().getName())) {
            return ApiResponse.<Object>builder()
                    .result(booking)
                    .build();
        }

        booking.setCustomerName(customerName);
        booking.setCustomerPhoneNumber(customerPhoneNumber);
        booking.setRoom(room);
        booking.setStatus(bookingStatusRepo.findByName(bookingDTO.getStatus())
                .orElseThrow(
                        () -> new ApiException(ApiException.ERROR_CREATE, "Booking status not found"))
        );
        switch (bookingDTO.getStatus()) {
            case ConstBooking.STATUS_FINISHED:
            case ConstBooking.STATUS_CANCELLED:
                roomService.setRoomStatusAvailable(booking.getRoom());
                break;
            case ConstBooking.STATUS_PENDING:
                roomService.setRoomStatusBooked(booking.getRoom());
                break;
            case ConstBooking.STATUS_CONFIRMED:
                roomService.setRoomStatusOccupied(booking.getRoom());
                break;
            default:
                Logger.DebugLogic("Unknow status?? :  " + bookingDTO.getStatus());
                break;
        }
        roomService.save(room);
        booking = bookingRepo.save(booking);

        bookingDTO.setCustomerName(booking.getCustomerName());
        bookingDTO.setCustomerPhoneNumber(booking.getCustomerPhoneNumber());
        bookingDTO.setRoomNumber(booking.getRoom().getNumber());
        bookingDTO.setStatus(booking.getStatus().getName());

        return ApiResponse.<Object>builder()
                .result(bookingDTO)
                .build();
    }

    @PostMapping("/cancel/{id}")
    public synchronized ApiResponse cancelBooking(@PathVariable int id) {

        var booking = bookingRepo.findById(id)
                .orElseThrow(
                        () -> new ApiException(ApiException.ERROR_CREATE, "Booking not found")
                );
        if (ConstBooking.isCancelled(booking.getStatus().getName())) {
            return ApiResponse.<Object>builder()
                    .result(booking)
                    .build();
        }

        booking.setStatus(bookingStatusRepo.findByName(ConstBooking.STATUS_CANCELLED)
                .orElseThrow(
                        () -> new ApiException(ApiException.ERROR_CREATE, "Booking status not found"))
        );

        roomService.setRoomStatusAvailable(booking.getRoom());
        roomService.save(booking.getRoom());
        booking = bookingRepo.save(booking);

        var bookingDTO = new BookingDTO();
        bookingDTO.setCustomerName(booking.getCustomerName());
        bookingDTO.setCustomerPhoneNumber(booking.getCustomerPhoneNumber());
        bookingDTO.setRoomNumber(booking.getRoom().getNumber());
        bookingDTO.setStatus(booking.getStatus().getName());

        return ApiResponse.<Object>builder()
                .result(booking)
                .build();
    }
}
