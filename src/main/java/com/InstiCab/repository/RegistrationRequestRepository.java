package com.InstiCab.repository;

import com.InstiCab.model.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest,Long> {
    RegistrationRequest findByRequestId(Long id);
}
