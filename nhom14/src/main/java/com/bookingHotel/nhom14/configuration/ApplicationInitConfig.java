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

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner createUserDefault(UserRepository userRepository) {
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
        };
    }

    @Bean
    ApplicationRunner createRoomTypeDefault(RoomTypeRepository roomTypeRepository) {
        return args -> {

            List<String> ROOM_TYPE_DEFAULT = new ArrayList<>() {
                {
                    this.add("SINGLE");
                    this.add("DOUBLE");
                    this.add("SINGLE_VIP");
                    this.add("DOUBLE_VIP");
                }
            };

            Map<String, Boolean> roomTypeChecked = new HashMap<>();

            var allRoomType = roomTypeRepository.findAll();

            for (RoomType roomType : allRoomType) {
                for (String name : ROOM_TYPE_DEFAULT) {
                    if (name.equals(roomType.getName())) {
                        roomTypeChecked.put(name, Boolean.TRUE);
                    }
                }
            }

            for (Map.Entry<String, Boolean> entry : roomTypeChecked.entrySet()) {
                String roomTypeName = entry.getKey();
                Boolean isHave = entry.getValue();

                if (!isHave) {
                    var roomType = new RoomType(roomTypeName);
                    roomTypeRepository.save(roomType);
                }

            }
        };
    }

}
