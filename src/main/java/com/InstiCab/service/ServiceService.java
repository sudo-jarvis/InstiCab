package com.InstiCab.service;

import com.InstiCab.dto.ServiceDto;
import com.InstiCab.model.Service;
import java.util.List;

public interface ServiceService {
    void saveService(ServiceDto serviceDto);

    Service findByServiceId(Long id);

    List<ServiceDto> findAllServices();
}