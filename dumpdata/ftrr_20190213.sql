CREATE DATABASE  IF NOT EXISTS `ftrr` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ftrr`;
-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: ftrr
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.16.04.2

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
-- Table structure for table `final_results`
--

DROP TABLE IF EXISTS `final_results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `final_results` (
  `trainee_for_request_id` int(11) NOT NULL,
  `result` enum('Join DIV','Waiting','Extend','Fail') COLLATE utf8_vietnamese_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`trainee_for_request_id`),
  KEY `fk_final_requests_users_1` (`updated_by`),
  KEY `fk_final_requests_trainee_for_requests_1` (`trainee_for_request_id`) USING BTREE,
  CONSTRAINT `fk_final_requests_trainee_for_requests_1` FOREIGN KEY (`trainee_for_request_id`) REFERENCES `trainee_for_requests` (`id`),
  CONSTRAINT `fk_final_requests_users_1` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `final_results`
--

LOCK TABLES `final_results` WRITE;
/*!40000 ALTER TABLE `final_results` DISABLE KEYS */;
INSERT INTO `final_results` VALUES (4,'Join DIV','2019-01-01 00:00:00',NULL,NULL);
/*!40000 ALTER TABLE `final_results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `levels`
--

DROP TABLE IF EXISTS `levels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `levels` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` enum('Bad','Good','Excelent') DEFAULT NULL,
  `created_at` datetime(1) DEFAULT NULL,
  `updated_at` datetime(1) DEFAULT NULL,
  `deleted_at` datetime(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=armscii8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `levels`
--

LOCK TABLES `levels` WRITE;
/*!40000 ALTER TABLE `levels` DISABLE KEYS */;
INSERT INTO `levels` VALUES (1,'Excelent','1991-06-24 06:40:44.0',NULL,NULL),(2,'Bad','2009-11-06 00:20:50.0',NULL,NULL),(3,'Good','2009-06-10 23:44:47.0',NULL,NULL);
/*!40000 ALTER TABLE `levels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` tinytext,
  `status` enum('unread','read') DEFAULT NULL,
  `url_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `readed_at` datetime DEFAULT NULL,
  `user_request` int(11) DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_notifications_users_1` (`user_id`),
  CONSTRAINT `fk_notifications_users_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=armscii8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,'Commodi est rerum consequuntur repellendus. Omnis omnis nesciunt id possimus nulla itaque. Optio aut unde soluta libero repellendus magnam sint.','read',9,57,'2015-04-22 21:16:46',NULL,'1971-10-23 02:23:37',NULL,NULL),(2,'Sed nulla ipsam provident quaerat voluptatem. Aut facere magni aspernatur voluptatum cumque voluptas. Officia exercitationem consectetur facilis dolorem.','unread',1,3,'1976-07-09 05:15:40',NULL,'1970-12-10 14:09:16',NULL,NULL),(3,'Rem et ut non vero modi et. Magnam rem non voluptatem est hic a voluptatem.','read',1,64,'1996-07-11 23:30:23',NULL,'1981-08-16 09:39:26',NULL,NULL),(4,'Laborum quia commodi sed soluta. Quaerat commodi possimus voluptas ipsam quibusdam. Excepturi doloribus nobis quibusdam labore.','unread',3,55,'1999-02-02 13:36:31',NULL,'2018-03-18 00:44:20',NULL,NULL),(5,'A est vitae incidunt necessitatibus. Distinctio quia non est magnam. Hic quam sunt repudiandae sed accusantium. Quia culpa omnis recusandae dolorum deserunt.','unread',8,94,'1975-01-12 14:20:34',NULL,'2009-03-26 20:14:15',NULL,NULL),(6,'Qui placeat sed et voluptas voluptatem est. Est repellendus et sapiente est laudantium labore. Deserunt tenetur voluptas et.','read',2,68,'1997-12-18 16:22:42',NULL,'1972-06-23 15:20:04',NULL,NULL),(7,'Occaecati cum molestias odit ducimus odio. Neque recusandae est corporis temporibus soluta amet. Sapiente consectetur molestiae et itaque aspernatur aut.','read',7,53,'2011-02-23 05:29:24',NULL,'1983-07-19 23:20:28',NULL,NULL),(8,'Ut est neque ut ex commodi tenetur corporis. Consequatur minima pariatur corrupti iusto voluptatem. Ab ad ea asperiores saepe et. Voluptatum doloremque voluptate suscipit quam non.','unread',1,83,'2011-07-30 03:16:16',NULL,'2000-03-18 01:31:53',NULL,NULL),(9,'Delectus aliquid porro eos et et aspernatur. Repudiandae delectus enim dolorem aut ab voluptatum ut provident. Laborum perspiciatis excepturi sapiente dicta numquam. Omnis nihil dolore iste dolor.','unread',5,91,'2004-11-22 18:56:30',NULL,'2000-05-08 19:03:29',NULL,NULL),(10,'Aut quaerat id assumenda qui molestiae. Quod unde et repudiandae est voluptatem.','unread',5,15,'1988-06-20 08:26:48',NULL,'2012-04-23 12:41:27',NULL,NULL),(11,'Et sint ut dolore laudantium qui eius. Eveniet odit voluptas consequuntur voluptatem voluptatem. Dolores eaque autem natus illum.','read',7,58,'2009-09-25 09:21:02',NULL,'2002-01-07 08:05:23',NULL,NULL),(12,'Velit non cupiditate rerum culpa ipsa ut expedita. Quia dicta ut eos. Quia ut quis aliquam.','read',7,48,'2014-08-15 19:04:20',NULL,'2004-12-17 13:33:58',NULL,NULL),(13,'Ut omnis et sint occaecati voluptatibus id neque ut. Incidunt est debitis vitae reiciendis nulla illum enim. Qui nihil voluptatem pariatur beatae quos enim dolores.','read',8,27,'1995-10-31 09:17:47',NULL,'2008-01-15 21:25:52',NULL,NULL),(14,'Repellat maxime rem sequi ut vero perspiciatis assumenda est. Autem iusto qui nostrum vero qui enim. Sed placeat tempore ut corporis sed impedit placeat.','read',3,40,'1986-03-30 12:04:38',NULL,'1985-04-12 00:58:42',NULL,NULL),(15,'Culpa dicta eum eveniet. Minus repudiandae nihil earum delectus voluptatem adipisci in. Natus sint reprehenderit itaque et vitae.','read',4,64,'2007-08-30 22:20:45',NULL,'1975-06-01 18:25:33',NULL,NULL),(16,'Aut autem architecto quae ipsum eum voluptatum. Sapiente debitis repellat sequi assumenda id quia non. Voluptates omnis harum deserunt tempora. Sit debitis ut consectetur et eum.','read',1,34,'2008-06-23 14:03:22',NULL,'2018-10-22 22:08:50',NULL,NULL),(17,'Sit sit minus molestias doloremque alias id atque. Quo enim nostrum accusamus hic.','unread',1,58,'1996-12-10 10:31:31',NULL,'1981-01-29 09:38:28',NULL,NULL),(18,'Non labore cupiditate veniam incidunt autem ad. Quaerat nisi dolorem sunt ipsam corrupti. Aperiam commodi ab beatae hic. Dolorem odit ex quia vel non.','unread',7,14,'1996-11-20 08:34:58',NULL,'2011-10-02 07:56:15',NULL,NULL),(19,'Libero dolorum eligendi et aspernatur quas est. Neque et rerum doloremque commodi omnis molestiae delectus.','unread',2,43,'2011-12-04 03:16:55',NULL,'1970-01-26 13:26:28',NULL,NULL),(20,'Ut aliquid atque facilis quisquam delectus. Asperiores facere velit sunt nesciunt consequatur. Vel minus maiores ut ut voluptatem commodi. Et et nam ut fuga dolores praesentium.','unread',8,16,'1998-09-05 06:28:02',NULL,'2007-02-12 13:01:23',NULL,NULL),(21,'Autem non expedita quidem quo voluptatibus quo. Molestias nesciunt consectetur quam aspernatur expedita voluptas inventore.','read',4,91,'2016-01-21 07:26:14',NULL,'1978-12-31 13:45:57',NULL,NULL),(22,'Consequatur officia illo dolorum ipsum corrupti expedita. Rerum doloremque molestias molestias totam aut totam est ipsum. Nam id ea possimus eum molestiae.','read',2,44,'2011-08-21 01:38:52',NULL,'1976-04-26 21:45:32',NULL,NULL),(23,'Perferendis molestiae illo consectetur adipisci. Minus repudiandae cupiditate ipsam sit et officiis ad velit. Esse perspiciatis eum laboriosam nesciunt qui ab perferendis at.','unread',4,31,'1996-08-07 12:35:42',NULL,'1972-11-11 17:31:33',NULL,NULL),(24,'Quod excepturi magni unde ullam animi. Laudantium omnis est dolor et veritatis. Aperiam distinctio eligendi consequatur dicta fuga. Et quae in velit. Deserunt tempora ut nulla sed illum.','unread',1,13,'1998-01-01 07:39:08',NULL,'1974-06-28 12:14:21',NULL,NULL),(25,'Totam dolore amet distinctio perferendis veritatis odit. Qui et dignissimos ipsam aut blanditiis quos. Laboriosam non officiis doloremque molestiae culpa dolores ab. A et harum quam aliquam quia non.','unread',6,66,'1999-01-03 10:18:32',NULL,'2014-05-28 20:23:25',NULL,NULL),(26,'Et modi dolorum laudantium dolorem placeat ex laudantium. Unde non totam sunt eveniet voluptatem fuga. Qui voluptatem consequuntur veritatis nesciunt aut. Recusandae quas perferendis enim.','unread',3,79,'1971-01-10 10:23:14',NULL,'2016-09-20 18:11:14',NULL,NULL),(27,'Qui labore reiciendis tempore ipsam perspiciatis velit at. Porro est ullam unde velit dolores non sit. Sed dolores quidem suscipit repudiandae. Qui ipsa consequuntur asperiores aut optio cupiditate.','read',7,26,'2016-12-26 17:04:15',NULL,'2006-08-01 19:52:28',NULL,NULL),(28,'Voluptatem voluptas enim ut vel. Sit sunt autem culpa consequatur deleniti. Natus et deleniti dolore repudiandae ipsum.','read',3,9,'2007-01-21 04:23:46',NULL,'1975-06-20 11:37:22',NULL,NULL),(29,'Error nisi vero expedita et voluptates soluta suscipit. Dignissimos laborum a est repellat deleniti laudantium nam et. Dignissimos iste velit velit debitis voluptatem voluptatum sed sed.','unread',2,10,'1995-05-06 23:28:50',NULL,'2012-12-06 07:13:55',NULL,NULL),(30,'Beatae alias et assumenda sunt. Necessitatibus omnis facilis voluptatibus et dolores molestias quia. Dolore laborum omnis est inventore consectetur consequatur nostrum.','read',2,36,'2009-07-02 22:36:55',NULL,'1989-08-19 13:26:26',NULL,NULL),(31,'Fugiat quibusdam itaque saepe minima. Est accusamus deleniti et architecto explicabo. Suscipit occaecati dolorem ea nihil perspiciatis. In illum ea optio quam dolor aliquam.','read',5,62,'2003-10-20 11:09:44',NULL,'1977-02-22 17:26:31',NULL,NULL),(32,'Dolor dolor et ea deserunt eius nulla a. Facilis sint est non et repellendus. Et sunt animi veniam saepe et.','unread',6,67,'1971-07-04 22:48:29',NULL,'1993-11-23 22:11:23',NULL,NULL),(33,'Et fugiat asperiores unde libero. A omnis magni iure. Repudiandae nesciunt temporibus quia consequuntur ipsam. Nihil beatae earum aspernatur natus. Modi voluptatem consequuntur voluptatem minus.','read',2,39,'1974-11-10 08:30:13',NULL,'1985-10-01 02:30:31',NULL,NULL),(34,'Voluptatum praesentium et eius dolorum consequatur. Non iste rem esse quia. Error ipsam velit impedit aliquid nam. Qui voluptatem tempora ad sit aut delectus.','read',4,25,'1989-09-13 06:14:51',NULL,'2011-11-09 17:10:29',NULL,NULL),(35,'Repellendus eos harum in. Hic pariatur quia facilis ut ea. Omnis quia necessitatibus molestiae.','unread',9,21,'2016-07-03 02:26:07',NULL,'2003-11-09 21:44:59',NULL,NULL),(36,'Esse assumenda quia iste architecto tempora. At quisquam ullam sit reprehenderit. Quis repellat sint tenetur voluptatem id eos quo. Aut neque et beatae eius.','unread',9,33,'2011-03-21 09:28:35',NULL,'1991-12-21 06:57:07',NULL,NULL),(37,'Est et aperiam exercitationem expedita cupiditate aliquam. Consequatur magni et quasi placeat laborum laboriosam. Est enim perferendis eaque.','read',3,93,'2012-02-07 11:25:20',NULL,'2009-06-10 05:04:22',NULL,NULL),(38,'Incidunt magni ratione reprehenderit quisquam eaque. Vel unde qui ipsa quisquam minima. Velit ab modi molestiae voluptatem omnis.','unread',1,74,'2012-10-02 03:01:36',NULL,'2009-04-11 15:08:55',NULL,NULL),(39,'Est iure itaque perspiciatis et sint consectetur. Sed dolor et voluptatem porro est. Illo qui fugiat explicabo impedit.','unread',6,15,'1988-12-19 16:06:15',NULL,'1971-06-19 18:32:33',NULL,NULL),(40,'Iste autem molestiae voluptatem et ratione nam ad. Qui deleniti ratione optio nam. Maxime voluptatum animi repellat.','unread',6,84,'2006-12-14 15:22:09',NULL,'1996-04-05 07:21:30',NULL,NULL),(41,'Magnam enim facilis sequi numquam. Aut enim veniam voluptas temporibus quo in ut quia. Nemo tempora beatae quidem maxime cum. Libero officia aut impedit provident deserunt sit aut.','read',9,88,'1979-10-04 21:19:16',NULL,'2011-01-17 18:40:10',NULL,NULL),(42,'Libero nesciunt dignissimos nisi velit consequatur hic eveniet. Sint est non est soluta. Nihil commodi velit labore qui et tempora.','read',2,72,'2008-10-04 14:08:04',NULL,'2003-04-02 05:46:23',NULL,NULL),(43,'Dolorem enim dolor voluptatum rerum voluptatum quo. Totam est tempora consequatur ea. Quo et a nisi omnis totam.','unread',3,31,'2004-01-23 22:31:46',NULL,'1975-10-31 20:03:58',NULL,NULL),(44,'Nisi ea ut quaerat. Et voluptatem ut quia dolores perspiciatis quae. Voluptas consequatur fuga est atque pariatur aut explicabo. Eos odit omnis voluptates laboriosam ut qui consectetur.','read',6,15,'1996-07-06 16:01:30',NULL,'1982-04-27 22:57:01',NULL,NULL),(45,'Culpa recusandae voluptatem quos. Dolore aliquam nemo recusandae maxime praesentium.','read',6,11,'2004-12-01 14:45:06',NULL,'2007-08-23 19:41:24',NULL,NULL),(46,'Sed eum dolorem ea laborum assumenda et dolores animi. Voluptas impedit quidem quia sapiente est. Eos cum facilis qui sequi.','unread',7,95,'2005-01-31 10:02:09',NULL,'2002-04-14 12:11:48',NULL,NULL),(47,'Nihil recusandae recusandae tenetur ipsum non nemo necessitatibus. Ea nemo sunt iure necessitatibus rerum tempora aperiam. Aut dolor eos natus.','read',6,48,'2009-05-15 18:53:34',NULL,'2014-07-22 12:46:08',NULL,NULL),(48,'Harum qui consequatur vel incidunt. Minus saepe dolor quod consequatur.\nArchitecto est porro tenetur aut error aut. Eos ducimus id blanditiis aut. Sed velit vero iure mollitia ex minima eveniet.','read',2,68,'1995-04-24 09:53:30',NULL,'1996-06-01 18:37:10',NULL,NULL),(49,'Mollitia ut numquam atque est. Earum incidunt velit minima voluptatem. Sed atque reprehenderit provident architecto.','read',3,8,'2014-06-08 21:32:23',NULL,'1986-11-03 15:22:20',NULL,NULL),(50,'Cupiditate quasi cum eos mollitia. Provident ratione libero expedita sit quo nulla. Et blanditiis sed dolorem et sint sed.','unread',9,90,'1991-02-20 11:43:02',NULL,'2012-09-05 06:09:52',NULL,NULL);
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan_resources`
--

DROP TABLE IF EXISTS `plan_resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plan_resources` (
  `final_request_id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `mentor` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `trainee_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`final_request_id`),
  CONSTRAINT `fk_plan_resources_final_requests_1` FOREIGN KEY (`final_request_id`) REFERENCES `final_results` (`trainee_for_request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan_resources`
--

LOCK TABLES `plan_resources` WRITE;
/*!40000 ALTER TABLE `plan_resources` DISABLE KEYS */;
INSERT INTO `plan_resources` VALUES (4,'2019-01-05','Do Quang Duy',44);
/*!40000 ALTER TABLE `plan_resources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requests`
--

DROP TABLE IF EXISTS `requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `division` enum('Div1','Div2','Div3') COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `language` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_requests_users_1` (`updated_by`),
  KEY `fk_requests_users_2` (`created_by`),
  CONSTRAINT `fk_requests_users_1` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_requests_users_2` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requests`
--

LOCK TABLES `requests` WRITE;
/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
INSERT INTO `requests` VALUES (1,'Div1',5,'1998-06-20','Go',0,15,'1985-09-03 01:41:16',NULL,NULL),(2,'Div2',1,'1991-01-12','NodeJS',0,14,'2013-03-18 20:44:27',NULL,NULL),(3,'Div2',3,'1979-04-29','React',0,76,'2009-01-30 22:23:48',NULL,NULL),(4,'Div2',3,'2015-02-20','Front-end',0,73,'2009-02-08 23:06:02',NULL,NULL),(5,'Div3',3,'1990-01-08','Java',0,49,'1977-05-28 09:28:21',NULL,NULL);
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result_interviews`
--

DROP TABLE IF EXISTS `result_interviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `result_interviews` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trainee_for_request_id` int(11) DEFAULT NULL,
  `content` tinytext,
  `result` enum('Pass','Fail') DEFAULT NULL,
  `title_interview_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `result_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_result_interviews_result_types_1` (`result_type_id`),
  KEY `fk_result_interviews_trainees_1` (`trainee_for_request_id`) USING BTREE,
  KEY `fk_result_interviews_users_1` (`updated_by`),
  KEY `fk_result_interviews_users_2` (`created_by`),
  CONSTRAINT `fk_result_interviews_result_types_1` FOREIGN KEY (`result_type_id`) REFERENCES `result_types` (`id`),
  CONSTRAINT `fk_result_interviews_trainee_for_requests_1` FOREIGN KEY (`trainee_for_request_id`) REFERENCES `trainee_for_requests` (`id`),
  CONSTRAINT `fk_result_interviews_trainees_1` FOREIGN KEY (`trainee_for_request_id`) REFERENCES `trainees` (`id`),
  CONSTRAINT `fk_result_interviews_users_1` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_result_interviews_users_2` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=armscii8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result_interviews`
--

LOCK TABLES `result_interviews` WRITE;
/*!40000 ALTER TABLE `result_interviews` DISABLE KEYS */;
INSERT INTO `result_interviews` VALUES (1,1,'Commodi est rerum consequuntur repellendus. Omnis omnis nesciunt id possimus nulla itaque. Optio aut unde soluta libero repellendus magnam sint.','Pass',1,'2019-01-01 00:00:00',2,NULL,NULL,1),(2,1,'Rem et ut non vero modi et. Magnam rem non voluptatem est hic a voluptatem.','Pass',1,'2019-01-01 00:00:00',4,NULL,NULL,2),(3,2,'Sed nulla ipsam provident quaerat voluptatem. Aut facere magni aspernatur voluptatum cumque voluptas. Officia exercitationem consectetur facilis dolorem.','Pass',2,'2019-01-01 00:00:00',4,NULL,NULL,1),(4,2,'Rem et ut non vero modi et. Magnam rem non voluptatem est hic a voluptatem.','Pass',2,'2019-01-01 00:00:00',4,NULL,NULL,2),(5,3,'Rem et ut non vero modi et. Magnam rem non voluptatem est hic a voluptatem.','Pass',5,'2019-01-01 00:00:00',6,NULL,NULL,1),(6,3,'Rem et ut non vero modi et. Magnam rem non voluptatem est hic a voluptatem.','Fail',5,'2019-01-01 00:00:00',7,NULL,NULL,2),(7,4,'Rem et ut non vero modi et. Magnam rem non voluptatem est hic a voluptatem.','Pass',1,'2019-01-01 00:00:00',4,NULL,NULL,1),(8,4,'Rem et ut non vero modi et. Magnam rem non voluptatem est hic a voluptatem.','Pass',1,'2019-01-01 00:00:00',4,NULL,NULL,2),(9,5,'Rem et ut non vero modi et. Magnam rem non voluptatem est hic a voluptatem.','Pass',1,'2019-01-01 00:00:00',4,NULL,NULL,1),(10,5,'Rem et ut non vero modi et. Magnam rem non voluptatem est hic a voluptatem.','Fail',1,'2019-01-01 00:00:00',4,NULL,NULL,2),(11,6,'Rem et ut non vero modi et. Magnam rem non voluptatem est hic a voluptatem.','Fail',1,'2019-01-01 00:00:00',4,NULL,NULL,1),(12,6,'Rem et ut non vero modi et. Magnam rem non voluptatem est hic a voluptatem.','Fail',1,'2019-01-01 00:00:00',4,NULL,NULL,2);
/*!40000 ALTER TABLE `result_interviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result_trainings`
--

DROP TABLE IF EXISTS `result_trainings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `result_trainings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `point` int(11) DEFAULT NULL,
  `trainee_id` int(11) DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `results` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_result_trainnings_trainees_1` (`trainee_id`),
  KEY `fk_result_trainnings_users_1` (`updated_by`),
  KEY `fk_result_trainnings_users_2` (`created_by`),
  CONSTRAINT `fk_result_trainnings_trainees_1` FOREIGN KEY (`trainee_id`) REFERENCES `trainees` (`id`),
  CONSTRAINT `fk_result_trainnings_users_1` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_result_trainnings_users_2` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result_trainings`
--

LOCK TABLES `result_trainings` WRITE;
/*!40000 ALTER TABLE `result_trainings` DISABLE KEYS */;
INSERT INTO `result_trainings` VALUES (1,9,89,'Voluptatem numquam voluptas ea est reiciendis sunt perferendis. Sit incidunt id aut non. Quo debitis impedit distinctio quod.',NULL,'1986-08-31 08:00:48',48,NULL),(2,20,67,'Ipsum aut cum dolore nulla. Neque autem iure assumenda illo repudiandae aut blanditiis labore. Iusto voluptas provident iusto. Quibusdam est consectetur ut ducimus accusamus sit.',NULL,'1991-11-06 18:03:38',34,NULL),(3,86,83,'Quos numquam consequatur harum optio libero dolores vel eum. Voluptatum voluptatum soluta vero quo harum enim.',NULL,'2000-06-02 04:11:30',78,NULL),(4,89,41,'Minus aut minima qui provident et veritatis sint. Culpa qui suscipit esse enim. Quia modi voluptatum rerum perspiciatis soluta nihil ut rerum.',NULL,'1991-07-07 22:47:00',77,NULL),(5,68,44,'Sit ad odit ipsa harum. Repellat voluptatem eum molestias qui. Ab illum nostrum labore esse quas fugiat similique in.',NULL,'2001-11-19 00:56:34',17,NULL),(6,76,72,'Soluta qui distinctio illo vel et itaque. Sed velit in dolore sint quos. Et ut qui iusto dolore.',NULL,'1985-04-26 11:51:49',61,NULL),(7,37,42,'Eos perspiciatis perspiciatis tempore voluptatum et adipisci. Assumenda qui sunt aut minima ullam quisquam ratione voluptas.',NULL,'1979-12-18 08:12:26',94,NULL),(8,2,11,'Ratione molestiae officiis et. Odit nostrum fugit illo veniam autem vel. Recusandae cum officiis nihil repellat quis omnis. Sed voluptatum officiis explicabo qui repellendus.',NULL,'2006-02-20 16:11:03',50,NULL),(9,11,7,'Vitae labore sunt quasi et vel ipsam non. Odio dolorem sed id doloremque numquam. Accusantium dolore nostrum cumque harum sed autem id. Ea earum minima dolor molestiae sint iusto quam.',NULL,'1984-12-13 00:55:03',26,NULL),(10,68,24,'Corporis nesciunt eius numquam. Consequatur consequuntur vel quisquam cum eum perferendis rerum. Eum animi porro et magni aut.',NULL,'1991-11-04 08:59:06',95,NULL),(11,87,83,'Rerum necessitatibus omnis et et maxime similique et omnis. Et odit qui quae beatae. Voluptatibus pariatur nulla nostrum assumenda dolore.',NULL,'1971-09-12 23:01:17',46,NULL),(12,29,24,'Iusto rerum quia ut sunt consectetur nemo. Veritatis harum autem consequatur quibusdam et. Laudantium sint voluptatem quae qui quae cupiditate. Omnis sunt ut expedita ducimus neque doloremque.',NULL,'1986-08-07 09:26:47',29,NULL),(13,76,3,'Reiciendis nisi facere fugit facere officia. Reprehenderit quos rerum dolore. Eaque eos porro perspiciatis ullam ipsam.',NULL,'2012-11-10 07:46:25',9,NULL),(14,65,80,'Rem adipisci voluptatibus sint consectetur. Ut eum sed incidunt fugit provident ab.',NULL,'1980-05-13 20:11:45',33,NULL),(15,60,1,'Doloremque sit ducimus est hic nam reiciendis reprehenderit. Ad rerum dignissimos numquam. Voluptatem voluptas aut suscipit natus.',NULL,'1973-12-06 20:55:53',52,NULL),(16,87,76,'Dolore ullam nemo deserunt incidunt. Non est ea praesentium. Possimus ex est quo ut fugit et aut ullam. Voluptatem architecto maiores deserunt ut laborum assumenda nam.',NULL,'2003-07-14 02:31:57',90,NULL),(17,68,99,'Et animi magni atque nobis ut voluptas impedit. Quo porro illum velit iure delectus aut dolor quas. Atque iste nihil excepturi eum explicabo. Et asperiores ipsum neque tempora consequuntur numquam.',NULL,'1988-12-14 02:49:26',42,NULL),(18,64,24,'Ut eum cupiditate animi quod tempore. Atque ducimus fugit ut voluptas aut iusto. Voluptas voluptatem accusantium sit velit earum earum necessitatibus in. Voluptas dolorum et animi mollitia est.',NULL,'2016-02-02 22:02:02',82,NULL),(19,86,20,'Ad illo labore facere soluta. Illo dolore voluptatem aut consequatur nesciunt beatae. Veniam architecto possimus ab inventore possimus. Nisi perferendis vel voluptas quia ut modi.',NULL,'1977-06-27 04:59:13',64,NULL),(20,26,59,'Aut ullam perspiciatis voluptas ad in ipsa. Quo nulla a sed iusto dolore. Facilis molestias officiis magnam veritatis exercitationem vitae et laudantium. Veritatis est nesciunt in doloremque est.',NULL,'2009-02-09 22:36:02',64,NULL),(21,59,81,'Non adipisci iusto voluptatem nisi numquam. Numquam non repellat est et sunt vel cumque. Repellendus nam ea labore quia qui id enim. Doloremque sed dolor exercitationem quam nam molestiae eveniet.',NULL,'2008-11-04 16:10:15',30,NULL),(22,62,24,'Illum autem laborum earum voluptas aliquam. Amet ratione excepturi et voluptatem. Possimus aut qui pariatur assumenda. Aut accusamus exercitationem velit occaecati ea earum qui fugiat.',NULL,'1995-01-31 00:26:57',22,NULL),(23,17,24,'Sapiente maiores sit maxime sunt expedita earum. Reprehenderit suscipit culpa exercitationem et consequuntur. Suscipit nobis sed laudantium sint dolores enim.',NULL,'2003-04-15 04:34:48',35,NULL),(24,72,72,'Voluptate reprehenderit non voluptatem. Et dolor in repudiandae vitae eveniet sequi. Ullam adipisci veniam a deserunt.',NULL,'1991-03-15 04:00:23',31,NULL),(25,55,60,'Nesciunt quam est rem. Nisi nihil accusantium odio ratione ipsam magni. Unde ut molestiae dolorum debitis. Tempore eius incidunt tempore et natus enim perferendis.',NULL,'2006-03-28 20:14:45',95,NULL),(26,81,25,'Sit aut odio debitis quo nisi. Ratione dolor velit tempore ut doloremque et consequatur. Ullam sit alias voluptatibus ut sint aperiam quia. Voluptas veritatis neque temporibus beatae.',NULL,'1976-04-16 11:35:07',43,NULL),(27,73,40,'Sit magni modi nulla. Esse beatae quas quidem perferendis praesentium architecto. Explicabo ullam aut deserunt magnam.',NULL,'1971-03-20 22:37:40',49,NULL),(28,50,72,'A veniam et officia voluptatem mollitia. Doloribus quas odit deserunt. Laudantium amet magnam quo iusto.',NULL,'2014-04-28 17:31:40',43,NULL),(29,58,57,'Temporibus qui numquam suscipit optio in ducimus. Sequi est quasi fugit ut nam cum similique. Ut dicta quo excepturi eum qui.',NULL,'2016-10-30 20:17:30',51,NULL),(30,56,76,'Deleniti rem rerum repudiandae at. In quis et id architecto. Sapiente est numquam est voluptate quo.',NULL,'1998-03-18 23:29:12',58,NULL),(31,19,3,'Culpa sed dignissimos aut. Voluptas soluta nam in occaecati provident architecto labore. Vero eaque rerum consequatur ipsam eum.',NULL,'2005-02-17 15:20:36',51,NULL),(32,40,47,'Minus sint aut dolores consequatur doloremque. Maxime ut rerum sed tenetur nostrum similique. Quia non tenetur vel dignissimos impedit.',NULL,'1972-09-18 16:12:13',1,NULL),(33,63,92,'Dicta facere id eos id repellat deleniti velit. Aliquid excepturi culpa unde itaque. Vel qui sint necessitatibus a iure sed. A voluptatem ipsa magnam enim animi et.',NULL,'2016-02-14 13:32:43',70,NULL),(34,74,83,'Velit officiis nulla molestias incidunt. Rerum ut asperiores nisi sunt laborum labore sint accusantium. Unde sapiente consectetur voluptate velit id vel.',NULL,'2016-11-17 10:13:00',12,NULL),(35,44,34,'Explicabo fuga tenetur sunt. Sunt fugiat incidunt nisi sit ratione provident. Voluptas quasi omnis accusamus ut recusandae quisquam. Et libero et officia ea.',NULL,'1990-06-01 16:11:22',41,NULL),(36,63,75,'Asperiores mollitia omnis unde dolores nobis. Dolore molestias vel quidem et. Itaque voluptas aperiam est corrupti et consectetur consectetur voluptatem.',NULL,'1982-03-08 15:52:37',14,NULL),(37,1,71,'Placeat unde placeat dolorem dolores qui non maiores. Officia culpa consectetur sed dignissimos. Odio quaerat recusandae praesentium iure aut natus.',NULL,'1997-03-06 02:54:59',98,NULL),(38,74,35,'Omnis dicta quia quia veritatis. Ipsum quidem animi provident. Dolorem rerum totam fugit eum qui eligendi aut.',NULL,'1993-11-04 05:46:57',94,NULL),(39,32,33,'Ratione nesciunt placeat nisi quas minus. Voluptatem et minus maxime dolores similique voluptatibus. Id cumque incidunt iusto nobis itaque. Beatae et perspiciatis in suscipit repellat.',NULL,'2015-05-11 18:32:33',7,NULL),(40,52,25,'Vero et neque magni illum facilis corrupti nam. Repellendus rerum quaerat fugiat dolorum illum numquam aut. Culpa rem asperiores est iste occaecati pariatur. Quis quod impedit sint ut.',NULL,'2016-05-15 10:08:50',92,NULL),(41,52,1,'Quidem optio voluptatibus eum ut dolor in natus. Aut dolores expedita exercitationem nesciunt maxime aut. Minus consequatur unde libero et.',NULL,'1996-07-04 22:32:13',49,NULL),(42,96,34,'Autem quis quae sed asperiores. Aut ab quae asperiores dolorem perspiciatis. Qui porro autem voluptatum laborum non.',NULL,'1995-08-12 03:46:10',40,NULL),(43,17,98,'Blanditiis perferendis fugit mollitia ut at quia rerum autem. Dolorem magni cumque quod totam rerum ut. Aut autem consectetur sed minima iste est quis. Aut omnis voluptate dolorem itaque sunt.',NULL,'2002-12-06 06:40:41',5,NULL),(44,63,83,'Voluptatibus ipsam eos reiciendis ut voluptas voluptatem sint. Optio possimus soluta reiciendis quis. Itaque molestiae beatae porro provident. Est non et praesentium voluptatem.',NULL,'2002-08-06 11:21:12',55,NULL),(45,13,56,'Eum reiciendis ut molestias sequi. Impedit ut pariatur facere dolores sint et esse nulla. Voluptates expedita id ullam quisquam nihil.',NULL,'1995-08-22 23:49:41',58,NULL),(46,13,12,'Sit est quod impedit. Dicta inventore dicta dignissimos possimus assumenda aut aut. Facere modi autem molestiae.',NULL,'1987-01-12 15:08:12',58,NULL),(47,74,8,'Dolorum vero iure atque debitis ut voluptates. Dolorem alias esse culpa illo quia. Enim et dolores et aut eum.\nUt quam libero nemo ea vel veritatis quam facilis. Quo voluptas qui voluptatum nobis.',NULL,'2004-12-10 15:29:22',59,NULL),(48,15,59,'Similique quo saepe facilis quam natus qui. Iusto ex tempore tenetur quam molestias nemo voluptatibus. Tempora omnis mollitia accusantium harum.',NULL,'1980-04-16 21:58:42',100,NULL),(49,29,29,'Porro recusandae qui non voluptatem magni. Et et et aut aut quos sit. Dolorem rerum dolores qui delectus nesciunt dolor sapiente. Optio commodi rerum consequatur nostrum et rerum.',NULL,'1971-06-07 08:16:26',41,NULL),(50,47,11,'Voluptatem dicta maxime minima cupiditate non. Ut libero quas nobis aliquid. Eos et est ea repudiandae vero sit.',NULL,'2016-05-12 11:09:17',62,NULL),(51,64,81,'Voluptatum ut quia eos. Et vel amet quod et quaerat veniam non. Et ut quia quos vel. Laborum nobis eligendi sunt illo ex ab.',NULL,'1985-09-12 02:17:15',86,NULL),(52,52,75,'Omnis voluptatem enim laborum quo. Iste et sed suscipit explicabo ipsum magnam rerum. Nisi tempore blanditiis doloremque omnis quis pariatur nemo.',NULL,'1988-05-15 08:37:14',51,NULL),(53,90,83,'Odio impedit deserunt et nam nesciunt. Doloribus et et et. Eum quod consequatur fugit ducimus fuga. Quasi atque est qui aspernatur iure qui ab. Eius harum est omnis quo magnam dolores quisquam.',NULL,'1997-11-15 03:54:54',10,NULL),(54,39,44,'Ut ratione vel qui et ipsum beatae tempore. Vel occaecati et dolore qui magnam ut illo.',NULL,'1977-04-22 12:11:11',15,NULL),(55,40,16,'Voluptatem magnam neque voluptate aut omnis. Aut numquam temporibus ea est. Velit aut similique sint delectus qui aut.',NULL,'1990-02-28 03:23:06',69,NULL),(56,2,7,'Qui deleniti cumque aperiam et accusantium dolores est. Unde nesciunt et soluta voluptatem quibusdam. Itaque quae occaecati totam voluptas accusantium omnis.',NULL,'2004-08-13 21:54:37',16,NULL),(57,89,17,'In voluptas necessitatibus placeat cum nobis et. Harum provident sint sequi. Sed adipisci iusto animi nostrum debitis voluptatem illo fugiat.',NULL,'1983-11-17 18:14:08',40,NULL),(58,52,56,'Quasi sed libero quasi. Qui porro atque esse quae illum ea illo. Corrupti et atque provident laboriosam.\nQui ut sunt dignissimos qui. Quo delectus aut blanditiis libero delectus eos.',NULL,'1973-11-23 03:52:15',15,NULL),(59,60,45,'Qui impedit id quasi quo. Mollitia unde pariatur quo aut est aliquid. Et aut blanditiis nam velit ex sunt ut voluptas. Recusandae cum iure id.',NULL,'1989-03-22 03:31:26',38,NULL),(60,88,69,'Aut neque eligendi quo non enim. Vel similique fugit quo animi assumenda officiis aut. Quia ut vel fugiat voluptates ipsam.',NULL,'1976-06-12 01:51:12',100,NULL),(61,16,96,'Iusto dolorem commodi vel ut et similique qui quod. Iure amet qui et et doloremque.',NULL,'1981-07-09 07:11:33',81,NULL),(62,84,57,'Expedita quidem sit tenetur adipisci corporis quia. Quia molestiae reiciendis soluta rem accusamus voluptatem occaecati. Et laudantium minus vel nostrum quis.',NULL,'1975-11-27 07:07:08',3,NULL),(63,29,40,'Officiis unde pariatur asperiores sit eligendi dolores dolorem aut. Sunt consequatur quo aut neque. Qui ipsum quaerat nihil id ea nihil deleniti.',NULL,'1975-09-17 22:18:47',15,NULL),(64,88,3,'Consequatur est nihil fugiat quis. Et error in aut et voluptatem et.\nRerum illo accusamus quam cupiditate ullam cum. Eaque qui eius mollitia ab.',NULL,'1972-10-04 22:27:23',68,NULL),(65,82,55,'Debitis exercitationem placeat qui laboriosam vitae. Tempora nesciunt quidem quos vitae. Velit repellat dolorum nemo delectus.',NULL,'1984-12-19 19:26:43',13,NULL),(66,46,29,'Iure nobis similique suscipit et iusto odio corporis. Aliquid nostrum voluptas saepe omnis repellendus itaque fuga. Libero vitae maiores autem.',NULL,'1991-11-08 09:32:32',35,NULL),(67,28,98,'Quas error mollitia ut ea. Atque laborum consequatur recusandae esse qui rem. Consequatur provident architecto ullam culpa dolor voluptatem aut. Magni non deserunt assumenda voluptate eos.',NULL,'2002-04-23 19:30:15',4,NULL),(68,53,86,'Fugiat esse laudantium consequatur quia exercitationem veritatis. Temporibus nesciunt velit soluta sit aut doloremque. Id aut ratione hic ex.',NULL,'1997-05-18 00:24:38',81,NULL),(69,2,13,'Officiis sint non aut velit maxime aliquam esse facere. Beatae repudiandae et omnis magni officia sed. Sed rem vero inventore dolorem ducimus.',NULL,'1984-08-29 08:55:23',30,NULL),(70,51,96,'Voluptas ea ratione voluptate. Rerum repellat impedit ullam soluta similique itaque tenetur ex. Doloribus est voluptas qui velit illo praesentium architecto facilis. Earum iusto rem est rerum et.',NULL,'2007-05-08 21:33:42',29,NULL),(71,36,98,'Laboriosam minus dolor aspernatur pariatur aut deserunt officiis. Et qui fugit dolor fugiat id. Quia ipsam consectetur libero dolore.',NULL,'1981-04-26 10:26:31',2,NULL),(72,90,45,'Et ipsum impedit facilis commodi nihil nisi. Nobis in velit sed facilis. Exercitationem aut qui id at voluptatibus. Dolor explicabo sed sit placeat.',NULL,'2005-08-07 13:11:42',15,NULL),(73,74,42,'Non eaque modi dignissimos impedit deserunt molestiae corporis. Porro voluptates quis a est. Autem enim ad commodi delectus quibusdam. Repudiandae voluptatem et libero sed ut.',NULL,'1978-09-22 17:25:27',100,NULL),(74,8,29,'Omnis eos voluptates voluptas. Facere esse et quia dicta id nesciunt. Quidem inventore autem provident id. Et atque nisi aut cumque aperiam.',NULL,'2015-02-05 23:22:53',86,NULL),(75,95,38,'Consectetur illo deleniti est saepe consequuntur assumenda. Et nihil et nihil molestias. Cum quaerat temporibus numquam.',NULL,'1972-10-30 02:21:14',97,NULL),(76,83,86,'Corporis aut et aut dolor commodi fugit corrupti accusamus. Itaque error id dolores ea quisquam. Numquam tempora quae nostrum libero. Sunt quia unde voluptate aspernatur nihil facilis.',NULL,'1978-02-22 13:18:06',33,NULL),(77,40,78,'Quis accusantium expedita aut soluta totam. Reiciendis voluptas laboriosam quis molestiae fuga cum. Voluptate voluptatum laudantium et vero aperiam maiores architecto.',NULL,'2000-08-15 22:06:15',42,NULL),(78,67,36,'Ut id saepe rerum voluptatem qui dolore. Aut harum necessitatibus at magnam est sint dicta. Laudantium sit laborum dolores iure.',NULL,'2008-09-16 09:17:23',17,NULL),(79,2,56,'Fugit quis explicabo ad magnam officiis assumenda at. Nesciunt eum sit et reiciendis nihil adipisci earum.',NULL,'1996-06-09 04:19:50',38,NULL),(80,87,84,'Nihil ut vitae sit recusandae. Iste eaque sed cum culpa voluptatibus itaque dolorum. Saepe animi in ad est doloremque quas. Est quam vero veritatis et.',NULL,'2003-07-24 11:55:10',11,NULL),(81,60,50,'Voluptatibus eos facilis repudiandae aperiam et aspernatur. Consequatur magni rem ea illum enim ex aliquam. Voluptatem distinctio et incidunt et.',NULL,'1991-02-17 18:45:01',13,NULL),(82,90,46,'Sapiente voluptatem sint sit voluptatem minima dolor deleniti. Architecto delectus et pariatur ullam repudiandae. Odit minima expedita omnis quo delectus.',NULL,'1987-06-14 00:26:58',48,NULL),(83,66,17,'Magnam explicabo rerum quia quia. Repellat et ratione fugiat inventore. Libero voluptatibus explicabo cupiditate voluptas ea.',NULL,'2001-08-11 13:58:11',31,NULL),(84,98,29,'Neque voluptates ratione sed eum. Rerum sed praesentium voluptas sit. Pariatur nam odit voluptas magnam nam voluptatum. Voluptatem ducimus nobis molestiae fugiat sed quaerat incidunt.',NULL,'1978-01-15 06:43:37',30,NULL),(85,84,60,'Rerum voluptatem rerum dolorem quaerat. Hic mollitia dignissimos unde vitae qui in fugiat. Aspernatur qui ratione est ipsam voluptatum dolor.',NULL,'1971-04-10 13:32:05',24,NULL),(86,53,58,'A minima assumenda alias molestiae. Molestias consequatur temporibus consequatur beatae. Hic quia hic odio dolorem cupiditate autem adipisci. Ut autem voluptatem dicta commodi atque modi.',NULL,'1988-02-27 03:24:20',58,NULL),(87,73,26,'Hic reprehenderit sint blanditiis. Consectetur quis nihil nisi dolorem dolor vel molestiae. Ut est eos et.',NULL,'1981-09-04 09:28:55',2,NULL),(88,45,72,'Nobis voluptate quam vero nulla voluptatem. Consequuntur ut ab natus dolores. Eligendi error et nemo non error eum ut ut.',NULL,'1986-06-22 12:25:58',68,NULL),(89,84,2,'A voluptatem odio reiciendis et culpa reprehenderit. Et sed non molestias dignissimos voluptas. Sed sint sed aliquam maxime illo expedita qui. Aperiam iusto ducimus non iure.',NULL,'1989-05-19 15:06:38',100,NULL),(90,37,53,'Delectus consequatur vel sunt quo est. Quis mollitia voluptatibus omnis sit. Ut iste dignissimos in facilis id esse aliquid. Exercitationem earum est est facilis corporis neque voluptate omnis.',NULL,'1996-03-12 13:26:34',39,NULL),(91,53,97,'Eum sed vel quis nulla doloribus iusto excepturi. Quia dolores nesciunt minus. Sunt cupiditate quia possimus accusantium.',NULL,'1993-07-14 14:37:53',39,NULL),(92,31,72,'Assumenda ea dolorem voluptas ut. Aliquid et ipsa praesentium ipsam rerum a illum. Ipsum magnam nostrum a quisquam temporibus. Sunt vero corrupti quisquam explicabo similique alias.',NULL,'1972-11-02 05:20:05',74,NULL),(93,20,80,'Maxime maxime harum vero autem. Consequatur natus totam quis quidem et. Id voluptatem consequatur maxime quis. Recusandae ut expedita nihil dolore ipsam quo voluptates.',NULL,'1984-01-05 13:25:39',7,NULL),(94,47,90,'Facilis saepe necessitatibus et modi deserunt in illo. Et omnis quaerat aut cum itaque recusandae maxime adipisci. Et eius tempora eligendi.',NULL,'1996-08-29 23:04:14',36,NULL),(95,80,44,'Quos quia nulla maxime molestiae sit. Omnis ut voluptas ut eum. Quas dolor id esse.',NULL,'2017-11-19 12:45:14',74,NULL),(96,72,47,'Sed omnis et magni rem rerum consequatur non. Temporibus quia sed natus rerum. Fugiat quae voluptatem qui quod quia. Nostrum et dolorem officiis ducimus minima consequatur.',NULL,'1984-03-26 17:57:47',94,NULL),(97,59,87,'Omnis nostrum explicabo optio aliquam consequuntur similique ipsum. Facilis facere placeat voluptatem saepe non.',NULL,'2016-10-23 11:24:30',92,NULL),(98,61,42,'Corrupti quis ut velit minus. Tenetur sit dolore harum atque veniam deserunt. Tempora eius nemo error non cum.',NULL,'1982-07-08 16:10:11',3,NULL),(99,15,23,'Omnis cumque atque optio in dolorem voluptatum. Saepe non illo officiis aperiam soluta. Amet omnis laudantium voluptatem quis optio et fuga ut.',NULL,'2006-08-06 21:54:33',70,NULL),(100,3,32,'Sed tenetur tenetur tempore. Non quibusdam praesentium eum et ullam. Totam fugiat qui commodi est doloribus.',NULL,'1999-06-04 14:02:29',83,NULL);
/*!40000 ALTER TABLE `result_trainings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result_types`
--

DROP TABLE IF EXISTS `result_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `result_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result_types`
--

LOCK TABLES `result_types` WRITE;
/*!40000 ALTER TABLE `result_types` DISABLE KEYS */;
INSERT INTO `result_types` VALUES (1,'HR'),(2,'Div');
/*!40000 ALTER TABLE `result_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `steps`
--

DROP TABLE IF EXISTS `steps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `steps` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `steps`
--

LOCK TABLES `steps` WRITE;
/*!40000 ALTER TABLE `steps` DISABLE KEYS */;
/*!40000 ALTER TABLE `steps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `title_interviews`
--

DROP TABLE IF EXISTS `title_interviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `title_interviews` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `title_interviews`
--

LOCK TABLES `title_interviews` WRITE;
/*!40000 ALTER TABLE `title_interviews` DISABLE KEYS */;
INSERT INTO `title_interviews` VALUES (1,'quia','1983-03-01 09:09:10'),(2,'occaecati','2008-04-12 07:47:40'),(3,'excepturi','1993-02-26 18:44:55'),(4,'amet','2013-01-08 23:04:14'),(5,'omnis','2007-04-01 02:41:40'),(6,'et','2005-04-07 01:18:51'),(7,'sapiente','2008-07-02 13:22:55'),(8,'eum','1978-10-08 07:43:58'),(9,'blanditiis','1996-03-13 18:04:56'),(10,'reprehenderit','2007-09-23 11:13:57');
/*!40000 ALTER TABLE `title_interviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainee_for_requests`
--

DROP TABLE IF EXISTS `trainee_for_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trainee_for_requests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `request_id` int(11) DEFAULT NULL,
  `trainee_id` int(11) DEFAULT NULL,
  `status` enum('reject interview','processing interview','finish interview') COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_trainee_for_requests_requests_1` (`request_id`),
  KEY `fk_trainee_for_requests_users_1` (`updated_by`),
  CONSTRAINT `fk_trainee_for_requests_requests_1` FOREIGN KEY (`request_id`) REFERENCES `requests` (`id`),
  CONSTRAINT `fk_trainee_for_requests_users_1` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainee_for_requests`
--

LOCK TABLES `trainee_for_requests` WRITE;
/*!40000 ALTER TABLE `trainee_for_requests` DISABLE KEYS */;
INSERT INTO `trainee_for_requests` VALUES (1,1,30,'processing interview','2018-12-11 00:00:00',NULL,NULL),(2,1,37,'processing interview','2018-12-11 00:00:00',NULL,NULL),(3,1,42,'processing interview','2018-12-11 00:00:00',NULL,NULL),(4,1,44,'finish interview','2018-12-10 00:00:00',NULL,NULL),(5,1,48,'processing interview','2018-12-13 00:00:00',NULL,NULL),(6,2,1,'processing interview','2019-01-19 00:00:00',NULL,NULL),(8,3,3,'processing interview','2019-01-19 00:00:00',NULL,NULL),(9,3,4,'processing interview','2019-01-19 00:00:00',NULL,NULL),(10,3,5,'processing interview','2019-01-19 00:00:00',NULL,NULL),(11,4,73,'finish interview','2019-01-19 00:00:00',NULL,NULL),(12,4,77,'finish interview','2019-01-19 00:00:00',NULL,NULL),(13,4,78,'finish interview','2019-01-19 00:00:00',NULL,NULL),(14,5,20,'processing interview','2019-01-19 00:00:00',NULL,NULL),(15,5,21,'processing interview','2019-01-19 00:00:00',NULL,NULL),(16,5,22,'processing interview','2019-01-19 00:00:00',NULL,NULL);
/*!40000 ALTER TABLE `trainee_for_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainees`
--

DROP TABLE IF EXISTS `trainees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trainees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `started_time` datetime(1) DEFAULT NULL,
  `office` enum('Hanoi','Danang','Tp.HCM') DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `deleted_at` datetime(1) DEFAULT NULL,
  `level_id` int(11) DEFAULT NULL,
  `language` enum('Java','Ruby','PHP','Android','iOS','React','NodeJS','Front-end','Go','Python') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_trainees_levels_1` (`level_id`),
  KEY `fk_trainees_users_1` (`updated_by`),
  CONSTRAINT `fk_trainees_levels_1` FOREIGN KEY (`level_id`) REFERENCES `levels` (`id`),
  CONSTRAINT `fk_trainees_users_1` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=armscii8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainees`
--

LOCK TABLES `trainees` WRITE;
/*!40000 ALTER TABLE `trainees` DISABLE KEYS */;
INSERT INTO `trainees` VALUES (1,'Gladyce','colby43@example.net','1997-10-05 08:46:45.0','Danang','Debitis et quo sit aliquid sint et. Atque vol','2008-12-20 18:30:14',NULL,NULL,NULL,3,'NodeJS'),(2,'Jamir','rosie.mohr@example.org','2014-08-09 05:14:00.0','Hanoi','Illo quisquam quam in et. Sint vitae est id s','1988-07-25 12:20:39',NULL,NULL,NULL,1,'NodeJS'),(3,'Duane','loyce22@example.com','1972-12-09 12:30:43.0','Tp.HCM','Veritatis ut ipsam ea assumenda et. Sit asper','1987-12-11 02:35:07',NULL,NULL,NULL,2,'React'),(4,'Victor','kevon.o\'keefe@example.com','2000-06-18 09:27:49.0','Tp.HCM','Sint tempore cum incidunt. Velit et distincti','2011-08-27 14:47:41',NULL,NULL,NULL,2,'React'),(5,'Judy','aking@example.org','1994-07-24 19:40:12.0','Hanoi','Ipsa necessitatibus hic temporibus in volupta','1987-04-01 14:42:36',NULL,NULL,NULL,1,'React'),(6,'Jamel','gleason.leila@example.com','1995-11-04 13:16:30.0','Tp.HCM','Iusto delectus quos dicta molestiae aut exerc','1980-01-03 15:06:27',NULL,NULL,NULL,2,'React'),(7,'Vita','brown.wilkinson@example.com','1985-07-17 15:49:09.0','Hanoi','Et consectetur aut accusamus ut facilis quod.','1973-10-27 02:10:28',NULL,NULL,NULL,3,'Ruby'),(8,'Nickolas','rsteuber@example.com','1991-09-27 16:18:36.0','Tp.HCM','Nesciunt in natus eius qui. Aspernatur volupt','2007-06-08 02:11:36',NULL,NULL,NULL,1,'Python'),(9,'Burnice','demetris.hane@example.org','1975-03-11 07:12:51.0','Danang','Molestias blanditiis nobis nihil qui et. Sit ','1982-08-06 21:49:07',NULL,NULL,NULL,1,'React'),(10,'Linnea','blick.bernardo@example.org','1998-02-01 22:58:48.0','Tp.HCM','Occaecati itaque voluptatem dolorem voluptatu','2016-08-10 23:47:32',NULL,NULL,NULL,1,'iOS'),(11,'Jazmyne','swiza@example.net','1995-02-09 11:26:27.0','Danang','Voluptatem quia necessitatibus itaque hic arc','1990-02-10 10:14:31',NULL,NULL,NULL,2,'iOS'),(12,'Rickey','marjorie.haley@example.net','2005-04-04 08:34:22.0','Danang','Illum vel id dolores explicabo ipsa aut velit','1974-07-15 15:46:17',NULL,NULL,NULL,1,'Ruby'),(13,'Jamel','schiller.sallie@example.com','1976-10-22 01:21:20.0','Hanoi','Et ad voluptas consequatur sunt. Atque iusto ','1991-04-08 21:01:08',NULL,NULL,NULL,2,'iOS'),(14,'Ronny','zhaag@example.net','1996-09-03 04:49:40.0','Tp.HCM','Quo reiciendis eligendi ratione ut fuga dolor','1992-11-11 13:19:38',NULL,NULL,NULL,2,'Ruby'),(15,'Seamus','werner.dare@example.com','1982-08-14 18:40:25.0','Danang','Quia repellat quae et consequatur ea tenetur.','2008-01-21 16:25:09',NULL,NULL,NULL,1,'Ruby'),(16,'Cindy','twilkinson@example.com','1974-06-12 23:59:12.0','Tp.HCM','Accusantium quia voluptatem quasi laborum vol','1975-04-14 03:10:44',NULL,NULL,NULL,3,'Ruby'),(17,'Casper','herzog.dewitt@example.org','2016-05-27 17:29:23.0','Danang','Molestiae aut deserunt praesentium. Iusto nih','1970-04-07 20:10:44',NULL,NULL,NULL,3,'Ruby'),(18,'Meggie','magali.carroll@example.net','1982-12-25 03:36:16.0','Tp.HCM','Recusandae asperiores qui occaecati ducimus q','1985-07-02 19:06:18',NULL,NULL,NULL,2,'Ruby'),(19,'Joelle','jason.prohaska@example.com','2016-08-28 23:33:28.0','Danang','Ipsum repudiandae eaque qui voluptatum quod. ','1990-08-22 23:31:46',NULL,NULL,NULL,3,'Python'),(20,'Julie','muller.david@example.com','1980-01-08 05:18:16.0','Tp.HCM','Pariatur voluptas consequuntur dolores ut. Ve','1982-11-12 11:46:23',NULL,NULL,NULL,3,'Java'),(21,'Ernestina','whaley@example.net','1993-01-06 11:49:50.0','Hanoi','Sed qui voluptatum nihil ipsam in. Beatae qua','1970-11-10 03:17:56',NULL,NULL,NULL,1,'Java'),(22,'Eriberto','norbert91@example.com','1992-10-26 14:02:25.0','Tp.HCM','At aut aut qui. Non recusandae aut aut quibus','1991-04-16 16:00:42',NULL,NULL,NULL,2,'Java'),(23,'Fausto','ahettinger@example.net','1987-06-10 09:40:59.0','Danang','Ab quia quod aut. Voluptates quia corporis re','1973-12-06 13:08:02',NULL,NULL,NULL,1,'Java'),(24,'Bailey','noah.gerhold@example.net','2014-01-05 11:32:29.0','Tp.HCM','Velit fugit et voluptas id nulla. Explicabo q','2017-11-14 23:37:34',NULL,NULL,NULL,2,'NodeJS'),(25,'Anna','hhaag@example.org','1979-08-10 01:56:39.0','Tp.HCM','Placeat non voluptatem repellat quis voluptas','1998-01-01 23:59:38',NULL,NULL,NULL,3,'Java'),(26,'Annabel','slindgren@example.org','1971-02-03 06:26:18.0','Danang','Nam repellat quo sed qui id aut. Animi ad qui','2011-09-23 14:33:25',NULL,NULL,NULL,3,'React'),(27,'Josiane','stark.rocky@example.net','1982-08-25 05:44:17.0','Hanoi','Aut magnam placeat ad ipsum. Ut fugit autem v','1987-10-14 04:48:48',NULL,NULL,NULL,2,'Python'),(28,'Bailey','wdooley@example.org','1970-02-06 12:35:53.0','Danang','Quaerat assumenda temporibus officia et et ea','2001-07-07 23:34:37',NULL,NULL,NULL,3,'Android'),(29,'Elmira','stanton.sam@example.net','1986-08-17 11:54:17.0','Tp.HCM','Non sed praesentium ratione dolores. Distinct','2010-11-08 08:54:22',NULL,NULL,NULL,3,'React'),(30,'Antone','wuckert.blake@example.net','1993-06-14 06:19:59.0','Tp.HCM','Accusantium non ut et expedita. Id totam ut p','1976-04-11 20:41:54',NULL,NULL,NULL,3,'Go'),(31,'Brielle','jena.west@example.org','1996-12-17 08:41:59.0','Danang','Provident magnam non pariatur et dignissimos ','1987-03-22 06:49:59',NULL,NULL,NULL,3,'React'),(32,'Alexandre','xmcglynn@example.net','2003-03-25 01:05:32.0','Hanoi','Ea cumque qui totam est ut alias qui. Quibusd','2011-12-20 07:19:07',NULL,NULL,NULL,2,'Ruby'),(33,'Dallas','coralie87@example.com','1976-04-28 11:49:50.0','Tp.HCM','Nam delectus totam nobis pariatur. Qui fuga c','2004-03-27 10:52:01',NULL,NULL,NULL,1,'Android'),(34,'Lilly','mohr.sanford@example.net','1996-08-18 09:14:23.0','Hanoi','Praesentium aut aperiam laudantium similique ','1999-04-25 03:17:48',NULL,NULL,NULL,2,'Android'),(35,'Leola','gerda.windler@example.com','1992-04-07 07:23:38.0','Hanoi','Rerum veritatis itaque dicta asperiores. Accu','1974-08-09 13:36:27',NULL,NULL,NULL,1,'iOS'),(36,'Jayne','lockman.alberto@example.com','2014-03-27 03:59:16.0','Hanoi','Deserunt sunt dolor accusamus architecto porr','1982-10-15 18:05:50',NULL,NULL,NULL,1,'Android'),(37,'Maudie','arianna.williamson@example.com','1975-08-26 04:17:10.0','Hanoi','Voluptas quo consectetur ea. Vitae commodi al','1997-05-23 11:18:46',NULL,NULL,NULL,1,'Go'),(38,'Velma','tbecker@example.org','2016-02-20 12:23:43.0','Tp.HCM','Itaque placeat dolorum reprehenderit quia aut','2016-10-15 22:54:05',NULL,NULL,NULL,1,'Android'),(39,'Sarah','emmerich.mckayla@example.net','1972-05-02 07:15:00.0','Tp.HCM','Et voluptatem nihil blanditiis distinctio rep','2000-06-05 09:08:35',NULL,NULL,NULL,1,'Python'),(40,'Jennings','ubaldo51@example.org','1987-07-31 18:17:52.0','Tp.HCM','Animi suscipit enim nam et voluptas qui omnis','1987-04-16 10:25:21',NULL,NULL,NULL,1,'Front-end'),(41,'Barton','kattie.sawayn@example.org','1986-05-10 03:59:59.0','Hanoi','Ab mollitia asperiores unde ratione iste dolo','2004-10-17 15:53:53',NULL,NULL,NULL,1,'Python'),(42,'Alysa','enid.gibson@example.org','1998-05-31 20:50:17.0','Tp.HCM','Explicabo quia deleniti enim. Aut incidunt an','2011-04-18 21:54:18',NULL,NULL,NULL,3,'Go'),(43,'Vallie','runolfsdottir.rafaela@example.net','2013-06-27 02:36:30.0','Danang','Qui quod deserunt rerum voluptatem eligendi m','1979-08-16 01:44:27',NULL,NULL,NULL,1,'Python'),(44,'Asa','rkihn@example.com','1973-05-23 02:07:15.0','Tp.HCM','Velit eum temporibus quod quisquam. Doloremqu','1984-02-08 02:04:05',NULL,NULL,NULL,3,'Go'),(45,'Loy','muller.timothy@example.org','2010-03-25 09:08:09.0','Danang','Cupiditate eos est accusamus est recusandae c','2007-08-18 20:03:31',NULL,NULL,NULL,1,'Android'),(46,'Pascale','lucinda20@example.org','1993-07-09 15:47:32.0','Tp.HCM','Velit repellat voluptatem doloremque fugit te','2018-01-11 13:46:04',NULL,NULL,NULL,2,'NodeJS'),(47,'Akeem','lucius.kuhlman@example.net','1985-06-30 20:30:16.0','Tp.HCM','Aspernatur dolor laudantium porro assumenda s','1978-01-26 18:00:30',NULL,NULL,NULL,3,'Ruby'),(48,'Cristopher','maximo.quitzon@example.net','1996-05-13 00:37:18.0','Hanoi','Amet veritatis libero cupiditate eligendi et ','1987-03-13 06:01:07',NULL,NULL,NULL,1,'Go'),(49,'Linda','michelle57@example.org','1978-11-06 15:25:22.0','Danang','Consequuntur maiores hic accusamus at dolor a','1971-12-15 06:57:20',NULL,NULL,NULL,3,'iOS'),(50,'Dessie','jerome.smitham@example.com','1994-03-21 15:47:17.0','Hanoi','Exercitationem aut nihil quos quas. Quia volu','2016-12-14 07:46:10',NULL,NULL,NULL,2,'Ruby'),(51,'Laila','nwolf@example.org','2014-05-08 23:09:20.0','Hanoi','Amet modi dignissimos amet ratione corrupti r','2005-05-27 06:35:43',NULL,NULL,NULL,3,'Python'),(52,'Rosalia','althea55@example.com','1981-08-20 08:28:32.0','Danang','Voluptatum suscipit eos delectus qui vero dol','2004-09-09 05:49:02',NULL,NULL,NULL,3,'Ruby'),(53,'Talia','ahmad15@example.org','1987-02-25 20:33:20.0','Danang','Voluptatem commodi debitis perferendis nobis ','2011-09-24 12:33:45',NULL,NULL,NULL,1,'Ruby'),(54,'Oma','odietrich@example.org','1987-07-02 16:25:27.0','Hanoi','Sed sequi adipisci pariatur. Dicta rerum nobi','2013-08-13 12:09:20',NULL,NULL,NULL,1,'Ruby'),(55,'Obie','ervin31@example.net','1997-10-18 20:32:18.0','Danang','Tempora quo sunt et quis nihil. Similique ea ','1996-07-09 19:13:54',NULL,NULL,NULL,2,'Ruby'),(56,'Alice','nolan.williamson@example.org','1985-10-17 13:56:21.0','Tp.HCM','Dolores voluptatibus magnam facere consequatu','1970-12-20 20:25:52',NULL,NULL,NULL,1,'iOS'),(57,'Cruz','vmosciski@example.com','1999-03-26 00:00:10.0','Tp.HCM','Unde omnis nisi dolor consequatur quasi. Sed ','1979-07-10 13:25:22',NULL,NULL,NULL,3,'iOS'),(58,'Celine','watson.gottlieb@example.net','2010-05-24 20:22:25.0','Tp.HCM','Laboriosam et id illum. Nam quos voluptas ven','1998-11-09 05:17:23',NULL,NULL,NULL,1,'iOS'),(59,'Graciela','braulio31@example.net','1971-11-11 04:19:12.0','Danang','Et veniam qui tempore labore similique et lab','1988-07-03 02:21:33',NULL,NULL,NULL,3,'iOS'),(60,'Marques','kameron.crooks@example.org','1992-08-12 13:00:12.0','Tp.HCM','Est quis rerum consectetur et. Laboriosam sap','2009-03-04 10:29:20',NULL,NULL,NULL,2,'iOS'),(61,'Blake','nwisozk@example.net','2005-11-02 22:57:31.0','Hanoi','Aspernatur vero asperiores ab porro consequun','1975-08-30 00:29:01',NULL,NULL,NULL,3,'Go'),(62,'Jaren','marian96@example.net','1975-08-06 20:19:14.0','Danang','Non laborum magnam in voluptatem. Non asperna','2008-07-03 15:16:58',NULL,NULL,NULL,3,'Go'),(63,'Scottie','sritchie@example.org','1996-01-20 04:35:43.0','Danang','Voluptatem reiciendis est blanditiis sed. Et ','1976-06-30 04:12:51',NULL,NULL,NULL,1,'Go'),(64,'Orville','ora31@example.com','2013-03-01 21:57:04.0','Hanoi','Vero voluptatem occaecati id odio voluptatem.','1983-10-07 12:27:35',NULL,NULL,NULL,3,'Go'),(65,'Brianne','rodriguez.jovan@example.org','2005-07-25 10:37:54.0','Hanoi','Cupiditate et consequatur mollitia atque nam ','1979-03-10 07:35:03',NULL,NULL,NULL,2,'React'),(66,'Wava','rmann@example.com','1986-01-20 15:27:15.0','Danang','Ea fuga consectetur eos quibusdam omnis. Aut ','1983-11-17 14:13:17',NULL,NULL,NULL,2,'PHP'),(67,'Rachel','dibbert.mariela@example.net','1985-04-12 12:19:35.0','Tp.HCM','Ad nobis quo et assumenda quae. Assumenda ten','1982-04-16 11:08:05',NULL,NULL,NULL,1,'PHP'),(68,'Mollie','ggorczany@example.org','2006-03-09 00:55:46.0','Tp.HCM','Nobis facere ut eius dolor nihil est. Quibusd','1981-11-08 20:31:16',NULL,NULL,NULL,2,'iOS'),(69,'Brendan','eschumm@example.net','2008-07-17 03:11:22.0','Danang','Expedita quas voluptates fugiat consequatur. ','2009-02-12 18:10:31',NULL,NULL,NULL,2,'Ruby'),(70,'Delphine','cordell.wilkinson@example.org','2010-12-08 08:31:43.0','Hanoi','Itaque molestiae voluptates neque et. Sit nec','1973-08-30 17:37:51',NULL,NULL,NULL,2,'Go'),(71,'Greta','wheller@example.org','1996-04-08 18:34:29.0','Hanoi','Atque quibusdam quam qui consequuntur nihil. ','2008-12-14 10:44:51',NULL,NULL,NULL,2,'Front-end'),(72,'Taryn','ireichert@example.org','2016-08-13 13:10:49.0','Danang','Tenetur officiis suscipit qui in repudiandae ','2013-02-04 00:31:24',NULL,NULL,NULL,3,'Go'),(73,'Afton','amina.larson@example.org','2003-05-21 12:27:39.0','Tp.HCM','Corporis sunt repudiandae incidunt distinctio','2005-08-19 17:07:50',NULL,NULL,NULL,1,'Front-end'),(74,'Gudrun','domenick79@example.org','1975-04-11 05:55:06.0','Hanoi','Cupiditate tempore minus quia incidunt aut re','2000-06-28 09:02:05',NULL,NULL,NULL,3,'Go'),(75,'Elouise','oliver39@example.net','1990-06-12 01:44:20.0','Tp.HCM','Dolore eius optio non quas amet reprehenderit','1994-04-03 11:32:54',NULL,NULL,NULL,3,'Ruby'),(76,'Perry','cindy.batz@example.org','2014-01-20 11:47:30.0','Tp.HCM','Veniam cum voluptatem ut ipsum deserunt labor','1981-05-27 20:10:05',NULL,NULL,NULL,2,'Ruby'),(77,'Una','barton.cesar@example.net','2014-02-22 01:18:47.0','Hanoi','Est facilis quam maiores in. Corporis tempora','1979-12-01 00:39:47',NULL,NULL,NULL,1,'Front-end'),(78,'Earlene','abernathy.peggie@example.org','1986-01-22 00:15:12.0','Hanoi','Fugiat labore quisquam minus ipsa. Voluptas v','2004-05-26 10:18:27',NULL,NULL,NULL,3,'Front-end'),(79,'Casimir','fadel.zena@example.net','1974-08-10 20:10:03.0','Tp.HCM','Officiis aut molestiae deserunt quas at nobis','1984-03-31 05:04:20',NULL,NULL,NULL,2,'iOS'),(80,'Vito','kkilback@example.com','2017-08-19 07:58:16.0','Tp.HCM','Error qui temporibus ut corrupti odio ipsam u','1981-01-18 13:20:13',NULL,NULL,NULL,1,'Ruby'),(81,'Nikita','gleichner.alva@example.net','2013-12-25 17:39:27.0','Hanoi','Non quas et et reiciendis repellendus omnis. ','2018-06-08 05:59:41',NULL,NULL,NULL,1,'NodeJS'),(82,'Clarissa','stacey.cremin@example.com','1974-11-18 11:24:34.0','Danang','Et incidunt pariatur aliquid laborum exceptur','2008-05-11 13:21:29',NULL,NULL,NULL,2,'NodeJS'),(83,'Russell','roob.johanna@example.org','2018-08-31 16:27:25.0','Hanoi','Vel ratione eius necessitatibus accusantium s','1976-08-12 14:00:39',NULL,NULL,NULL,1,'Ruby'),(84,'Camren','kvolkman@example.org','1975-05-30 18:46:27.0','Danang','Eius nesciunt et consectetur explicabo error ','2006-03-09 12:30:45',NULL,NULL,NULL,1,'Ruby'),(85,'Kennedy','schimmel.susan@example.org','2013-04-04 13:05:05.0','Hanoi','Placeat quam id ad dolore nesciunt. Quis saep','1999-10-05 23:12:31',NULL,NULL,NULL,2,'Front-end'),(86,'Bella','berta.weissnat@example.org','1984-11-08 14:29:29.0','Hanoi','Sit ipsam voluptates provident qui explicabo ','2015-04-29 06:22:11',NULL,NULL,NULL,2,'Python'),(87,'Ettie','stephanie46@example.net','1981-01-31 19:05:31.0','Hanoi','Quaerat quasi et accusantium architecto ab. R','2000-12-02 01:12:54',NULL,NULL,NULL,1,'Ruby'),(88,'Jack','ceasar.haag@example.com','1973-04-20 15:55:37.0','Tp.HCM','Id ab blanditiis nostrum minus saepe eligendi','2014-08-20 07:58:48',NULL,NULL,NULL,2,'Front-end'),(89,'Darian','mhilll@example.com','1982-02-06 01:17:39.0','Tp.HCM','Iste quia repellendus exercitationem sit laud','1981-04-30 00:53:53',NULL,NULL,NULL,3,'Go'),(90,'Kallie','erin73@example.com','1994-08-13 14:16:38.0','Hanoi','Nihil exercitationem officia quidem voluptate','1995-07-23 08:31:21',NULL,NULL,NULL,1,'NodeJS'),(91,'Una','ospencer@example.org','1971-09-09 14:59:25.0','Danang','Explicabo quia eum et quibusdam. Et quas volu','1988-06-15 21:51:38',NULL,NULL,NULL,3,'Go'),(92,'Chelsey','brook41@example.com','1972-07-31 04:20:32.0','Danang','Eligendi iste doloribus ullam veritatis conse','1978-11-01 13:54:15',NULL,NULL,NULL,2,'React'),(93,'Dylan','gillian64@example.net','1987-09-17 17:52:27.0','Tp.HCM','Dolorem et nemo necessitatibus et ex. Expedit','1989-10-05 09:20:21',NULL,NULL,NULL,1,'Front-end'),(94,'Amely','shields.vladimir@example.com','1995-02-15 02:02:53.0','Tp.HCM','Atque mollitia amet tenetur. Et nostrum volup','1972-10-27 19:51:01',NULL,NULL,NULL,1,'Ruby'),(95,'Briana','peter.rempel@example.com','2001-09-03 03:08:12.0','Tp.HCM','Nisi voluptas cum doloremque quia. Quas iure ','1980-01-14 01:24:57',NULL,NULL,NULL,2,'Front-end'),(96,'Jessyca','rollin.moore@example.net','1976-09-06 21:38:57.0','Danang','Ea expedita odit illo labore. Qui dolor rerum','1984-10-18 10:55:25',NULL,NULL,NULL,2,'Go'),(97,'Aliya','fkoelpin@example.org','1992-07-13 18:38:20.0','Tp.HCM','Est tempora quae et inventore. Laborum hic as','1992-01-02 21:34:43',NULL,NULL,NULL,3,'Python'),(98,'Ebony','marcellus67@example.org','1993-01-08 10:15:36.0','Hanoi','Voluptatem et id dolore debitis odit et. A si','2003-11-02 14:56:35',NULL,NULL,NULL,2,'Front-end'),(99,'Alene','clara52@example.org','1990-09-07 15:31:59.0','Hanoi','Ut non et tenetur. Quae molestiae recusandae ','2006-10-16 18:15:40',NULL,NULL,NULL,1,'iOS'),(100,'Eve','eusebio93@example.org','2005-02-16 04:34:01.0','Danang','Magnam fuga itaque et ut temporibus. Quae con','1988-10-14 07:58:13',NULL,NULL,NULL,2,'Go');
/*!40000 ALTER TABLE `trainees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `gender` enum('MALE','FEMALE') DEFAULT NULL,
  `role` enum('M','SM','EC','TN') DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `encrypted_password` varchar(45) DEFAULT NULL,
  `reset_password_token` varchar(45) DEFAULT NULL,
  `reset_password_sent_at` datetime DEFAULT NULL,
  `remember_created_at` datetime DEFAULT NULL,
  `confirmation_token` varchar(45) DEFAULT NULL,
  `confirmation_at` datetime DEFAULT NULL,
  `confirmation_sent_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=armscii8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'6046','Elroy Streich','1-911-154-2853x951','FEMALE','EC','2008-04-23 19:53:40',NULL,'d212aa4294062a88a1055c2111b3a241eeb25e97',NULL,NULL,NULL,NULL,NULL,NULL),(2,'3416','Earnest Brown MD','(741)518-3418','MALE','EC','2017-04-28 20:07:36',NULL,'0a55dca975117955391f942749d1e0ced1ec6ee5',NULL,NULL,NULL,NULL,NULL,NULL),(3,'7863','Evan Thompson','1-941-409-9380','MALE','SM','1970-06-19 22:11:20',NULL,'d59c42891465a69495e42eebffb62cacb262ffa4',NULL,NULL,NULL,NULL,NULL,NULL),(4,'4663','Nella Rogahn IV','614.206.9929x488','FEMALE','SM','2002-07-01 17:39:39',NULL,'bbcd2fb835ef4126f23bde12fb91914270b36a67',NULL,NULL,NULL,NULL,NULL,NULL),(5,'7402','Petra Bogan','1-131-088-4892x06201','MALE','TN','2002-11-23 03:16:09',NULL,'e87ce5cfaac6fbd253c3b3e7a9767f584e2565bb',NULL,NULL,NULL,NULL,NULL,NULL),(6,'3958','Alanna Botsford','241-364-0270x08741','MALE','SM','1982-12-13 21:16:22',NULL,'f8a9e6a128677c5613359b7ab5aef2c0dabada8f',NULL,NULL,NULL,NULL,NULL,NULL),(7,'9836','Laurine Cremin Sr.','967.459.8149','FEMALE','TN','2006-09-12 23:11:07',NULL,'4af820f85577897d5d49300652d1d181a3add480',NULL,NULL,NULL,NULL,NULL,NULL),(8,'6768','Mr. Willy Sanford','734.648.6758','FEMALE','TN','1995-05-19 04:16:33',NULL,'31ced11cad05885c52f8adbb68afed92fe1ca98e',NULL,NULL,NULL,NULL,NULL,NULL),(9,'4579','Mrs. Lenore Shanahan','286-136-2109','FEMALE','SM','1974-10-27 12:25:50',NULL,'06201612c0989afbc25a6dc777f701ce5a37a54d',NULL,NULL,NULL,NULL,NULL,NULL),(10,'6267','Dr. Fernando Schmitt Jr.','131.353.7561x498','FEMALE','M','1985-06-17 11:44:52',NULL,'3056ccca1b28eb7be230a09473c9886cb0e6f3f1',NULL,NULL,NULL,NULL,NULL,NULL),(11,'9036','Kendrick Heidenreich','1-383-838-8866','FEMALE','SM','1988-12-08 18:36:33',NULL,'672c465c6ffe108666ad266ed29943aa68084ab7',NULL,NULL,NULL,NULL,NULL,NULL),(12,'3686','Neoma Klocko Jr.','1-814-385-7663x9308','MALE','TN','1985-10-12 23:17:50',NULL,'f5d6200e338baf626a852e5cb71f34e2de7e585a',NULL,NULL,NULL,NULL,NULL,NULL),(13,'4590','Rolando Olson','00347351376','MALE','TN','1990-10-02 15:30:34',NULL,'aaf42b2eab9ea388009ca56141b0f57a2f04d8ce',NULL,NULL,NULL,NULL,NULL,NULL),(14,'7174','Clint Harris','1-562-822-6541','MALE','M','2001-01-07 00:26:07',NULL,'5c16a107ef5307c6571e786427c3a2c8b97f24be',NULL,NULL,NULL,NULL,NULL,NULL),(15,'8210','Dr. Winfield Bechtelar V','+22(1)9491211975','FEMALE','EC','1996-11-28 23:13:07',NULL,'403c5eeaa6a52c0718935075f8259bcce05e1250',NULL,NULL,NULL,NULL,NULL,NULL),(16,'7193','Mr. Luther O\'Kon','598-519-2496x93280','MALE','M','2012-12-11 23:52:10',NULL,'3985e79e57d099b1ae9b6792ead274ee002a1e3b',NULL,NULL,NULL,NULL,NULL,NULL),(17,'8435','Leonie Mante','942-733-2812x8269','FEMALE','M','1989-09-01 08:24:13',NULL,'8ad88234e9ee8c1dd565c6c61a8a3400d64c4618',NULL,NULL,NULL,NULL,NULL,NULL),(18,'5180','Dr. Pauline Brekke','651.417.9938x06603','FEMALE','SM','2005-05-25 02:06:19',NULL,'62b955039fb67202574106656ec4e03304228f51',NULL,NULL,NULL,NULL,NULL,NULL),(19,'9947','Westley Senger','080-599-8897','MALE','EC','2008-11-01 18:38:21',NULL,'0f845229c5eaf551c542da3da5362f7af12e7c08',NULL,NULL,NULL,NULL,NULL,NULL),(20,'9976','Margaret Watsica','1-025-384-3814x750','MALE','TN','2009-09-25 22:43:46',NULL,'b70f15bb558116917d40fa6c2b7eef834592ba89',NULL,NULL,NULL,NULL,NULL,NULL),(21,'3738','Buddy Runte','1-306-725-8495x00547','MALE','EC','1986-01-28 18:04:14',NULL,'e700a7e5cbe5e465f8514bb22ced43822c8b881c',NULL,NULL,NULL,NULL,NULL,NULL),(22,'4177','Dr. Colby Ledner PhD','(720)408-0973x818','MALE','EC','1999-06-06 20:55:27',NULL,'38c4a4d008d1dd9303243ce266a6f9ae05686d7b',NULL,NULL,NULL,NULL,NULL,NULL),(23,'7811','Prof. Kamryn Durgan IV','(984)325-2596x003','MALE','SM','1985-07-09 09:39:17',NULL,'8b21942a2297d81209cddbe73fa4ac9cdb429685',NULL,NULL,NULL,NULL,NULL,NULL),(24,'4372','Effie Gutkowski','1-693-176-4996','MALE','SM','2016-02-11 11:10:35',NULL,'e5eb47c3fda63c093943830396a39eed2fe21e19',NULL,NULL,NULL,NULL,NULL,NULL),(25,'8368','Liam Schamberger Sr.','091.358.9606x5376','FEMALE','EC','2010-01-17 08:25:38',NULL,'4323705c119321b3800654ebf6e7ffdfcabed47a',NULL,NULL,NULL,NULL,NULL,NULL),(26,'7938','Reymundo Russel','049.127.1070x30897','MALE','M','1999-10-14 12:37:24',NULL,'b1e8b27493eaf015f71f569bb809d855a02dc143',NULL,NULL,NULL,NULL,NULL,NULL),(27,'9281','Riley Kozey','08946842434','FEMALE','SM','1981-01-16 10:39:27',NULL,'e363f2d2c2de488d390d8ab7b4c3d2cf94d97eee',NULL,NULL,NULL,NULL,NULL,NULL),(28,'4478','Dr. Darryl Miller','(802)236-0787','FEMALE','M','2002-08-11 14:06:12',NULL,'27b7b1d19716b5d2ac98791b13193ea92fcd92cd',NULL,NULL,NULL,NULL,NULL,NULL),(29,'2470','Alice Davis','1-147-533-6498','FEMALE','TN','1992-08-31 01:19:58',NULL,'496c4a99065c7da570e9154f90cc87484b429a9a',NULL,NULL,NULL,NULL,NULL,NULL),(30,'8714','Edyth Schumm DVM','636-712-5856x117','FEMALE','EC','1975-11-26 11:27:34',NULL,'ac689b29b3f0c9afdff29ca03a6f6dd59f160a5f',NULL,NULL,NULL,NULL,NULL,NULL),(31,'1637','Yadira Schamberger','1-974-920-1321x566','FEMALE','EC','1977-01-21 09:28:49',NULL,'a2a61b4948d027bf630461a5cb6ddef65e503e66',NULL,NULL,NULL,NULL,NULL,NULL),(32,'6204','Orpha Barrows','1-770-156-9579x82355','MALE','SM','2014-05-29 06:15:59',NULL,'d021714a2103e7e1f26642c9c28ef3c5658247e9',NULL,NULL,NULL,NULL,NULL,NULL),(33,'4147','Prof. Emil Rempel','01888738805','FEMALE','SM','1978-01-28 17:22:36',NULL,'0549038fe9a030b4fbee2fbe6c3065bad68e3076',NULL,NULL,NULL,NULL,NULL,NULL),(34,'2328','Waldo Macejkovic','995-410-4195x6586','MALE','SM','1984-08-31 03:44:22',NULL,'5fadc2524d4ac883b5b4810249d6162aca3bf5db',NULL,NULL,NULL,NULL,NULL,NULL),(35,'6280','Pierre Terry','441.611.5368','MALE','TN','2015-10-29 02:42:30',NULL,'0261138f1cf610b20f95d3789c37cab6bb5a3b9c',NULL,NULL,NULL,NULL,NULL,NULL),(36,'6942','Cade Bernhard V','935-481-2328','FEMALE','SM','2004-12-06 02:01:41',NULL,'169694c7b7ca6c09649667e78b3d86bebc596d3a',NULL,NULL,NULL,NULL,NULL,NULL),(37,'3964','Dr. Julianne Prohaska III','886-971-0334x421','FEMALE','SM','1999-06-20 14:34:20',NULL,'74614aaec91dd855be197d1e9b117012e1afc771',NULL,NULL,NULL,NULL,NULL,NULL),(38,'8037','Hertha Runte','637-004-4997x0081','MALE','EC','1999-03-21 12:59:10',NULL,'85613d65cac2b139669f65285f59d4066d0b48e9',NULL,NULL,NULL,NULL,NULL,NULL),(39,'2837','Leland Torphy','+69(3)7140453258','FEMALE','EC','2013-05-14 18:44:51',NULL,'727b90b06524dee79af7b97d76d425bea04ea5cf',NULL,NULL,NULL,NULL,NULL,NULL),(40,'5529','Milford Legros V','739-914-9285','MALE','TN','1991-01-10 20:47:42',NULL,'25018b177e0f1e1adb7f19110d8b3538707b1caa',NULL,NULL,NULL,NULL,NULL,NULL),(41,'5911','Ms. Marjory Nitzsche Jr.','377.851.3593','MALE','M','1982-09-28 02:33:23',NULL,'edece62518e4cb9118e8d2ca5b9d0679e2c1a12f',NULL,NULL,NULL,NULL,NULL,NULL),(42,'6755','Orlo Schmeler','817-079-6550x191','FEMALE','SM','1997-03-02 09:20:17',NULL,'11210e5413b9ca2d1267fb03d419f19b01332f07',NULL,NULL,NULL,NULL,NULL,NULL),(43,'7680','Lilian Rolfson DDS','1-978-080-6477x91505','MALE','EC','1975-08-20 03:44:07',NULL,'f45ba2396dc860fe06ea87a9a461d9b0cff9f754',NULL,NULL,NULL,NULL,NULL,NULL),(44,'5599','Ransom Jaskolski MD','(439)767-8051x48542','FEMALE','TN','2003-08-18 02:43:27',NULL,'638db6fcf4f8beafe859a445b3479f63d03c6ef6',NULL,NULL,NULL,NULL,NULL,NULL),(45,'7777','Ms. Billie Crona MD','1-138-767-3900','MALE','SM','2000-12-27 02:09:34',NULL,'68793300ba0aa60354976f3685bc160e62b77c8e',NULL,NULL,NULL,NULL,NULL,NULL),(46,'6161','Evangeline McCullough','(610)619-6252x0032','FEMALE','TN','1985-11-13 20:11:31',NULL,'c8538a7bab5a23e4cbe3173c58df12cc361a8676',NULL,NULL,NULL,NULL,NULL,NULL),(47,'7214','Dr. Raquel Howe','658-593-3375x97979','FEMALE','SM','1983-06-27 09:33:53',NULL,'68348ce72a50e504029d51509e6cc6c75dcd70f9',NULL,NULL,NULL,NULL,NULL,NULL),(48,'4770','Miss Amalia Cartwright','02884955938','MALE','EC','1982-05-13 20:52:26',NULL,'8ded308f2a80e17dd31b494b7eba9846a774d4d0',NULL,NULL,NULL,NULL,NULL,NULL),(49,'2340','Deangelo Maggio','226-744-1297','MALE','M','1991-09-24 12:04:03',NULL,'ed02fa14767aa8459f5d3398df1b71e8daabf3cf',NULL,NULL,NULL,NULL,NULL,NULL),(50,'6138','Miss Trycia Greenfelder V','378-123-3716x609','FEMALE','TN','2014-07-22 18:50:53',NULL,'684bee7aa362f38109bf11b49c36aaf7044b9e8b',NULL,NULL,NULL,NULL,NULL,NULL),(51,'7238','Kristofer Nader','140.941.0100x0236','MALE','SM','1971-08-03 07:50:36',NULL,'10ba34dcf3e2769ad1654ae2564398639574d6ec',NULL,NULL,NULL,NULL,NULL,NULL),(52,'8117','Prof. Burnice Lindgren IV','06985298533','FEMALE','EC','2006-01-16 12:14:08',NULL,'5699b9577532a1b828538d7b21171086a929643b',NULL,NULL,NULL,NULL,NULL,NULL),(53,'3116','Mr. Roger Crist','+32(1)1788876866','MALE','SM','1979-02-14 12:29:01',NULL,'83fdc7411f4f85135e72b924a8e8576ae461a7bf',NULL,NULL,NULL,NULL,NULL,NULL),(54,'2204','Maribel Ferry','+91(6)2772670199','FEMALE','TN','1999-01-11 04:06:44',NULL,'e342abb9e33da3bab9eec8bc0de1dd3585f35723',NULL,NULL,NULL,NULL,NULL,NULL),(55,'8516','Rahsaan Roob Sr.','165-722-5491','FEMALE','SM','1978-09-22 08:44:55',NULL,'c26bdcf03b5cc6d0b1c57ac0366f4c2b338722a1',NULL,NULL,NULL,NULL,NULL,NULL),(56,'4423','Mr. Kirk Kerluke','1-939-740-0801','MALE','M','2012-09-27 19:47:09',NULL,'4e45f2e87fa3e47816981ed8da5e23f720a8677d',NULL,NULL,NULL,NULL,NULL,NULL),(57,'3619','Aracely Hills','968.212.5085','FEMALE','EC','1985-07-24 01:25:22',NULL,'914e816c284cc1133d8397698cc85a24bdeedd1b',NULL,NULL,NULL,NULL,NULL,NULL),(58,'3328','Enoch Maggio','511-378-7975x214','FEMALE','M','2006-04-11 13:09:51',NULL,'74768fc5f43eb703e09382da87af4175c092c068',NULL,NULL,NULL,NULL,NULL,NULL),(59,'6981','Dayna Pfannerstill','+08(6)6401290192','FEMALE','SM','2009-06-04 06:34:54',NULL,'ff5d0b317ed3182d8e5260586199e4c431727fdc',NULL,NULL,NULL,NULL,NULL,NULL),(60,'6090','Jerrold Vandervort','(680)144-0800x46398','FEMALE','M','1989-03-23 17:53:10',NULL,'45367c901ff9fa315c2e6a4a9c0d0b1b9ec270e3',NULL,NULL,NULL,NULL,NULL,NULL),(61,'6979','Dr. Scottie Reinger','(577)836-5484x86123','FEMALE','EC','1970-09-11 16:12:09',NULL,'ec7e341d06cb03b20020ddb425c419cdb38c9b24',NULL,NULL,NULL,NULL,NULL,NULL),(62,'7194','Porter Kris MD','(800)853-4877x14037','MALE','TN','1977-07-24 07:10:02',NULL,'96fe141b7918919edca3d431a9b8123e0623a085',NULL,NULL,NULL,NULL,NULL,NULL),(63,'9755','Miss Pattie Kunde DVM','(345)540-4842','MALE','EC','2003-04-04 19:21:52',NULL,'07297ff1c6fcd9d212cac544cab56c48883be31d',NULL,NULL,NULL,NULL,NULL,NULL),(64,'9657','Frances Barton','624.306.6999','MALE','EC','1991-08-06 04:28:36',NULL,'e0cc6b48d02814f997bba650bbd1ee0e32ba3656',NULL,NULL,NULL,NULL,NULL,NULL),(65,'2730','Alycia Ernser','519-000-5154','FEMALE','TN','1995-01-01 01:52:37',NULL,'bff66745e40bbc276ce9d2e3f420a5bfd5deae21',NULL,NULL,NULL,NULL,NULL,NULL),(66,'8059','Kayleigh Schamberger II','+71(1)6171274507','FEMALE','EC','2008-10-20 12:41:19',NULL,'459038e7e831586a572346134960a4e124917c1b',NULL,NULL,NULL,NULL,NULL,NULL),(67,'9078','Mrs. Eula Schuppe','+42(8)0706183000','MALE','EC','1981-10-12 01:54:23',NULL,'07c37010126326ce6514c715667150b00255464d',NULL,NULL,NULL,NULL,NULL,NULL),(68,'4658','Conner Pagac','+91(9)4371831414','MALE','EC','1988-10-17 17:13:56',NULL,'e81ea9100b02e94864490bc09a951a3f97a7722e',NULL,NULL,NULL,NULL,NULL,NULL),(69,'8452','Marjolaine McLaughlin','(526)698-4149','MALE','M','1980-09-27 23:30:49',NULL,'d036249c7e7613071a7859b4d5a4c4a0c876dd8c',NULL,NULL,NULL,NULL,NULL,NULL),(70,'6970','Sadie Breitenberg','920.225.7097x4461','FEMALE','TN','2015-11-23 03:33:17',NULL,'56b83072faba073fe501908ac280c27ab316c7ea',NULL,NULL,NULL,NULL,NULL,NULL),(71,'5437','Lisandro Heidenreich','428-964-2808x2622','FEMALE','M','1976-08-05 05:18:41',NULL,'83a9ddffe4c6c4dcb5e54b4f1ceea2c1856f5258',NULL,NULL,NULL,NULL,NULL,NULL),(72,'1603','Ms. Christina Wunsch I','267.123.3353x1540','MALE','SM','1977-04-10 19:11:01',NULL,'7a40ef9408dc69fe0994891fd029477bdcd54576',NULL,NULL,NULL,NULL,NULL,NULL),(73,'3418','Dr. Montana Cole','955-758-3145x851','MALE','M','1973-08-28 05:49:38',NULL,'941518153cb84bfcc6d9e6d7b1a5623b5c815ee4',NULL,NULL,NULL,NULL,NULL,NULL),(74,'9516','Carlotta Olson PhD','(535)909-3617','FEMALE','M','2011-09-24 16:35:54',NULL,'d7f3e05526ef66f9f3eedbd2bcffaa4d80b85f55',NULL,NULL,NULL,NULL,NULL,NULL),(75,'4077','Hassan Prosacco','(547)146-1211','FEMALE','M','1977-02-20 11:23:10',NULL,'af82475e579c24ccfbe475a8343a6bbec1ab35e8',NULL,NULL,NULL,NULL,NULL,NULL),(76,'3643','Vada Hickle III','1-686-034-1073','MALE','EC','1991-01-15 18:11:13',NULL,'6c464a0a3ea6cb1818231d774017bcccfa555373',NULL,NULL,NULL,NULL,NULL,NULL),(77,'6486','Nicolas Erdman','241-020-7405x9202','FEMALE','EC','2018-12-01 02:57:12',NULL,'7000a751632a422827b4254c909ae931ae321ef6',NULL,NULL,NULL,NULL,NULL,NULL),(78,'8868','Lenore Bechtelar','899-384-3098x0316','MALE','SM','1972-11-17 08:49:24',NULL,'88339a2f819f516bbc3aa3c94ef09814c2fb009f',NULL,NULL,NULL,NULL,NULL,NULL),(79,'7390','Gordon Lemke','1-034-871-4059x221','FEMALE','TN','2007-03-13 01:59:45',NULL,'16632d803d6f0308588b8c9b3e760a1b798108f6',NULL,NULL,NULL,NULL,NULL,NULL),(80,'5332','Toy Heidenreich IV','061.007.6837x501','FEMALE','M','2008-10-03 09:03:35',NULL,'24736f36a104cb1578af69d9e5daebd90df546d2',NULL,NULL,NULL,NULL,NULL,NULL),(81,'4701','Cassandra Bailey','+45(7)3416202317','MALE','SM','1985-06-10 08:39:56',NULL,'577980f296d83e464f69ed1d6eadc59824cb0983',NULL,NULL,NULL,NULL,NULL,NULL),(82,'7549','Keira McClure','1-540-541-9476','MALE','TN','2000-10-12 14:52:46',NULL,'3f0934e90b6d1e70f5394015157f55117ca9e660',NULL,NULL,NULL,NULL,NULL,NULL),(83,'2073','Providenci Becker','(257)351-0422','MALE','M','2013-08-02 01:18:05',NULL,'746026b1e1cee403c2bc7982323d4837fa406753',NULL,NULL,NULL,NULL,NULL,NULL),(84,'5145','Beaulah Leffler','251.273.1974','FEMALE','EC','1999-06-15 18:20:53',NULL,'ecdc36a1467045a85c8a871b2acc5cdc788d977a',NULL,NULL,NULL,NULL,NULL,NULL),(85,'3945','Krystina Schinner III','(590)481-0519','FEMALE','EC','2013-08-14 13:46:46',NULL,'aeed5c7be8a050e2b595c647df6f261a6153c167',NULL,NULL,NULL,NULL,NULL,NULL),(86,'4420','Mrs. Camila Glover','(756)714-4349','MALE','M','2007-01-28 07:56:12',NULL,'ee3025b1e438bfa9ff59051625786120eac531f2',NULL,NULL,NULL,NULL,NULL,NULL),(87,'5554','Brandt Spinka','(862)741-8797x76049','FEMALE','EC','2004-09-11 10:19:37',NULL,'950dcf0808d988ba961fb866e464018c8ca7ebaf',NULL,NULL,NULL,NULL,NULL,NULL),(88,'3458','Dr. Marilyne Homenick Jr.','227-074-5085x12062','MALE','TN','1984-04-07 16:22:20',NULL,'4b784f58388a9cf4e10f78ecec1e2b24dc518479',NULL,NULL,NULL,NULL,NULL,NULL),(89,'3875','Baby Reichel','172-491-2567','MALE','M','1984-05-29 06:27:32',NULL,'7e2d6e81118a209e3bfad4f77fd0edc84680da58',NULL,NULL,NULL,NULL,NULL,NULL),(90,'3796','Vince Blanda','+69(9)2907292500','MALE','M','1996-10-07 19:05:37',NULL,'422a8b3bf819d0acaa8ac380bd1554fc300e569d',NULL,NULL,NULL,NULL,NULL,NULL),(91,'8606','Hassie Moen Sr.','(586)144-3559','FEMALE','SM','2014-07-05 12:44:44',NULL,'2bfe1f1ec58795638fbab9d2c47a4b9bbad6eca0',NULL,NULL,NULL,NULL,NULL,NULL),(92,'1119','Leonor Hauck','1-520-463-1786','FEMALE','SM','2013-09-15 18:00:37',NULL,'dd5d8ea43f59ed10146993e00c7ef4ebd7e58341',NULL,NULL,NULL,NULL,NULL,NULL),(93,'8254','Brad Windler V','1-852-371-7135','MALE','SM','2013-04-14 10:57:44',NULL,'0b3e33c2aab7a3818935130c62b8272382d295d1',NULL,NULL,NULL,NULL,NULL,NULL),(94,'6308','Jules Welch','1-705-355-2837x544','MALE','M','2010-09-26 13:54:43',NULL,'0863d8aeda9ef740f639f07f814704b9234c4b36',NULL,NULL,NULL,NULL,NULL,NULL),(95,'3735','Ms. Gail Friesen II','1-330-797-9925x2595','MALE','M','2006-11-02 19:19:38',NULL,'fb40ad133b020995f6a1aa32567105c7b9428d75',NULL,NULL,NULL,NULL,NULL,NULL),(96,'6531','Miss Marjory Runolfsson','(706)477-8491','MALE','TN','1971-11-13 11:26:28',NULL,'6c3b27b647704e00bdd3a2f3ebc8ab373f57574a',NULL,NULL,NULL,NULL,NULL,NULL),(97,'3695','Cayla Kuhlman','(177)566-7708','MALE','EC','1998-07-04 11:53:55',NULL,'d2a92bcfa0efa6ebd31cc66fc001d5cc0d733e0e',NULL,NULL,NULL,NULL,NULL,NULL),(98,'7305','Prof. Rozella Hudson Jr.','(220)390-6280x956','MALE','M','2011-06-15 22:40:57',NULL,'092e1f3729b3002e62eda6d4a9c2468dceb3a70e',NULL,NULL,NULL,NULL,NULL,NULL),(99,'6775','Darian Leannon','726-233-2861','MALE','SM','1998-07-19 19:14:48',NULL,'913042659d5ff99833ed7db14a3ff2cacd92728b',NULL,NULL,NULL,NULL,NULL,NULL),(100,'1256','Noelia Kilback','1-067-316-5713x64313','MALE','M','1974-02-10 14:16:32',NULL,'a24c407df45b1fe40f62231558ad43ad28f0513d',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workfollows`
--

DROP TABLE IF EXISTS `workfollows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workfollows` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL,
  `steps` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workfollows`
--

LOCK TABLES `workfollows` WRITE;
/*!40000 ALTER TABLE `workfollows` DISABLE KEYS */;
/*!40000 ALTER TABLE `workfollows` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-13  8:24:03
