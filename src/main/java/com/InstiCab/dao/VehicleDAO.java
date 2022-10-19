package com.InstiCab.dao;

import com.InstiCab.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public VehicleDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveVehicle(Vehicle vehicle){
        final String sql = "INSERT INTO vehicle(vehicle_name, registration_number, insurance_number, " +
                "registration_state, driver_id) VALUES (?,?,?,?,?)";
        try {
            jdbcTemplate.update(sql,vehicle.getVehicleName(),vehicle.getRegistrationNumber()
                    ,vehicle.getInsuranceNumber(),vehicle.getRegistrationState(),vehicle.getDriverId());
        } catch (Exception e){
            System.out.println(e);
            throw new DuplicateKeyException("Vehicle already exists ! !");
        }
    }
}
