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

    @Override
    public void addDefaultLocation(Long passengerId) {
        FavouriteLocation l1 = new FavouriteLocation();
        FavouriteLocation l2 = new FavouriteLocation();
        FavouriteLocation l3 = new FavouriteLocation();
        FavouriteLocation vt = new FavouriteLocation();
        FavouriteLocation lib = new FavouriteLocation();

        l1.setLatitudeLocation((float) 25.260220092673407);
        l1.setLongitudeLocation((float) 82.99086984049433);
        l1.setPassengerId(passengerId);
        l1.setLabel("LT-1");
        favouriteLocationDAO.saveFavouriteLocation(l1);

        l2.setLatitudeLocation((float) 25.26203299523337);
        l2.setLongitudeLocation((float) 82.99381735939455);
        l2.setPassengerId(passengerId);
        l2.setLabel("LT-2");
        favouriteLocationDAO.saveFavouriteLocation(l2);

        l3.setLatitudeLocation((float) 25.258980826409978);
        l3.setLongitudeLocation((float) 82.99280814420261);
        l3.setPassengerId(passengerId);
        l3.setLabel("LT-3");
        favouriteLocationDAO.saveFavouriteLocation(l3);

        vt.setLatitudeLocation((float) 25.266216084701153);
        vt.setLongitudeLocation((float) 82.98792913561284);
        vt.setPassengerId(passengerId);
        vt.setLabel("VT");
        favouriteLocationDAO.saveFavouriteLocation(vt);

        lib.setLatitudeLocation((float) 25.261953268672897);
        lib.setLongitudeLocation((float) 82.9895353508379);
        lib.setPassengerId(passengerId);
        lib.setLabel("Library");

        favouriteLocationDAO.saveFavouriteLocation(lib);

    }
}
