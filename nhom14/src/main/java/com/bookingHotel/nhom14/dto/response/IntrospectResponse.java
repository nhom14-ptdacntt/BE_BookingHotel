package com.bookingHotel.nhom14.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@AllArgsConstructor
@Setter

public class IntrospectResponse {
    private boolean valid;
}
