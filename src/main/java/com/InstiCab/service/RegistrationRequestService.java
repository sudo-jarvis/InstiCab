package com.InstiCab.service;

import com.InstiCab.dto.RegistrationRequestDto;
import com.InstiCab.model.RegistrationRequest;
import java.util.List;

public interface RegistrationRequestService {
    void saveRequest(RegistrationRequestDto userDto);

    RegistrationRequest findByRequestId(Long id);

    List<RegistrationRequestDto> findAllRequests();
}