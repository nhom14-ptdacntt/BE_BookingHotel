package com.bookingHotel.nhom14.configuration;

import com.bookingHotel.nhom14.entity.Users;
import com.bookingHotel.nhom14.enums.Role;
import com.bookingHotel.nhom14.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
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


}
