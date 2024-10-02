package com.bookingHotel.nhom14.dto.request;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class IntrospectRequest {
    private String token;
}
