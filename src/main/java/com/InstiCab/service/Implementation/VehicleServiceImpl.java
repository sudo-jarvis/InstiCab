package com.InstiCab.service.Implementation;

import com.InstiCab.dao.VehicleDAO;
import com.InstiCab.models.Vehicle;
import com.InstiCab.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleDAO vehicleDAO;
    @Autowired
    public VehicleServiceImpl(VehicleDAO vehicleDAO){
        this.vehicleDAO = vehicleDAO;
    }
    @Override
    public void saveVehicle(Vehicle vehicle) {
        vehicleDAO.saveVehicle(vehicle);
    }
}
