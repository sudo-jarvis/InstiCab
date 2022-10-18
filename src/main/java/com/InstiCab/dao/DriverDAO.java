package com.InstiCab.dao;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.InstiCab.models.Driver;
import com.InstiCab.models.Trip;
import com.InstiCab.models.User;
import com.InstiCab.utils.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DriverDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DriverDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createDriver(Driver driver){
        final String sql = "INSERT INTO driver(driver_id, license_number, aadhar_number, account_no, account_name, " +
                "ifsc_code, bank_name, username) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql,driver.getDriverId(),driver.getLicenseNumber(),driver.getAadharNumber(),
                    driver.getAccountNo(),driver.getAccountName(),driver.getIfscCode(),driver.getBankName(),
                    driver.getUsername());
        } catch (Exception e) {
            System.out.println(e);
            throw new DuplicateKeyException("Driver Already Registered ! !");
        }
    }

    public void updateDriver(Driver driver) {
        final String sql = "UPDATE driver SET driver_id = ?, license_number = ?, aadhar_number = ?, account_no = ?, " +
                "account_name= ?, ifsc_code = ?, bank_name = ?, username = ?";
        jdbcTemplate.update(sql,driver.getDriverId(),driver.getLicenseNumber(),driver.getAadharNumber(),
                driver.getAccountNo(),driver.getAccountName(),driver.getIfscCode(),driver.getBankName(),
                driver.getUsername());
    }

    public Driver getDriverDataBydriverId(Long driverId) {
        final String sql = "SELECT * FROM driver WHERE driver_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, RowMappers.driverRowMapper, driverId);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Driver not found ! !");
        }
    }
    public Driver getDriverDataByUsername(String username) {
        final String sql = "SELECT * FROM driver WHERE username=?";
        try {
            return jdbcTemplate.queryForObject(sql, RowMappers.driverRowMapper, username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Driver not found ! !");
        }
    }
    public List<Driver> getAllPendingDrivers(){
        final String sql = "SELECT d.driver_id, d.license_number,d.aadhar_number,d.account_no,d.account_name,d" +
                ".ifsc_code," +
                "d.bank_name, d.username" +
                " FROM " +
                "driver as d, registration_request as r WHERE" +
                " d.driver_id=r.driver_id and r.status = 0 order by d.driver_id";
        try {
            return jdbcTemplate.query(sql, RowMappers.driverRowMapper);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Error");
        }
    }

}
