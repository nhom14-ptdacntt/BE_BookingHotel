package com.bookingHotel.nhom14.controller;

import com.bookingHotel.nhom14.dto.response.ApiResponse;
import com.bookingHotel.nhom14.dto.EmployeeDTO;
import com.bookingHotel.nhom14.service.Impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")

public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("/add-employee")
    public ApiResponse<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO employee = employeeService.createEmployee(employeeDTO);
        return ApiResponse.<EmployeeDTO>builder()
                .result(employee)
                .build();
    }
}
