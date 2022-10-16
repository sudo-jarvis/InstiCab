CREATE DATABASE IF NOT EXISTS insticab;
USE insticab;

CREATE TABLE IF NOT EXISTS user(
    username VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255) DEFAULT NULL,
    last_name VARCHAR(255) DEFAULT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_no VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    date_created date NOT NULL,
    last_login_date date DEFAULT NULL,
    last_login_time time DEFAULT NULL,
    role VARCHAR(255) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS passenger(
    passenger_id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    PRIMARY KEY (passenger_id),
    FOREIGN KEY (username) REFERENCES user(username) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS driver (
    driver_id BIGINT NOT NULL AUTO_INCREMENT,
    license_number VARCHAR(255) NOT NULL UNIQUE,
    aadhar_number VARCHAR(255) NOT NULL UNIQUE,
    account_no VARCHAR(255) NOT NULL UNIQUE,
    account_name VARCHAR(255) NOT NULL,
    ifsc_code VARCHAR(255) NOT NULL,
    bank_name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    PRIMARY KEY (driver_id),
    FOREIGN KEY (username) REFERENCES user(username) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS coupon (
    coupon_id BIGINT NOT NULL AUTO_INCREMENT,
    coupon_discount FLOAT NOT NULL,
    coupon_validity DATE NOT NULL,
    max_discount INT NOT NULL,
    passenger_id BIGINT NOT NULL,
    PRIMARY KEY (coupon_id),
    FOREIGN KEY (passenger_id) REFERENCES passenger(passenger_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS earning_history(
    earning_id BIGINT NOT NULL AUTO_INCREMENT,
    cost FLOAT NOT NULL,
    distance_travelled FLOAT NOT NULL,
    driver_id BIGINT NOT NULL,
    PRIMARY KEY(earning_id),
    FOREIGN KEY (driver_id) REFERENCES driver(driver_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS favourite_location(
    location_id BIGINT NOT NULL AUTO_INCREMENT,
    latitude_location FLOAT NOT NULL,
    longitude_location FLOAT NOT NULL,
    passenger_id BIGINT NOT NULL,
    PRIMARY KEY (location_id),
    FOREIGN KEY (passenger_id) REFERENCES passenger(passenger_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS registration_request(
    request_id BIGINT NOT NULL AUTO_INCREMENT,
    time_applied time NOT NULL,
    date_applied date NOT NULL,
    status INT NOT NULL,
    time_accepted time DEFAULT NULL,
    date_accepted date DEFAULT NULL,
    driver_id BIGINT NOT NULL,
    PRIMARY KEY (request_id),
    FOREIGN KEY (driver_id) REFERENCES driver(driver_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS role(
    id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS trip(
    trip_id BIGINT NOT NULL AUTO_INCREMENT,
    start_date date NOT NULL,
    start_dime time NOT NULL,
    end_date date NOT NULL,
    end_time time NOT NULL,
    status INT NOT NULL,
    start_latitude FLOAT NOT NULL,
    start_longitude FLOAT NOT NULL,
    end_latitude FLOAT NOT NULL,
    end_longitude FLOAT NOT NULL,
    driver_id BIGINT NOT NULL,
    passenger_id BIGINT NOT NULL,
    PRIMARY KEY (trip_id),
    FOREIGN KEY (driver_id) REFERENCES driver(driver_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (passenger_id) REFERENCES passenger(passenger_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS scheduled_trip(
    scheduled_trip_id BIGINT NOT NULL AUTO_INCREMENT,
    trip_time time NOT NULL,
    start_latitude FLOAT NOT NULL,
    start_longitude FLOAT NOT NULL,
    end_latitude FLOAT NOT NULL,
    end_longitude FLOAT NOT NULL,
    driver_id BIGINT NOT NULL,
    trip_id BIGINT NOT NULL,
    PRIMARY KEY (scheduled_trip_id),
    FOREIGN KEY (driver_id) REFERENCES driver(driver_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (trip_id) REFERENCES trip(trip_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS vehicle(
    vehicle_id BIGINT NOT NULL,
    vehicle_type VARCHAR(255) NOT NULL,
    registration_number VARCHAR(255) NOT NULL UNIQUE,
    insurance_number VARCHAR(255) NOT NULL UNIQUE,
    registration_state VARCHAR(255) NOT NULL,
    driver_id BIGINT NOT NULL,
    PRIMARY KEY (vehicle_id),
    FOREIGN KEY (driver_id) REFERENCES driver(driver_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS service(
    service_id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    latitude_location FLOAT NOT NULL,
    longitude_location FLOAT NOT NULL,
    contact_no VARCHAR(255) NOT NULL UNIQUE,
    vehicle_id BIGINT,
    PRIMARY KEY (service_id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS transaction(
    transaction_id BIGINT NOT NULL AUTO_INCREMENT,
    time_transaction time NOT NULL,
    date_transcation date NOT NULL,
    amount FLOAT NOT NULL,
    status INT NOT NULL,
    username VARCHAR(255) NOT NULL,
    PRIMARY KEY (transaction_id),
    FOREIGN KEY (username) REFERENCES user(username) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS trip_request(
    trip_request_id INT NOT NULL AUTO_INCREMENT,
    passenger_id BIGINT NOT NULL,
    driver_id BIGINT NOT NULL,
    trip_id BIGINT NOT NULL,
    PRIMARY KEY (trip_request_id),
    FOREIGN KEY (passenger_id) REFERENCES passenger(passenger_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (driver_id) REFERENCES driver(driver_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (trip_id) REFERENCES trip(trip_id) ON DELETE CASCADE ON UPDATE CASCADE
);