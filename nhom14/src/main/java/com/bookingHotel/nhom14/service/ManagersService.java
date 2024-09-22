package com.bookingHotel.nhom14.service;

import com.bookingHotel.nhom14.dto.ManagersDTO;

import java.util.List;

public interface ManagersService {
    ManagersDTO createManagers(ManagersDTO managersDTO);
    List<ManagersDTO> getAllManagers();

    ManagersDTO getManagersById(Integer id);

    ManagersDTO updateManager(Integer id, ManagersDTO managersDTO);

    void deleteManager(ManagersDTO managersDTO);
}
