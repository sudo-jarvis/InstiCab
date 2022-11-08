package com.InstiCab.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
        }
    }
}
