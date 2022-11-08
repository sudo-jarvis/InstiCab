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
    coupon_discount INT NOT NULL,
    coupon_validity DATE NOT NULL,
    max_discount INT NOT NULL,
    passenger_id BIGINT NOT NULL,
    PRIMARY KEY (coupon_id),
    FOREIGN KEY (passenger_id) REFERENCES passenger(passenger_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS trip(
    trip_id BIGINT NOT NULL AUTO_INCREMENT,
    start_date date,
    start_time time,
    end_date date,
    end_time time,
    status INT NOT NULL,
    start_latitude FLOAT NOT NULL,
    start_longitude FLOAT NOT NULL,
    end_latitude FLOAT NOT NULL,
    end_longitude FLOAT NOT NULL,
    driver_id BIGINT,
    passenger_id BIGINT NOT NULL,
    feedback VARCHAR(255),
    PRIMARY KEY (trip_id),
    FOREIGN KEY (driver_id) REFERENCES driver(driver_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (passenger_id) REFERENCES passenger(passenger_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS earning_history(
    trip_id BIGINT NOT NULL AUTO_INCREMENT,
    cost FLOAT NOT NULL,
    distance_travelled FLOAT NOT NULL,
    driver_id BIGINT NOT NULL,
    PRIMARY KEY(trip_id),
    FOREIGN KEY (driver_id) REFERENCES driver(driver_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (trip_id) REFERENCES trip(trip_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS location(
    location_id BIGINT NOT NULL AUTO_INCREMENT,
    latitude FLOAT NOT NULL,
    longitude FLOAT NOT NULL,
    PRIMARY KEY (location_id)
);

CREATE TABLE IF NOT EXISTS favourite_location(
    location_id BIGINT NOT NULL AUTO_INCREMENT,
    label VARCHAR(255) NOT NULL,
    passenger_id BIGINT NOT NULL,
    PRIMARY KEY (location_id,passenger_id),
    FOREIGN KEY (passenger_id) REFERENCES passenger(passenger_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (location_id) REFERENCES location(location_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS registration_request(
    driver_id BIGINT NOT NULL AUTO_INCREMENT,
    time_applied time NOT NULL,
    date_applied date NOT NULL,
    status INT NOT NULL,
    time_accepted time DEFAULT NULL,
    date_accepted date DEFAULT NULL,
    PRIMARY KEY (driver_id),
    FOREIGN KEY (driver_id) REFERENCES driver(driver_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS scheduled_trip(
    scheduled_trip_id BIGINT NOT NULL AUTO_INCREMENT,
    trip_time time NOT NULL,
    trip_id BIGINT NOT NULL,
    PRIMARY KEY (scheduled_trip_id),
    FOREIGN KEY (trip_id) REFERENCES trip(trip_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS vehicle(
    vehicle_id BIGINT NOT NULL AUTO_INCREMENT,
    vehicle_name VARCHAR(255) NOT NULL,
    registration_number VARCHAR(255) NOT NULL UNIQUE,
    insurance_number VARCHAR(255) NOT NULL UNIQUE,
    registration_state VARCHAR(255) NOT NULL,
    driver_id BIGINT NOT NULL,
    PRIMARY KEY (vehicle_id),
    FOREIGN KEY (driver_id) REFERENCES driver(driver_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS emergency_service(
    request_id BIGINT NOT NULL AUTO_INCREMENT,
    request_time time,
    type VARCHAR(255) NOT NULL,
    username VARCHAR(255),
    PRIMARY KEY (request_id),
    FOREIGN KEY (username) REFERENCES user(username) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS transaction(
    transaction_id BIGINT NOT NULL AUTO_INCREMENT,
    trip_id BIGINT NOT NULL,
    time_transaction time,
    date_transaction date,
    amount INT NOT NULL,
    status INT NOT NULL,
    username VARCHAR(255) NOT NULL,
    PRIMARY KEY (transaction_id),
    FOREIGN KEY (username) REFERENCES user(username) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (trip_id) REFERENCES trip(trip_id) ON DELETE CASCADE ON UPDATE CASCADE
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

CREATE TABLE IF NOT EXISTS transaction_dispute(
    transaction_id BIGINT NOT NULL AUTO_INCREMENT,
    status INT NOT NULL DEFAULT 0,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (transaction_id),
    FOREIGN KEY (transaction_id) REFERENCES transaction(transaction_id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT IGNORE INTO location(location_id,latitude,longitude) VALUES(1,25.260220092673407,82.99086984049433);

INSERT IGNORE INTO location(location_id,latitude,longitude) VALUES(2,25.26203299523337,82.99381735939455);

INSERT IGNORE INTO location(location_id,latitude,longitude) VALUES(3,25.258980826409978,82.99280814420261);

INSERT IGNORE INTO location(location_id,latitude,longitude) VALUES(4,25.266216084701153,82.98792913561284);

INSERT IGNORE INTO location(location_id,latitude,longitude) VALUES(5,25.261953268672897,82.9895353508379);
