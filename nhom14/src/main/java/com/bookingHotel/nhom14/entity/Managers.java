package com.bookingHotel.nhom14.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // 4 anotation này dùng để thay cho contrucstor, getter, setter
@Entity // anotation này để đánh dấu đây là một lớp thực thể
@Table(name = "managers") // anotation này dùng để liên kết đến bảng trong db để ánh xạ tới nó
public class
Managers {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tụ động tăng
    @Column(name = "ManagerID") // liên kết đến cột tương ứng trong db
    private Integer managerId;

    @Column(name = "Username")
    private String userName;

    @Column(name = "Password")
    private String password;

    @Column(name = "FullName")
    private String fullName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Image")
    private String image;
}
