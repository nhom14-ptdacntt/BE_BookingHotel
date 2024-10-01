package com.bookingHotel.nhom14.service;

import com.bookingHotel.nhom14.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> getAllCustomer();
    CustomerDTO getCustomerById(int id);
    CustomerDTO updateCustomer(int id,CustomerDTO customerDTO);
    void deleteCustomer(CustomerDTO customerDTO);

}
