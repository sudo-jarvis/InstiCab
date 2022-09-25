package com.InstiCab.repository;

import com.InstiCab.model.TripRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRequestRepository extends JpaRepository<TripRequest,Long> {
    TripRequest findByTripRequestId(Long id);
}
