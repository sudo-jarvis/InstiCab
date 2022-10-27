package com.InstiCab.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.InstiCab.models.*;
import org.springframework.jdbc.core.RowMapper;

public final class RowMappers {


    private static boolean isValid(String s) {
        return ((s != null) && (s != ""));
    }

    public static RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet row, int i) throws SQLException {
            User user = new User();
            user.setUsername(row.getString("username"));
            user.setFirstName(row.getString("first_name"));
            user.setMiddleName(row.getString("middle_name"));
            user.setLastName(row.getString("last_name"));
            user.setEmail(row.getString("email"));
            user.setPhoneNo(row.getString("phone_no"));
            user.setPassword(row.getString("password"));
            user.setDateCreated(row.getDate("date_created"));
            user.setLastLoginDate(row.getDate("last_login_date"));
            user.setLastLoginTime(row.getTime("last_login_time"));
            user.setRole(row.getString("role"));
            return user;
        }
    };

    public static RowMapper<Driver> driverRowMapper = new RowMapper<Driver>() {
        @Override
        public Driver mapRow(ResultSet row, int i) throws SQLException {
            Driver driver = new Driver();
            driver.setDriverId(row.getLong("driver_id"));
            driver.setLicenseNumber(row.getString("license_number"));
            driver.setAadharNumber(row.getString("aadhar_number"));
            driver.setAccountNo(row.getString("account_no"));
            driver.setAccountName(row.getString("account_name"));
            driver.setIfscCode(row.getString("ifsc_code"));
            driver.setBankName(row.getString("bank_name"));
            driver.setUsername(row.getString("username"));

            return driver;
        }
    };

    public static RowMapper<RegistrationRequest> RegistrationRequestRowMapper = new RowMapper<RegistrationRequest>() {
        @Override
        public RegistrationRequest mapRow(ResultSet row, int i) throws SQLException {
            RegistrationRequest registrationRequest = new RegistrationRequest();
            registrationRequest.setRequestId(row.getLong("request_id"));
            registrationRequest.setTimeApplied(row.getTime("time_applied"));
            registrationRequest.setDateApplied(row.getDate("date_applied"));
            registrationRequest.setStatus(row.getInt("status"));
            registrationRequest.setTimeAccepted(row.getTime("time_accepted"));
            registrationRequest.setDateAccepted(row.getDate("date_accepted"));
            registrationRequest.setDriverId(row.getLong("driver_id"));
            return registrationRequest;
        }
    };

    public static RowMapper<Passenger>passengerRowMapper = new RowMapper<Passenger>() {
        @Override
        public Passenger mapRow(ResultSet row, int i) throws SQLException {
            Passenger passenger = new Passenger();
            passenger.setPassengerId(row.getLong("passenger_id"));
            passenger.setUsername(row.getString("username"));
            return passenger;
        }
    };

    public static RowMapper<Trip> tripRowMapper = new RowMapper<Trip>() {
        @Override
        public Trip mapRow(ResultSet row, int i) throws SQLException {
            Trip trip = new Trip();
            trip.setTripId(row.getLong("trip_id"));
            trip.setStartDate(row.getDate("start_date"));
            trip.setStartTime(row.getTime("start_time"));
            trip.setEndDate(row.getDate("end_date"));
            trip.setEndTime(row.getTime("end_time"));
            trip.setStatus(row.getInt("status"));
            trip.setStartLatitude(row.getFloat("start_latitude"));
            trip.setStartLongitude(row.getFloat("start_longitude"));
            trip.setEndLatitude(row.getFloat("end_latitude"));
            trip.setEndLongitude(row.getFloat("end_longitude"));
            trip.setDriverId(row.getLong("driver_id"));
            trip.setPassengerId(row.getLong("passenger_id"));
            return trip;

        }
    };

    public static RowMapper<TripRequest> TripRequestRowMapper = new RowMapper<TripRequest>() {
        @Override
        public TripRequest mapRow(ResultSet row, int i) throws SQLException {
            TripRequest tripRequest = new TripRequest();
            tripRequest.setTripRequestId(row.getInt("trip_request_id"));
            tripRequest.setPassengerId(row.getLong("passenger_id"));
            tripRequest.setDriverId(row.getLong("driver_id"));
            tripRequest.setTripId(row.getLong("trip_id"));
            return tripRequest;
        }
    };

}
