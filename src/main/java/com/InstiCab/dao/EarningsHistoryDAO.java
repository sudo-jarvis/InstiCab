package com.InstiCab.dao;

import com.InstiCab.models.EarningsHistory;
import com.InstiCab.utils.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EarningsHistoryDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EarningsHistoryDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public void saveEarning(EarningsHistory earning) {
        final String sql = "INSERT INTO earning_history(driver_id, cost, distance_travelled) VALUES(?, ?, ?)";
        try {
            jdbcTemplate.update(sql,earning.getDriverId(),earning.getCost(),earning.getDistanceTravelled());
        } catch (Exception e) {
            System.out.println(e);
            throw new DuplicateKeyException("Earning Already Exists ! !");
        }
    }

    public List<EarningsHistory> getEarningHistory(Long driverId) {
        final String sql = "SELECT e.earning_id, e.cost, e.distance_travelled, e.driver_id" +
                " FROM " +
                "earning_history as e WHERE" +
                " e.driver_id = ? order by e.earning_id";
        try {
            return jdbcTemplate.query(sql, RowMappers.earningsHistoryRowMapper, driverId);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Error");
        }
    }
}
