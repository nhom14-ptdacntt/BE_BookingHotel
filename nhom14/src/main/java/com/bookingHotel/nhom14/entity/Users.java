package com.bookingHotel.nhom14.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    private String fullName;
    private String phone;
    private String identityNumber;
    private String email;
    private String gender;
    private LocalDate birthDate;
    private String password;
    private String image;

    @ElementCollection
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "userID"))
    private Set<String> roles;
}
