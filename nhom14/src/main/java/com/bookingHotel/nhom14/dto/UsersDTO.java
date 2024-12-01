package com.bookingHotel.nhom14.dto;

import com.bookingHotel.nhom14.enums.Role;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;


@Data
@Builder
@ToString
public class UsersDTO {
    private int userID;
    private String fullName;
    private String phone;
    private String identityNumber;
    private String email;
    private String gender;
    private LocalDate birthDate;
    private String password;
    private String image;
    private Set<String> roles;


}
