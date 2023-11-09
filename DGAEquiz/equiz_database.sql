-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: dga_data
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `campaign`
--

DROP TABLE IF EXISTS `campaign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campaign` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` text COLLATE utf8mb4_general_ci,
  `description` text COLLATE utf8mb4_general_ci,
  `lesson_data` text COLLATE utf8mb4_general_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campaign`
--

LOCK TABLES `campaign` WRITE;
/*!40000 ALTER TABLE `campaign` DISABLE KEYS */;
INSERT INTO `campaign` VALUES (1,'Phần 1: Sưu tầm tranh','Bài học này chứa rất nhiều thông tin về tranh vẽ','{\"id\":1,\"image_questions_id\":[1,2],\"fill_questions_id\":[1,2],\"listening_questions_id\":[1,2],\"translate_questions_id\":[1,2]}'),(2,'Phần 2: Thế giới động vật','Bài học này sẽ cho các bé tìm hiểu về thế giới động vật','{\"id\":1,\"image_questions_id\":[1,2],\"fill_questions_id\":[1,2],\"listening_questions_id\":[1,2],\"translate_questions_id\":[1,2]}'),(3,'Phần 3: Chiến tranh trung cổ','Trong khoá này các bé sẽ học về các sự kiện trung cổ','{\"id\":1,\"image_questions_id\":[1,2],\"fill_questions_id\":[1,2],\"listening_questions_id\":[1,2],\"translate_questions_id\":[1,2]}'),(4,'Phần 4: Cuộc chiến đông âu','Hãy tự tìm hiểu qua bài học này nhé!','{\"id\":1,\"image_questions_id\":[1,2],\"fill_questions_id\":[1,2],\"listening_questions_id\":[1,2],\"translate_questions_id\":[1,2]}');
/*!40000 ALTER TABLE `campaign` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fill_question`
--

DROP TABLE IF EXISTS `fill_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fill_question` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `question` text COLLATE utf8mb4_general_ci NOT NULL,
  `context` text COLLATE utf8mb4_general_ci,
  `option1` text COLLATE utf8mb4_general_ci,
  `option2` text COLLATE utf8mb4_general_ci,
  `option3` text COLLATE utf8mb4_general_ci,
  `option4` text COLLATE utf8mb4_general_ci,
  `correct_answer` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fill_question`
--

LOCK TABLES `fill_question` WRITE;
/*!40000 ALTER TABLE `fill_question` DISABLE KEYS */;
INSERT INTO `fill_question` VALUES (1,'Chọn đáp án đúng:','The first time that an (1) \r\n of air rage was recorded was in the 1940’s, but the passenger was never actually charged for an offence because there were no clear rules in place to specify where to prosecute','predicted','incident','passenger','hoped',2),(2,'Chọn đáp án đúng:','It was later (2) \r\n that it would be the country where the plane is registered. ','passengers','established','occurring','hoped',3);
/*!40000 ALTER TABLE `fill_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image_question`
--

DROP TABLE IF EXISTS `image_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image_question` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `question` text COLLATE utf8mb4_general_ci NOT NULL,
  `image_source` text COLLATE utf8mb4_general_ci,
  `option1` text COLLATE utf8mb4_general_ci,
  `option2` text COLLATE utf8mb4_general_ci,
  `option3` text COLLATE utf8mb4_general_ci,
  `option4` text COLLATE utf8mb4_general_ci,
  `correct_answer` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_question`
--

LOCK TABLES `image_question` WRITE;
/*!40000 ALTER TABLE `image_question` DISABLE KEYS */;
INSERT INTO `image_question` VALUES (1,'Hãy chọn đáp án đúng','https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/Dodo_1.JPG/640px-Dodo_1.JPG','Con chim dodo','Con vẹt','Con khủng long','Con chó',1),(2,'Đây là đội tuyển nào','https://liquipedia.net/commons/images/thumb/7/7c/GAM_Worlds_2022.jpg/562px-GAM_Worlds_2022.jpg','Seed 1 coincard','Ông trùm VCS','TeamWhale','GAM Esport',4);
/*!40000 ALTER TABLE `image_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `information`
--

DROP TABLE IF EXISTS `information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `information` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `github` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `link_ava` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `level` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `confirmCode` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `information`
--

LOCK TABLES `information` WRITE;
/*!40000 ALTER TABLE `information` DISABLE KEYS */;
INSERT INTO `information` VALUES (1,'Tran Duy Tuan Anh','22028228@gmail.com','2004-11-11','09009877788','https://github.com/tranduytuana20045',NULL,'B1','tranduytuana123','\n11112004',NULL),(2,'Nguyen Tuan Duong','tuanduong152004@gmail.com','2004-05-01','000000010','https://github.com/koezyrs',NULL,'B1','koezyrs123','2324324',NULL),(3,'Vu Ninh Giang','giang@gmail.com','2004-06-13','000000020','https://github.com/Zeus',NULL,'C1','giangkakaka','69659654',NULL),(4,'Chu Quang Can','ccc@gmail.com','2004-01-23','403594039543','ccc.com',NULL,NULL,'chuquangcccc123','43546546',NULL),(5,'','','0001-01-01','','',NULL,NULL,'nguyenduckien1212','9454854',NULL);
/*!40000 ALTER TABLE `information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `listening_question`
--

DROP TABLE IF EXISTS `listening_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `listening_question` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `question` text COLLATE utf8mb4_general_ci NOT NULL,
  `audio_source` text COLLATE utf8mb4_general_ci,
  `option1` text COLLATE utf8mb4_general_ci,
  `option2` text COLLATE utf8mb4_general_ci,
  `correct_answer` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listening_question`
--

LOCK TABLES `listening_question` WRITE;
/*!40000 ALTER TABLE `listening_question` DISABLE KEYS */;
INSERT INTO `listening_question` VALUES (1,'Chọn đáp án đúng:','https://api.dictionaryapi.dev/media/pronunciations/en/book-uk.mp3','Book','Bob',1),(2,'Chọn đáp án đúng:','https://api.dictionaryapi.dev/media/pronunciations/en/field-au.mp3','Feel','Field',2);
/*!40000 ALTER TABLE `listening_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `translate_question`
--

DROP TABLE IF EXISTS `translate_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `translate_question` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `question` text COLLATE utf8mb4_general_ci NOT NULL,
  `context` text COLLATE utf8mb4_general_ci,
  `option1` text COLLATE utf8mb4_general_ci,
  `option2` text COLLATE utf8mb4_general_ci,
  `option3` text COLLATE utf8mb4_general_ci,
  `option4` text COLLATE utf8mb4_general_ci,
  `correct_answer` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `translate_question`
--

LOCK TABLES `translate_question` WRITE;
/*!40000 ALTER TABLE `translate_question` DISABLE KEYS */;
INSERT INTO `translate_question` VALUES (1,'Chọn đáp án đúng:','Independent','Độc lập','Phụ thuộc','Ức chế','Kiểm soát',1),(2,'Chọn đáp án đúng:','Zoo','Ngựa vằn','Sở thú','Cá heo','Mắt',2);
/*!40000 ALTER TABLE `translate_question` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-09 22:44:51
