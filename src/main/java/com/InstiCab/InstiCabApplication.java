package com.InstiCab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class InstiCabApplication implements CommandLineRunner {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(InstiCabApplication.class, args);
	}

	@Override
	public void run(String... args) {
		runJDBC();
	}
	void runJDBC() {
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Coupon(couponId INT, couponDiscount FLOAT, couponValidity DATE, maxDiscount INT)");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Driver(driverId INT, licenseNumber VARCHAR(255), aadharNumber VARCHAR(255),accountNo VARCHAR(255),accountName VARCHAR(255),ifscCode VARCHAR(255),bankName VARCHAR(255),userId INT )");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS EarningsHistory(earningId INT, cost FLOAT, distanceTravelled FLOAT)");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS FavouriteLocation(locationId INT, latitudeLocation FLOAT, longitudeLocation FLOAT)");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Passenger(passengerId INT)");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS RegistrationRequest(requestId INT, timeApplied TIME, dateApplied DATE, status INT, timeAccepted TIME, dateAccepted DATE)");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Role(id INT, name VARCHAR(255))");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS ScheduledTrip(scheduledTripId INT, tripTime TIME, startLatitude FLOAT, startLongitude FLOAT, endLatitude FLOAT, endLongitude FLOAT)");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Service(serviceId INT, name VARCHAR(255), type VARCHAR(255), latitudeLocation FLOAT, longitudeLocation FLOAT, contactNo VARCHAR(255))");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Transaction(transactionId INT, timeTransaction TIME, dateTransaction DATE, amount FLOAT, status INT)");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Trip(tripId INT, startDate DATE, startTime TIME, endDate DATE, endTime TIME, status INT, startLatitude FLOAT, startLongitude FLOAT, endLatitude FLOAT, endLongitude FLOAT, driverId INT)");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS TripRequest(tripRequestId INT, tripId INT)");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS User(username VARCHAR(255), firstName VARCHAR(255), middleName VARCHAR(255), lastName VARCHAR(255), email VARCHAR(255), phoneNo VARCHAR(255), password VARCHAR(255), dateCreated DATE, lastLoginDate DATE, lastLoginTime TIME, isDriver INT, driverId INT)");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Vehicle(vehicleId INT, vehicleType VARCHAR(255), registrationNumber VARCHAR(255), insuranceNumber VARCHAR(255), registrationState VARCHAR(255))");

	}
}
