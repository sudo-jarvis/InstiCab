package com.InstiCab.dao;

import com.InstiCab.models.ScheduledTrip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduledTripDAO {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ScheduledTripDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public void saveScheduledTrip(ScheduledTrip scheduledTrip) throws Exception {
        final String sql = "INSERT INTO scheduled_trip(trip_time,trip_id) VALUES (?, ?)";
        try {
            jdbcTemplate.update(sql,scheduledTrip.getTripTime(),scheduledTrip.getTripId());
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("error in schedule trip dao");
        }
    }

    public void delete(ScheduledTrip scheduledTrip) throws Exception {
        final String sql = "DELETE FROM scheduled_trip WHERE trip_id=?";
        try {
            jdbcTemplate.update(sql,scheduledTrip.getTripId());
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("error in deleting scheduled trip");
        }
    }
}
