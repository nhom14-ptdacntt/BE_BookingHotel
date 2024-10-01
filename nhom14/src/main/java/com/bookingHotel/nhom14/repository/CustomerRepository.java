package com.bookingHotel.nhom14.repository;

import com.bookingHotel.nhom14.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByEmail(String email);
}
