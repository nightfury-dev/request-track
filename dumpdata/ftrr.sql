/*
Navicat MySQL Data Transfer

Source Server         : db
Source Server Version : 100136
Source Host           : localhost:3306
Source Database       : ftrr

Target Server Type    : MYSQL
Target Server Version : 100136
File Encoding         : 65001

Date: 2019-03-20 11:18:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for final_results
-- ----------------------------
DROP TABLE IF EXISTS `final_results`;
CREATE TABLE `final_results` (
  `id` int(11) NOT NULL,
  `result` enum('1','2','3','4') COLLATE utf8_vietnamese_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_final_requests_users_1` (`updated_by`),
  KEY `fk_final_requests_trainee_for_requests_1` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
-- Records of final_results
-- ----------------------------
INSERT INTO `final_results` VALUES ('4', '2', '2019-01-01 00:00:00', null, null);

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
  `read_at` datetime DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
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
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `mentor` varchar(255) DEFAULT NULL,
  `trainee_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
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
  `division` enum('1','2','3') COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `language` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
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
INSERT INTO `requests` VALUES ('1', '1', '5', '1998-06-20', 'Go', '3', '2', '1985-09-03 01:41:16', null, null);
INSERT INTO `requests` VALUES ('2', '2', '1', '1991-01-12', 'NodeJS', '2', '2', '2013-03-18 20:44:27', '2', '2019-02-26 16:02:07');
INSERT INTO `requests` VALUES ('3', '2', '3', '1979-04-29', 'React', '1', '10', '2009-01-30 11:23:48', null, null);
INSERT INTO `requests` VALUES ('4', '2', '3', '2015-02-20', 'Front-end', '4', '10', '2009-02-08 23:06:02', null, '2013-03-18 20:44:27');
INSERT INTO `requests` VALUES ('5', '3', '3', '1990-01-08', 'Java', '5', '11', '1977-05-28 09:28:21', '1', '2013-03-18 20:44:27');
INSERT INTO `requests` VALUES ('6', '2', '2', '2019-02-21', 'Java', '3', '2', '2019-02-21 09:07:42', '1', '2019-02-21 09:07:42');
INSERT INTO `requests` VALUES ('7', '1', '5', '2018-12-12', 'Ruby', '3', '11', '2019-02-21 09:07:42', '1', '2019-02-21 09:07:42');

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
  `result` varchar(255) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `trainee_id` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6b68ymgx6h6osqbia5sdd0uhw` (`created_by`),
  KEY `FKna7hoajqujtalg4ol9rw0s9ea` (`trainee_id`),
  KEY `FKp397nvwy2yu2skp4trqf18103` (`updated_by`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of result_trainings
-- ----------------------------
INSERT INTO `result_trainings` VALUES ('1', 'abc', '2019-03-01 09:34:25', '12', '3da', '1', '3', '2');
INSERT INTO `result_trainings` VALUES ('10', 'abc', '2019-03-07 10:41:53', '12', '3da', '13', '2', '13');
INSERT INTO `result_trainings` VALUES ('11', 'abc', '2019-03-07 15:02:51', '12', null, '13', '2', '13');
INSERT INTO `result_trainings` VALUES ('24', null, '2019-03-12 13:40:38', '34', 'awerwe', '13', '7', '13');
INSERT INTO `result_trainings` VALUES ('25', null, '2019-03-12 13:40:38', '23', 'werewr', '13', '8', '13');
INSERT INTO `result_trainings` VALUES ('26', null, '2019-03-12 13:40:39', '43', 'werewr', '13', '9', '13');
INSERT INTO `result_trainings` VALUES ('27', null, '2019-03-12 13:43:31', '34', 'awerwe', '13', '10', '13');
INSERT INTO `result_trainings` VALUES ('28', null, '2019-03-12 13:43:31', '23', 'werewr', '13', '11', '13');
INSERT INTO `result_trainings` VALUES ('29', null, '2019-03-12 13:43:31', '43', 'werewr', '13', '12', '13');

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
INSERT INTO `scope_trainings` VALUES ('1', '13', 'JAVA', null, null);

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
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of steps
-- ----------------------------
INSERT INTO `steps` VALUES ('1', '2019-03-13 09:08:58', null, 'sdf', '123', '2019-03-13 09:08:58');
INSERT INTO `steps` VALUES ('2', '2019-03-13 09:09:31', null, 'sdf', '123', '2019-03-13 09:09:31');

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
  `status` int(11) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
-- Records of trainee_for_requests
-- ----------------------------
INSERT INTO `trainee_for_requests` VALUES ('1', '1', '2', '3', '2018-12-11 00:00:00', '2019-03-06 14:12:40', '1');
INSERT INTO `trainee_for_requests` VALUES ('2', '1', '1', '3', '2018-12-11 00:00:00', '2019-03-06 14:12:40', '2');
INSERT INTO `trainee_for_requests` VALUES ('3', '1', '3', '3', '2018-12-11 00:00:00', '2019-03-06 14:12:40', null);
INSERT INTO `trainee_for_requests` VALUES ('4', '1', '1', '3', '2018-12-10 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('5', '1', '2', '3', '2018-12-13 00:00:00', '2019-03-06 14:12:40', null);
INSERT INTO `trainee_for_requests` VALUES ('6', '2', '3', '2', '2019-01-19 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('8', '3', '3', '3', '2019-01-19 00:00:00', '2019-03-06 14:15:05', '13');
INSERT INTO `trainee_for_requests` VALUES ('9', '3', '1', '3', '2019-01-19 00:00:00', '2019-03-06 14:15:05', '13');
INSERT INTO `trainee_for_requests` VALUES ('10', '3', '2', '3', '2019-01-19 00:00:00', '2019-03-06 14:15:05', '13');
INSERT INTO `trainee_for_requests` VALUES ('11', '4', '3', '3', '2019-01-19 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('12', '4', '1', '3', '2019-01-19 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('13', '4', '2', '3', '2019-01-19 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('14', '5', '3', '2', '2019-01-19 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('15', '5', '2', '2', '2019-01-19 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('16', '5', '2', '2', '2019-01-19 00:00:00', null, null);
INSERT INTO `trainee_for_requests` VALUES ('57', '3', '3', '3', '2019-03-04 14:43:24', '2019-03-06 14:15:05', '13');

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
  `status` int(11) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `level_id` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `trainer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrm6589y9696whfr319pyjfvj8` (`level_id`),
  KEY `FKew8bf0q134atgw51jy9hj3toi` (`updated_by`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trainees
-- ----------------------------
INSERT INTO `trainees` VALUES ('1', '2019-02-27 16:40:58', null, 'doandinhlinh@gmail.com', 'JAVA', 'Linh', 'Toong', null, '0', '2019-02-21 16:41:36', '1', '2', '13');
INSERT INTO `trainees` VALUES ('2', '2019-02-27 16:40:58', null, 'doandinhlinh@gmail.com', 'JAVA', 'Linh', 'Toong', null, '0', '2019-02-21 16:41:36', '1', null, '13');
INSERT INTO `trainees` VALUES ('3', '2019-02-27 16:40:58', null, '123', 'JAVA', 'Linh', 'Toong', null, '0', '2019-02-21 16:41:36', '1', '2', '1');
INSERT INTO `trainees` VALUES ('4', null, null, 'abc@gmail.com', 'JAVA', 'Nguyen Van A', 'abc', '1996-01-11 00:00:00', null, null, null, null, null);
INSERT INTO `trainees` VALUES ('5', null, null, 'Doan Dinh Linh', 'JAVA', 'Nguyen Van B', 'abc', '1996-01-11 00:00:00', null, null, null, null, null);
INSERT INTO `trainees` VALUES ('6', null, null, 'Doan Dinh Linh', 'JAVA', 'Nguyen Van C', 'abc', '1996-01-11 00:00:00', null, null, null, null, null);
INSERT INTO `trainees` VALUES ('7', null, null, 'abc@gmail.com', 'JAVA', 'Nguyen Van A', 'abc', '1996-01-11 00:00:00', null, null, null, null, null);
INSERT INTO `trainees` VALUES ('8', null, null, 'Doan Dinh Linh', 'JAVA', 'Nguyen Van B', 'abc', '1996-01-11 00:00:00', null, null, null, null, null);
INSERT INTO `trainees` VALUES ('9', null, null, 'Doan Dinh Linh', 'JAVA', 'Nguyen Van C', 'abc', '1996-01-11 00:00:00', null, null, null, null, null);
INSERT INTO `trainees` VALUES ('10', null, null, 'abc@gmail.com', 'JAVA', 'Nguyen Van A', 'abc', '1996-01-11 00:00:00', null, null, null, null, null);
INSERT INTO `trainees` VALUES ('11', null, null, 'Doan Dinh Linh', 'JAVA', 'Nguyen Van B', 'abc', '1996-01-11 00:00:00', null, null, null, null, null);
INSERT INTO `trainees` VALUES ('12', null, null, 'Doan Dinh Linh', 'JAVA', 'Nguyen Van C', 'abc', '1996-01-11 00:00:00', null, null, null, null, null);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `division` enum('Division 1','Division 2','Division 3','Human Development Division','BackOffice') DEFAULT 'Human Development Division',
  `phone` varchar(45) DEFAULT NULL,
  `gender` enum('MALE','FEMALE') DEFAULT NULL,
  `role` enum('1','2','3','4','5','6','7') DEFAULT '7',
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=armscii8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '6046', 'ec1@gmail.com', 'Elroy Streich', 'Human Development Division', '1-911-154-2853x951', 'MALE', '', '2008-04-23 19:53:40', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('2', '3416', 'sm11@gmail.com', 'Earnest Brown MD', 'Division 1', '(741)518-3418', 'MALE', '', '2017-04-28 20:07:36', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('3', '7863', 'hr1@gmail.com', 'Evan Thompson', 'BackOffice', '1-941-409-9380', 'MALE', '', '1970-06-19 22:11:20', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('4', '4663', 'hr2@gmail.com', 'Nella Rogahn IV', 'BackOffice', '614.206.9929x488', 'FEMALE', '', '2002-07-01 17:39:39', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('5', '7402', 'hr3@gmail.com', 'Petra Bogan', 'BackOffice', '1-131-088-4892x06201', 'MALE', '', '2002-11-23 03:16:09', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('6', '3958', 'dm1@gmail.com', 'Alanna Botsford', 'Division 1', '241-364-0270x08741', 'MALE', '', '1982-12-13 21:16:22', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('7', '9836', 'dm3@gmail.com', 'Laurine Cremin Sr.', 'Division 3', '967.459.8149', 'FEMALE', '', '2006-09-12 23:11:07', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('8', '6768', 'dm2@gmail.com', 'Mr. Willy Sanford', 'Division 2', '734.648.6758', 'FEMALE', '', '1995-05-19 04:16:33', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('9', '4579', 'sm1@gmail.com', 'Mrs. Lenore Shanahan', 'Division 1', '286-136-2109', 'FEMALE', '', '1974-10-27 12:25:50', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('10', '6267', 'sm2@gmail.com', 'Dr. Fernando Schmitt Jr.', 'Division 2', '131.353.7561x498', 'FEMALE', '', '1985-06-17 11:44:52', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('11', '9036', 'sm3@gmail.com', 'Kendrick Heidenreich', 'Division 3', '1-383-838-8866', 'FEMALE', '', '1988-12-08 18:36:33', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('12', '3686', 'sm@gmail.com', 'Neoma Klocko Jr.', 'Human Development Division', '1-814-385-7663x9308', 'MALE', '', '1985-10-12 23:17:50', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('13', '4590', 'trainer1@gmail.com', 'Doan Dinh Linh', 'Human Development Division', '00347351376', 'MALE', '5', '1990-10-02 15:30:34', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('14', '7174', 'trainer2@gmail.com', 'Clint Harris', 'Human Development Division', '1-562-822-6541', 'MALE', '', '2001-01-07 00:26:07', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('15', '8210', 'trainer3@gmail.com', 'Dr. Winfield Bechtelar V', 'Human Development Division', '+22(1)9491211975', 'FEMALE', '', '1996-11-28 23:13:07', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('16', '7193', 'trainer4@gmail.com', 'Mr. Luther O\'Kon', 'Human Development Division', '598-519-2496x93280', 'MALE', '', '2012-12-11 23:52:10', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('17', '8435', 'trainer5@gmail.com', 'Leonie Mante', 'Human Development Division', '942-733-2812x8269', 'FEMALE', '', '1989-09-01 08:24:13', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('18', '5180', 'trainer6@gmail.com', 'Dr. Pauline Brekke', 'Human Development Division', '651.417.9938x06603', 'FEMALE', '', '2005-05-25 02:06:19', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('19', '9947', 'gl1@gmail.com', 'Westley Senger', 'Division 1', '080-599-8897', 'MALE', '', '2008-11-01 18:38:21', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('20', '9976', 'gl2@gmail.com', 'Margaret Watsica', 'Division 1', '1-025-384-3814x750', 'MALE', '', '2009-09-25 22:43:46', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('21', '3738', 'gl3@gmail.com', 'Buddy Runte', 'Division 1', '1-306-725-8495x00547', 'MALE', '', '1986-01-28 18:04:14', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('22', '4177', 'gl4@gmail.com', 'Dr. Colby Ledner PhD', 'Division 1', '(720)408-0973x818', 'MALE', '', '1999-06-06 20:55:27', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('23', '7811', 'gl5@gmail.com', 'Prof. Kamryn Durgan IV', 'Division 1', '(984)325-2596x003', 'MALE', '', '1985-07-09 09:39:17', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('24', '4372', 'gl6@gmail.com', 'Effie Gutkowski', 'Division 2', '1-693-176-4996', 'MALE', '', '2016-02-11 11:10:35', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('25', '8368', 'gl7@gmail.com', 'Liam Schamberger Sr.', 'Division 2', '091.358.9606x5376', 'FEMALE', '', '2010-01-17 08:25:38', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('26', '7938', 'gl8@gmail.com', 'Reymundo Russel', 'Division 2', '049.127.1070x30897', 'MALE', '', '1999-10-14 12:37:24', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('27', '9281', 'gl9@gmail.com', 'Riley Kozey', 'Division 3', '08946842434', 'FEMALE', '', '1981-01-16 10:39:27', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');

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
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workfollows
-- ----------------------------
INSERT INTO `workfollows` VALUES ('1', null, null, 'dasd', 'asdsa', '1', '[1,2]', '2019-03-15 11:13:18');
INSERT INTO `workfollows` VALUES ('2', null, null, 'dasd', 'asdsa', '1', '[1,2,3]', null);
INSERT INTO `workfollows` VALUES ('3', null, null, 'dasd', 'asdsa', '1', '[1,2,3]', null);
INSERT INTO `workfollows` VALUES ('4', null, null, 'dasd', 'asdsa', '1', '[1,2,3]', null);
INSERT INTO `workfollows` VALUES ('5', null, null, 'dasd', 'asdsa', '1', '[1,2,3]', null);
INSERT INTO `workfollows` VALUES ('6', null, null, 'dasd', 'asdsa', '1', '[1,2,3]', null);
INSERT INTO `workfollows` VALUES ('7', null, null, 'dasd', 'asdsa', '1', '[1,2,3]', null);
INSERT INTO `workfollows` VALUES ('8', null, null, 'dasd', 'asdsa', '1', '[1,2,3]', null);
INSERT INTO `workfollows` VALUES ('9', null, null, 'dasd', 'asdsa', '1', '[1,2,3]', null);
INSERT INTO `workfollows` VALUES ('10', '2019-03-15 08:41:34', null, 'dasd', 'asdsa', '1', '[1,2,3]', '2019-03-15 08:41:34');
INSERT INTO `workfollows` VALUES ('11', '2019-03-15 08:43:35', null, 'dasd', 'asdsa', '1', '[1,2,3]', '2019-03-15 08:43:35');
INSERT INTO `workfollows` VALUES ('12', '2019-03-15 08:43:58', null, 'dasd', 'asdsa', '1', '[1,2,3]', '2019-03-15 08:43:58');
SET FOREIGN_KEY_CHECKS=1;
