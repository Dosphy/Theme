/*
 Navicat Premium Dump SQL

 Source Server         : 本地1
 Source Server Type    : MySQL
 Source Server Version : 80026 (8.0.26)
 Source Host           : localhost:3306
 Source Schema         : keshexiaobao

 Target Server Type    : MySQL
 Target Server Version : 80026 (8.0.26)
 File Encoding         : 65001

 Date: 13/01/2026 20:06:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `teacher` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '老师名称',
  `subject` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学习科目',
  `time` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '计划学习时长',
  `addTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '计划新增时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of appointment
-- ----------------------------

-- ----------------------------
-- Table structure for lesson
-- ----------------------------
DROP TABLE IF EXISTS `lesson`;
CREATE TABLE `lesson`  (
  `id` int NOT NULL,
  `subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程',
  `teacher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程对应老师',
  `portrait` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '老师图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lesson
-- ----------------------------
INSERT INTO `lesson` VALUES (1, 'Python编程基础', '韩振钢', NULL);
INSERT INTO `lesson` VALUES (2, '数据结构与算法', '李东博', NULL);
INSERT INTO `lesson` VALUES (3, '计算机网络原理', '鞠金炜', NULL);
INSERT INTO `lesson` VALUES (4, 'JavaWeb开发', '李嘉伟', NULL);
INSERT INTO `lesson` VALUES (5, '数据库系统设计', '胡新俊', NULL);
INSERT INTO `lesson` VALUES (6, '操作系统原理', '冯一', NULL);
INSERT INTO `lesson` VALUES (7, '前端开发实战（HTML/CSS/JS）', '蔡徐坤', NULL);
INSERT INTO `lesson` VALUES (8, '机器学习入门', '常小雨', NULL);
INSERT INTO `lesson` VALUES (9, 'Linux系统运维', '老师1', NULL);
INSERT INTO `lesson` VALUES (10, '软件工程与项目管理', '老师2', NULL);

-- ----------------------------
-- Table structure for tb_option
-- ----------------------------
DROP TABLE IF EXISTS `tb_option`;
CREATE TABLE `tb_option`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `question_id` bigint NOT NULL COMMENT '所属题目ID',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '选项内容',
  `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '选项编码（如A、B、C、D）',
  `is_correct` int NOT NULL DEFAULT 0 COMMENT '是否正确答案：0-错误，1-正确',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `question_id`(`question_id` ASC) USING BTREE,
  CONSTRAINT `tb_option_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `tb_question` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 169 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '选项表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_option
-- ----------------------------
INSERT INTO `tb_option` VALUES (1, 1, 'int', 'A', 0, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (2, 1, 'String', 'B', 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (3, 1, 'boolean', 'C', 0, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (4, 1, 'double', 'D', 0, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (5, 2, '// 这是注释', 'A', 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (6, 2, '/* 这是注释 */', 'B', 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (7, 2, '-- 这是注释', 'C', 0, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (8, 2, '<!-- 这是注释 -->', 'D', 0, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (9, 3, 'DELETE TABLE', 'A', 0, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (10, 3, 'DROP TABLE', 'B', 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (11, 3, 'REMOVE TABLE', 'C', 0, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (12, 3, 'ERASE TABLE', 'D', 0, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (13, 4, '封装', 'A', 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (14, 4, '继承', 'B', 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (15, 4, '多态', 'C', 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (16, 4, '抽象', 'D', 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (17, 4, '接口', 'E', 0, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (18, 5, '正确', 'A', 0, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (19, 5, '错误', 'B', 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (20, 7, 'def 函数名():', 'A', 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (21, 9, 'GET', 'A', 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (22, 9, 'POST', 'B', 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (23, 9, 'PUT', 'C', 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (24, 9, 'DELETE', 'D', 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (25, 9, 'UPDATE', 'E', 0, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_option` VALUES (76, 33, '队列', 'A', 0, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_option` VALUES (77, 33, '栈', 'B', 1, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_option` VALUES (78, 33, '链表', 'C', 0, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_option` VALUES (79, 33, '数组', 'D', 0, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_option` VALUES (80, 34, '左-根-右', 'A', 0, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_option` VALUES (81, 34, '根-左-右', 'B', 1, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_option` VALUES (82, 34, '右-根-左', 'C', 0, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_option` VALUES (83, 34, '根-右-左', 'D', 0, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_option` VALUES (84, 35, '不可靠的、无连接的服务', 'A', 0, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_option` VALUES (85, 35, '可靠的、面向连接的服务', 'B', 1, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_option` VALUES (86, 35, '可靠的、无连接的服务', 'C', 0, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_option` VALUES (87, 35, '不可靠的、面向连接的服务', 'D', 0, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_option` VALUES (88, 36, '服务器内部错误', 'A', 0, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_option` VALUES (89, 36, '请求成功', 'B', 0, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_option` VALUES (90, 36, '资源未找到', 'C', 1, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_option` VALUES (91, 36, '请求被拒绝', 'D', 0, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_option` VALUES (92, 37, '队列', 'A', 0, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_option` VALUES (93, 37, '栈', 'B', 1, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_option` VALUES (94, 37, '链表', 'C', 0, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_option` VALUES (95, 37, '数组', 'D', 0, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_option` VALUES (96, 38, '左-根-右', 'A', 0, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_option` VALUES (97, 38, '根-左-右', 'B', 1, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_option` VALUES (98, 38, '右-根-左', 'C', 0, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_option` VALUES (99, 38, '根-右-左', 'D', 0, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_option` VALUES (100, 39, '不可靠的、无连接的服务', 'A', 0, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_option` VALUES (101, 39, '可靠的、面向连接的服务', 'B', 1, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_option` VALUES (102, 39, '可靠的、无连接的服务', 'C', 0, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_option` VALUES (103, 39, '不可靠的、面向连接的服务', 'D', 0, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_option` VALUES (104, 40, '服务器内部错误', 'A', 0, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_option` VALUES (105, 40, '请求成功', 'B', 0, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_option` VALUES (106, 40, '资源未找到', 'C', 1, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_option` VALUES (107, 40, '请求被拒绝', 'D', 0, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_option` VALUES (108, 41, '队列', 'A', 0, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_option` VALUES (109, 41, '栈', 'B', 1, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_option` VALUES (110, 41, '链表', 'C', 0, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_option` VALUES (111, 41, '数组', 'D', 0, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_option` VALUES (112, 42, '左-根-右', 'A', 0, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_option` VALUES (113, 42, '根-左-右', 'B', 1, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_option` VALUES (114, 42, '右-根-左', 'C', 0, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_option` VALUES (115, 42, '根-右-左', 'D', 0, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_option` VALUES (116, 43, '不可靠的、无连接的服务', 'A', 0, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_option` VALUES (117, 43, '可靠的、面向连接的服务', 'B', 1, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_option` VALUES (118, 43, '可靠的、无连接的服务', 'C', 0, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_option` VALUES (119, 43, '不可靠的、面向连接的服务', 'D', 0, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_option` VALUES (120, 44, '服务器内部错误', 'A', 0, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_option` VALUES (121, 44, '请求成功', 'B', 0, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_option` VALUES (122, 44, '资源未找到', 'C', 1, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_option` VALUES (123, 44, '请求被拒绝', 'D', 0, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_option` VALUES (128, 45, '队列', 'A', 0, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_option` VALUES (129, 45, '栈', 'B', 1, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_option` VALUES (130, 45, '链表', 'C', 0, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_option` VALUES (131, 45, '数组', 'D', 0, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_option` VALUES (132, 46, '左-根-右', 'A', 0, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_option` VALUES (133, 46, '根-左-右', 'B', 1, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_option` VALUES (134, 46, '右-根-左', 'C', 0, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_option` VALUES (135, 46, '根-右-左', 'D', 0, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_option` VALUES (136, 47, '不可靠的、无连接的服务', 'A', 0, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_option` VALUES (137, 47, '可靠的、面向连接的服务', 'B', 1, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_option` VALUES (138, 47, '可靠的、无连接的服务', 'C', 0, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_option` VALUES (139, 47, '不可靠的、面向连接的服务', 'D', 0, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_option` VALUES (140, 48, '服务器内部错误', 'A', 0, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_option` VALUES (141, 48, '请求成功', 'B', 0, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_option` VALUES (142, 48, '资源未找到', 'C', 1, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_option` VALUES (143, 48, '请求被拒绝', 'D', 0, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_option` VALUES (144, 49, 'ArrayList', 'A', 0, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (145, 49, 'HashMap', 'B', 0, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (146, 49, 'Vector', 'C', 1, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (147, 49, 'LinkedList', 'D', 0, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (148, 50, 'tuple', 'A', 0, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (149, 50, 'string', 'B', 0, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (150, 50, 'list', 'C', 1, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (151, 50, 'int', 'D', 0, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (152, 51, 'DISTINCT', 'A', 1, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (153, 51, 'UNIQUE', 'B', 0, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (154, 51, 'DIFFERENT', 'C', 0, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (155, 51, 'NOT SAME', 'D', 0, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (156, 52, 'div', 'A', 1, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (157, 52, 'span', 'B', 0, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (158, 52, 'p', 'C', 1, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (159, 52, 'a', 'D', 0, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (160, 52, 'h1', 'E', 1, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (161, 53, '提高内存访问速度', 'A', 0, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (162, 53, '扩大物理内存容量', 'B', 0, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (163, 53, '允许程序使用比物理内存更大的地址空间', 'C', 1, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (164, 53, '减少内存碎片', 'D', 0, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (165, 54, 'IOException', 'A', 0, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (166, 54, 'SQLException', 'B', 0, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (167, 54, 'NullPointerException', 'C', 1, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_option` VALUES (168, 54, 'ClassNotFoundException', 'D', 0, '2026-01-11 15:18:39', '2026-01-11 15:18:39');

