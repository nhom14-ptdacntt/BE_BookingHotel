package com.bookingHotel.nhom14.controller;

import com.bookingHotel.nhom14.dto.response.ApiResponse;
import com.bookingHotel.nhom14.dto.CustomerDTO;
import com.bookingHotel.nhom14.service.Impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")

public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @PostMapping("/add-customer")
    public ApiResponse<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO customer = customerServiceImpl.createCustomer(customerDTO);
        return ApiResponse.<CustomerDTO>builder()
                .result(customerDTO)
                .build();
    }
}
