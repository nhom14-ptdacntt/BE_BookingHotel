package com.bookingHotel.nhom14.dto;

import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
    private int userId;
    private String fullName;
    private String address;
    private String phone;
    private String identifyNumber;
    private String email;
    private String gender;
    private LocalDate birthDate;
    private String password;
    private String image;
}
