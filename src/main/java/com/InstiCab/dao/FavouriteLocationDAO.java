package com.InstiCab.dao;

import com.InstiCab.models.FavlocationJoinLocation;
import com.InstiCab.models.FavouriteLocation;
import com.InstiCab.models.Location;
import com.InstiCab.utils.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.util.List;

@Repository
public class FavouriteLocationDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public FavouriteLocationDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public void saveFavouriteLocation(FavouriteLocation favouriteLocation) {
        final String sql = "INSERT INTO favourite_location(label,location_id, passenger_id)" +
                " VALUES (?,?,?)";
        try {
            jdbcTemplate.update(sql,favouriteLocation.getLabel(),favouriteLocation.getLocationId()
                    ,favouriteLocation.getPassengerId());
        } catch (Exception e){
            System.out.println(e);
            throw new DuplicateKeyException("Location already exists ! !");
        }
    }

    public boolean existsLocation(Location location){
        final String sql = "SELECT * FROM location WHERE ABS(latitude-?)<0.0001 AND (longitude-?)<0.0001" ;
        try {
            DecimalFormat df = new DecimalFormat("#.####");
            Float n1 = location.getLatitudeLocation();
            Float n2 = location.getLongitudeLocation();

            return !(jdbcTemplate.query(sql, RowMappers.LocationRowMapper,df.format(n1),
                    df.format(n2)).isEmpty());
        } catch (Exception e) {
            System.out.println(e);
            throw new UsernameNotFoundException("Error");
        }
    }

    public List<FavlocationJoinLocation> getFavLocations(Long passengerId) {
        final String sql = "SELECT f.label as label,l.latitude as latitude_location,l.longitude as " +
                "longitude_location FROM favourite_location as f, " +
                "location as l WHERE passenger_id = ? AND l.location_id = f.location_id" ;
        try {
            return jdbcTemplate.query(sql, RowMappers.favouriteLocationRowMapper,passengerId);
        } catch (Exception e) {
            System.out.println(e);
            throw new UsernameNotFoundException("Error");
        }
    }

    public void saveLocation(Location location) {
        final String sql = "INSERT INTO location(location_id, latitude,longitude) VALUES(?,?,?)";
        try{
            jdbcTemplate.update(sql,location.getLocationId(),location.getLatitudeLocation(),
                    location.getLongitudeLocation());
        }
        catch(Exception e){
            System.out.println(e);
            throw new UsernameNotFoundException("Error");
        }
    }

    public Location getLocation(Location location){
        final String sql = "SELECT * FROM location WHERE ABS(latitude-?)<0.0001 AND (longitude-?)<0.0001";
        try {
            DecimalFormat df = new DecimalFormat("#.####");
            Float n1 = location.getLatitudeLocation();
            Float n2 = location.getLongitudeLocation();
            return jdbcTemplate.queryForObject(sql, RowMappers.LocationRowMapper, df.format(n1),df.format(n2));
        } catch (Exception e) {
            System.out.println(e);
            throw new UsernameNotFoundException("Driver not found ! !");
        }
    }
}
