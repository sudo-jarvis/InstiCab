package com.InstiCab.repository;

import com.InstiCab.model.FavouriteLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteLocationRepository extends JpaRepository<FavouriteLocation,Long> {
    FavouriteLocation findByLocationId(Long id);
}
