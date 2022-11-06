package com.InstiCab.service;

import com.InstiCab.models.FavouriteLocation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface FavouriteLocationService {
    void saveFavouriteLocation(FavouriteLocation favouriteLocation);

    void addDefaultLocation(Long passengerId);

    List<FavouriteLocation> getFavLocations(Long passengerId);
}
