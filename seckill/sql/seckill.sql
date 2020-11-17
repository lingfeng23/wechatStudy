/*
 Navicat Premium Data Transfer

 Source Server         : malf
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : seckill

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 17/11/2020 16:44:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for seckill
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill`  (
  `seckill_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
  `name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `number` int(11) NOT NULL COMMENT '库存数量',
  `start_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '秒杀开启的时间',
  `end_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '秒杀结束的时间',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建的时间',
  PRIMARY KEY (`seckill_id`) USING BTREE,
  INDEX `idx_start_time`(`start_time`) USING BTREE,
  INDEX `idx_end_time`(`end_time`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1004 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '秒杀库存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill` VALUES (1000, '1000元秒杀iphone6', 100, '2016-05-22 00:00:00', '2016-05-23 00:00:00', '2020-11-17 16:43:49');
INSERT INTO `seckill` VALUES (1001, '500元秒杀iPad2', 200, '2016-05-22 00:00:00', '2016-05-23 00:00:00', '2020-11-17 16:43:49');
INSERT INTO `seckill` VALUES (1002, '300元秒杀小米4', 300, '2016-05-22 00:00:00', '2016-05-23 00:00:00', '2020-11-17 16:43:49');
INSERT INTO `seckill` VALUES (1003, '200元秒杀红米note', 400, '2016-05-22 00:00:00', '2016-05-23 00:00:00', '2020-11-17 16:43:49');

SET FOREIGN_KEY_CHECKS = 1;

-- 秒杀成功明细表
DROP TABLE IF EXISTS `success_killed`;
create table success_killed(
  `seckill_id` BIGINT NOT NULL COMMENT '秒杀商品ID',
  `user_phone` BIGINT NOT NULL COMMENT '用户手机号',
  `state` TINYINT NOT NULL DEFAULT -1 COMMENT '状态标示:-1无效 0成功 1已付款',
  `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
  PRIMARY KEY (seckill_id,user_phone), /*联合主键*/
  KEY idx_create_time(create_time)
)ENGINE =InnDB DEFAULT CHARSET =utf8 COMMENT ='秒杀成功明细表'
