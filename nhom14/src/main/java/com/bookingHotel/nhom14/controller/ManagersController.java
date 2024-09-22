package com.bookingHotel.nhom14.controller;

import com.bookingHotel.nhom14.dto.ManagersDTO;
import com.bookingHotel.nhom14.service.Impl.ManagersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // đánh dấu đây là một lóp controller
@RequestMapping("/api") // t quên mẹ r ae tra chatgpt nhé
@CrossOrigin("*") // cho phép mọi endpoint đều có thể call api
public class ManagersController {
    @Autowired // cái này cũng tra chatgpt
    private ManagersServiceImpl managersServiceImpl;

    @PostMapping("/managers") //phương thức post
    public ResponseEntity<ManagersDTO> createManagers(@RequestBody ManagersDTO managersDTO) {
        ManagersDTO saveManager = managersServiceImpl.createManagers(managersDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveManager);
    }
}
