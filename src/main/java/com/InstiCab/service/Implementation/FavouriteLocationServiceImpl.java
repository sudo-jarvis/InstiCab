package com.InstiCab.service.Implementation;

import com.InstiCab.dao.FavouriteLocationDAO;
import com.InstiCab.dao.VehicleDAO;
import com.InstiCab.models.FavlocationJoinLocation;
import com.InstiCab.models.FavouriteLocation;
import com.InstiCab.models.Location;
import com.InstiCab.service.FavouriteLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void addDefaultLocation(Long passengerId) {
        FavouriteLocation l1 = new FavouriteLocation();
        FavouriteLocation l2 = new FavouriteLocation();
        FavouriteLocation l3 = new FavouriteLocation();
        FavouriteLocation vt = new FavouriteLocation();
        FavouriteLocation lib = new FavouriteLocation();

        l1.setPassengerId(passengerId);
        l1.setLabel("LT-1");
        l1.setLocationId(1L);
        favouriteLocationDAO.saveFavouriteLocation(l1);

        l2.setPassengerId(passengerId);
        l2.setLabel("LT-2");
        l2.setLocationId(2L);
        favouriteLocationDAO.saveFavouriteLocation(l2);

        l3.setPassengerId(passengerId);
        l3.setLabel("LT-3");
        l3.setLocationId(3L);
        favouriteLocationDAO.saveFavouriteLocation(l3);

        vt.setPassengerId(passengerId);
        vt.setLabel("VT");
        vt.setLocationId(4L);
        favouriteLocationDAO.saveFavouriteLocation(vt);

        lib.setPassengerId(passengerId);
        lib.setLabel("Library");
        lib.setLocationId(5L);
        favouriteLocationDAO.saveFavouriteLocation(lib);
    }

    @Override
    public List<FavlocationJoinLocation> getFavLocations(Long passengerId) {
        return favouriteLocationDAO.getFavLocations(passengerId);
    }

    @Override
    public Location saveLocation(Location location) {
        if(!favouriteLocationDAO.existsLocation(location)){
            favouriteLocationDAO.saveLocation(location);
        }
        return favouriteLocationDAO.getLocation(location);
    }
}
