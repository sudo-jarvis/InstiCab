package com.InstiCab.service;

import com.InstiCab.models.FavlocationJoinLocation;
import com.InstiCab.models.FavouriteLocation;
import com.InstiCab.models.Location;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface FavouriteLocationService {
    void saveFavouriteLocation(FavouriteLocation favouriteLocation);

    void addDefaultLocation(Long passengerId);

    List<FavlocationJoinLocation> getFavLocations(Long passengerId);

    Location saveLocation(Location location);
}
