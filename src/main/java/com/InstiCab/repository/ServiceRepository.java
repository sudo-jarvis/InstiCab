package com.InstiCab.repository;

import com.InstiCab.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {
    Service findByServiceId(Long id);
}
