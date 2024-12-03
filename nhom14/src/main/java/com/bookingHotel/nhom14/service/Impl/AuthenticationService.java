package com.bookingHotel.nhom14.service.Impl;


import com.bookingHotel.nhom14.core.util.Logger;
import com.bookingHotel.nhom14.dto.UsersDTO;
import com.bookingHotel.nhom14.dto.request.IntrospectRequest;
import com.bookingHotel.nhom14.dto.response.AuthenticationResponse;
import com.bookingHotel.nhom14.dto.response.IntrospectResponse;

import com.bookingHotel.nhom14.entity.Users;
import com.bookingHotel.nhom14.exception.AppException;
import com.bookingHotel.nhom14.exception.ErrorCode;

import com.bookingHotel.nhom14.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service

public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public String generateJwt(UsersDTO usersDTO) {
        Optional<Users> users = userRepository.findByEmail(usersDTO.getEmail());

        if(users.isEmpty()) { throw new AppException(ErrorCode.USER_NOT_EXISTED);}

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(String.valueOf(users.get().getUserID())) // Chứa định danh người dùng, thường là username, userId
                .issuer("localhost") //là claim chỉ định ai đã phát hành token này, thường là tên máy chủ hoặc dịch vụ phát hành
                .issueTime(new Date()) //là claim ghi lại thời điểm token được phát hành, ở dây là lấy thời gian hiện tại
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope",buildScope(usersDTO))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        //Tạo một đối tượng JWSObject bằng cách kết hợp header và payload
        JWSObject jwsObject = new JWSObject(header,payload);

        //generate signature
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes())); //Ký JWSObject bằng cách sử dụng MACSigner và khóa bí mật
            return jwsObject.serialize(); //Chuyển đổi JWSObject đã ký thành một chuỗi JWT hoàn chỉnh để có thể sử dụng.
        } catch (JOSEException e) { //Xử lý ngoại lệ nếu có lỗi xảy ra trong quá trình ký.
            Logger.DebugLogic("ERROR SIGN: " ,e);
            throw new RuntimeException(e);
        }
    }

    public AuthenticationResponse authenticate(UsersDTO usersDTO) {
        Users users = new Users();
        if(userRepository.existsByEmail(usersDTO.getEmail())) {
            Optional<Users> userFind = userRepository.findByEmail(usersDTO.getEmail());
            if(userFind.isEmpty()) {

                throw new AppException(ErrorCode.USER_NOT_EXISTED);
            }
             users = userFind.get();
        } else {
            throw new AppException(ErrorCode.EMAIL_NOT_EXISTED);
        }

        boolean checkPassword = passwordEncoder.matches(usersDTO.getPassword(),users.getPassword());
        if(!checkPassword) {
            throw new AppException(ErrorCode.PASSWORD_FAILED);
        }
        String token = generateJwt(usersDTO);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    public IntrospectResponse introspectResponse(IntrospectRequest introspectRequest) throws JOSEException, ParseException {
        String token = introspectRequest.getToken();

        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean verified = signedJWT.verify(jwsVerifier);
        boolean time = expiryTime.after(new Date());
        return IntrospectResponse.builder()
                .valid(verified && time)
                .build();
    }
    private String buildScope(UsersDTO usersDTO) {

        Optional<Users> users = userRepository.findByEmail(usersDTO.getEmail());

        Set<String> roles = users.get().getRoles();
        StringBuilder scopeBuilder = new StringBuilder();
        if (!roles.isEmpty()) {
            for (String role : roles) {
                scopeBuilder.append(role  ).append(" ");
            }
        }
        return scopeBuilder.toString().trim(); // Xóa khoảng trắng thừa ở cuối
    }
}
