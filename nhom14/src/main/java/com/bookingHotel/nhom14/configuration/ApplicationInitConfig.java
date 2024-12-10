package com.bookingHotel.nhom14.configuration;

import com.bookingHotel.nhom14.entity.RoomType;
import com.bookingHotel.nhom14.entity.Users;
import com.bookingHotel.nhom14.enums.Role;
import com.bookingHotel.nhom14.repository.UserRepository;
import com.bookingHotel.nhom14.repository.impl.RoomTypeRepository;
import java.util.ArrayList;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner createUserDefault(UserRepository userRepository, RoomTypeRepository roomTypeRepository) {
        return args -> {
            if (userRepository.findByEmail("admin@admin.com").isEmpty()) {
                Set<String> roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());
                Users admin = Users.builder()
                        .password(passwordEncoder.encode("admin"))
                        .fullName("admin")
                        .gender("male")
                        .email("admin@admin.com")
                        .phone("0965523100")
                        .roles(roles)
                        .build();
                userRepository.save(admin);
            }
            List<String> ROOM_TYPE_DEFAULT = List.of("SINGLE", "DOUBLE", "SINGLE_VIP", "DOUBLE_VIP");

// Lấy tất cả RoomType từ database
            var allRoomType = roomTypeRepository.findAll();

// Tạo Set để kiểm tra nhanh
            Set<String> existingRoomTypes = allRoomType.stream()
                    .map(RoomType::getName)
                    .collect(Collectors.toSet());

// Duyệt qua ROOM_TYPE_DEFAULT và thêm các RoomType còn thiếu
            for (String roomTypeName : ROOM_TYPE_DEFAULT) {
                if (!existingRoomTypes.contains(roomTypeName)) {
                    var roomType = new RoomType(roomTypeName);
                    roomTypeRepository.save(roomType);
                }
            }


        };
    }
}

