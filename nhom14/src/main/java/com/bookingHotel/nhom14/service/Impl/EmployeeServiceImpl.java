package com.bookingHotel.nhom14.service.Impl;


import com.bookingHotel.nhom14.dto.ApiResponse;
import com.bookingHotel.nhom14.dto.EmployeeDTO;
import com.bookingHotel.nhom14.entity.Employee;
import com.bookingHotel.nhom14.mapper.EmployeeMapper;
import com.bookingHotel.nhom14.repository.EmployeeRepository;
import com.bookingHotel.nhom14.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;





    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {


        Employee employee = employeeMapper.mapToEmployee(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.mapToEmployeeDTO(savedEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        return null;
    }

    @Override
    public EmployeeDTO getEmployeeById(int id) {
        return null;
    }

    @Override
    public EmployeeDTO updateEmployee(int id, EmployeeDTO employeeDTO) {
        return null;
    }


}
