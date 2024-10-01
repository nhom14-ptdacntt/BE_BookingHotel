package com.bookingHotel.nhom14.mapper;

import com.bookingHotel.nhom14.dto.EmployeeDTO;
import com.bookingHotel.nhom14.entity.Employee;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee mapToEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO mapToEmployeeDTO(Employee employee);
}
