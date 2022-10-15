package com.InstiCab.dao;

import com.InstiCab.models.RegistrationRequest;
import com.InstiCab.utils.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegistrationRequestDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RegistrationRequestDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<RegistrationRequest> getAllActiveRegistrationRequests() {
        final String sql = "SELECT * FROM registration_request WHERE status = ?";
        return jdbcTemplate.query(sql, RowMappers.RegistrationRequestRowMapper, "0");
    }
}
