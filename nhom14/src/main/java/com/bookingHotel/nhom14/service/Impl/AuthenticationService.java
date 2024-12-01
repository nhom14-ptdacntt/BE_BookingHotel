package com.bookingHotel.nhom14.service.Impl;

import com.bookingHotel.nhom14.dto.CustomerDTO;
import com.bookingHotel.nhom14.dto.request.IntrospectRequest;
import com.bookingHotel.nhom14.dto.response.AuthenticationResponse;
import com.bookingHotel.nhom14.dto.response.IntrospectResponse;
import com.bookingHotel.nhom14.entity.Customer;
import com.bookingHotel.nhom14.exception.AppException;
import com.bookingHotel.nhom14.exception.ErrorCode;
import com.bookingHotel.nhom14.repository.CustomerRepository;
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

@Service

public class AuthenticationService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public String generateJwt(CustomerDTO customerDTO) {
        Customer customer = customerRepository.findByEmail(customerDTO.getEmail());
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(String.valueOf(customer.getUserId())) // Chứa định danh người dùng, thường là username, userId
                .issuer("localhost") //là claim chỉ định ai đã phát hành token này, thường là tên máy chủ hoặc dịch vụ phát hành
                .issueTime(new Date()) //là claim ghi lại thời điểm token được phát hành, ở dây là lấy thời gian hiện tại
                .expirationTime(new Date( //expirationTime  là claim chỉ định thời gian mà token sẽ hết hạn
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli() //Instant.now() lấy thời gian hiện tại,
                        // sau đó .plus(1, ChronoUnit.HOURS) sẽ cộng thêm 1 giờ vào thời gian hiện tại, tạo ra một thời điểm tương lai là 1 giờ sau.
                ))
                .claim("scope","CUSTOMER")
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        //Tạo một đối tượng JWSObject bằng cách kết hợp header và payload
        JWSObject jwsObject = new JWSObject(header,payload);

        //generate signature
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes())); //Ký JWSObject bằng cách sử dụng MACSigner và khóa bí mật
            return jwsObject.serialize(); //Chuyển đổi JWSObject đã ký thành một chuỗi JWT hoàn chỉnh để có thể sử dụng.
        } catch (JOSEException e) { //Xử lý ngoại lệ nếu có lỗi xảy ra trong quá trình ký.
            throw new RuntimeException(e);
        }
    }

    public AuthenticationResponse authenticate(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        if(customerRepository.existsByEmail(customerDTO.getEmail())) {
             customer = customerRepository.findByEmail(customerDTO.getEmail());
        } else {
            throw new AppException(ErrorCode.EMAIL_NOT_EXISTED);
        }

        boolean checkPassword = passwordEncoder.matches(customerDTO.getPassword(),customer.getPassword());
        if(!checkPassword) {
            throw new AppException(ErrorCode.PASSWORD_FAILED);
        }
        String token = generateJwt(customerDTO);
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
        boolean time = expiryTime.before(new Date());
        return IntrospectResponse.builder()
                .valid(verified && time)
                .build();
    }


}
