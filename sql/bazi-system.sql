/*
 Navicat Premium Dump SQL

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : bazi-system

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 12/06/2025 18:57:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bazi_records
-- ----------------------------
DROP TABLE IF EXISTS `bazi_records`;
CREATE TABLE `bazi_records`  (
  `id` bigint NOT NULL COMMENT '记录ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` tinyint NULL DEFAULT NULL COMMENT '性别（0:女，1:男）',
  `occupy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '占事',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `date` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '日期',
  `date_type` tinyint NULL DEFAULT 0 COMMENT '日期类型（0:公历，1:农历）',
  `leap_month_type` tinyint NULL DEFAULT 0 COMMENT '闰月类型（0:不使用闰月，1:使用闰月）',
  `jie_qi_type` tinyint NULL DEFAULT 1 COMMENT '节气类型（0:按天计算，1:按分钟计算）',
  `year_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '年干支类型',
  `month_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '月干支类型',
  `day_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '日干支类型',
  `hour_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '时干支类型',
  `qi_yun_liu_pai_type` tinyint NULL DEFAULT NULL COMMENT '起运流派',
  `year_gan` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '年干',
  `year_zhi` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '年支',
  `month_gan` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '月干',
  `month_zhi` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '月支',
  `day_gan` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '日干',
  `day_zhi` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '日支',
  `hour_gan` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '时干',
  `hour_zhi` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '时支',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_day_gan`(`day_gan` ASC) USING BTREE,
  INDEX `idx_day_zhi`(`day_zhi` ASC) USING BTREE,
  INDEX `idx_year_gan`(`year_gan` ASC) USING BTREE,
  INDEX `idx_year_zhi`(`year_zhi` ASC) USING BTREE,
  INDEX `idx_user_created`(`user_id` ASC, `created_at` ASC) USING BTREE,
  INDEX `idx_created_at`(`created_at` ASC) USING BTREE,
  CONSTRAINT `fk_bazi_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '八字记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for liuyao_records
-- ----------------------------
DROP TABLE IF EXISTS `liuyao_records`;
CREATE TABLE `liuyao_records`  (
  `id` bigint NOT NULL COMMENT '记录ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` tinyint NULL DEFAULT NULL COMMENT '性别（0:女，1:男）',
  `occupy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '占事',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `date` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '日期',
  `date_type` tinyint NULL DEFAULT 0 COMMENT '日期类型（0:公历，1:农历）',
  `leap_month_type` tinyint NULL DEFAULT 0 COMMENT '闰月类型（0:不使用闰月，1:使用闰月）',
  `jie_qi_type` tinyint NULL DEFAULT 1 COMMENT '节气类型（0:按天计算，1:按分钟计算）',
  `pai_pan_type` tinyint NULL DEFAULT NULL COMMENT '排盘类型',
  `yao1` tinyint NULL DEFAULT NULL COMMENT '初爻',
  `yao2` tinyint NULL DEFAULT NULL COMMENT '二爻',
  `yao3` tinyint NULL DEFAULT NULL COMMENT '三爻',
  `yao4` tinyint NULL DEFAULT NULL COMMENT '四爻',
  `yao5` tinyint NULL DEFAULT NULL COMMENT '五爻',
  `yao6` tinyint NULL DEFAULT NULL COMMENT '上爻',
  `year_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '年干支类型',
  `month_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '月干支类型',
  `day_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '日干支类型',
  `hour_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '时干支类型',
  `ben_gua_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '本卦名称',
  `bian_gua_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '变卦名称',
  `ben_gua_xiang` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '本卦卦象',
  `bian_gua_xiang` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '变卦卦象',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_ben_gua`(`ben_gua_name` ASC) USING BTREE,
  INDEX `idx_user_created`(`user_id` ASC, `created_at` ASC) USING BTREE,
  INDEX `idx_created_at`(`created_at` ASC) USING BTREE,
  CONSTRAINT `fk_liuyao_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '六爻记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for meihua_records
-- ----------------------------
DROP TABLE IF EXISTS `meihua_records`;
CREATE TABLE `meihua_records`  (
  `id` bigint NOT NULL COMMENT '记录ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` tinyint NULL DEFAULT NULL COMMENT '性别（0:女，1:男）',
  `occupy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '占事',
  `date` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '日期',
  `date_type` tinyint NULL DEFAULT 0 COMMENT '日期类型（0:公历，1:农历）',
  `leap_month_type` tinyint NULL DEFAULT 0 COMMENT '闰月类型（0:不使用闰月，1:使用闰月）',
  `pai_pan_type` tinyint NULL DEFAULT NULL COMMENT '排盘类型',
  `shang_gua_num` int NULL DEFAULT NULL COMMENT '上卦数',
  `xia_gua_num` int NULL DEFAULT NULL COMMENT '下卦数',
  `dong_yao_num` int NULL DEFAULT NULL COMMENT '动爻数',
  `year_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '年干支类型',
  `month_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '月干支类型',
  `day_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '日干支类型',
  `hour_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '时干支类型',
  `ben_gua_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '本卦名称',
  `bian_gua_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '变卦名称',
  `ti_gua` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '体卦',
  `yong_gua` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用卦',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_ben_gua`(`ben_gua_name` ASC) USING BTREE,
  INDEX `idx_user_created`(`user_id` ASC, `created_at` ASC) USING BTREE,
  INDEX `idx_created_at`(`created_at` ASC) USING BTREE,
  CONSTRAINT `fk_meihua_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '梅花易数记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qimen_records
-- ----------------------------
DROP TABLE IF EXISTS `qimen_records`;
CREATE TABLE `qimen_records`  (
  `id` bigint NOT NULL COMMENT '记录ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` tinyint NULL DEFAULT NULL COMMENT '性别（0:女，1:男）',
  `occupy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '占事',
  `date` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '日期',
  `date_type` tinyint NULL DEFAULT 0 COMMENT '日期类型（0:公历，1:农历）',
  `leap_month_type` tinyint NULL DEFAULT 0 COMMENT '闰月类型（0:不使用闰月，1:使用闰月）',
  `jie_qi_type` tinyint NULL DEFAULT 1 COMMENT '节气类型（0:按天计算，1:按分钟计算）',
  `pai_pan_type` tinyint NULL DEFAULT NULL COMMENT '排盘类型',
  `zhi_shi_type` tinyint NULL DEFAULT NULL COMMENT '值使类型',
  `yue_jia_qi_ju_type` tinyint NULL DEFAULT NULL COMMENT '月家起局类型',
  `year_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '年干支类型',
  `month_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '月干支类型',
  `day_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '日干支类型',
  `hour_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '时干支类型',
  `ju_shu` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '局数',
  `xun_shou` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '旬首',
  `zhi_fu` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '值符',
  `zhi_shi` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '值使',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_ju_shu`(`ju_shu` ASC) USING BTREE,
  INDEX `idx_user_created`(`user_id` ASC, `created_at` ASC) USING BTREE,
  INDEX `idx_created_at`(`created_at` ASC) USING BTREE,
  CONSTRAINT `fk_qimen_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '奇门遁甲记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint NOT NULL COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '登录令牌',
  `login_at` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `uk_email`(`email` ASC) USING BTREE,
  INDEX `idx_token`(`token` ASC) USING BTREE,
  INDEX `idx_created_at`(`created_at` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ziwei_records
-- ----------------------------
DROP TABLE IF EXISTS `ziwei_records`;
CREATE TABLE `ziwei_records`  (
  `id` bigint NOT NULL COMMENT '记录ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` tinyint NULL DEFAULT NULL COMMENT '性别（0:女，1:男）',
  `occupy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '占事',
  `date` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '日期',
  `date_type` tinyint NULL DEFAULT 0 COMMENT '日期类型（0:公历，1:农历）',
  `leap_month_type` tinyint NULL DEFAULT 0 COMMENT '闰月类型（0:不使用闰月，1:使用闰月）',
  `jie_qi_type` tinyint NULL DEFAULT 1 COMMENT '节气类型（0:按天计算，1:按分钟计算）',
  `wu_xing_ju_type` tinyint NULL DEFAULT NULL COMMENT '五行局类型',
  `liu_nian_zhi` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '流年支',
  `year_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '年干支类型',
  `month_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '月干支类型',
  `day_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '日干支类型',
  `hour_gan_zhi_type` tinyint NULL DEFAULT NULL COMMENT '时干支类型',
  `ming_gong` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '命宫',
  `shen_gong` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '身宫',
  `wu_xing_ju` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '五行局',
  `zhu_xing` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '主星',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_ming_gong`(`ming_gong` ASC) USING BTREE,
  INDEX `idx_user_created`(`user_id` ASC, `created_at` ASC) USING BTREE,
  INDEX `idx_created_at`(`created_at` ASC) USING BTREE,
  CONSTRAINT `fk_ziwei_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '紫微斗数记录表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
