package com.InstiCab.dao;

import com.InstiCab.models.Driver;
import com.InstiCab.models.RegistrationRequest;
import com.InstiCab.models.ScheduledTrip;
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
            System.out.println(e);
            throw new Exception("error in saving trip");
        }
    }


    public boolean tripAlreadyExists(Long passengerId) {
        final String sql = "SELECT * FROM trip WHERE (status<2 OR status=5) AND passenger_id=?";
        try {
            return !jdbcTemplate.query(sql, RowMappers.tripRowMapper, passengerId).isEmpty();
        } catch (Exception e) {
            System.out.println(e);
            throw new UsernameNotFoundException("Error trip already exists");
        }
    }
    public List<Trip> getTripReqList() throws Exception {
        final String sql = "SELECT * from trip WHERE status = ? order by trip_id";
        try {
            return jdbcTemplate.query(sql,RowMappers.tripRowMapper,0);
        }catch (Exception e) {
            System.out.println(e);
            throw new Exception("error in getting trip request list");
        }
    }

    public Trip getTripByTripId(Long tripId) {
        final String sql = "SELECT * FROM trip WHERE trip_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, RowMappers.tripRowMapper, tripId);
        } catch (Exception e) {
            System.out.println(e);
            throw new UsernameNotFoundException("Trip not found ! !");
        }
    }

    public Trip getScheduledTrip(Long passengerId){
        final String sql = "SELECT * FROM trip WHERE passenger_id=? and status=5";
        try {
            return jdbcTemplate.queryForObject(sql, RowMappers.tripRowMapper, passengerId);
        } catch (Exception e) {
            System.out.println(e);
            throw new UsernameNotFoundException("Trip not found ! !");
        }
    }

    public List<Trip> getPassengerAllTrips(Long passengerId){
        final String sql = "SELECT * FROM " +
                "trip as t WHERE" +
                " t.passenger_id = ? order by t.trip_id";
        try {
            return jdbcTemplate.query(sql, RowMappers.tripRowMapper, passengerId);
        } catch (Exception e) {
            System.out.println(e);
            throw new UsernameNotFoundException("Error in getting all passenger trips");
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
            System.out.println(e);
            throw new Exception("error in getting trip list");
        }
    }

    public boolean tripAlreadyRunning(Long driverId) throws Exception {
        final String sql = "SELECT * from trip WHERE status = ? AND driver_id = ?";
        try {
            return !(jdbcTemplate.query(sql,RowMappers.tripRowMapper,1,driverId).isEmpty());
        }catch (Exception e){
            System.out.println(e);
            throw new Exception("error trip already running");
        }
    }

    public void endTrip(Trip trip) {
        final String sql = "UPDATE trip SET status = 3,end_date = ?,end_time = ?,end_latitude = ?,end_longitude = ? WHERE" +
                " trip_id = ?";
        try {
            jdbcTemplate.update(sql,trip.getEndDate(),trip.getEndTime(),trip.getEndLatitude(),trip.getEndLongitude(),
                    trip.getTripId());
        } catch (Exception e){
            System.out.println(e);
            throw new DuplicateKeyException("Trip doesnt exist ! !");
        }
    }
    public void changeTripStatus(Long tripId, int status) {
        final String sql = "UPDATE trip SET status = ? WHERE " +
                "trip_id = ?";
        try {
            jdbcTemplate.update(sql,status,tripId);
        } catch (Exception e){
            System.out.println(e);
            throw new DuplicateKeyException("Trip Request doesnt exist ! !");
        }
    }

    public void cancelTrip(Long tripId) {
        final String sql = "UPDATE trip SET status = 2 WHERE trip_id = ? AND (status=0 OR status = 5) ";
        try {
            jdbcTemplate.update(sql, tripId);
        } catch (Exception e){
            System.out.println(e);
            throw new DuplicateKeyException("Trip doesnt exist ! !");
        }
    }

    public void saveFeedback(String feedback, Long tripId) {
        final String sql = "UPDATE trip SET feedback = ? WHERE " +
                "trip_id = ?";
        try {
            jdbcTemplate.update(sql,feedback,tripId);
        } catch (Exception e){
            System.out.println(e);
            throw new DuplicateKeyException("Trip Request doesnt exist ! !");
        }
    }

    public List<Trip> getDriverPreviousTrips(Long driverId) throws Exception {
        final String sql = "SELECT * from trip WHERE driver_id = ? and (status = 2 or status = 4) order by trip_id";
        try {
            return jdbcTemplate.query(sql,RowMappers.tripRowMapper, driverId);
        }catch (Exception e) {
            System.out.println(e);
            throw new Exception("error in getting all driver trips ");
        }
    }

    public void updateTrip(Trip trip) {
        final String sql = "UPDATE trip SET status = ?, end_time = ?, end_date = ? WHERE trip_id = ?";
        try {
            jdbcTemplate.update(sql,trip.getStatus(),trip.getEndTime(),trip.getEndDate(),trip.getTripId());
        } catch (Exception e){
            System.out.println(e);
            throw new DuplicateKeyException("Trip Request doesnt exist ! !");
        }
    }

//    public List<Trip> getDriverCurrentTrips(Long driverId) throws Exception {
//        final String sql = "SELECT * from trip WHERE driver_id = ? and (status = 0 or status = 1) order by trip_id";
//        try {
//            return jdbcTemplate.query(sql,RowMappers.tripRowMapper, driverId);
//        }catch (Exception e) {
//            throw new Exception(e);
//        }
//    }
}
