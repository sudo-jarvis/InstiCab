package com.InstiCab.dao;

import com.InstiCab.models.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class TripDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TripDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveTrip(Trip trip) throws Exception {
        final String sql = "INSERT INTO trip(status,start_latitude,start_longitude,end_latitude,end_longitude," +
                "passenger_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql,trip.getStatus(),trip.getStartLatitude(),trip.getStartLongitude(),
                    trip.getEndLatitude(),trip.getEndLongitude(),trip.getPassengerId());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
