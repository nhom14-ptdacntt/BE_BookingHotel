package com.bookingHotel.nhom14.service.Impl;

import com.bookingHotel.nhom14.dto.ManagersDTO;
import com.bookingHotel.nhom14.entity.Managers;
import com.bookingHotel.nhom14.mapper.ManagersMapper;
import com.bookingHotel.nhom14.repository.ManagersRepository;
import com.bookingHotel.nhom14.service.ManagersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // đánh dấu đây là một lớp service
public class ManagersServiceImpl implements ManagersService {
    @Autowired
    private ManagersRepository managersRepository;
    @Override
    public ManagersDTO createManagers(ManagersDTO managersDTO) {
        Managers manager = ManagersMapper.mapToManagers(managersDTO);
        Managers savedManagers = managersRepository.save(manager);
        return ManagersMapper.mapToManagersDTO(savedManagers);
    }

    @Override
    public List<ManagersDTO> getAllManagers() {
        return null;
    }

    @Override
    public ManagersDTO getManagersById(Integer id) {
        return null;
    }

    @Override
    public ManagersDTO updateManager(Integer id, ManagersDTO managersDTO) {
        return null;
    }

    @Override
    public void deleteManager(ManagersDTO managersDTO) {

    }
}
