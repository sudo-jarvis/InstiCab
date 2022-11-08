-- MySQL dump 10.13  Distrib 8.0.31, for Linux (x86_64)
--
-- Host: localhost    Database: insticab
-- ------------------------------------------------------
-- Server version	8.0.31-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon` (
  `coupon_id` bigint NOT NULL AUTO_INCREMENT,
  `coupon_discount` int NOT NULL,
  `coupon_validity` date NOT NULL,
  `max_discount` int NOT NULL,
  `passenger_id` bigint NOT NULL,
  PRIMARY KEY (`coupon_id`),
  KEY `passenger_id` (`passenger_id`),
  CONSTRAINT `coupon_ibfk_1` FOREIGN KEY (`passenger_id`) REFERENCES `passenger` (`passenger_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `driver` (
  `driver_id` bigint NOT NULL AUTO_INCREMENT,
  `license_number` varchar(255) NOT NULL,
  `aadhar_number` varchar(255) NOT NULL,
  `account_no` varchar(255) NOT NULL,
  `account_name` varchar(255) NOT NULL,
  `ifsc_code` varchar(255) NOT NULL,
  `bank_name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`driver_id`),
  UNIQUE KEY `license_number` (`license_number`),
  UNIQUE KEY `aadhar_number` (`aadhar_number`),
  UNIQUE KEY `account_no` (`account_no`),
  KEY `username` (`username`),
  CONSTRAINT `driver_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` VALUES (1,'RJ01 12345678901','270565621347','1234567890','Dhruv Singh','PYTM0123456','Paytm','driver');
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `earning_history`
--

DROP TABLE IF EXISTS `earning_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `earning_history` (
  `trip_id` bigint NOT NULL AUTO_INCREMENT,
  `cost` float NOT NULL,
  `distance_travelled` float NOT NULL,
  `driver_id` bigint NOT NULL,
  PRIMARY KEY (`trip_id`),
  KEY `driver_id` (`driver_id`),
  CONSTRAINT `earning_history_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`driver_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `earning_history_ibfk_2` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`trip_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `earning_history`
--

LOCK TABLES `earning_history` WRITE;
/*!40000 ALTER TABLE `earning_history` DISABLE KEYS */;
INSERT INTO `earning_history` VALUES (1,6,0.174416,1);
/*!40000 ALTER TABLE `earning_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emergency_service`
--

DROP TABLE IF EXISTS `emergency_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emergency_service` (
  `request_id` bigint NOT NULL AUTO_INCREMENT,
  `request_time` time DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`request_id`),
  KEY `username` (`username`),
  CONSTRAINT `emergency_service_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emergency_service`
--

LOCK TABLES `emergency_service` WRITE;
/*!40000 ALTER TABLE `emergency_service` DISABLE KEYS */;
INSERT INTO `emergency_service` VALUES (1,'19:23:08','2','user'),(2,'00:00:59','1','user'),(3,'00:20:21','2','driver');
/*!40000 ALTER TABLE `emergency_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favourite_location`
--

DROP TABLE IF EXISTS `favourite_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favourite_location` (
  `location_id` bigint NOT NULL AUTO_INCREMENT,
  `label` varchar(255) NOT NULL,
  `passenger_id` bigint NOT NULL,
  PRIMARY KEY (`location_id`,`passenger_id`),
  KEY `passenger_id` (`passenger_id`),
  CONSTRAINT `favourite_location_ibfk_1` FOREIGN KEY (`passenger_id`) REFERENCES `passenger` (`passenger_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `favourite_location_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favourite_location`
--

LOCK TABLES `favourite_location` WRITE;
/*!40000 ALTER TABLE `favourite_location` DISABLE KEYS */;
INSERT INTO `favourite_location` VALUES (1,'LT-1',1),(1,'LT-1',2),(1,'LT-1',3),(1,'LT-1',4),(1,'LT-1',5),(1,'LT-1',6),(1,'LT-1',7),(2,'LT-2',1),(2,'LT-2',2),(2,'LT-2',3),(2,'LT-2',4),(2,'LT-2',5),(2,'LT-2',6),(2,'LT-2',7),(3,'LT-3',1),(3,'LT-3',2),(3,'LT-3',3),(3,'LT-3',4),(3,'LT-3',5),(3,'LT-3',6),(3,'LT-3',7),(4,'VT',1),(4,'VT',2),(4,'VT',3),(4,'VT',4),(4,'VT',5),(4,'VT',6),(4,'VT',7),(5,'Library',1),(5,'Library',2),(5,'Library',3),(5,'Library',4),(5,'Library',5),(5,'Library',6),(5,'Library',7),(13,'Locj',1),(14,'ad',1),(15,'lab',1);
/*!40000 ALTER TABLE `favourite_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `location_id` bigint NOT NULL AUTO_INCREMENT,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,25.2602,82.9909),(2,25.262,82.9938),(3,25.259,82.9928),(4,25.2662,82.9879),(5,25.262,82.9895),(6,25.2692,82.988),(7,25.2692,82.988),(8,25.2684,82.9867),(9,25.2696,82.9869),(10,25.2651,82.9853),(11,25.268,82.986),(12,25.2692,82.9863),(13,25.2677,82.9874),(14,25.2649,82.9879),(15,25.2671,82.9844);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passenger`
--

DROP TABLE IF EXISTS `passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passenger` (
  `passenger_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`passenger_id`),
  KEY `username` (`username`),
  CONSTRAINT `passenger_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger`
--

LOCK TABLES `passenger` WRITE;
/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
INSERT INTO `passenger` VALUES (2,'admin'),(7,'darshil'),(3,'dhruv'),(5,'romir'),(4,'tarun'),(1,'user'),(6,'vicky');
/*!40000 ALTER TABLE `passenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration_request`
--

DROP TABLE IF EXISTS `registration_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration_request` (
  `driver_id` bigint NOT NULL AUTO_INCREMENT,
  `time_applied` time NOT NULL,
  `date_applied` date NOT NULL,
  `status` int NOT NULL,
  `time_accepted` time DEFAULT NULL,
  `date_accepted` date DEFAULT NULL,
  PRIMARY KEY (`driver_id`),
  CONSTRAINT `registration_request_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`driver_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration_request`
--

LOCK TABLES `registration_request` WRITE;
/*!40000 ALTER TABLE `registration_request` DISABLE KEYS */;
INSERT INTO `registration_request` VALUES (1,'14:07:17','2022-11-08',1,'14:17:59','2022-11-08');
/*!40000 ALTER TABLE `registration_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scheduled_trip`
--

DROP TABLE IF EXISTS `scheduled_trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scheduled_trip` (
  `scheduled_trip_id` bigint NOT NULL AUTO_INCREMENT,
  `trip_time` time NOT NULL,
  `trip_id` bigint NOT NULL,
  PRIMARY KEY (`scheduled_trip_id`),
  KEY `trip_id` (`trip_id`),
  CONSTRAINT `scheduled_trip_ibfk_1` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`trip_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scheduled_trip`
--

LOCK TABLES `scheduled_trip` WRITE;
/*!40000 ALTER TABLE `scheduled_trip` DISABLE KEYS */;
INSERT INTO `scheduled_trip` VALUES (2,'02:23:00',6);
/*!40000 ALTER TABLE `scheduled_trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `request_id` bigint NOT NULL AUTO_INCREMENT,
  `request_time` time DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`request_id`),
  KEY `username` (`username`),
  CONSTRAINT `service_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `transaction_id` bigint NOT NULL AUTO_INCREMENT,
  `trip_id` bigint NOT NULL,
  `time_transaction` time DEFAULT NULL,
  `date_transaction` date DEFAULT NULL,
  `amount` int NOT NULL,
  `status` int NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `username` (`username`),
  KEY `trip_id` (`trip_id`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`trip_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,1,'23:53:55','2022-11-08',6,3,'user');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_dispute`
--

DROP TABLE IF EXISTS `transaction_dispute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_dispute` (
  `transaction_id` bigint NOT NULL AUTO_INCREMENT,
  `status` int NOT NULL DEFAULT '0',
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`transaction_id`),
  CONSTRAINT `transaction_dispute_ibfk_1` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`transaction_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_dispute`
--

LOCK TABLES `transaction_dispute` WRITE;
/*!40000 ALTER TABLE `transaction_dispute` DISABLE KEYS */;
INSERT INTO `transaction_dispute` VALUES (1,1,'eioryf');
/*!40000 ALTER TABLE `transaction_dispute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip`
--

DROP TABLE IF EXISTS `trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip` (
  `trip_id` bigint NOT NULL AUTO_INCREMENT,
  `start_date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `status` int NOT NULL,
  `start_latitude` float NOT NULL,
  `start_longitude` float NOT NULL,
  `end_latitude` float NOT NULL,
  `end_longitude` float NOT NULL,
  `driver_id` bigint DEFAULT NULL,
  `passenger_id` bigint NOT NULL,
  `feedback` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`trip_id`),
  KEY `driver_id` (`driver_id`),
  KEY `passenger_id` (`passenger_id`),
  CONSTRAINT `trip_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`driver_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `trip_ibfk_2` FOREIGN KEY (`passenger_id`) REFERENCES `passenger` (`passenger_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip`
--

LOCK TABLES `trip` WRITE;
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
INSERT INTO `trip` VALUES (1,'2022-11-08','18:23:38',NULL,NULL,4,25.2662,82.9879,25.2677,82.9874,1,1,'234'),(5,NULL,NULL,NULL,NULL,2,25.2626,82.9892,25.2649,82.9879,NULL,1,NULL),(6,NULL,NULL,NULL,NULL,5,0,0,25.2671,82.9844,NULL,1,NULL);
/*!40000 ALTER TABLE `trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip_request`
--

DROP TABLE IF EXISTS `trip_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip_request` (
  `trip_request_id` int NOT NULL AUTO_INCREMENT,
  `passenger_id` bigint NOT NULL,
  `driver_id` bigint NOT NULL,
  `trip_id` bigint NOT NULL,
  PRIMARY KEY (`trip_request_id`),
  KEY `passenger_id` (`passenger_id`),
  KEY `driver_id` (`driver_id`),
  KEY `trip_id` (`trip_id`),
  CONSTRAINT `trip_request_ibfk_1` FOREIGN KEY (`passenger_id`) REFERENCES `passenger` (`passenger_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `trip_request_ibfk_2` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`driver_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `trip_request_ibfk_3` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`trip_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip_request`
--

LOCK TABLES `trip_request` WRITE;
/*!40000 ALTER TABLE `trip_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `trip_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `phone_no` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `date_created` date NOT NULL,
  `last_login_date` date DEFAULT NULL,
  `last_login_time` time DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone_no` (`phone_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','admin','admin','admin','admin@admin.com','9718212375','$2a$10$30dcVJoSbVWqwV/gjcTQD.rvwz7SS0yxL.g1Lv1eyajDAt.HPu0ze','2022-11-08','2022-11-08','23:33:33','ROLE_ADMIN'),('darshil','Darshil','Kumar','Agrawal','darshil@gmail.com','9898989898','$2a$10$h8CHw1naEONsd.1FLtzMr.goQTKSzhWNirnCXXEd7rEIIikoIPa7u','2022-11-08','2022-11-08','22:19:01','ROLE_PASSENGER'),('dhruv','Dhruv','Pratap','Singh','dhruv1778@gmail.com','9718212373','$2a$10$49KPqth.hyvKPilejW4y4u3VZjPi2A19o1EJxmuYYXK/Muh0lm4wS','2022-11-08','2022-11-08','19:54:31','ROLE_PASSENGER'),('driver','driver','driver','driver','driver@driver.com','9718212374','$2a$10$lptJtXIUPbEhwwfPu3HZUe0wJam9dkLcdUfnvcwxs1nMCcyfQlMv.','2022-11-08','2022-11-09','00:24:33','ROLE_DRIVER'),('romir','Romir','','Mehta','romir@gmail.com','1919191919','$2a$10$kwYiR1Hkna5XZAqkr1XKgeERfae7YW.yFdHYsRGKNIUodYiDZRXem','2022-11-08','2022-11-08','19:57:10','ROLE_PASSENGER'),('tarun','Tarun','Kumar','Arora','tarun@gmail.com','6262828729','$2a$10$TLaKqWM3Jd2CHEvzaMfqPem060FlWRpn4MDXS.36QdSGRbYcNkkNq','2022-11-08','2022-11-08','19:55:01','ROLE_PASSENGER'),('user','user','user','user','user@user.com','9999999999','$2a$10$pTf1FTx.NBmGchnb5muRge9lbA7GUeSpXuCmq3XTYPE/lq8YIUKD.','2022-11-08','2022-11-09','00:29:04','ROLE_PASSENGER'),('vicky','Vicky','Kumar','Nayak','vicky@gmail.com','9090909090','$2a$10$6vy66skZXe/U060YcqGMoOzK54916Ynz2tLFzWmmdVUfK76Yiwebq','2022-11-08','2022-11-08','19:59:00','ROLE_PASSENGER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle` (
  `vehicle_id` bigint NOT NULL AUTO_INCREMENT,
  `vehicle_name` varchar(255) NOT NULL,
  `registration_number` varchar(255) NOT NULL,
  `insurance_number` varchar(255) NOT NULL,
  `registration_state` varchar(255) NOT NULL,
  `driver_id` bigint NOT NULL,
  PRIMARY KEY (`vehicle_id`),
  UNIQUE KEY `registration_number` (`registration_number`),
  UNIQUE KEY `insurance_number` (`insurance_number`),
  KEY `driver_id` (`driver_id`),
  CONSTRAINT `vehicle_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`driver_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,'Dzire','HR51AR6625','123','Haryana',1);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-09  0:30:10
