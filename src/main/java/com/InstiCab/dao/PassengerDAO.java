package com.InstiCab.dao;

import com.InstiCab.models.Passenger;
import com.InstiCab.service.DriverService;
import com.InstiCab.utils.RowMappers;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerDAO {
    private JdbcTemplate jdbcTemplate;

    public PassengerDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void savePassenger(String username){
        final String sql = "INSERT INTO passenger(username) VALUES(?)";
        try {
            jdbcTemplate.update(sql,username);
        } catch (Exception e) {
            System.out.println(e);
            throw new DuplicateKeyException("Passenger Already Registered ! !");
        }
    }

    public Passenger getPassengerByUsername(String username){
        final String sql = "SELECT * FROM passenger WHERE username=?";
        try {
            return jdbcTemplate.queryForObject(sql, RowMappers.passengerRowMapper, username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Passenger not found");
        }
    }

    public Passenger getPassengerByPassengerId(Long passengerId) {
        final String sql = "SELECT * FROM passenger WHERE passenger_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, RowMappers.passengerRowMapper, passengerId);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Passenger not found ! !");
        }
    }
}
