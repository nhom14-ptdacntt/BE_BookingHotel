package com.bookingHotel.nhom14.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ManagersDTO {
    private Integer managerId;


    private String userName;


    private String password;


    private String fullName;


    private String email;


    private String phone;


    private String gender;


    private String image;
}
