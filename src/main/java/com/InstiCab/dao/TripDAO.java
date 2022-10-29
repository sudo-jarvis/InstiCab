package com.InstiCab.dao;

import com.InstiCab.models.Driver;
import com.InstiCab.models.RegistrationRequest;
import com.InstiCab.models.Trip;
import com.InstiCab.utils.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

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


    public boolean tripAlreadyExists(Long passengerId) {
        final String sql = "SELECT * FROM trip WHERE status!=2 AND passenger_id=?";
        try {
            return !jdbcTemplate.query(sql, RowMappers.tripRowMapper, passengerId).isEmpty();
        } catch (Exception e) {
            System.out.println(e);
            throw new UsernameNotFoundException("Error");
        }
    }
    public List<Trip> getTripReqList() throws Exception {
        final String sql = "SELECT * from trip WHERE status = ? order by trip_id";
        try {
            return jdbcTemplate.query(sql,RowMappers.tripRowMapper,0);
        }catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Trip getTripByTripId(Long tripId) {
        final String sql = "SELECT * FROM trip WHERE trip_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, RowMappers.tripRowMapper, tripId);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Trip not found ! !");
        }
    }

    public List<Trip> getPassengerAllTrips(Long passengerId){
        final String sql = "SELECT t.trip_id, t.start_date, t.start_time, t.end_date, t.end_time, " +
                "t.status, " +
                "t.start_latitude, t.start_longitude, t.end_latitude, t.end_longitude, t.driver_id, t.passenger_id" +
                " FROM " +
                "trip as t WHERE" +
                " t.passenger_id = ? order by t.trip_id";
        try {
            return jdbcTemplate.query(sql, RowMappers.tripRowMapper, passengerId);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Error");
        }
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

    public void acceptTripRequest(Trip trip) {
        final String sql = "UPDATE trip SET status = 1,start_date = ?,start_time = ?,driver_id = ? WHERE " +
                "trip_id = ?";
        try {
            jdbcTemplate.update(sql,trip.getStartDate(),
                    trip.getStartTime(),trip.getDriverId(),trip.getTripId());
        } catch (Exception e){
            System.out.println(e);
            throw new DuplicateKeyException("Trip Request doesnt exist ! !");
        }
    }

    public List<Trip> getTripList() throws Exception {
        final String sql = "SELECT * from trip WHERE status = ? order by trip_id";
        try {
            return jdbcTemplate.query(sql,RowMappers.tripRowMapper,1);
        }catch (Exception e) {
            throw new Exception(e);
        }
    }

    public boolean tripAlreadyRunning(Long driverId) throws Exception {
        final String sql = "SELECT * from trip WHERE status = ? AND driver_id = ?";
        try {
            return !(jdbcTemplate.query(sql,RowMappers.tripRowMapper,1,driverId).isEmpty());
        }catch (Exception e){
            throw new Exception(e);
        }
    }
}
