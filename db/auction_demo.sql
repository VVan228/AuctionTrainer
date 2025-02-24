-- MySQL dump 10.13  Distrib 8.0.29, for macos12 (arm64)
--
-- Host: localhost    Database: auction_demo
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Current Database: `auction_demo`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `auction_demo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `auction_demo`;

--
-- Table structure for table `interval`
--

DROP TABLE IF EXISTS `interval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interval` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `autostart` bit(1) DEFAULT NULL,
  `duration` bigint DEFAULT NULL,
  `entity_id` bigint DEFAULT NULL,
  `status` smallint DEFAULT NULL,
  `type` smallint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interval`
--

LOCK TABLES `interval` WRITE;
/*!40000 ALTER TABLE `interval` DISABLE KEYS */;
/*!40000 ALTER TABLE `interval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interval_intervals`
--

DROP TABLE IF EXISTS `interval_intervals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interval_intervals` (
  `interval_id` bigint NOT NULL,
  `intervals_id` bigint NOT NULL,
  UNIQUE KEY `UK_k4jffxgt7jlerkfmqffa2lq3i` (`intervals_id`),
  KEY `FKkt3fo1xh8a6ldpcme17526246` (`interval_id`),
  CONSTRAINT `FKkt3fo1xh8a6ldpcme17526246` FOREIGN KEY (`interval_id`) REFERENCES `interval` (`id`),
  CONSTRAINT `FKnybcc75ypi2bdjasiqlk6oq3o` FOREIGN KEY (`intervals_id`) REFERENCES `interval` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interval_intervals`
--

LOCK TABLES `interval_intervals` WRITE;
/*!40000 ALTER TABLE `interval_intervals` DISABLE KEYS */;
/*!40000 ALTER TABLE `interval_intervals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interval_point`
--

DROP TABLE IF EXISTS `interval_point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interval_point` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `queue_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interval_point`
--

LOCK TABLES `interval_point` WRITE;
/*!40000 ALTER TABLE `interval_point` DISABLE KEYS */;
/*!40000 ALTER TABLE `interval_point` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interval_point_interval_end_ids`
--

DROP TABLE IF EXISTS `interval_point_interval_end_ids`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interval_point_interval_end_ids` (
  `interval_point_id` bigint NOT NULL,
  `interval_end_ids` bigint DEFAULT NULL,
  KEY `FKibx29xqm1bhsrx6edhy6su1lx` (`interval_point_id`),
  CONSTRAINT `FKibx29xqm1bhsrx6edhy6su1lx` FOREIGN KEY (`interval_point_id`) REFERENCES `interval_point` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interval_point_interval_end_ids`
--

LOCK TABLES `interval_point_interval_end_ids` WRITE;
/*!40000 ALTER TABLE `interval_point_interval_end_ids` DISABLE KEYS */;
/*!40000 ALTER TABLE `interval_point_interval_end_ids` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interval_point_interval_start_ids`
--

DROP TABLE IF EXISTS `interval_point_interval_start_ids`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interval_point_interval_start_ids` (
  `interval_point_id` bigint NOT NULL,
  `interval_start_ids` bigint DEFAULT NULL,
  KEY `FKtj1xg8pkc53acb44et7r94di6` (`interval_point_id`),
  CONSTRAINT `FKtj1xg8pkc53acb44et7r94di6` FOREIGN KEY (`interval_point_id`) REFERENCES `interval_point` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interval_point_interval_start_ids`
--

LOCK TABLES `interval_point_interval_start_ids` WRITE;
/*!40000 ALTER TABLE `interval_point_interval_start_ids` DISABLE KEYS */;
/*!40000 ALTER TABLE `interval_point_interval_start_ids` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interval_queue`
--

DROP TABLE IF EXISTS `interval_queue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interval_queue` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `current_index` bigint DEFAULT NULL,
  `room_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interval_queue`
--

LOCK TABLES `interval_queue` WRITE;
/*!40000 ALTER TABLE `interval_queue` DISABLE KEYS */;
/*!40000 ALTER TABLE `interval_queue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interval_queue_interval_points`
--

DROP TABLE IF EXISTS `interval_queue_interval_points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interval_queue_interval_points` (
  `interval_queue_id` bigint NOT NULL,
  `interval_points_id` bigint NOT NULL,
  UNIQUE KEY `UK_5byuo89gk3mvg9va01u5ug65e` (`interval_points_id`),
  KEY `FKc1b9060gvnhgvrik9x7b9qhrt` (`interval_queue_id`),
  CONSTRAINT `FKc1b9060gvnhgvrik9x7b9qhrt` FOREIGN KEY (`interval_queue_id`) REFERENCES `interval_queue` (`id`),
  CONSTRAINT `FKcdni8ufaonam3oc4leix6lm02` FOREIGN KEY (`interval_points_id`) REFERENCES `interval_point` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interval_queue_interval_points`
--

LOCK TABLES `interval_queue_interval_points` WRITE;
/*!40000 ALTER TABLE `interval_queue_interval_points` DISABLE KEYS */;
/*!40000 ALTER TABLE `interval_queue_interval_points` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lot`
--

DROP TABLE IF EXISTS `lot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lot` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end_on_all_answered` bit(1) DEFAULT NULL,
  `limit_sum` int DEFAULT NULL,
  `min_bet_step` int DEFAULT NULL,
  `start_sum` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lot`
--

LOCK TABLES `lot` WRITE;
/*!40000 ALTER TABLE `lot` DISABLE KEYS */;
/*!40000 ALTER TABLE `lot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `payload` json DEFAULT NULL,
  `type` smallint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `creator_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtl14kvxhp8bam5wu4f1oqn3gs` (`creator_id`),
  CONSTRAINT `FKtl14kvxhp8bam5wu4f1oqn3gs` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_intervals`
--

DROP TABLE IF EXISTS `room_intervals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_intervals` (
  `room_id` bigint NOT NULL,
  `intervals_id` bigint NOT NULL,
  UNIQUE KEY `UK_6m9c9g02yo41f9m3mn1s1q862` (`intervals_id`),
  KEY `FK3l929apwwmprcbr1vf8l5ritp` (`room_id`),
  CONSTRAINT `FK3l929apwwmprcbr1vf8l5ritp` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`),
  CONSTRAINT `FKstnuye035lrx5o1hlg45tp87k` FOREIGN KEY (`intervals_id`) REFERENCES `interval` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_intervals`
--

LOCK TABLES `room_intervals` WRITE;
/*!40000 ALTER TABLE `room_intervals` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_intervals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `round`
--

DROP TABLE IF EXISTS `round`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `round` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ascending` bit(1) DEFAULT NULL,
  `room_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `round`
--

LOCK TABLES `round` WRITE;
/*!40000 ALTER TABLE `round` DISABLE KEYS */;
/*!40000 ALTER TABLE `round` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `captain_id` bigint DEFAULT NULL,
  `creator_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmy5ix43udwyht8c2diwt4cyua` (`captain_id`),
  KEY `FK16hyjup1jsh1sr9x3q1pm3rhl` (`creator_id`),
  CONSTRAINT `FK16hyjup1jsh1sr9x3q1pm3rhl` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKmy5ix43udwyht8c2diwt4cyua` FOREIGN KEY (`captain_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `current_refresh_token_hash` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` smallint DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-11 18:55:49
