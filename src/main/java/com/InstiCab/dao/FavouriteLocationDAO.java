package com.InstiCab.dao;

import com.InstiCab.models.FavouriteLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FavouriteLocationDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public FavouriteLocationDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public void saveFavouriteLocation(FavouriteLocation favouriteLocation) {
        final String sql = "INSERT INTO favourite_location(latitude_location, longitude_location, passenger_id) VALUES (?,?,?)";
        try {
            jdbcTemplate.update(sql,favouriteLocation.getLatitudeLocation(),favouriteLocation.getLongitudeLocation()
                    ,favouriteLocation.getPassengerId());
        } catch (Exception e){
            System.out.println(e);
            throw new DuplicateKeyException("Location already exists ! !");
        }
    }
}
