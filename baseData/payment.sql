/*
Navicat MySQL Data Transfer

Source Server         : centos7Mysql01
Source Server Version : 80013
Source Host           : 192.168.56.101:3306
Source Database       : db_carmanager

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-06-27 01:39:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `id` varchar(40) NOT NULL,
  `student_id` varchar(40) DEFAULT NULL,
  `payment_amount` decimal(5,2) DEFAULT NULL,
  `is_offer` bit(2) DEFAULT NULL,
  `referrer` varchar(10) DEFAULT NULL,
  `offer_amount` decimal(5,2) DEFAULT NULL,
  `payment_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
