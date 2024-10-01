package com.bookingHotel.nhom14.service;

import com.bookingHotel.nhom14.dto.EmployeeDTO;
import com.bookingHotel.nhom14.entity.Employee;
import jakarta.persistence.criteria.ListJoin;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployee();
    EmployeeDTO getEmployeeById(int id);

    EmployeeDTO updateEmployee(int id,EmployeeDTO employeeDTO);
}
