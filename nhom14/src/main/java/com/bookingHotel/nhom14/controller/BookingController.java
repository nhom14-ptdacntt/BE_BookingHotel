/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingHotel.nhom14.controller;

import com.bookingHotel.nhom14.constant.ConstRoom;
import com.bookingHotel.nhom14.core.util.Validator;
import com.bookingHotel.nhom14.dto.BookingDTO;
import com.bookingHotel.nhom14.dto.response.ApiResponse;
import com.bookingHotel.nhom14.entity.Booking;
import com.bookingHotel.nhom14.exception.ApiException;
import com.bookingHotel.nhom14.repository.impl.BookingRepository;
import com.bookingHotel.nhom14.repository.impl.BookingStatusRepository;
import com.bookingHotel.nhom14.service.Impl.RoomService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.ResponseEntity.status;
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

    @PostMapping("/create")
    public synchronized ApiResponse create(@RequestBody BookingDTO bookingDTO) {
        String customerName = bookingDTO.getCustomerName();
        if (customerName == null || customerName.isEmpty() || customerName.isBlank()) {
            throw new ApiException(ApiException.ERROR_CREATE, "Customer name cannot be empty or blank");
        }
        String customerPhoneNumber = bookingDTO.getCustomerPhoneNumber();
        if (customerPhoneNumber == null || customerPhoneNumber.isEmpty() || customerPhoneNumber.isBlank()) {
            throw new ApiException(ApiException.ERROR_CREATE, "Customer phone number cannot be empty or blank");
        }

        if (Validator.isInvalidPhoneNumber(customerPhoneNumber)) {
            throw new ApiException(ApiException.ERROR_CREATE, "Customer phone number is invalid");
        }

        var room = roomService.findByNumber(bookingDTO.getRoomNumber());
        if (room == null) {
            throw new ApiException(ApiException.ERROR_CREATE, "Not found room number : " + bookingDTO.getRoomNumber());
        }

        if (!ConstRoom.isRoomAvaiable(room.getRoomStatus().getName())) {
            throw new ApiException(ApiException.ERROR_CREATE, "Room is not avaiable : " + room.getRoomStatus().getName());
        }

        var booking = new Booking();
        booking.setCustomerName(customerName);
        booking.setRoom(room);
        booking.setStatus(bookingStatusRepo.findById(1)
                .orElseThrow(
                        () -> new ApiException(ApiException.ERROR_CREATE, "Booking status not found"))
        );

        booking = bookingRepo.save(booking);

        bookingDTO.setCustomerName(booking.getCustomerName());
        bookingDTO.setRoomNumber(booking.getRoom().getNumber());
        bookingDTO.setStatus(booking.getStatus().getName());

        return ApiResponse.<Object>builder()
                .result(bookingDTO)
                .build();

//        var roomType = roomTypeService.findById(roomDTO.getRoomTypeId());
//        if (roomType == null) {
//            throw new ApiException(ApiException.ERROR_CREATE, "not found room type id: " + roomDTO.getRoomTypeId());
//        }
//
//        var room = new Room();
//        room.setNumber(roomDTO.getRoomNumber());
//        room.setPrice(roomDTO.getPrice());
//        room.setRoomType(roomType);
//        room.setRoomStatus(roomStatusRepo.findById(1)
//                .orElseThrow(
//                        () -> new ApiException(ApiException.ERROR_CREATE, "Room status not found"))
//        );
//
//        roomDTO = roomService.save(room);
//
//        return ApiResponse.<RoomDTO>builder()
//                .result(roomDTO)
//                .build();
    }

//    @PostMapping("/edit/{id}")
//    public ApiResponse edit(@RequestBody RoomDTO roomDTO, @PathVariable int id) {
//
//        var room = roomService.findById(id);
//        if (room == null) {
//            throw new ApiException(ApiException.ERROR_FIND, "not found room id: " + roomDTO.getId());
//        }
//
//        var roomType = roomTypeService.findById(roomDTO.getRoomTypeId());
//        if (roomType == null) {
//            throw new ApiException(ApiException.ERROR_FIND, "not found room type id: " + roomDTO.getRoomTypeId());
//        }
//
//        room.setNumber(roomDTO.getRoomNumber());
//        room.setRoomType(roomType);
//        room.setPrice(roomDTO.getPrice());
//
//        roomDTO = roomService.save(room);
//
//        return ApiResponse.<RoomDTO>builder()
//                .result(roomDTO)
//                .build();
//    }
//    @DeleteMapping("/delete/{id}")
//    public ApiResponse deleteRoom(@PathVariable int id) {
//        roomService.deleteById(id);
//        return ApiResponse.<String>builder()
//                .result("OK")
//                .build();
//    }
}
