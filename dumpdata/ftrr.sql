/*
Navicat MySQL Data Transfer

Source Server         : db
Source Server Version : 100136
Source Host           : localhost:3306
Source Database       : ftrr

Target Server Type    : MYSQL
Target Server Version : 100136
File Encoding         : 65001

Date: 2019-02-25 14:22:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for final_results
-- ----------------------------
DROP TABLE IF EXISTS `final_results`;
CREATE TABLE `final_results` (
  `trainee_for_request_id` int(11) NOT NULL,
  `result` enum('Join DIV','Waiting','Extend','Fail') COLLATE utf8_vietnamese_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`trainee_for_request_id`),
  KEY `fk_final_requests_users_1` (`updated_by`),
  KEY `fk_final_requests_trainee_for_requests_1` (`trainee_for_request_id`) USING BTREE,
  CONSTRAINT `FKao4i7614bsx4ix03hb68wr6om` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_final_requests_trainee_for_requests_1` FOREIGN KEY (`trainee_for_request_id`) REFERENCES `trainee_for_requests` (`id`),
  CONSTRAINT `fk_final_requests_users_1` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
-- Records of final_results
-- ----------------------------
INSERT INTO `final_results` VALUES ('4', 'Join DIV', '2019-01-01 00:00:00', null, null);

-- ----------------------------
-- Table structure for levels
-- ----------------------------
DROP TABLE IF EXISTS `levels`;
CREATE TABLE `levels` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` enum('Bad','Good','Excelent') DEFAULT NULL,
  `created_at` datetime(1) DEFAULT NULL,
  `updated_at` datetime(1) DEFAULT NULL,
  `deleted_at` datetime(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=armscii8;

-- ----------------------------
-- Records of levels
-- ----------------------------
INSERT INTO `levels` VALUES ('1', 'Excelent', '1991-06-24 06:40:44.0', null, null);
INSERT INTO `levels` VALUES ('2', 'Bad', '2009-11-06 00:20:50.0', null, null);
INSERT INTO `levels` VALUES ('3', 'Good', '2009-06-10 23:44:47.0', null, null);

-- ----------------------------
-- Table structure for notifications
-- ----------------------------
DROP TABLE IF EXISTS `notifications`;
CREATE TABLE `notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `readed_at` datetime DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `url_id` int(11) DEFAULT NULL,
  `user_request` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9y21adhxn0ayjhfocscqox7bh` (`user_id`),
  KEY `fk_user_request` (`user_request`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_user_request` FOREIGN KEY (`user_request`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notifications
-- ----------------------------

-- ----------------------------
-- Table structure for plan_resources
-- ----------------------------
DROP TABLE IF EXISTS `plan_resources`;
CREATE TABLE `plan_resources` (
  `final_request_id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `mentor` varchar(255) DEFAULT NULL,
  `trainee_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`final_request_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plan_resources
-- ----------------------------

-- ----------------------------
-- Table structure for requests
-- ----------------------------
DROP TABLE IF EXISTS `requests`;
CREATE TABLE `requests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `division` enum('Division 1','Division 2','Division 3') COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `language` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `status` enum('Waiting','Confirmed','In progress','Interview','Done','Pending','Reject') COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_requests_users_1` (`updated_by`),
  KEY `fk_requests_users_2` (`created_by`),
  CONSTRAINT `FK54q28hp8nd2pk4mxkagxt4rbd` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`),
  CONSTRAINT `FKloup0e0spn9aytpdjfplf1y77` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_requests_users_1` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_requests_users_2` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
-- Records of requests
-- ----------------------------
INSERT INTO `requests` VALUES ('1', 'Division 1', '5', '1998-06-20', 'Go', 'In progress', '15', '1985-09-03 01:41:16', null, null);
INSERT INTO `requests` VALUES ('2', 'Division 1', '1', '1991-01-12', 'NodeJS', 'Waiting', '14', '2013-03-18 20:44:27', null, null);
INSERT INTO `requests` VALUES ('3', 'Division 2', '3', '1979-04-29', 'React', 'Waiting', '76', '2009-01-30 11:23:48', null, null);
INSERT INTO `requests` VALUES ('4', 'Division 2', '3', '2015-02-20', 'Front-end', 'Interview', '73', '2009-02-08 23:06:02', null, null);
INSERT INTO `requests` VALUES ('5', 'Division 3', '3', '1990-01-08', 'Java', 'Done', '49', '1977-05-28 09:28:21', null, null);
INSERT INTO `requests` VALUES ('6', 'Division 1', '2', '2019-02-21', 'Java', 'In progress', '1', '2019-02-21 09:07:42', '1', '2019-02-21 09:07:42');
INSERT INTO `requests` VALUES ('7', 'Division 3', '5', '2018-12-12', 'Ruby', 'In progress', '1', '2019-02-21 09:07:42', '1', '2019-02-21 09:07:42');

-- ----------------------------
-- Table structure for result_interviews
-- ----------------------------
DROP TABLE IF EXISTS `result_interviews`;
CREATE TABLE `result_interviews` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `result_type_id` int(11) DEFAULT NULL,
  `title_interview_id` int(11) DEFAULT NULL,
  `trainee_for_request_id` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKol939k8b8j8ny97lsyuuumnob` (`created_by`),
  KEY `FK235nv0lkbk8utb64ffxk4g0t9` (`result_type_id`),
  KEY `FK3r0rndoi7bm0a3s2xl7bq6uo6` (`title_interview_id`),
  KEY `FKgpnxtqwqewbw85qahm33ecdu5` (`trainee_for_request_id`),
  KEY `FKgb74pr9u658k2eavm62lokjyf` (`updated_by`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of result_interviews
-- ----------------------------

-- ----------------------------
-- Table structure for result_trainings
-- ----------------------------
DROP TABLE IF EXISTS `result_trainings`;
CREATE TABLE `result_trainings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  `results` varchar(255) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `trainee_id` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6b68ymgx6h6osqbia5sdd0uhw` (`created_by`),
  KEY `FKna7hoajqujtalg4ol9rw0s9ea` (`trainee_id`),
  KEY `FKp397nvwy2yu2skp4trqf18103` (`updated_by`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of result_trainings
-- ----------------------------

-- ----------------------------
-- Table structure for result_types
-- ----------------------------
DROP TABLE IF EXISTS `result_types`;
CREATE TABLE `result_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of result_types
-- ----------------------------

-- ----------------------------
-- Table structure for scope_trainings
-- ----------------------------
DROP TABLE IF EXISTS `scope_trainings`;
CREATE TABLE `scope_trainings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `language` enum('JAVA','PHP','RUBY','IOS','FRONTEND','ANDROID') COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `created_at` datetime(1) DEFAULT NULL,
  `updated_at` datetime(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_trainer` (`user_id`),
  CONSTRAINT `fk_trainer` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
-- Records of scope_trainings
-- ----------------------------
INSERT INTO `scope_trainings` VALUES ('1', '2', 'JAVA', null, null);

-- ----------------------------
-- Table structure for steps
-- ----------------------------
DROP TABLE IF EXISTS `steps`;
CREATE TABLE `steps` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of steps
-- ----------------------------

-- ----------------------------
-- Table structure for title_interviews
-- ----------------------------
DROP TABLE IF EXISTS `title_interviews`;
CREATE TABLE `title_interviews` (
  `id` int(11) NOT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of title_interviews
-- ----------------------------

-- ----------------------------
-- Table structure for trainee_for_requests
-- ----------------------------
DROP TABLE IF EXISTS `trainee_for_requests`;
CREATE TABLE `trainee_for_requests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `request_id` int(11) DEFAULT NULL,
  `trainee_id` int(11) DEFAULT NULL,
  `status` enum('Reject interview','Processing interview','Finish interview') COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_trainee_for_requests_requests_1` (`request_id`),
  KEY `fk_trainee_for_requests_users_1` (`updated_by`),
  CONSTRAINT `FKh509qxrnh89i8ulle65d9j1po` FOREIGN KEY (`request_id`) REFERENCES `requests` (`id`),
  CONSTRAINT `FKkstuf1durysvwke22co8u4s72` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_trainee_for_requests_requests_1` FOREIGN KEY (`request_id`) REFERENCES `requests` (`id`),
  CONSTRAINT `fk_trainee_for_requests_users_1` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
-- Records of trainee_for_requests
-- ----------------------------
INSERT INTO `trainee_for_requests` VALUES ('1', '1', '30', 'Processing interview', '2018-12-11 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('2', '1', '37', 'Processing interview', '2018-12-11 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('3', '1', '42', 'Processing interview', '2018-12-11 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('4', '1', '44', 'Finish interview', '2018-12-10 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('5', '1', '48', 'Processing interview', '2018-12-13 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('6', '2', '1', 'Processing interview', '2019-01-19 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('8', '3', '3', 'Processing interview', '2019-01-19 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('9', '3', '4', 'Processing interview', '2019-01-19 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('10', '3', '5', 'Processing interview', '2019-01-19 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('11', '4', '73', 'Finish interview', '2019-01-19 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('12', '4', '77', 'Finish interview', '2019-01-19 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('13', '4', '78', 'Finish interview', '2019-01-19 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('14', '5', '20', 'Processing interview', '2019-01-19 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('15', '5', '21', 'Processing interview', '2019-01-19 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('16', '5', '22', 'Processing interview', '2019-01-19 00:00:00', null, null);

-- ----------------------------
-- Table structure for trainees
-- ----------------------------
DROP TABLE IF EXISTS `trainees`;
CREATE TABLE `trainees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `office` varchar(50) DEFAULT NULL,
  `started_time` datetime DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `level_id` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrm6589y9696whfr319pyjfvj8` (`level_id`),
  KEY `FKew8bf0q134atgw51jy9hj3toi` (`updated_by`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trainees
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `division` enum('Division 1','Division 2','Division 3','Human Development') DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `gender` enum('MALE','FEMALE') DEFAULT NULL,
  `role` enum('Manager','Section Manager','Section Manager Education','Human Resource','Education Coordinator','Trainer') DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `encrypted_password` varchar(100) DEFAULT NULL,
  `reset_password_token` varchar(45) DEFAULT NULL,
  `reset_password_sent_at` datetime DEFAULT NULL,
  `remember_created_at` datetime DEFAULT NULL,
  `confirmation_token` varchar(45) DEFAULT NULL,
  `confirmation_at` datetime DEFAULT NULL,
  `confirmation_sent_at` datetime DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=armscii8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '6046', 'van@gmail.com', 'Elroy Streich', 'Division 1', '1-911-154-2853x951', 'MALE', 'Manager', '2008-04-23 19:53:40', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('2', '3416', 'linh@gmail.com', 'Earnest Brown MD', 'Division 2', '(741)518-3418', 'MALE', 'Section Manager Education', '2017-04-28 20:07:36', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('3', '7863', 'manh@gmail.com', 'Evan Thompson', 'Division 2', '1-941-409-9380', 'MALE', 'Section Manager', '1970-06-19 22:11:20', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('4', '4663', 'tung@gmail.com', 'Nella Rogahn IV', null, '614.206.9929x488', 'FEMALE', 'Section Manager', '2002-07-01 17:39:39', null, 'bbcd2fb835ef4126f23bde12fb91914270b36a67', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('5', '7402', null, 'Petra Bogan', null, '1-131-088-4892x06201', 'MALE', 'Trainer', '2002-11-23 03:16:09', null, 'e87ce5cfaac6fbd253c3b3e7a9767f584e2565bb', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('6', '3958', null, 'Alanna Botsford', null, '241-364-0270x08741', 'MALE', 'Section Manager', '1982-12-13 21:16:22', null, 'f8a9e6a128677c5613359b7ab5aef2c0dabada8f', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('7', '9836', null, 'Laurine Cremin Sr.', null, '967.459.8149', 'FEMALE', 'Trainer', '2006-09-12 23:11:07', null, '4af820f85577897d5d49300652d1d181a3add480', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('8', '6768', null, 'Mr. Willy Sanford', null, '734.648.6758', 'FEMALE', 'Trainer', '1995-05-19 04:16:33', null, '31ced11cad05885c52f8adbb68afed92fe1ca98e', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('9', '4579', null, 'Mrs. Lenore Shanahan', null, '286-136-2109', 'FEMALE', 'Section Manager', '1974-10-27 12:25:50', null, '06201612c0989afbc25a6dc777f701ce5a37a54d', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('10', '6267', null, 'Dr. Fernando Schmitt Jr.', null, '131.353.7561x498', 'FEMALE', 'Manager', '1985-06-17 11:44:52', null, '3056ccca1b28eb7be230a09473c9886cb0e6f3f1', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('11', '9036', null, 'Kendrick Heidenreich', null, '1-383-838-8866', 'FEMALE', 'Section Manager', '1988-12-08 18:36:33', null, '672c465c6ffe108666ad266ed29943aa68084ab7', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('12', '3686', null, 'Neoma Klocko Jr.', null, '1-814-385-7663x9308', 'MALE', 'Trainer', '1985-10-12 23:17:50', null, 'f5d6200e338baf626a852e5cb71f34e2de7e585a', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('13', '4590', null, 'Rolando Olson', null, '00347351376', 'MALE', 'Trainer', '1990-10-02 15:30:34', null, 'aaf42b2eab9ea388009ca56141b0f57a2f04d8ce', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('14', '7174', null, 'Clint Harris', null, '1-562-822-6541', 'MALE', 'Manager', '2001-01-07 00:26:07', null, '5c16a107ef5307c6571e786427c3a2c8b97f24be', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('15', '8210', null, 'Dr. Winfield Bechtelar V', null, '+22(1)9491211975', 'FEMALE', 'Education Coordinator', '1996-11-28 23:13:07', null, '403c5eeaa6a52c0718935075f8259bcce05e1250', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('16', '7193', null, 'Mr. Luther O\'Kon', null, '598-519-2496x93280', 'MALE', 'Manager', '2012-12-11 23:52:10', null, '3985e79e57d099b1ae9b6792ead274ee002a1e3b', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('17', '8435', null, 'Leonie Mante', null, '942-733-2812x8269', 'FEMALE', 'Manager', '1989-09-01 08:24:13', null, '8ad88234e9ee8c1dd565c6c61a8a3400d64c4618', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('18', '5180', null, 'Dr. Pauline Brekke', null, '651.417.9938x06603', 'FEMALE', 'Section Manager', '2005-05-25 02:06:19', null, '62b955039fb67202574106656ec4e03304228f51', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('19', '9947', null, 'Westley Senger', null, '080-599-8897', 'MALE', 'Education Coordinator', '2008-11-01 18:38:21', null, '0f845229c5eaf551c542da3da5362f7af12e7c08', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('20', '9976', null, 'Margaret Watsica', null, '1-025-384-3814x750', 'MALE', 'Trainer', '2009-09-25 22:43:46', null, 'b70f15bb558116917d40fa6c2b7eef834592ba89', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('21', '3738', null, 'Buddy Runte', null, '1-306-725-8495x00547', 'MALE', 'Education Coordinator', '1986-01-28 18:04:14', null, 'e700a7e5cbe5e465f8514bb22ced43822c8b881c', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('22', '4177', null, 'Dr. Colby Ledner PhD', null, '(720)408-0973x818', 'MALE', 'Education Coordinator', '1999-06-06 20:55:27', null, '38c4a4d008d1dd9303243ce266a6f9ae05686d7b', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('23', '7811', null, 'Prof. Kamryn Durgan IV', null, '(984)325-2596x003', 'MALE', 'Section Manager', '1985-07-09 09:39:17', null, '8b21942a2297d81209cddbe73fa4ac9cdb429685', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('24', '4372', null, 'Effie Gutkowski', null, '1-693-176-4996', 'MALE', 'Section Manager', '2016-02-11 11:10:35', null, 'e5eb47c3fda63c093943830396a39eed2fe21e19', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('25', '8368', null, 'Liam Schamberger Sr.', null, '091.358.9606x5376', 'FEMALE', 'Education Coordinator', '2010-01-17 08:25:38', null, '4323705c119321b3800654ebf6e7ffdfcabed47a', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('26', '7938', null, 'Reymundo Russel', null, '049.127.1070x30897', 'MALE', 'Manager', '1999-10-14 12:37:24', null, 'b1e8b27493eaf015f71f569bb809d855a02dc143', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('27', '9281', null, 'Riley Kozey', null, '08946842434', 'FEMALE', 'Section Manager', '1981-01-16 10:39:27', null, 'e363f2d2c2de488d390d8ab7b4c3d2cf94d97eee', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('28', '4478', null, 'Dr. Darryl Miller', null, '(802)236-0787', 'FEMALE', 'Manager', '2002-08-11 14:06:12', null, '27b7b1d19716b5d2ac98791b13193ea92fcd92cd', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('29', '2470', null, 'Alice Davis', null, '1-147-533-6498', 'FEMALE', 'Trainer', '1992-08-31 01:19:58', null, '496c4a99065c7da570e9154f90cc87484b429a9a', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('30', '8714', null, 'Edyth Schumm DVM', null, '636-712-5856x117', 'FEMALE', 'Education Coordinator', '1975-11-26 11:27:34', null, 'ac689b29b3f0c9afdff29ca03a6f6dd59f160a5f', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('31', '1637', null, 'Yadira Schamberger', null, '1-974-920-1321x566', 'FEMALE', 'Education Coordinator', '1977-01-21 09:28:49', null, 'a2a61b4948d027bf630461a5cb6ddef65e503e66', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('32', '6204', null, 'Orpha Barrows', null, '1-770-156-9579x82355', 'MALE', 'Section Manager', '2014-05-29 06:15:59', null, 'd021714a2103e7e1f26642c9c28ef3c5658247e9', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('33', '4147', null, 'Prof. Emil Rempel', null, '01888738805', 'FEMALE', 'Section Manager', '1978-01-28 17:22:36', null, '0549038fe9a030b4fbee2fbe6c3065bad68e3076', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('34', '2328', null, 'Waldo Macejkovic', null, '995-410-4195x6586', 'MALE', 'Section Manager', '1984-08-31 03:44:22', null, '5fadc2524d4ac883b5b4810249d6162aca3bf5db', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('35', '6280', null, 'Pierre Terry', null, '441.611.5368', 'MALE', 'Trainer', '2015-10-29 02:42:30', null, '0261138f1cf610b20f95d3789c37cab6bb5a3b9c', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('36', '6942', null, 'Cade Bernhard V', null, '935-481-2328', 'FEMALE', 'Section Manager', '2004-12-06 02:01:41', null, '169694c7b7ca6c09649667e78b3d86bebc596d3a', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('37', '3964', null, 'Dr. Julianne Prohaska III', null, '886-971-0334x421', 'FEMALE', 'Section Manager', '1999-06-20 14:34:20', null, '74614aaec91dd855be197d1e9b117012e1afc771', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('38', '8037', null, 'Hertha Runte', null, '637-004-4997x0081', 'MALE', 'Education Coordinator', '1999-03-21 12:59:10', null, '85613d65cac2b139669f65285f59d4066d0b48e9', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('39', '2837', null, 'Leland Torphy', null, '+69(3)7140453258', 'FEMALE', 'Education Coordinator', '2013-05-14 18:44:51', null, '727b90b06524dee79af7b97d76d425bea04ea5cf', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('40', '5529', null, 'Milford Legros V', null, '739-914-9285', 'MALE', 'Trainer', '1991-01-10 20:47:42', null, '25018b177e0f1e1adb7f19110d8b3538707b1caa', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('41', '5911', null, 'Ms. Marjory Nitzsche Jr.', null, '377.851.3593', 'MALE', 'Manager', '1982-09-28 02:33:23', null, 'edece62518e4cb9118e8d2ca5b9d0679e2c1a12f', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('42', '6755', null, 'Orlo Schmeler', null, '817-079-6550x191', 'FEMALE', 'Section Manager', '1997-03-02 09:20:17', null, '11210e5413b9ca2d1267fb03d419f19b01332f07', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('43', '7680', null, 'Lilian Rolfson DDS', null, '1-978-080-6477x91505', 'MALE', 'Education Coordinator', '1975-08-20 03:44:07', null, 'f45ba2396dc860fe06ea87a9a461d9b0cff9f754', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('44', '5599', null, 'Ransom Jaskolski MD', null, '(439)767-8051x48542', 'FEMALE', 'Trainer', '2003-08-18 02:43:27', null, '638db6fcf4f8beafe859a445b3479f63d03c6ef6', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('45', '7777', null, 'Ms. Billie Crona MD', null, '1-138-767-3900', 'MALE', 'Section Manager', '2000-12-27 02:09:34', null, '68793300ba0aa60354976f3685bc160e62b77c8e', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('46', '6161', null, 'Evangeline McCullough', null, '(610)619-6252x0032', 'FEMALE', 'Section Manager', '1985-11-13 20:11:31', null, 'c8538a7bab5a23e4cbe3173c58df12cc361a8676', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('47', '7214', null, 'Dr. Raquel Howe', null, '658-593-3375x97979', 'FEMALE', 'Section Manager', '1983-06-27 09:33:53', null, '68348ce72a50e504029d51509e6cc6c75dcd70f9', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('48', '4770', null, 'Miss Amalia Cartwright', null, '02884955938', 'MALE', 'Section Manager', '1982-05-13 20:52:26', null, '8ded308f2a80e17dd31b494b7eba9846a774d4d0', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('49', '2340', null, 'Deangelo Maggio', null, '226-744-1297', 'MALE', 'Manager', '1991-09-24 12:04:03', null, 'ed02fa14767aa8459f5d3398df1b71e8daabf3cf', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('50', '6138', null, 'Miss Trycia Greenfelder V', null, '378-123-3716x609', 'FEMALE', 'Trainer', '2014-07-22 18:50:53', null, '684bee7aa362f38109bf11b49c36aaf7044b9e8b', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('51', '7238', null, 'Kristofer Nader', null, '140.941.0100x0236', 'MALE', 'Section Manager', '1971-08-03 07:50:36', null, '10ba34dcf3e2769ad1654ae2564398639574d6ec', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('52', '8117', null, 'Prof. Burnice Lindgren IV', null, '06985298533', 'FEMALE', 'Education Coordinator', '2006-01-16 12:14:08', null, '5699b9577532a1b828538d7b21171086a929643b', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('53', '3116', null, 'Mr. Roger Crist', null, '+32(1)1788876866', 'MALE', 'Section Manager', '1979-02-14 12:29:01', null, '83fdc7411f4f85135e72b924a8e8576ae461a7bf', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('54', '2204', null, 'Maribel Ferry', null, '+91(6)2772670199', 'FEMALE', 'Trainer', '1999-01-11 04:06:44', null, 'e342abb9e33da3bab9eec8bc0de1dd3585f35723', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('55', '8516', null, 'Rahsaan Roob Sr.', null, '165-722-5491', 'FEMALE', 'Section Manager', '1978-09-22 08:44:55', null, 'c26bdcf03b5cc6d0b1c57ac0366f4c2b338722a1', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('56', '4423', null, 'Mr. Kirk Kerluke', null, '1-939-740-0801', 'MALE', 'Human Resource', '2012-09-27 19:47:09', null, '4e45f2e87fa3e47816981ed8da5e23f720a8677d', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('57', '3619', null, 'Aracely Hills', null, '968.212.5085', 'FEMALE', 'Human Resource', '1985-07-24 01:25:22', null, '914e816c284cc1133d8397698cc85a24bdeedd1b', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('58', '3328', null, 'Enoch Maggio', null, '511-378-7975x214', 'FEMALE', 'Manager', '2006-04-11 13:09:51', null, '74768fc5f43eb703e09382da87af4175c092c068', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('59', '6981', null, 'Dayna Pfannerstill', null, '+08(6)6401290192', 'FEMALE', 'Section Manager', '2009-06-04 06:34:54', null, 'ff5d0b317ed3182d8e5260586199e4c431727fdc', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('60', '6090', null, 'Jerrold Vandervort', null, '(680)144-0800x46398', 'FEMALE', 'Manager', '1989-03-23 17:53:10', null, '45367c901ff9fa315c2e6a4a9c0d0b1b9ec270e3', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('61', '6979', null, 'Dr. Scottie Reinger', null, '(577)836-5484x86123', 'FEMALE', 'Human Resource', '1970-09-11 16:12:09', null, 'ec7e341d06cb03b20020ddb425c419cdb38c9b24', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('62', '7194', null, 'Porter Kris MD', null, '(800)853-4877x14037', 'MALE', 'Trainer', '1977-07-24 07:10:02', null, '96fe141b7918919edca3d431a9b8123e0623a085', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('63', '9755', null, 'Miss Pattie Kunde DVM', null, '(345)540-4842', 'MALE', 'Education Coordinator', '2003-04-04 19:21:52', null, '07297ff1c6fcd9d212cac544cab56c48883be31d', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('64', '9657', null, 'Frances Barton', null, '624.306.6999', 'MALE', 'Education Coordinator', '1991-08-06 04:28:36', null, 'e0cc6b48d02814f997bba650bbd1ee0e32ba3656', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('65', '2730', null, 'Alycia Ernser', null, '519-000-5154', 'FEMALE', 'Trainer', '1995-01-01 01:52:37', null, 'bff66745e40bbc276ce9d2e3f420a5bfd5deae21', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('66', '8059', null, 'Kayleigh Schamberger II', null, '+71(1)6171274507', 'FEMALE', 'Human Resource', '2008-10-20 12:41:19', null, '459038e7e831586a572346134960a4e124917c1b', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('67', '9078', null, 'Mrs. Eula Schuppe', null, '+42(8)0706183000', 'MALE', 'Human Resource', '1981-10-12 01:54:23', null, '07c37010126326ce6514c715667150b00255464d', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('68', '4658', null, 'Conner Pagac', null, '+91(9)4371831414', 'MALE', 'Human Resource', '1988-10-17 17:13:56', null, 'e81ea9100b02e94864490bc09a951a3f97a7722e', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('69', '8452', null, 'Marjolaine McLaughlin', null, '(526)698-4149', 'MALE', 'Manager', '1980-09-27 23:30:49', null, 'd036249c7e7613071a7859b4d5a4c4a0c876dd8c', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('70', '6970', null, 'Sadie Breitenberg', null, '920.225.7097x4461', 'FEMALE', 'Trainer', '2015-11-23 03:33:17', null, '56b83072faba073fe501908ac280c27ab316c7ea', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('71', '5437', null, 'Lisandro Heidenreich', null, '428-964-2808x2622', 'FEMALE', 'Section Manager', '1976-08-05 05:18:41', null, '83a9ddffe4c6c4dcb5e54b4f1ceea2c1856f5258', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('72', '1603', null, 'Ms. Christina Wunsch I', null, '267.123.3353x1540', 'MALE', 'Section Manager', '1977-04-10 19:11:01', null, '7a40ef9408dc69fe0994891fd029477bdcd54576', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('73', '3418', null, 'Dr. Montana Cole', null, '955-758-3145x851', 'MALE', 'Section Manager', '1973-08-28 05:49:38', null, '941518153cb84bfcc6d9e6d7b1a5623b5c815ee4', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('74', '9516', null, 'Carlotta Olson PhD', null, '(535)909-3617', 'FEMALE', 'Section Manager', '2011-09-24 16:35:54', null, 'd7f3e05526ef66f9f3eedbd2bcffaa4d80b85f55', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('75', '4077', null, 'Hassan Prosacco', null, '(547)146-1211', 'FEMALE', 'Human Resource', '1977-02-20 11:23:10', null, 'af82475e579c24ccfbe475a8343a6bbec1ab35e8', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('76', '3643', null, 'Vada Hickle III', null, '1-686-034-1073', 'MALE', 'Human Resource', '1991-01-15 18:11:13', null, '6c464a0a3ea6cb1818231d774017bcccfa555373', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('77', '6486', null, 'Nicolas Erdman', null, '241-020-7405x9202', 'FEMALE', 'Human Resource', '2018-12-01 02:57:12', null, '7000a751632a422827b4254c909ae931ae321ef6', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('78', '8868', null, 'Lenore Bechtelar', null, '899-384-3098x0316', 'MALE', 'Human Resource', '1972-11-17 08:49:24', null, '88339a2f819f516bbc3aa3c94ef09814c2fb009f', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('79', '7390', null, 'Gordon Lemke', null, '1-034-871-4059x221', 'FEMALE', 'Human Resource', '2007-03-13 01:59:45', null, '16632d803d6f0308588b8c9b3e760a1b798108f6', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('80', '5332', null, 'Toy Heidenreich IV', null, '061.007.6837x501', 'FEMALE', 'Human Resource', '2008-10-03 09:03:35', null, '24736f36a104cb1578af69d9e5daebd90df546d2', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('81', '4701', null, 'Cassandra Bailey', null, '+45(7)3416202317', 'MALE', 'Human Resource', '1985-06-10 08:39:56', null, '577980f296d83e464f69ed1d6eadc59824cb0983', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('82', '7549', null, 'Keira McClure', null, '1-540-541-9476', 'MALE', 'Human Resource', '2000-10-12 14:52:46', null, '3f0934e90b6d1e70f5394015157f55117ca9e660', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('83', '2073', null, 'Providenci Becker', null, '(257)351-0422', 'MALE', 'Human Resource', '2013-08-02 01:18:05', null, '746026b1e1cee403c2bc7982323d4837fa406753', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('84', '5145', null, 'Beaulah Leffler', null, '251.273.1974', 'FEMALE', 'Human Resource', '1999-06-15 18:20:53', null, 'ecdc36a1467045a85c8a871b2acc5cdc788d977a', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('85', '3945', null, 'Krystina Schinner III', null, '(590)481-0519', 'FEMALE', 'Human Resource', '2013-08-14 13:46:46', null, 'aeed5c7be8a050e2b595c647df6f261a6153c167', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('86', '4420', null, 'Mrs. Camila Glover', null, '(756)714-4349', 'MALE', 'Human Resource', '2007-01-28 07:56:12', null, 'ee3025b1e438bfa9ff59051625786120eac531f2', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('87', '5554', null, 'Brandt Spinka', null, '(862)741-8797x76049', 'FEMALE', 'Trainer', '2004-09-11 10:19:37', null, '950dcf0808d988ba961fb866e464018c8ca7ebaf', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('88', '3458', null, 'Dr. Marilyne Homenick Jr.', null, '227-074-5085x12062', 'MALE', 'Trainer', '1984-04-07 16:22:20', null, '4b784f58388a9cf4e10f78ecec1e2b24dc518479', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('89', '3875', null, 'Baby Reichel', null, '172-491-2567', 'MALE', 'Trainer', '1984-05-29 06:27:32', null, '7e2d6e81118a209e3bfad4f77fd0edc84680da58', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('90', '3796', null, 'Vince Blanda', null, '+69(9)2907292500', 'MALE', 'Trainer', '1996-10-07 19:05:37', null, '422a8b3bf819d0acaa8ac380bd1554fc300e569d', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('91', '8606', null, 'Hassie Moen Sr.', null, '(586)144-3559', 'FEMALE', 'Trainer', '2014-07-05 12:44:44', null, '2bfe1f1ec58795638fbab9d2c47a4b9bbad6eca0', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('92', '1119', null, 'Leonor Hauck', null, '1-520-463-1786', 'FEMALE', 'Trainer', '2013-09-15 18:00:37', null, 'dd5d8ea43f59ed10146993e00c7ef4ebd7e58341', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('93', '8254', null, 'Brad Windler V', null, '1-852-371-7135', 'MALE', 'Trainer', '2013-04-14 10:57:44', null, '0b3e33c2aab7a3818935130c62b8272382d295d1', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('94', '6308', null, 'Jules Welch', null, '1-705-355-2837x544', 'MALE', 'Trainer', '2010-09-26 13:54:43', null, '0863d8aeda9ef740f639f07f814704b9234c4b36', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('95', '3735', null, 'Ms. Gail Friesen II', null, '1-330-797-9925x2595', 'MALE', 'Trainer', '2006-11-02 19:19:38', null, 'fb40ad133b020995f6a1aa32567105c7b9428d75', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('96', '6531', null, 'Miss Marjory Runolfsson', null, '(706)477-8491', 'MALE', 'Trainer', '1971-11-13 11:26:28', null, '6c3b27b647704e00bdd3a2f3ebc8ab373f57574a', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('97', '3695', null, 'Cayla Kuhlman', null, '(177)566-7708', 'MALE', 'Trainer', '1998-07-04 11:53:55', null, 'd2a92bcfa0efa6ebd31cc66fc001d5cc0d733e0e', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('98', '7305', null, 'Prof. Rozella Hudson Jr.', null, '(220)390-6280x956', 'MALE', 'Trainer', '2011-06-15 22:40:57', null, '092e1f3729b3002e62eda6d4a9c2468dceb3a70e', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('99', '6775', null, 'Darian Leannon', null, '726-233-2861', 'MALE', 'Trainer', '1998-07-19 19:14:48', null, '913042659d5ff99833ed7db14a3ff2cacd92728b', null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('100', '1256', null, 'Noelia Kilback', null, '1-067-316-5713x64313', 'MALE', 'Trainer', '1974-02-10 14:16:32', null, 'a24c407df45b1fe40f62231558ad43ad28f0513d', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for workfollows
-- ----------------------------
DROP TABLE IF EXISTS `workfollows`;
CREATE TABLE `workfollows` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `steps` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workfollows
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
