package com.bookingHotel.nhom14.mapper;

import com.bookingHotel.nhom14.dto.CustomerDTO;
import com.bookingHotel.nhom14.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer mapToCustomer(CustomerDTO customerDTO);

    CustomerDTO mapToCustomerDTO(Customer customer);
}
