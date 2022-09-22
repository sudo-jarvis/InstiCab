package com.InstiCab.repository;

import com.InstiCab.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip,Long> {
    Trip findByTripid(Long id);
}
