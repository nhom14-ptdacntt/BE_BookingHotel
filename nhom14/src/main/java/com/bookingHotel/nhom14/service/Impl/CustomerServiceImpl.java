package com.bookingHotel.nhom14.service.Impl;

import com.bookingHotel.nhom14.dto.CustomerDTO;
import com.bookingHotel.nhom14.entity.Customer;
import com.bookingHotel.nhom14.exception.AppException;
import com.bookingHotel.nhom14.exception.ErrorCode;
import com.bookingHotel.nhom14.mapper.CustomerMapper;
import com.bookingHotel.nhom14.repository.CustomerRepository;
import com.bookingHotel.nhom14.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        if(customerRepository.existsByEmail(customerDTO.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        String encodePassWord = passwordEncoder.encode(customerDTO.getPassword());
        customerDTO.setPassword(encodePassWord);
        Customer customer = customerMapper.mapToCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.mapToCustomerDTO(savedCustomer);
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return null;
    }

    @Override
    public CustomerDTO getCustomerById(int id) {
        return null;
    }

    @Override
    public CustomerDTO updateCustomer(int id, CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public void deleteCustomer(CustomerDTO customerDTO) {

    }
}
