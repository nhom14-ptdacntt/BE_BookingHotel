package com.bookingHotel.nhom14.dto;

import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDTO {
    private int employeeID;
    private int positionId;
    private String fullName;
    private String address;
    private String phone;
    private String email;
    private String gender;
    private LocalDate birthDate;
}
