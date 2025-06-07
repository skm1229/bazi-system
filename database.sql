-- =====================================================
-- 八字系统数据库脚本
-- 数据库名称: bazi-system
-- 作者: skm1229
-- 创建时间: 2024-01-01
-- =====================================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS `bazi-system` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `bazi-system`;

-- =====================================================
-- 用户表
-- =====================================================
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL COMMENT '用户ID（雪花算法）',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码（加密）',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像URL',
  `status` tinyint(1) DEFAULT 1 COMMENT '状态（0:禁用 1:启用）',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  UNIQUE KEY `uk_email` (`email`),
  KEY `idx_phone` (`phone`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- =====================================================
-- 八字记录表
-- =====================================================
DROP TABLE IF EXISTS `bazi_records`;
CREATE TABLE `bazi_records` (
  `id` bigint(20) NOT NULL COMMENT '记录ID（雪花算法）',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `gender` tinyint(1) NOT NULL COMMENT '性别（0:女 1:男）',
  `birth_date` varchar(50) NOT NULL COMMENT '出生日期（如：2002年02月10日）',
  `birth_time` varchar(50) NOT NULL COMMENT '出生时间（如：2003年1月15日00时00分）',
  `address` varchar(200) DEFAULT NULL COMMENT '出生地址',
  `occupy` varchar(100) DEFAULT '未填写' COMMENT '占事',
  
  -- 四柱干支（分别存储天干和地支）
  `year_gan` varchar(2) NOT NULL COMMENT '年干',
  `year_zhi` varchar(2) NOT NULL COMMENT '年支',
  `month_gan` varchar(2) NOT NULL COMMENT '月干',
  `month_zhi` varchar(2) NOT NULL COMMENT '月支',
  `day_gan` varchar(2) NOT NULL COMMENT '日干',
  `day_zhi` varchar(2) NOT NULL COMMENT '日支',
  `hour_gan` varchar(2) NOT NULL COMMENT '时干',
  `hour_zhi` varchar(2) NOT NULL COMMENT '时支',
  
  -- 纳音
  `year_na_yin` varchar(10) DEFAULT NULL COMMENT '年柱纳音',
  `month_na_yin` varchar(10) DEFAULT NULL COMMENT '月柱纳音',
  `day_na_yin` varchar(10) DEFAULT NULL COMMENT '日柱纳音',
  `hour_na_yin` varchar(10) DEFAULT NULL COMMENT '时柱纳音',
  
  -- 系统字段
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_name` (`name`),
  KEY `idx_gender` (`gender`),
  KEY `idx_created_at` (`created_at`),
  
  -- 外键约束
  CONSTRAINT `fk_bazi_records_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='八字记录表';

-- =====================================================
-- 排盘记录表（可选，用于存储完整的排盘结果）
-- =====================================================
DROP TABLE IF EXISTS `paipan_records`;
CREATE TABLE `paipan_records` (
  `id` bigint(20) NOT NULL COMMENT '记录ID（雪花算法）',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `bazi_record_id` bigint(20) NOT NULL COMMENT '八字记录ID',
  
  -- 基础信息
  `solar_date` varchar(50) DEFAULT NULL COMMENT '公历日期',
  `lunar_date` varchar(50) DEFAULT NULL COMMENT '农历日期',
  `sheng_xiao` varchar(10) DEFAULT NULL COMMENT '生肖',
  `xing_zuo` varchar(10) DEFAULT NULL COMMENT '星座',
  `ji_jie` varchar(10) DEFAULT NULL COMMENT '季节',
  
  -- 宫位信息
  `tai_yuan` varchar(20) DEFAULT NULL COMMENT '胎元',
  `tai_xi` varchar(20) DEFAULT NULL COMMENT '胎息',
  `ming_gong` varchar(20) DEFAULT NULL COMMENT '命宫',
  `shen_gong` varchar(20) DEFAULT NULL COMMENT '身宫',
  
  -- 分析结果
  `ge_ju` text DEFAULT NULL COMMENT '格局判断',
  `day_zhu_lun_ming` text DEFAULT NULL COMMENT '日柱论命',
  `yin_yuan_fen_xi` text DEFAULT NULL COMMENT '姻缘分析',
  `ming_gua` varchar(50) DEFAULT NULL COMMENT '命卦',
  `ming_gua_fen_xi` text DEFAULT NULL COMMENT '命卦分析',
  `wu_xing_fen_xi` text DEFAULT NULL COMMENT '五行分析',
  
  -- 系统字段
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_bazi_record_id` (`bazi_record_id`),
  KEY `idx_created_at` (`created_at`),
  
  -- 外键约束
  CONSTRAINT `fk_paipan_records_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_paipan_records_bazi_record_id` FOREIGN KEY (`bazi_record_id`) REFERENCES `bazi_records` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='排盘记录表';

-- =====================================================
-- 初始化数据
-- =====================================================

-- 插入测试用户
INSERT INTO `users` (`id`, `username`, `password`, `email`, `nickname`, `status`) VALUES
(1, 'admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKyZFzUNjKjFKBedKxrOF8rdMoSu', 'admin@bazi.com', '管理员', 1),
(2, 'test', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKyZFzUNjKjFKBedKxrOF8rdMoSu', 'test@bazi.com', '测试用户', 1);

-- 插入测试八字记录
INSERT INTO `bazi_records` (`id`, `user_id`, `name`, `gender`, `birth_date`, `birth_time`, `address`, `occupy`, 
                           `year_gan`, `year_zhi`, `month_gan`, `month_zhi`, `day_gan`, `day_zhi`, `hour_gan`, `hour_zhi`,
                           `year_na_yin`, `month_na_yin`, `day_na_yin`, `hour_na_yin`) VALUES
(1, 1, '张三', 1, '1990年05月15日', '1990年05月15日08时30分', '北京市', '测试排盘',
 '庚', '午', '辛', '巳', '甲', '子', '戊', '辰',
 '路旁土', '白腊金', '海中金', '大林木'),
(2, 2, '李四', 0, '1985年12月20日', '1985年12月20日14时15分', '上海市', '事业运势',
 '乙', '丑', '戊', '子', '丁', '未', '丁', '未',
 '海中金', '霹雳火', '天河水', '天河水');

-- =====================================================
-- 索引优化建议
-- =====================================================

-- 为经常查询的字段添加复合索引
CREATE INDEX `idx_bazi_records_user_gender` ON `bazi_records` (`user_id`, `gender`);
CREATE INDEX `idx_bazi_records_user_created` ON `bazi_records` (`user_id`, `created_at` DESC);

-- 为排盘记录表添加复合索引
CREATE INDEX `idx_paipan_records_user_created` ON `paipan_records` (`user_id`, `created_at` DESC);

-- =====================================================
-- 权限设置（可选）
-- =====================================================

-- 创建数据库用户（根据实际需要调整）
-- CREATE USER 'bazi_user'@'localhost' IDENTIFIED BY 'your_password_here';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON `bazi-system`.* TO 'bazi_user'@'localhost';
-- FLUSH PRIVILEGES;

-- =====================================================
-- 备份建议
-- =====================================================

-- 定期备份命令示例：
-- mysqldump -u root -p bazi-system > bazi_backup_$(date +%Y%m%d_%H%M%S).sql

-- =====================================================
-- 性能优化建议
-- =====================================================

-- 1. 定期分析表统计信息
-- ANALYZE TABLE users, bazi_records, paipan_records;

-- 2. 定期优化表
-- OPTIMIZE TABLE users, bazi_records, paipan_records;

-- 3. 监控慢查询
-- SET GLOBAL slow_query_log = 'ON';
-- SET GLOBAL long_query_time = 2;

-- =====================================================
-- 脚本执行完成
-- =====================================================
