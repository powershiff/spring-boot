/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : student

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2017-12-19 17:30:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_stu
-- ----------------------------
DROP TABLE IF EXISTS `t_stu`;
CREATE TABLE `t_stu` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(255) collate utf8_unicode_ci default NULL,
  `age` int(11) default NULL,
  `password` varchar(255) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_stu
-- ----------------------------
INSERT INTO `t_stu` VALUES ('1', '张三', '13', '111');
INSERT INTO `t_stu` VALUES ('2', '李四', '19', '21321');
INSERT INTO `t_stu` VALUES ('3', '王五', '32', '11');
INSERT INTO `t_stu` VALUES ('4', '赵六', '22', '11');
INSERT INTO `t_stu` VALUES ('5', '马自达', '11', '1w1');
INSERT INTO `t_stu` VALUES ('6', '宝马', '29', '1w1');
INSERT INTO `t_stu` VALUES ('7', '马宁', '12', null);
