/*
Navicat MySQL Data Transfer

Source Server         : centos7Mysql01
Source Server Version : 80013
Source Host           : 192.168.56.101:3306
Source Database       : db_carmanager

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-06-27 01:40:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for registration_intention
-- ----------------------------
DROP TABLE IF EXISTS `registration_intention`;
CREATE TABLE `registration_intention` (
  `id` varchar(40) NOT NULL,
  `student_id` varchar(40) DEFAULT NULL,
  `intention` int(2) DEFAULT NULL,
  `deposit` decimal(4,2) DEFAULT NULL,
  `is_renew` bit(2) DEFAULT NULL,
  `contact_result` varchar(200) DEFAULT NULL,
  `contact_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
