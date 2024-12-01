package com.bookingHotel.nhom14.repository;

import com.bookingHotel.nhom14.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> {
    boolean existsByEmail(String email);

    Optional<Users> findByEmail(String email);

}
