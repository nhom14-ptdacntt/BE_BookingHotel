package com.bookingHotel.nhom14.service.Impl;

import com.bookingHotel.nhom14.dto.CustomerDTO;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class Authentication {
    @NonFinal
    @Value("{jwt.secretkey}")
    private String SIGNER_KEY;

    public String generateJwt(CustomerDTO customerDTO) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(String.valueOf(customerDTO.getUserId())) // Chứa định danh người dùng, thường là username, userId
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


}
