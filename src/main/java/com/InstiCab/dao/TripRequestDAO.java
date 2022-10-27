package com.InstiCab.dao;

import com.InstiCab.models.RegistrationRequest;
import com.InstiCab.models.TripRequest;
import com.InstiCab.utils.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TripRequestDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TripRequestDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTripRequest(TripRequest tripRequest){
        final String sql = "INSERT INTO trip_request(passenger_id, driver_id, trip_id) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(sql,tripRequest.getPassengerId(),tripRequest.getDriverId(),tripRequest.getTripId());
        }
        catch (Exception e){
            System.out.println(e);
            throw new DuplicateKeyException("Trip Request for this passenger already exists ! !");
        }
    }
    public List<TripRequest> getAllActiveTripRequests() {
        final String sql = "SELECT * FROM trip WHERE status = ? order by trip_id";
        return jdbcTemplate.query(sql, RowMappers.TripRequestRowMapper, "0");
    }

    public void rejectTripRequest(Long tripId) {
        final String sql = "UPDATE trip SET status = 2 WHERE trip_id = ?";
        try {
            jdbcTemplate.update(sql,tripId);
        } catch (Exception e){
            System.out.println(e);
            throw new DuplicateKeyException("Trip Request doesnt exist ! !");
        }
    }

    public void acceptTripRequest(TripRequest tripRequest) {
        final String sql = "UPDATE trip SET status = 1 WHERE " +
                "driver_id = ?";
        try {
            jdbcTemplate.update(sql,tripRequest.getTripId());
        } catch (Exception e){
            System.out.println(e);
            throw new DuplicateKeyException("Trip Request doesnt exist ! !");
        }
    }

    public TripRequest getTripRequestByTripId(Long tripId) {
        final String sql = "SELECT * FROM trip_request WHERE trip_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, RowMappers.TripRequestRowMapper, tripId);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Trip Request not found");
        }
    }
}
