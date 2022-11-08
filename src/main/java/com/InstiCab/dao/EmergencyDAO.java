package com.InstiCab.dao;

import com.InstiCab.models.Emergency;
import com.InstiCab.utils.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public class EmergencyDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createEmergencyRequest(Integer type, String loggedInUsername) {
        final String sql = "INSERT INTO emergency_service(type,request_time,username) VALUES(?,?,?)";
        try {
            jdbcTemplate.update(sql,type, Time.valueOf(LocalTime.now()),loggedInUsername);
        } catch (Exception e) {
            System.out.println(e);
            throw new UsernameNotFoundException("Error in creating Emergency Request");
        }
    }

    public List<Emergency> getEmergencyRequests() {
        final String sql = "SELECT * FROM emergency_service";
        try {
            return jdbcTemplate.query(sql, RowMappers.emergencyRowMapper);
        } catch (Exception e) {
            System.out.println(e);
            throw new UsernameNotFoundException("Error in listing emergency");
        }
    }
}
