package com.bookingHotel.nhom14.controller;

import com.bookingHotel.nhom14.core.util.Logger;
import com.bookingHotel.nhom14.dto.UsersDTO;
import com.bookingHotel.nhom14.dto.request.IntrospectRequest;
import com.bookingHotel.nhom14.dto.response.ApiResponse;
import com.bookingHotel.nhom14.dto.response.AuthenticationResponse;
import com.bookingHotel.nhom14.dto.response.IntrospectResponse;
import com.bookingHotel.nhom14.service.Impl.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authentication(@RequestBody UsersDTO usersDTO) {
        ApiResponse<AuthenticationResponse> response = new ApiResponse<>();
        Logger.DebugLogic("Start authen:  ");
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(usersDTO);
        Logger.DebugLogic("DONE AUTHEN ??? :  " + authenticationResponse.toString());
        response.setResult(authenticationResponse);
        return response;
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        IntrospectResponse introspectResponse = authenticationService.introspectResponse(introspectRequest);
        return ApiResponse.<IntrospectResponse>builder()
                .result(introspectResponse)
                .build();
    }
}
