package com.InstiCab.service.Implementation;

import com.InstiCab.dao.FavouriteLocationDAO;
import com.InstiCab.dao.VehicleDAO;
import com.InstiCab.models.FavouriteLocation;
import com.InstiCab.service.FavouriteLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouriteLocationServiceImpl implements FavouriteLocationService {
    private final FavouriteLocationDAO favouriteLocationDAO;
    @Autowired
    public FavouriteLocationServiceImpl(FavouriteLocationDAO favouriteLocationDAO){
        this.favouriteLocationDAO = favouriteLocationDAO;
    }
    @Override
    public void saveFavouriteLocation(FavouriteLocation favouriteLocation) {
        favouriteLocationDAO.saveFavouriteLocation(favouriteLocation);
    }
}
