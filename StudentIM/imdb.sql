-- MySQL dump 10.13  Distrib 5.7.17, for Win32 (AMD64)
--
-- Host: localhost    Database: imdb
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` varchar(10) NOT NULL,
  `teacher_id` varchar(10) DEFAULT NULL,
  `level` varchar(3) DEFAULT NULL,
  `last_time` varchar(30) DEFAULT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `teacher` (`teacher_id`),
  CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  CONSTRAINT `admin_info` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('admin','10001','10','2017-06-16 06:30:48','admin'),('root','10001','6','2017-05-31 10:25:12','root');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class` (
  `id` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `teacher_id` varchar(10) DEFAULT NULL,
  `major_id` varchar(20) DEFAULT NULL,
  `part_id` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `teacher` (`teacher_id`),
  KEY `major` (`major_id`),
  KEY `part_id` (`part_id`),
  CONSTRAINT `class_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  CONSTRAINT `class_ibfk_2` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`),
  CONSTRAINT `class_ibfk_3` FOREIGN KEY (`part_id`) REFERENCES `part` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES ('101','软件1601','10001','2655','01'),('102','软件1602','10001','2655','01'),('103','软件1603','10001','2655','01'),('104','软件1604','10001','2655','01'),('227','会计1501','10002','1144','04'),('228','会计1502','10004','1144','04'),('229','会计1503','10004','1144','04'),('230','会计1504','10004','1144','04'),('231','会计1505','10004','1144','04'),('232','会计1506','10003','1144','04'),('233','会计1507','10004','1144','04');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `credit` varchar(2) NOT NULL,
  `kind` varchar(10) NOT NULL,
  `major_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `major` (`major_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('2314','Go语言','5','必修','2655'),('2316','Java语言','5','必修','2655'),('2319','XML结构分析','5','必修','2655'),('2324','Python语言','5','必修','2655'),('2333','C语言','5','必修','2655'),('2334','汇编语言','5','必修','2655'),('2341','数据结构','5','必修','2655'),('2342','算法分析','3','选修','2655');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `major` (
  `id` varchar(10) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `part_id` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `part_id` (`part_id`),
  CONSTRAINT `major_ibfk_1` FOREIGN KEY (`part_id`) REFERENCES `part` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES ('1144','会计','04'),('1202','建筑信息项目化管理','02'),('1232','电子信息工程技术','02'),('2655','软件工程','01');
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part`
--

DROP TABLE IF EXISTS `part`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `part` (
  `id` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `teacher_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `part_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part`
--

LOCK TABLES `part` WRITE;
/*!40000 ALTER TABLE `part` DISABLE KEYS */;
INSERT INTO `part` VALUES ('01','计算机与科学系',NULL),('02','信息工程系',NULL),('03','土木工程系',NULL),('04','经济管理系',NULL),('05','铁道工程系',NULL),('06','道桥工程系',NULL),('07','机械工程系',NULL),('08','材料工程系',NULL);
/*!40000 ALTER TABLE `part` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `student_id` varchar(10) NOT NULL,
  `mon_1` varchar(10) DEFAULT NULL,
  `mon_2` varchar(10) DEFAULT NULL,
  `mon_3` varchar(10) DEFAULT NULL,
  `mon_4` varchar(10) DEFAULT NULL,
  `mon_5` varchar(10) DEFAULT NULL,
  `tue_1` varchar(10) DEFAULT NULL,
  `tue_2` varchar(10) DEFAULT NULL,
  `tue_3` varchar(10) DEFAULT NULL,
  `tue_4` varchar(10) DEFAULT NULL,
  `tue_5` varchar(10) DEFAULT NULL,
  `wed_1` varchar(10) DEFAULT NULL,
  `wed_2` varchar(10) DEFAULT NULL,
  `wed_3` varchar(10) DEFAULT NULL,
  `wed_4` varchar(10) DEFAULT NULL,
  `wed_5` varchar(10) DEFAULT NULL,
  `thu_1` varchar(10) DEFAULT NULL,
  `thu_2` varchar(10) DEFAULT NULL,
  `thu_3` varchar(10) DEFAULT NULL,
  `thu_4` varchar(10) DEFAULT NULL,
  `thu_5` varchar(10) DEFAULT NULL,
  `fri_1` varchar(10) DEFAULT NULL,
  `fri_2` varchar(10) DEFAULT NULL,
  `fri_3` varchar(10) DEFAULT NULL,
  `fri_4` varchar(10) DEFAULT NULL,
  `fri_5` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES ('20166168','2342',NULL,'2316','2341',NULL,NULL,NULL,NULL,NULL,'2342',NULL,'2334',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2319',NULL,NULL,NULL);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `score` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(10) NOT NULL,
  `course_id` varchar(10) NOT NULL,
  `mark` varchar(5) NOT NULL,
  `term` varchar(10) DEFAULT NULL,
  `teacher_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `course_id` (`course_id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `score_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `score_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `score_ibfk_3` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (1,'20166168','2342','100','2','10001'),(2,'20166168','2342','98','2','10001'),(3,'20166168','2316','100','1','10002');
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` varchar(10) NOT NULL,
  `name` varchar(10) NOT NULL,
  `sex` varchar(1) NOT NULL,
  `birthday` varchar(30) DEFAULT NULL,
  `join_time` varchar(30) DEFAULT NULL,
  `id_card` varchar(18) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `class_id` varchar(10) DEFAULT NULL,
  `grade` varchar(10) NOT NULL,
  `dorm` varchar(5) NOT NULL,
  `years` varchar(10) DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `last_time` varchar(30) DEFAULT NULL,
  `major_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `class_id` (`class_id`),
  KEY `major_id` (`major_id`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `student_ibfk_4` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `student_ibfk_5` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('20166168','包凯聪','男','1995-11-02','2016-09-01','511623199511020038','17628029695','617780725@qq.com','101','2','2651','3','sources/photo/student/test.jpg','admin','2017-06-16 05:53:17','2655');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `id` varchar(10) NOT NULL,
  `name` varchar(10) NOT NULL,
  `sex` varchar(1) NOT NULL,
  `birthday` varchar(30) DEFAULT NULL,
  `id_card` varchar(18) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `years` varchar(4) DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `join_time` varchar(30) DEFAULT NULL,
  `title` varchar(10) DEFAULT NULL,
  `part_id` varchar(20) DEFAULT NULL,
  `kind` varchar(20) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `last_time` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `part_id` (`part_id`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`part_id`) REFERENCES `part` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('10001','包凯聪','男','1995-11-02','511623199511020038','13568384535','617780725@qq.com','5','','2011-11-11','教授','01','管理','admin','2017-06-15 19:45:42'),('10002','李超','男',NULL,'123456789123456789','12345678912',NULL,NULL,NULL,NULL,'讲师','02','全职教师','admin',NULL),('10003','刘丽','女','','123456789123456789','12345678912','','','','3','讲师','04','全职教师','admin',''),('10004','刘洪','男',NULL,'123456789123456789','12345678912',NULL,NULL,NULL,NULL,'助教','03','兼职教师','admin',NULL),('10005','陈佳洁','女',NULL,'123456789123456789','12345678912',NULL,NULL,NULL,NULL,'助教','08','全职教师','admin',NULL);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-16  6:33:42
