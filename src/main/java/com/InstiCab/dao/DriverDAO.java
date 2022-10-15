package com.InstiCab.dao;

import com.InstiCab.models.Driver;
import com.InstiCab.models.User;
import com.InstiCab.utils.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

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

    public Driver getDriverDataBydriverId(Integer driverId) {
        final String sql = "SELECT * FROM user WHERE username=?";
        try {
            return jdbcTemplate.queryForObject(sql, RowMappers.driverRowMapper, driverId);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
