package com.InstiCab.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.InstiCab.models.User;
import org.springframework.jdbc.core.RowMapper;
import com.InstiCab.models.RegistrationRequest;
import com.InstiCab.models.Driver;
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

}
