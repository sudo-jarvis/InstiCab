CREATE TABLE IF NOT EXISTS coupon(
    coupon_id INT,
    coupon_discount FLOAT,
    couponvalidity DATE,
    maxdiscount INT
);

CREATE TABLE IF NOT EXISTS driver(
    driverid INT,
    licensenumber VARCHAR(255),
    aadharnumber VARCHAR(255),
    accountno VARCHAR(255),
    accountname VARCHAR(255),
    ifsccode VARCHAR(255),
    bankname VARCHAR(255),
    userid INT
);

CREATE TABLE IF NOT EXISTS earningshistory(
    earningid INT,
    cost FLOAT,
    distancetravelled FLOAT
);

CREATE TABLE IF NOT EXISTS favouritelocation(
    locationid INT,
    latitudelocation FLOAT,
    longitudelocation FLOAT
);

CREATE TABLE IF NOT EXISTS passenger(passengerid INT);

CREATE TABLE IF NOT EXISTS registrationrequest(
    requestid INT,
    timeapplied time,
    dateapplied date,
    status INT,
    timeaccepted time,
    dateaccepted date
);

CREATE TABLE IF NOT EXISTS role(id INT, name VARCHAR(255));

CREATE TABLE IF NOT EXISTS scheduledtrip(
    scheduledtripid INT,
    triptime time,
    startlatitude FLOAT,
    startlongitude FLOAT,
    endlatitude FLOAT,
    endlongitude FLOAT
);

CREATE TABLE IF NOT EXISTS service(
    serviceid INT,
    name VARCHAR(255),
    type VARCHAR(255),
    latitudelocation FLOAT,
    longitudelocation FLOAT,
    contactno VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS transaction(
    transactionid INT,
    timetransaction time,
    datetransaction date,
    amount FLOAT,
    status INT
);

CREATE TABLE IF NOT EXISTS trip(
    tripid INT,
    startdate date,
    starttime time,
    enddate date,
    endtime time,
    status INT,
    startlatitude FLOAT,
    startlongitude FLOAT,
    endlatitude FLOAT,
    endlongitude FLOAT,
    driverid INT
);

CREATE TABLE IF NOT EXISTS triprequest(triprequestid INT, tripid INT);

CREATE TABLE IF NOT EXISTS user(
    username VARCHAR(255),
    firstname VARCHAR(255),
    middlename VARCHAR(255),
    lastname VARCHAR(255),
    email VARCHAR(255),
    phoneno VARCHAR(255),
    password VARCHAR(255),
    datecreated date,
    lastlogindate date,
    lastlogintime time,
    isdriver INT,
    driverid INT
);

CREATE TABLE IF NOT EXISTS vehicle(
    vehicleid INT,
    vehicletype VARCHAR(255),
    registrationnumber VARCHAR(255),
    insurancenumber VARCHAR(255),
    registrationstate VARCHAR(255)
);