-- ----------------------------
-- Table structure for tb_question
-- ----------------------------
DROP TABLE IF EXISTS `tb_question`;
CREATE TABLE `tb_question`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '题目内容',
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '题目描述（可选）',
  `type` int NOT NULL COMMENT '题目类型：1-单选题，2-多选题，3-判断题，4-填空题，5-简答题',
  `difficulty` int NOT NULL COMMENT '难度：1-简单，2-中等，3-困难',
  `category_id` bigint NOT NULL COMMENT '分类ID',
  `score` int NOT NULL DEFAULT 0 COMMENT '分值',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `category_id`(`category_id` ASC) USING BTREE,
  CONSTRAINT `tb_question_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `tb_question_category` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '题目表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_question
-- ----------------------------
INSERT INTO `tb_question` VALUES (1, 'Java中，以下哪个不是基本数据类型？', NULL, 1, 1, 1, 5, 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_question` VALUES (2, 'Python中，以下哪个是正确的注释方式？', NULL, 1, 1, 2, 5, 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_question` VALUES (3, 'MySQL中，用于删除表的命令是？', NULL, 1, 2, 3, 5, 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_question` VALUES (4, '以下哪些是Java的面向对象特性？（多选）', NULL, 2, 2, 1, 10, 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_question` VALUES (5, 'HTML是一种编程语言。', NULL, 3, 1, 4, 3, 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_question` VALUES (6, '简述Java中String和StringBuilder的区别。', NULL, 5, 3, 1, 15, 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_question` VALUES (7, 'Python中，如何定义一个函数？', NULL, 4, 2, 2, 8, 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_question` VALUES (8, 'SQL中，GROUP BY子句的作用是什么？', NULL, 5, 2, 3, 10, 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_question` VALUES (9, '以下哪些是HTTP请求方法？（多选）', NULL, 2, 2, 4, 8, 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_question` VALUES (10, '操作系统中的进程和线程有什么区别？', NULL, 5, 3, 5, 12, 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_question` VALUES (31, '以下哪种数据结构是后进先出（LIFO）的？', NULL, 1, 1, 16, 5, 1, '2026-01-11 15:09:06', '2026-01-11 15:09:06');
INSERT INTO `tb_question` VALUES (32, '以下哪种数据结构是后进先出（LIFO）的？', NULL, 1, 1, 16, 5, 1, '2026-01-11 15:14:09', '2026-01-11 15:14:09');
INSERT INTO `tb_question` VALUES (33, '以下哪种数据结构是后进先出（LIFO）的？', NULL, 1, 1, 16, 5, 1, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_question` VALUES (34, '二叉树的前序遍历顺序是？', NULL, 1, 2, 16, 8, 1, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_question` VALUES (35, 'TCP协议提供的是哪种服务？', NULL, 1, 1, 17, 5, 1, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_question` VALUES (36, 'HTTP状态码404表示什么？', NULL, 1, 1, 17, 5, 1, '2026-01-11 15:15:36', '2026-01-11 15:15:36');
INSERT INTO `tb_question` VALUES (37, '以下哪种数据结构是后进先出（LIFO）的？', NULL, 1, 1, 16, 5, 1, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_question` VALUES (38, '二叉树的前序遍历顺序是？', NULL, 1, 2, 16, 8, 1, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_question` VALUES (39, 'TCP协议提供的是哪种服务？', NULL, 1, 1, 17, 5, 1, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_question` VALUES (40, 'HTTP状态码404表示什么？', NULL, 1, 1, 17, 5, 1, '2026-01-11 15:16:16', '2026-01-11 15:16:16');
INSERT INTO `tb_question` VALUES (41, '以下哪种数据结构是后进先出（LIFO）的？', NULL, 1, 1, 16, 5, 1, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_question` VALUES (42, '二叉树的前序遍历顺序是？', NULL, 1, 2, 16, 8, 1, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_question` VALUES (43, 'TCP协议提供的是哪种服务？', NULL, 1, 1, 17, 5, 1, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_question` VALUES (44, 'HTTP状态码404表示什么？', NULL, 1, 1, 17, 5, 1, '2026-01-11 15:16:28', '2026-01-11 15:16:28');
INSERT INTO `tb_question` VALUES (45, '以下哪种数据结构是后进先出（LIFO）的？', NULL, 1, 1, 16, 5, 1, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_question` VALUES (46, '二叉树的前序遍历顺序是？', NULL, 1, 2, 16, 8, 1, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_question` VALUES (47, 'TCP协议提供的是哪种服务？', NULL, 1, 1, 17, 5, 1, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_question` VALUES (48, 'HTTP状态码404表示什么？', NULL, 1, 1, 17, 5, 1, '2026-01-11 15:16:37', '2026-01-11 15:16:37');
INSERT INTO `tb_question` VALUES (49, 'Java中，以下哪个集合是线程安全的？', NULL, 1, 2, 1, 5, 1, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_question` VALUES (50, 'Python中，以下哪个数据类型是可变的？', NULL, 1, 1, 2, 5, 1, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_question` VALUES (51, 'MySQL中，用于查询不重复记录的关键字是？', NULL, 1, 1, 3, 5, 1, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_question` VALUES (52, '以下哪些是HTML的块级元素？（多选）', NULL, 2, 2, 4, 10, 1, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_question` VALUES (53, '以下哪个是虚拟内存的主要作用？', NULL, 1, 2, 5, 8, 1, '2026-01-11 15:18:39', '2026-01-11 15:18:39');
INSERT INTO `tb_question` VALUES (54, 'Java中，以下哪个不是受检异常？', NULL, 1, 2, 1, 5, 1, '2026-01-11 15:18:39', '2026-01-11 15:18:39');

-- ----------------------------
-- Table structure for tb_question_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_question_category`;
CREATE TABLE `tb_question_category`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类描述',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '题目分类表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_question_category
-- ----------------------------
INSERT INTO `tb_question_category` VALUES (1, 'Java基础', 'Java编程语言基础知识', 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_question_category` VALUES (2, 'Python基础', 'Python编程语言基础知识', 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_question_category` VALUES (3, '数据库', '关系型数据库基础知识', 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_question_category` VALUES (4, 'Web开发', 'Web开发相关知识', 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_question_category` VALUES (5, '操作系统', '计算机操作系统知识', 1, '2026-01-07 16:23:05', '2026-01-07 16:23:05');
INSERT INTO `tb_question_category` VALUES (16, '数据结构', '数据结构与算法基础题目', 1, '2026-01-11 15:09:06', '2026-01-11 15:09:06');
INSERT INTO `tb_question_category` VALUES (17, '计算机网络', '计算机网络基础与应用题目', 1, '2026-01-11 15:09:06', '2026-01-11 15:09:06');
INSERT INTO `tb_question_category` VALUES (18, '操作系统', '操作系统原理与应用题目', 1, '2026-01-11 15:09:06', '2026-01-11 15:09:06');
INSERT INTO `tb_question_category` VALUES (19, '软件工程', '软件工程原理与实践题目', 1, '2026-01-11 15:09:06', '2026-01-11 15:09:06');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户主键ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码（建议加密存储）',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '用户昵称',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '头像URL',
  `status` int NULL DEFAULT 1 COMMENT '状态（1：正常，0：禁用等）',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'Dosphy', 'e10adc3949ba59abbe56e057f20f883e', 'Dosphy', '', '18766082270@163.com', '', 1, '2026-01-11 23:45:27', '2026-01-11 23:45:27');

SET FOREIGN_KEY_CHECKS = 1;
