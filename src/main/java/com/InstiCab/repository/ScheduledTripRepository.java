package com.InstiCab.repository;

import com.InstiCab.model.ScheduledTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledTripRepository extends JpaRepository<ScheduledTrip,Long> {
    ScheduledTrip findByscheduledTripId(Long id);
}
