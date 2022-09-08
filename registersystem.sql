/*
Navicat MySQL Data Transfer

Source Server         : Test
Source Server Version : 50557
Source Host           : localhost:3306
Source Database       : registersystem

Target Server Type    : MYSQL
Target Server Version : 50557
File Encoding         : 65001

Date: 2017-12-30 21:23:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for adminuser
-- ----------------------------
DROP TABLE IF EXISTS `adminuser`;
CREATE TABLE `adminuser` (
  `adminusername` varchar(40) NOT NULL,
  `adminuserpassword` varchar(40) DEFAULT NULL,
  `adminuserrole` int(11) DEFAULT NULL,
  PRIMARY KEY (`adminusername`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adminuser
-- ----------------------------
INSERT INTO `adminuser` VALUES ('wu1', '123', '1');
INSERT INTO `adminuser` VALUES ('wu2', '123', '2');
INSERT INTO `adminuser` VALUES ('wu3', '123', '3');
INSERT INTO `adminuser` VALUES ('wu4', '123', '4');

-- ----------------------------
-- Table structure for bedchamber
-- ----------------------------
DROP TABLE IF EXISTS `bedchamber`;
CREATE TABLE `bedchamber` (
  `BedchamberId` int(11) NOT NULL AUTO_INCREMENT,
  `BedchamberName` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`BedchamberId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bedchamber
-- ----------------------------

-- ----------------------------
-- Table structure for classta
-- ----------------------------
DROP TABLE IF EXISTS `classta`;
CREATE TABLE `classta` (
  `ClassId` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`ClassId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classta
-- ----------------------------

-- ----------------------------
-- Table structure for speciality
-- ----------------------------
DROP TABLE IF EXISTS `speciality`;
CREATE TABLE `speciality` (
  `SpecialityId` int(11) NOT NULL AUTO_INCREMENT,
  `SpecialityName` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`SpecialityId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of speciality
-- ----------------------------
INSERT INTO `speciality` VALUES ('25', '软件技术');
INSERT INTO `speciality` VALUES ('26', '软件技术9');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `StudentId` int(11) NOT NULL AUTO_INCREMENT,
  `StudentName` varchar(40) DEFAULT NULL,
  `SpecialityId` int(11) DEFAULT NULL,
  `ClassId` int(11) DEFAULT NULL,
  `BedchamberId` int(11) DEFAULT NULL,
  `MatriNo` varchar(40) DEFAULT NULL,
  `PayAmount` int(11) DEFAULT NULL,
  `PayOK` char(1) DEFAULT NULL,
  `RegistDate` datetime DEFAULT NULL,
  PRIMARY KEY (`StudentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
