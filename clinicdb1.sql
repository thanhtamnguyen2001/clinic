-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: clinicdb1
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `certificate`
--

DROP TABLE IF EXISTS `certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `certificate` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `conclusion` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `symptom` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `register_id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjrpe8dsf41ow8x5k8b8g3cdje` (`register_id`),
  KEY `FKp2ure8wwndmepxyj2ey8r3lb2` (`user_id`),
  CONSTRAINT `FKjrpe8dsf41ow8x5k8b8g3cdje` FOREIGN KEY (`register_id`) REFERENCES `register` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKp2ure8wwndmepxyj2ey8r3lb2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificate`
--

LOCK TABLES `certificate` WRITE;
/*!40000 ALTER TABLE `certificate` DISABLE KEYS */;
INSERT INTO `certificate` VALUES (78,'aa','2022-10-10 15:47:12','',3,2),(79,'f','2022-10-10 15:47:18','',3,2),(84,'cam cum','2022-10-12 09:54:09','sot',13,2),(85,'sot','2022-10-12 09:54:28','cam cum',13,2),(86,'soooot','2022-10-12 09:54:47','cam cum',13,2),(87,'cảm cúm','2022-10-12 16:45:29','sốt',14,2),(88,'biến thái','2022-10-12 17:09:00','mê gái',14,2),(89,'thiêu mau','2022-10-16 07:35:51','Chống mặt',1,2),(94,'sau rang',NULL,'dau rang',1,2),(95,'Thiếu máu','2022-10-17 07:33:55','Chống mặt',15,2),(96,'Tuột huyết áp','2022-10-17 07:34:32','Hoa mắt',15,2);
/*!40000 ALTER TABLE `certificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicine` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `actived` bit(1) DEFAULT NULL,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `note` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `quantity_per_unit` int DEFAULT NULL,
  `unit_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbwdi89abgfx5fks1p4gh7uxsh` (`unit_id`),
  CONSTRAINT `FKbwdi89abgfx5fks1p4gh7uxsh` FOREIGN KEY (`unit_id`) REFERENCES `unit` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
INSERT INTO `medicine` VALUES (1,NULL,'panadol','uong',10000,10000,30,1),(2,NULL,'paracetemol','an',40000,100000,20,2);
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precription`
--

DROP TABLE IF EXISTS `precription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `precription` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `certificate_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKju5d24groay1m9ovnnx2ov24q` (`certificate_id`),
  CONSTRAINT `FKju5d24groay1m9ovnnx2ov24q` FOREIGN KEY (`certificate_id`) REFERENCES `certificate` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precription`
--

LOCK TABLES `precription` WRITE;
/*!40000 ALTER TABLE `precription` DISABLE KEYS */;
INSERT INTO `precription` VALUES (23,'2022-10-12 16:45:47',87),(24,'2022-10-12 16:45:59',87),(25,'2022-10-12 17:09:07',88),(29,'2022-10-16 07:43:08',89),(42,'2022-10-17 07:34:01',95),(43,'2022-10-17 07:34:09',95),(44,'2022-10-17 07:34:35',96);
/*!40000 ALTER TABLE `precription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription_medicine`
--

DROP TABLE IF EXISTS `prescription_medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescription_medicine` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `medicine_id` bigint DEFAULT NULL,
  `prescription_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK628hc519rik10tuyw4xnxau06` (`medicine_id`),
  KEY `FKav9545tc0gt6ueh8sgwe5p6gw` (`prescription_id`),
  CONSTRAINT `FK628hc519rik10tuyw4xnxau06` FOREIGN KEY (`medicine_id`) REFERENCES `medicine` (`id`),
  CONSTRAINT `FKav9545tc0gt6ueh8sgwe5p6gw` FOREIGN KEY (`prescription_id`) REFERENCES `precription` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription_medicine`
--

LOCK TABLES `prescription_medicine` WRITE;
/*!40000 ALTER TABLE `prescription_medicine` DISABLE KEYS */;
INSERT INTO `prescription_medicine` VALUES (23,1,1,23),(24,1,2,23),(25,1,2,24),(26,1,1,25),(27,1,2,25),(37,1,2,29),(38,1,1,29),(41,1,1,42),(42,1,2,42),(43,1,1,43),(44,1,1,44),(45,1,2,44),(46,1,2,43);
/*!40000 ALTER TABLE `prescription_medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `register`
--

DROP TABLE IF EXISTS `register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `register` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `examination_time` date DEFAULT NULL,
  `health_issues` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `verified` bit(1) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlge7j322nfrl4e848e04511y3` (`user_id`),
  CONSTRAINT `FKlge7j322nfrl4e848e04511y3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `register`
--

LOCK TABLES `register` WRITE;
/*!40000 ALTER TABLE `register` DISABLE KEYS */;
INSERT INTO `register` VALUES (1,'2022-10-08 07:55:03','2022-12-28','Chống mặt','nancy','09294933',_binary '',NULL),(3,'2022-10-08 11:03:38','2022-12-28','Chống mặt','nhiet ba','09294933',_binary '\0',NULL),(4,'2022-10-08 11:21:11','2022-10-20','cam','tamleoleo','90514-012',_binary '\0',NULL),(5,'2022-10-12 09:29:33','2022-12-28','Chống mặt','nurse','09294933',_binary '\0',NULL),(6,'2022-10-12 09:30:33','2022-12-28','Chống mặt','nurse','09294933',_binary '\0',3),(7,'2022-10-12 09:30:59','2022-12-28','Chống mặt','nurse','09294933',_binary '\0',3),(8,'2022-10-12 09:30:59','2022-12-28','Chống mặt','nurse','09294933',_binary '\0',3),(9,'2022-10-12 09:39:36','2022-11-12','aloo134','heyman','2525',_binary '\0',NULL),(10,'2022-10-12 09:43:23','2022-11-04','ff','fsd','aa',_binary '\0',3),(11,'2022-10-12 09:43:29','2022-11-04','ff','fsd','aa',_binary '\0',3),(12,'2022-10-12 09:43:48','2022-11-04','ff','fsd','aa',_binary '\0',3),(13,'2022-10-12 09:53:48','2022-10-14','cam cum','im nurse','2180493',_binary '\0',3),(14,'2022-10-12 16:32:27','2022-10-18','sốt','i\'m patient','05812355123',_binary '\0',1),(15,'2022-10-17 07:33:28','2022-10-22','chống mặt','patient ola','851702510-',_binary '\0',1);
/*!40000 ALTER TABLE `register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_PATIENT'),(2,'ROLE_DOCTOR'),(3,'ROLE_NURSE');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialty`
--

DROP TABLE IF EXISTS `specialty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specialty` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialty`
--

LOCK TABLES `specialty` WRITE;
/*!40000 ALTER TABLE `specialty` DISABLE KEYS */;
INSERT INTO `specialty` VALUES (1,'https://res.cloudinary.com/tamdev/image/upload/v1665721770/clinic/icons/icon1_jtta4c.png','Khoa tim mạch'),(2,'https://res.cloudinary.com/tamdev/image/upload/v1665721770/clinic/icons/icon2-500x450_wwppfr.png','Khoa răng'),(3,'https://res.cloudinary.com/tamdev/image/upload/v1665721770/clinic/icons/icon3_x0zbjg.png','Khoa nội'),(4,'https://res.cloudinary.com/tamdev/image/upload/v1665721770/clinic/icons/icon5-500x450_eutooz.png','Khoa mắt'),(5,'https://res.cloudinary.com/tamdev/image/upload/v1665721771/clinic/icons/icon4_rf8hfu.png','Khoa thần kinh');
/*!40000 ALTER TABLE `specialty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (1,'Hộp'),(2,'Vĩ'),(3,'Viên');
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `actived` bit(1) DEFAULT NULL,
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `first_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sex` varchar(3) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,_binary '','https://res.cloudinary.com/tamdev/image/upload/v1665137711/acwikfsvxhal7ecvokz6.jpg',NULL,NULL,'','','$2a$10$7MM.puBZJ6n2tz7TXmg5nuB/poZl1bdUPaxlmCvaREPtCgdzFvKK.','0851249052','nam','patient'),(2,_binary '','https://res.cloudinary.com/tamdev/image/upload/v1665137711/acwikfsvxhal7ecvokz6.jpg',NULL,NULL,'Tâm','Nguyễn Thành','$2a$10$AFMtIy0omQmMPq0ZZp0QpO1LafRGO6EfO4QliZJJkkKfIlBmIChWC','0851249052','nam','doctor'),(3,_binary '','https://res.cloudinary.com/tamdev/image/upload/v1665137711/acwikfsvxhal7ecvokz6.jpg',NULL,NULL,'','','$2a$10$FqXl5BBN6hs0qEP58.uwg.BbBmToTj9Lo9bzb/5cE2LoLOzW0toXm','0851249052','nam','nurse'),(4,_binary '','',NULL,NULL,'','','$2a$10$B5A8.55KQoou3YVxW/vG8e5ev8YZ23/TkixGs6l5DituLlodh3RAi','0851249052','nam','tam'),(5,_binary '',NULL,NULL,NULL,NULL,NULL,'$2a$10$OfrIXtohd4gyKq6LqpV28u0ZH.VE57X6.OrHXvEkGHWIk0r0234jG','0851249052',NULL,'tam11'),(6,_binary '',NULL,NULL,NULL,NULL,NULL,'$2a$10$LnFesD1eef2w88P7e4wMQOMdrY/tKIdNGWL17jmHBUlgxqCe0eMbC','0851249052',NULL,'ass'),(7,_binary '',NULL,NULL,NULL,NULL,NULL,'$2a$10$6giGtORNWyEEPSOq6ofv6OTQn7UNBfiYYmLiRANsapWynPyjDhk0O','0851249052',NULL,'ass1'),(8,_binary '',NULL,NULL,NULL,NULL,NULL,'$2a$10$xV9oNW7g9iC43A7/hiblW.tu39/w5gvlszjOdeNQK93vhpm0jKPMa','0851249052',NULL,'ass1aa'),(9,_binary '',NULL,NULL,NULL,NULL,NULL,'$2a$10$Ykdm3pyi8EhxEQu9Q0sSoed44u7Q3Iw7ho0XuYu6xXvO1jn0xD3za','0898244563',NULL,'aasaa133'),(10,_binary '',NULL,NULL,NULL,NULL,NULL,'$2a$10$lYRELa9/deuusU06k9/WgufT5pzotHcqW07TmuPwAG/Fn7fe4oI0q','0851249052',NULL,'fasfsdfas'),(11,_binary '',NULL,NULL,NULL,NULL,NULL,'$2a$10$yS.xdd.Ji.bAB3lLtKY1..VoPLy.SFRwwbHFLsygMUtvnP/2KNFSW','0851249052',NULL,'fasfsdfa11as'),(12,_binary '',NULL,NULL,NULL,NULL,NULL,'$2a$10$B7MUkEAFiY7spA0QfX76ue92QIO57wrrjEFjS.9YDgL19qZ08k/X.','0851249052',NULL,'bb'),(13,_binary '',NULL,NULL,NULL,NULL,NULL,'$2a$10$uZ1Y5aiAOBNdznbb75oDxeRfzEYqSNurZjMCWp7kw39UR/wN5.Bny','0898244563',NULL,'ccc'),(14,_binary '',NULL,NULL,NULL,NULL,NULL,'$2a$10$onyPV6m1uQmwSZLwaaXtxumMqGBMEwhDrsD7sgf0wxWGHniI2bKO.','0898244563',NULL,'ccc11'),(15,_binary '','https://res.cloudinary.com/tamdev/image/upload/v1665137711/acwikfsvxhal7ecvokz6.jpg',NULL,NULL,NULL,NULL,'$2a$10$xkk/QDosgthJ2KVE2Pyee.UbFLCUHIj8GPv9DG/qla3iMAcXUAeui','1113',NULL,'doctoraaz111'),(16,_binary '','https://res.cloudinary.com/tamdev/image/upload/v1665138290/hwuhpmjl3q50h3pznszl.jpg',NULL,NULL,NULL,NULL,'$2a$10$HXRWA/kgWH21Cpheq2Y6vukNooE8ALT6CuaTbp3agdjKTFHVmWu66','1113',NULL,'one'),(17,_binary '','fjsdalf;fsdafsvvvvvv',NULL,NULL,'ba','nhiet','$2a$10$aT.jr/X8ApzcZ.1jcT3NmOVM9C7PnUBQ/LVxeRbb5RuANV4gsbS8K','0851249052','nu','tamleoleo1'),(18,_binary '','fjsdalf;fsdafsvvvvvv',NULL,NULL,'ba','nhiet','$2a$10$qRXT0zbN7A9mA0gOCDOYmuTg4jbfidLRVnuel7pQDepZdf04r7qgO','0851249052','nu','tamleoleo12');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` bigint NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(2,2),(3,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-17 11:53:18
