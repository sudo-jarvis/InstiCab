DROP DATABASE IF EXISTS InstiCab;
CREATE DATABASE InstiCab;
USE InstiCab;


CREATE TABLE IF NOT EXISTS User(
    username VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    middleName VARCHAR(255) DEFAULT NULL,
    lastName VARCHAR(255) DEFAULT NULL,
    email VARCHAR(255) NOT NULL,
    phoneNo VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    dateCreated date NOT NULL,
    lastLoginDate date DEFAULT NULL,
    lastLoginTime time DEFAULT NULL,
    isDriver INT DEFAULT 0 NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS Passenger(
    passengerId BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    PRIMARY KEY (passengerId),
    FOREIGN KEY (username) REFERENCES User(username) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Driver (
    driverId BIGINT NOT NULL AUTO_INCREMENT,
    licenseNumber VARCHAR(255) NOT NULL,
    aadharNumber VARCHAR(255) NOT NULL,
    accountNo VARCHAR(255) NOT NULL,
    accountName VARCHAR(255) NOT NULL,
    ifscCode VARCHAR(255) NOT NULL,
    bankName VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    PRIMARY KEY (driverId),
    FOREIGN KEY (username) REFERENCES User(username) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Coupon (
    couponId BIGINT NOT NULL AUTO_INCREMENT,
    couponDiscount FLOAT NOT NULL,
    couponValidity DATE NOT NULL,
    maxDiscount INT NOT NULL,
    passengerId BIGINT NOT NULL,
    PRIMARY KEY (couponId),
    FOREIGN KEY (passengerId) REFERENCES Passenger(passengerId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS EarningsHistory(
    earningId BIGINT NOT NULL AUTO_INCREMENT,
    cost FLOAT NOT NULL,
    distanceTravelled FLOAT NOT NULL,
    driverId BIGINT NOT NULL,
    PRIMARY KEY(earningId),
    FOREIGN KEY (driverId) REFERENCES Driver(driverId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS FavouriteLocation(
    locationId BIGINT NOT NULL AUTO_INCREMENT,
    latitudeLocation FLOAT NOT NULL,
    longitudeLocation FLOAT NOT NULL,
    passengerId BIGINT NOT NULL,
    PRIMARY KEY (locationId),
    FOREIGN KEY (passengerId) REFERENCES Passenger(passengerId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS RegistrationRequest(
    requestId BIGINT NOT NULL AUTO_INCREMENT,
    timeApplied time NOT NULL,
    dateApplied date NOT NULL,
    status INT NOT NULL,
    timeAccepted time DEFAULT NULL,
    dateAccepted date DEFAULT NULL,
    driverId BIGINT NOT NULL,
    PRIMARY KEY (requestId),
    FOREIGN KEY (driverId) REFERENCES Driver(driverId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Role(
    id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Trip(
    tripId BIGINT NOT NULL AUTO_INCREMENT,
    startDate date NOT NULL,
    startTime time NOT NULL,
    endDate date NOT NULL,
    endTime time NOT NULL,
    status INT NOT NULL,
    startLatitude FLOAT NOT NULL,
    startLongitude FLOAT NOT NULL,
    endLatitude FLOAT NOT NULL,
    endLongitude FLOAT NOT NULL,
    driverId BIGINT NOT NULL,
    passengerId BIGINT NOT NULL,
    PRIMARY KEY (tripId),
    FOREIGN KEY (driverId) REFERENCES Driver(driverId) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (passengerId) REFERENCES Passenger(passengerId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS ScheduledTrip(
    scheduledTripId BIGINT NOT NULL AUTO_INCREMENT,
    tripTime time NOT NULL,
    startLatitude FLOAT NOT NULL,
    startLongitude FLOAT NOT NULL,
    endLatitude FLOAT NOT NULL,
    endLongitude FLOAT NOT NULL,
    driverId BIGINT NOT NULL,
    tripId BIGINT NOT NULL,
    PRIMARY KEY (scheduledTripId),
    FOREIGN KEY (driverId) REFERENCES Driver(driverId) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (tripId) REFERENCES Trip(tripId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Vehicle(
    vehicleId BIGINT NOT NULL,
    vehicleType VARCHAR(255) NOT NULL,
    registrationNumber VARCHAR(255) NOT NULL,
    insuranceNumber VARCHAR(255) NOT NULL,
    registrationState VARCHAR(255) NOT NULL,
    driverId BIGINT NOT NULL,
    PRIMARY KEY (vehicleId),
    FOREIGN KEY (driverId) REFERENCES Driver(driverId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Service(
    serviceId BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    latitudeLocation FLOAT NOT NULL,
    longitudeLocation FLOAT NOT NULL,
    contactNo VARCHAR(255) NOT NULL,
    vehicleId BIGINT,
    PRIMARY KEY (serviceId),
    FOREIGN KEY (vehicleId) REFERENCES Vehicle(vehicleId) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Transaction(
    transactionId BIGINT NOT NULL AUTO_INCREMENT,
    timeTransaction time NOT NULL,
    dateTranscation date NOT NULL,
    amount FLOAT NOT NULL,
    status INT NOT NULL,
    username VARCHAR(255) NOT NULL,
    PRIMARY KEY (transactionId),
    FOREIGN KEY (username) REFERENCES User(username) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS TripRequest(
    tripRequestId INT NOT NULL AUTO_INCREMENT,
    passengerId BIGINT NOT NULL,
    driverId BIGINT NOT NULL,
    tripId BIGINT NOT NULL,
    PRIMARY KEY (tripRequestId),
    FOREIGN KEY (passengerId) REFERENCES Passenger(passengerId) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (driverId) REFERENCES Driver(driverId) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (tripId) REFERENCES Trip(tripId) ON DELETE CASCADE ON UPDATE CASCADE
);