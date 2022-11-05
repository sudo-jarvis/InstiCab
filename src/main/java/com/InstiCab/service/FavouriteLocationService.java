package com.InstiCab.service;

import com.InstiCab.models.FavouriteLocation;
import org.springframework.stereotype.Service;

@Service
public interface FavouriteLocationService {
    void saveFavouriteLocation(FavouriteLocation favouriteLocation);

    void addDefaultLocation(Long passengerId);
}
