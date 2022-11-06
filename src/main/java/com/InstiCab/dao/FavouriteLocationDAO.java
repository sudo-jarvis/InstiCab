package com.InstiCab.dao;

import com.InstiCab.models.FavouriteLocation;
import com.InstiCab.utils.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavouriteLocationDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public FavouriteLocationDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public void saveFavouriteLocation(FavouriteLocation favouriteLocation) {
        final String sql = "INSERT INTO favourite_location(label,latitude_location, longitude_location, passenger_id)" +
                " VALUES (?,?,?,?)";
        try {
            jdbcTemplate.update(sql,favouriteLocation.getLabel(),favouriteLocation.getLatitudeLocation(),
                    favouriteLocation.getLongitudeLocation()
                    ,favouriteLocation.getPassengerId());
        } catch (Exception e){
            System.out.println(e);
            throw new DuplicateKeyException("Location already exists ! !");
        }
    }

    public List<FavouriteLocation> getFavLocations(Long passengerId) {
        final String sql = "SELECT f.location_id, f.label,f.latitude_location,f.longitude_location FROM favourite_location as f WHERE passenger_id = ?";
        try {
            return jdbcTemplate.query(sql, RowMappers.favouriteLocationRowMapper,passengerId);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Error");
        }
    }
}
