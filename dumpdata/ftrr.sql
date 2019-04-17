/*
Navicat MySQL Data Transfer

Source Server         : db
Source Server Version : 100136
Source Host           : localhost:3306
Source Database       : ftrr

Target Server Type    : MYSQL
Target Server Version : 100136
File Encoding         : 65001

Date: 2019-04-17 08:52:40
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
  `rank` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_final_requests_users_1` (`updated_by`),
  KEY `fk_final_requests_trainee_for_requests_1` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
-- Records of final_results
-- ----------------------------

-- ----------------------------
-- Table structure for group_user
-- ----------------------------
DROP TABLE IF EXISTS `group_user`;
CREATE TABLE `group_user` (
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`group_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
-- Records of group_user
-- ----------------------------
INSERT INTO `group_user` VALUES ('29', '7');
INSERT INTO `group_user` VALUES ('29', '118');
INSERT INTO `group_user` VALUES ('29', '119');
INSERT INTO `group_user` VALUES ('29', '120');
INSERT INTO `group_user` VALUES ('29', '121');
INSERT INTO `group_user` VALUES ('29', '122');

-- ----------------------------
-- Table structure for groups
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `parent_path` varchar(1024) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
-- Records of groups
-- ----------------------------
INSERT INTO `groups` VALUES ('1', 'Education section', null);
INSERT INTO `groups` VALUES ('2', 'Section 1', null);
INSERT INTO `groups` VALUES ('3', 'Education section', null);
INSERT INTO `groups` VALUES ('4', 'Education section', null);
INSERT INTO `groups` VALUES ('5', 'Education section', null);
INSERT INTO `groups` VALUES ('6', 'Education section', null);
INSERT INTO `groups` VALUES ('7', 'Team 1', '[2]');
INSERT INTO `groups` VALUES ('8', 'Education section', null);
INSERT INTO `groups` VALUES ('67', 'Education section', null);
INSERT INTO `groups` VALUES ('68', 'Human development division', null);
INSERT INTO `groups` VALUES ('69', 'test', null);
INSERT INTO `groups` VALUES ('70', 'test con', null);
INSERT INTO `groups` VALUES ('71', 'test', null);
INSERT INTO `groups` VALUES ('72', '[pc] pm', null);
INSERT INTO `groups` VALUES ('73', '[pc] div 2', null);
INSERT INTO `groups` VALUES ('74', '[pc] div 2', null);
INSERT INTO `groups` VALUES ('75', 'Education section', null);
INSERT INTO `groups` VALUES ('76', 'Human development division', null);
INSERT INTO `groups` VALUES ('77', 'test', null);
INSERT INTO `groups` VALUES ('78', 'test con', null);
INSERT INTO `groups` VALUES ('79', 'test', null);
INSERT INTO `groups` VALUES ('80', '[pc] pm', null);
INSERT INTO `groups` VALUES ('81', '[pc] div 2', null);
INSERT INTO `groups` VALUES ('82', '[pc] div 2', null);
INSERT INTO `groups` VALUES ('83', 'Education section', '[258]');
INSERT INTO `groups` VALUES ('84', 'test', '[]');
INSERT INTO `groups` VALUES ('85', 'test con', '[540]');
INSERT INTO `groups` VALUES ('86', '[pc] pm', '[653]');
INSERT INTO `groups` VALUES ('87', '[pc] div 2', '[]');
INSERT INTO `groups` VALUES ('88', 'Education section', '[258]');
INSERT INTO `groups` VALUES ('89', 'test', '[]');
INSERT INTO `groups` VALUES ('90', 'test con', '[540]');
INSERT INTO `groups` VALUES ('91', '[pc] pm', '[653]');
INSERT INTO `groups` VALUES ('92', '[pc] div 2', '[]');
INSERT INTO `groups` VALUES ('93', 'Education section', '[258]');
INSERT INTO `groups` VALUES ('94', 'test', '[]');
INSERT INTO `groups` VALUES ('95', 'test con', '[540]');
INSERT INTO `groups` VALUES ('96', '[pc] pm', '[653]');
INSERT INTO `groups` VALUES ('97', '[pc] div 2', '[]');
INSERT INTO `groups` VALUES ('98', 'Education section', '[258]');
INSERT INTO `groups` VALUES ('99', 'test', '[]');
INSERT INTO `groups` VALUES ('100', 'test con', '[540]');
INSERT INTO `groups` VALUES ('101', '[pc] pm', '[653]');
INSERT INTO `groups` VALUES ('102', '[pc] div 2', '[]');
INSERT INTO `groups` VALUES ('103', 'Education section', '[258]');
INSERT INTO `groups` VALUES ('104', 'test', '[]');
INSERT INTO `groups` VALUES ('105', 'test con', '[540]');
INSERT INTO `groups` VALUES ('106', '[pc] pm', '[653]');
INSERT INTO `groups` VALUES ('107', '[pc] div 2', '[]');
INSERT INTO `groups` VALUES ('108', 'Education section', '[258]');
INSERT INTO `groups` VALUES ('109', 'test', '[]');
INSERT INTO `groups` VALUES ('110', 'test con', '[540]');
INSERT INTO `groups` VALUES ('111', '[pc] pm', '[653]');
INSERT INTO `groups` VALUES ('112', '[pc] div 2', '[]');
INSERT INTO `groups` VALUES ('113', 'Education section', '[258]');
INSERT INTO `groups` VALUES ('114', 'test', '[]');
INSERT INTO `groups` VALUES ('115', 'test con', '[540]');
INSERT INTO `groups` VALUES ('116', '[pc] pm', '[653]');
INSERT INTO `groups` VALUES ('117', '[pc] div 2', '[]');
INSERT INTO `groups` VALUES ('118', 'Education section', '[258]');
INSERT INTO `groups` VALUES ('119', 'test', '[]');
INSERT INTO `groups` VALUES ('120', 'test con', '[540]');
INSERT INTO `groups` VALUES ('121', '[pc] pm', '[653]');
INSERT INTO `groups` VALUES ('122', '[pc] div 2', '[]');

-- ----------------------------
-- Table structure for interviews
-- ----------------------------
DROP TABLE IF EXISTS `interviews`;
CREATE TABLE `interviews` (
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
  `reviewer` int(11) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKol939k8b8j8ny97lsyuuumnob` (`created_by`),
  KEY `FK235nv0lkbk8utb64ffxk4g0t9` (`result_type_id`),
  KEY `FK3r0rndoi7bm0a3s2xl7bq6uo6` (`title_interview_id`),
  KEY `FKgpnxtqwqewbw85qahm33ecdu5` (`trainee_for_request_id`),
  KEY `FKgb74pr9u658k2eavm62lokjyf` (`updated_by`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of interviews
-- ----------------------------
INSERT INTO `interviews` VALUES ('19', 'HR Pass\n- C?u ti?n\n- Thiên h??ng Tech, có kh? n?ng phát tri?n \n- T? h?c t?t tuy nhiên ch?a có nhi?u ph??ng pháp sáng t?o ?? rút ng?n th?i gian\n- Theo thiên h??ng ch?m ch?\n- Th?i gian ra tr??ng lâu nên c?n follow thêm v? ??nh h??ng', '2019-03-27 16:31:22', 'Pass', '2019-03-28 08:08:26', '3', null, '1', '1', '3', '13', '2008-04-23 19:53:40', '2008-04-23 19:53:40');
INSERT INTO `interviews` VALUES ('20', 'HR Pass\n- Có ý th?c trong công vi?c chung c?a nhóm\n- Nh?n th?c rõ ???c b?n thân ?ang y?u ? ?âu và c?n c?i thi?n nh?ng gì\n- Quan tâm ??n vi?c training và chia s? ki?n th?c cho m?i ng??i\n- Horenso t?t\n- C?n c?i thi?n thêm v? k? n?ng m?m\"', '2019-03-27 16:31:22', 'Pass', '2019-03-28 08:08:26', '3', null, '1', '2', '3', '3', '2008-04-23 19:53:40', '2008-04-23 19:53:40');
INSERT INTO `interviews` VALUES ('21', 'HR Pass\n- C?u ti?n\n- Thiên h??ng Tech, có kh? n?ng phát tri?n \n- T? h?c t?t tuy nhiên ch?a có nhi?u ph??ng pháp sáng t?o ?? rút ng?n th?i gian\n- Theo thiên h??ng ch?m ch?\n- Th?i gian ra tr??ng lâu nên c?n follow thêm v? ??nh h??ng', '2019-03-27 16:31:22', 'Pass', '2019-03-28 08:08:26', '3', null, '1', '3', '3', '13', '2008-04-23 19:53:40', '2008-04-23 19:53:40');
INSERT INTO `interviews` VALUES ('22', 'HR Pass\n- Có ý th?c trong công vi?c chung c?a nhóm\n- Nh?n th?c rõ ???c b?n thân ?ang y?u ? ?âu và c?n c?i thi?n nh?ng gì\n- Quan tâm ??n vi?c training và chia s? ki?n th?c cho m?i ng??i\n- Horenso t?t\n- C?n c?i thi?n thêm v? k? n?ng m?m\"', '2019-03-27 16:31:22', 'Pass', '2019-03-28 08:08:26', '3', null, '2', '1', '3', '13', '2008-04-23 19:53:40', '2008-04-23 19:53:40');
INSERT INTO `interviews` VALUES ('23', 'HR Pass\n- C?u ti?n\n- Thiên h??ng Tech, có kh? n?ng phát tri?n \n- T? h?c t?t tuy nhiên ch?a có nhi?u ph??ng pháp sáng t?o ?? rút ng?n th?i gian\n- Theo thiên h??ng ch?m ch?\n- Th?i gian ra tr??ng lâu nên c?n follow thêm v? ??nh h??ng', '2019-03-27 16:31:22', 'Pass', '2019-03-28 08:08:26', '3', null, '2', '2', '3', '3', '2008-04-23 19:53:40', '2008-04-23 19:53:40');
INSERT INTO `interviews` VALUES ('24', 'HR Pass\n- Có ý th?c trong công vi?c chung c?a nhóm\n- Nh?n th?c rõ ???c b?n thân ?ang y?u ? ?âu và c?n c?i thi?n nh?ng gì\n- Quan tâm ??n vi?c training và chia s? ki?n th?c cho m?i ng??i\n- Horenso t?t\n- C?n c?i thi?n thêm v? k? n?ng m?m\"', '2019-03-27 16:31:22', 'Pass', '2019-03-28 08:08:26', '3', null, '2', '3', '3', '3', '2008-04-23 19:53:40', '2008-04-23 19:53:40');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notifications
-- ----------------------------
INSERT INTO `notifications` VALUES ('1', 'notification to sm/dm', '2019-03-28 08:08:25', null, null, '1', '2019-03-28 08:08:25', null, '3', '13');
INSERT INTO `notifications` VALUES ('2', 'notification to sm/dm', '2019-03-28 08:08:26', null, null, '1', '2019-03-28 08:08:26', null, '3', '14');
INSERT INTO `notifications` VALUES ('3', 'notification to hr/sm/dm/ec', '2019-03-28 15:22:02', null, null, '1', '2019-03-28 15:22:02', null, '3', '3');
INSERT INTO `notifications` VALUES ('4', 'notification to hr/sm/dm/ec', '2019-03-28 15:22:02', null, null, '1', '2019-03-28 15:22:02', null, '3', '13');
INSERT INTO `notifications` VALUES ('5', 'notification to hr/sm/dm/ec', '2019-03-28 15:22:02', null, null, '1', '2019-03-28 15:22:02', null, '3', '14');

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
-- Table structure for positions
-- ----------------------------
DROP TABLE IF EXISTS `positions`;
CREATE TABLE `positions` (
  `id` int(1) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `deleted_at` date DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `position_type` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
-- Records of positions
-- ----------------------------
INSERT INTO `positions` VALUES ('1', 'Section Manager', 'Section Manager', null, '2017-06-19 10:17:02', '2017-06-19 10:17:02', '1', 'non_engineer');
INSERT INTO `positions` VALUES ('5', 'Section Manager', 'Section Manager', null, '2017-06-19 10:17:02', '2017-06-19 10:17:02', '1', 'non_engineer');

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
INSERT INTO `requests` VALUES ('1', '1', '5', '1998-06-20', 'Go', '6', '2', '1985-09-03 01:41:16', '3', '2019-03-28 15:22:02');
INSERT INTO `requests` VALUES ('2', '1', '1', '1991-01-12', 'NodeJS', '3', '2', '2013-03-18 20:44:27', '2', '2019-02-26 16:02:07');
INSERT INTO `requests` VALUES ('3', '1', '3', '1979-04-29', 'React', '1', '10', '2009-01-30 11:23:48', null, null);
INSERT INTO `requests` VALUES ('4', '1', '3', '2015-02-20', 'Front-end', '4', '10', '2009-02-08 23:06:02', null, '2013-03-18 20:44:27');
INSERT INTO `requests` VALUES ('5', '1', '3', '1990-01-08', 'Java', '5', '11', '1977-05-28 09:28:21', '1', '2013-03-18 20:44:27');
INSERT INTO `requests` VALUES ('6', '2', '2', '2019-02-21', 'Java', '3', '2', '2019-02-21 09:07:42', '1', '2019-02-21 09:07:42');
INSERT INTO `requests` VALUES ('7', '1', '5', '2018-12-12', 'Ruby', '3', '11', '2019-02-21 09:07:42', '1', '2019-02-21 09:07:42');

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
INSERT INTO `trainee_for_requests` VALUES ('1', '1', '2', '4', '2018-12-11 00:00:00', '2019-03-28 11:39:34', '1');
INSERT INTO `trainee_for_requests` VALUES ('2', '1', '1', '4', '2018-12-11 00:00:00', '2019-03-28 11:39:34', '2');
INSERT INTO `trainee_for_requests` VALUES ('3', '1', '3', '4', '2018-12-11 00:00:00', '2019-03-28 11:39:34', null);
INSERT INTO `trainee_for_requests` VALUES ('4', '2', '1', '1', '2018-12-10 00:00:00', '2019-03-22 10:47:51', null);
INSERT INTO `trainee_for_requests` VALUES ('5', '2', '2', '1', '2018-12-13 00:00:00', '2019-03-22 10:47:51', null);
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
  `position_id` int(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `division` enum('1','2','3','4','5','6','7') DEFAULT '1',
  `phone` varchar(45) DEFAULT NULL,
  `gender` enum('1','2') DEFAULT '1',
  `role` enum('1','2','3','4','5','6','7','8') DEFAULT '7',
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=armscii8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '6046', '1', 'ec1@gmail.com', 'Elroy Streich', '1', '1-911-154-2853x951', '1', '6', '2008-04-23 19:53:40', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('2', '3416', '1', 'sm11@gmail.com', 'Earnest Brown MD', '1', '(741)518-3418', '1', '8', '2017-04-28 20:07:36', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('3', '7863', '1', 'hr1@gmail.com', 'Evan Thompson', '7', '1-941-409-9380', '1', '7', '1970-06-19 22:11:20', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('4', '4663', null, 'hr2@gmail.com', 'Nella Rogahn IV', '7', '614.206.9929x488', '', '7', '2002-07-01 17:39:39', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('5', '7402', '1', 'hr3@gmail.com', 'Petra Bogan', '1', '1-131-088-4892x06201', '', '8', '2002-11-23 03:16:09', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('6', '3958', '1', 'dm1@gmail.com', 'Alanna Botsford', '', '241-364-0270x08741', '1', '', '1982-12-13 21:16:22', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('7', '9836', '1', 'dm3@gmail.com', 'Laurine Cremin Sr.', '', '967.459.8149', '', '', '2006-09-12 23:11:07', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('8', '6768', '1', 'dm2@gmail.com', 'Mr. Willy Sanford', '', '734.648.6758', '', '', '1995-05-19 04:16:33', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('9', '4579', '1', 'sm1@gmail.com', 'Mrs. Lenore Shanahan', '', '286-136-2109', '1', '', '1974-10-27 12:25:50', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('10', '6267w', null, 'sm2@gmail.com', 'Dr. Fernando Schmitt Jr.', '', '131.353.7561x498', '', '', '1985-06-17 11:44:52', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('11', '9036', null, 'sm3@gmail.com', 'Kendrick Heidenreich', '', '1-383-838-8866', '', '', '1988-12-08 18:36:33', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('12', '3686', null, 'sm@gmail.com', 'Neoma Klocko Jr.', '', '1-814-385-7663x9308', '', '', '1985-10-12 23:17:50', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('13', '4590', '1', 'trainer1@gmail.com', 'Doan Dinh Linh', '1', '00347351376', '', '1', '1990-10-02 15:30:34', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('14', '7174', null, 'trainer2@gmail.com', 'Clint Harris', '1', '1-562-822-6541', '', '1', '2001-01-07 00:26:07', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('15', '8210', null, 'trainer3@gmail.com', 'Dr. Winfield Bechtelar V', '', '+22(1)9491211975', '1', '', '1996-11-28 23:13:07', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('16', '7193', null, 'trainer4@gmail.com', 'Mr. Luther O\'Kon', '', '598-519-2496x93280', '', '', '2012-12-11 23:52:10', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('17', '8435', null, 'trainer5@gmail.com', 'Leonie Mante', '', '942-733-2812x8269', '', '', '1989-09-01 08:24:13', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('18', '5180', null, 'trainer6@gmail.com', 'Dr. Pauline Brekke', '', '651.417.9938x06603', '', '', '2005-05-25 02:06:19', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('19', '9947', null, 'gl1@gmail.com', 'Westley Senger', '', '080-599-8897', '', '', '2008-11-01 18:38:21', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('20', '9976', null, 'gl2@gmail.com', 'Margaret Watsica', '', '1-025-384-3814x750', '', '', '2009-09-25 22:43:46', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('21', '3738', null, 'gl3@gmail.com', 'Buddy Runte', '', '1-306-725-8495x00547', '', '', '1986-01-28 18:04:14', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('22', '4177', null, 'gl4@gmail.com', 'Dr. Colby Ledner PhD', '', '(720)408-0973x818', '', '', '1999-06-06 20:55:27', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('23', '7811', null, 'gl5@gmail.com', 'Prof. Kamryn Durgan IV', '', '(984)325-2596x003', '', '', '1985-07-09 09:39:17', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('24', '4372', null, 'gl6@gmail.com', 'Effie Gutkowski', '', '1-693-176-4996', '', '', '2016-02-11 11:10:35', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('25', '8368', null, 'gl7@gmail.com', 'Liam Schamberger Sr.', '', '091.358.9606x5376', '', '', '2010-01-17 08:25:38', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('26', '7938', null, 'gl8@gmail.com', 'Reymundo Russel', '', '049.127.1070x30897', '', '', '1999-10-14 12:37:24', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('27', '9281', null, 'gl9@gmail.com', 'Riley Kozey', '', '08946842434', '', '', '1981-01-16 10:39:27', null, '$2a$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('28', '1123', null, 'nguyen.van.tran.anhb@framgia.com.edev', 'fsdfsd', '1', null, '', '7', null, null, '$10$e1rjqncX9xvdTPrJMFowuuCgrhmmOXwiHnjBNVmUnX3hmLe1mxQO6', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('29', 'B120590', '32', 'hoang.nhac.trung@framgia.com.edev', 'Hoang Nhac Trung', null, null, '2', null, null, null, null, null, null, null, null, null, null, null);

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

-- ----------------------------
-- Table structure for workspace_user
-- ----------------------------
DROP TABLE IF EXISTS `workspace_user`;
CREATE TABLE `workspace_user` (
  `user_id` int(11) NOT NULL,
  `workspace_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`workspace_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
-- Records of workspace_user
-- ----------------------------
INSERT INTO `workspace_user` VALUES ('1', '2');
INSERT INTO `workspace_user` VALUES ('1', '3');
INSERT INTO `workspace_user` VALUES ('2', '1');
INSERT INTO `workspace_user` VALUES ('2', '4');

-- ----------------------------
-- Table structure for workspaces
-- ----------------------------
DROP TABLE IF EXISTS `workspaces`;
CREATE TABLE `workspaces` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `is_enable` tinyint(1) DEFAULT NULL,
  `open_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `close_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `owner_id` int(11) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `timezone` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
-- Records of workspaces
-- ----------------------------
INSERT INTO `workspaces` VALUES ('1', null, null, '3ee', '1', '2019-04-03 09:28:15', '2019-04-03 09:28:15', null, null, null, null, null, null);
INSERT INTO `workspaces` VALUES ('2', 'TEST', 'TEST', null, null, '2000-01-02 00:00:00', '2000-01-01 23:59:00', null, '1', '2017-06-09 09:02:18', '2017-06-09 09:52:43', null, null);
INSERT INTO `workspaces` VALUES ('3', 'TEST', 'TEST', null, null, '2019-04-09 10:14:03', '2019-04-09 10:14:03', null, '1', '2017-06-09 09:02:18', '2017-06-09 09:52:43', null, null);
INSERT INTO `workspaces` VALUES ('4', 'TEST', 'TEST', null, null, '2000-01-02 00:00:00', '2000-01-01 23:59:00', null, '1', '2017-06-09 09:02:18', '2017-06-09 09:52:43', null, null);
INSERT INTO `workspaces` VALUES ('5', 'TEST', 'TEST', null, null, '2000-01-02 00:00:00', '2000-01-01 23:59:00', null, '1', '2017-06-09 09:02:18', '2017-06-09 09:52:43', null, null);
INSERT INTO `workspaces` VALUES ('6', 'TEST', 'TEST', null, null, '2000-01-02 00:00:00', '2000-01-01 23:59:00', null, '1', '2017-06-09 09:02:18', '2017-06-09 09:52:43', null, null);
INSERT INTO `workspaces` VALUES ('7', 'TEST', 'TEST', null, null, '2000-01-02 00:00:00', '2000-01-01 23:59:00', null, '1', '2017-06-09 09:02:18', '2017-06-09 09:52:43', null, null);
INSERT INTO `workspaces` VALUES ('8', 'TEST', 'TEST', null, null, '2000-01-02 00:00:00', '2000-01-01 23:59:00', null, '1', '2017-06-09 09:02:18', '2017-06-09 09:52:43', null, null);
INSERT INTO `workspaces` VALUES ('9', 'TEST', 'TEST', null, null, '2000-01-02 00:00:00', '2000-01-01 23:59:00', null, '1', '2017-06-09 09:02:18', '2017-06-09 09:52:43', null, null);
INSERT INTO `workspaces` VALUES ('10', 'TEST', 'TEST', null, null, '2000-01-02 00:00:00', '2000-01-01 23:59:00', null, '1', '2017-06-09 09:02:18', '2017-06-09 09:52:43', null, null);
INSERT INTO `workspaces` VALUES ('11', 'TEST', 'TEST', null, null, '2000-01-02 00:00:00', '2000-01-01 23:59:00', null, '1', '2017-06-09 09:02:18', '2017-06-09 09:52:43', null, null);
SET FOREIGN_KEY_CHECKS=1;
