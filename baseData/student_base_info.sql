/*
Navicat MySQL Data Transfer

Source Server         : centos7Mysql01
Source Server Version : 80013
Source Host           : 192.168.56.101:3306
Source Database       : db_carmanager

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-06-27 01:39:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student_base_info
-- ----------------------------
DROP TABLE IF EXISTS `student_base_info`;
CREATE TABLE `student_base_info` (
  `id` int(40) NOT NULL,
  `student_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生姓名',
  `student_phone` int(15) DEFAULT NULL,
  `sex` tinyint(4) NOT NULL,
  `school_class` varchar(20) DEFAULT NULL,
  `home_address` varchar(100) DEFAULT NULL,
  `parent_name1` varchar(10) NOT NULL,
  `parant1_phone` int(15) DEFAULT NULL,
  `parent_name2` varchar(20) DEFAULT NULL,
  `parant2_phone` int(15) DEFAULT NULL,
  `registration_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
