package com.InstiCab.dao;

import com.InstiCab.models.User;
import com.InstiCab.utils.RowMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public class UserDAO {
    private JdbcTemplate jdbcTemplate;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setDateCreated(Date.valueOf(LocalDate.now()));
        user.setLastLoginTime(Time.valueOf(LocalTime.now()));
        user.setLastLoginDate(Date.valueOf(LocalDate.now()));
        final String sql = "INSERT INTO user(username, first_name, middle_name, last_name, email, phone_no, password," +
                " " + "date_created, last_login_date, last_login_time, role) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql,user.getUsername(),user.getFirstName(),user.getMiddleName(),user.getLastName(),
                    user.getEmail(), user.getPhoneNo(), user.getPassword(),user.getDateCreated(),
                    user.getLastLoginDate(), user.getLastLoginTime(), user.getRole());
        } catch (Exception e) {
            System.out.println(e);
            throw new DuplicateKeyException("Username not available!");
        }
    }

    public void updateUser(User user) {
        final String sql = "UPDATE user SET username = ?, first_name = ?, middle_name = ?, last_name = ?, email = ?, " +
                "phone_no = ?, password = ?, last_login_date = ?, last_login_time = ?, role = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getFirstName(), user.getMiddleName(),
                user.getLastName(), user.getEmail(), user.getPhoneNo(), user.getPassword(), user.getLastLoginDate(),
                user.getLastLoginTime(), user.getRole());
    }


    public User getUserDataByUsername(String username) {
        final String sql = "SELECT * FROM user WHERE username=?";
        try {
            return jdbcTemplate.queryForObject(sql, RowMappers.userRowMapper, username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

}
