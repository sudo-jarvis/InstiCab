package com.InstiCab.service;

import com.InstiCab.dto.FavouriteLocationDto;
import com.InstiCab.model.FavouriteLocation;
import java.util.List;

public interface FavouriteLocationService {
    void saveFavouriteLocation(FavouriteLocationDto favouriteLocationDto);

    FavouriteLocation findByLocationId(Long id);

    List<FavouriteLocationDto> findAllLocations();
}