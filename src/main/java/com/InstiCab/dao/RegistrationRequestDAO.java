package com.InstiCab.dao;

import com.InstiCab.models.RegistrationRequest;
import com.InstiCab.models.User;
import com.InstiCab.utils.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegistrationRequestDAO {
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public RegistrationRequestDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createRegistrationRequest(RegistrationRequest registrationRequest){
        final String sql = "INSERT INTO registration_request(time_applied, date_applied, status, " +
                "driver_id) VALUES (?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql,registrationRequest.getTimeApplied(),registrationRequest.getDateApplied(),registrationRequest.getStatus(),registrationRequest.getDriverId());
        } catch (Exception e){
            System.out.println(e);
            throw new DuplicateKeyException("Registration Request for this driver already exists ! !");
        }
    }
    public List<RegistrationRequest> getAllActiveRegistrationRequests() {
        final String sql = "SELECT * FROM registration_request WHERE status = ? order by driver_id";
        return jdbcTemplate.query(sql, RowMappers.RegistrationRequestRowMapper, "0");
    }

    public void rejectRequest(Long driverId) {
        final String sql = "UPDATE registration_request SET status = 2 WHERE driver_id = ?";
        try {
            jdbcTemplate.update(sql,driverId);
        } catch (Exception e){
            System.out.println(e);
            throw new DuplicateKeyException("Registration Request doesnt exist ! !");
        }
    }

    public void acceptRequest(RegistrationRequest registrationRequest) {
        final String sql = "UPDATE registration_request SET status = 1,time_accepted = ?,date_accepted = ? WHERE " +
                "driver_id = ?";
        try {
            jdbcTemplate.update(sql,registrationRequest.getTimeAccepted(),
                    registrationRequest.getDateAccepted(),registrationRequest.getDriverId());
        } catch (Exception e){
            System.out.println(e);
            throw new DuplicateKeyException("Registration Request doesnt exist ! !");
        }
    }

    public RegistrationRequest getRequestByDriverId(Long driverId) {
        final String sql = "SELECT * FROM registration_request WHERE driver_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, RowMappers.RegistrationRequestRowMapper, driverId);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Registration Request not found");
        }
    }
}
