/*
 Navicat Premium Data Transfer

 Source Server         : Rose
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : vue

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 20/12/2022 16:11:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course`  (
  `student_id` int NOT NULL COMMENT '学生id',
  `course_id` int NOT NULL COMMENT '课程id',
  PRIMARY KEY (`student_id`, `course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_course
-- ----------------------------
INSERT INTO `student_course` VALUES (2, 1);
INSERT INTO `student_course` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_article
-- ----------------------------
DROP TABLE IF EXISTS `sys_article`;
CREATE TABLE `sys_article`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '文章编号',
  `header` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
  `user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发布人',
  `time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '最新发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_article
-- ----------------------------
INSERT INTO `sys_article` VALUES (1, '文章一', '### 再也不会了\n+ this is d.rose\n**和过去告个别吧**\n不说再见\n*可她心如冰*\n![ai5.png](http://127.0.0.1:9090/file/b658bf6b702041f1b34c5abcba3f8837.png)\n*我想看看这句话是否起作用*', 'dong', '2022-12-18 15:49:32');
INSERT INTO `sys_article` VALUES (2, '文章二', '*她已不在*\n**她的眼眸**\n![ai4.jpg](http://127.0.0.1:9090/file/fbbee6e8232b4252be9c07da0e895e55.jpg)', 'dong', '2022-12-18 17:49:32');
INSERT INTO `sys_article` VALUES (3, '三', '## 可她早已不在\n**++==思念汇集成海==++**\n![ai3.jpg](http://127.0.0.1:9090/file/bf87e8b8906e465685453bc68fd861bd.jpg)', 'admin', '2022-12-18 19:24:31');
INSERT INTO `sys_article` VALUES (4, '文章四', '![ai5.png](http://127.0.0.1:9090/file/b658bf6b702041f1b34c5abcba3f8837.png)', 'admin', '2022-12-18 19:40:49');

-- ----------------------------
-- Table structure for sys_comment
-- ----------------------------
DROP TABLE IF EXISTS `sys_comment`;
CREATE TABLE `sys_comment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '评论序号',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
  `user_id` int NULL DEFAULT NULL COMMENT '评论人id',
  `time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评论时间',
  `pid` int NULL DEFAULT NULL COMMENT '父级评论(回复的评论)id',
  `puser_id` int NULL DEFAULT NULL COMMENT '父级评论(回复的评论)用户id',
  `puser_nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '父级评论(回复的评论)用户昵称',
  `origin_id` int NULL DEFAULT NULL COMMENT '最上级评论id',
  `article_id` int NULL DEFAULT NULL COMMENT '关联文章id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_comment
-- ----------------------------
INSERT INTO `sys_comment` VALUES (2, '慢慢忘记她吧...', 1, '2022-12-19 14:36:35', NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_comment` VALUES (6, '谢谢那些美好的瞬间..................', 2, '2022-12-19 14:38:55', 1, 1, 'dong', 1, 1);
INSERT INTO `sys_comment` VALUES (8, '这一切都会好起来的............', 3, '2022-12-19 14:41:35', 7, 1, 'dong', 5, 1);
INSERT INTO `sys_comment` VALUES (9, '1111111111111111111111', 3, '2022-12-19 14:44:11', NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_comment` VALUES (10, '会的\n', 3, '2022-12-19 15:00:20', 7, 1, 'dong', 5, 1);
INSERT INTO `sys_comment` VALUES (11, '可她心如冰', 2, '2022-12-19 15:07:38', 9, 3, '库里', 9, 1);
INSERT INTO `sys_comment` VALUES (12, '我已不再....', 2, '2022-12-19 15:07:49', 2, 1, 'dong', 2, 1);
INSERT INTO `sys_comment` VALUES (13, '学会释怀', 1, '2022-12-19 15:09:13', 11, 2, '猪八戒', 9, 1);
INSERT INTO `sys_comment` VALUES (15, NULL, 1, '2022-12-19 15:13:56', NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_comment` VALUES (16, '12233454', 1, '2022-12-19 15:14:03', 13, 1, 'dong', 9, 1);

-- ----------------------------
-- Table structure for sys_course
-- ----------------------------
DROP TABLE IF EXISTS `sys_course`;
CREATE TABLE `sys_course`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '课程编号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程名称',
  `score` int NULL DEFAULT NULL COMMENT '学分',
  `times` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上课时间',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '是否开课',
  `teacher_id` int NULL DEFAULT NULL COMMENT '授课老师id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_course
-- ----------------------------
INSERT INTO `sys_course` VALUES (1, '高等数学', 4, '2022/12/14', NULL, 3);
INSERT INTO `sys_course` VALUES (2, '线性代数', 2, '2022/12/1', NULL, 3);
INSERT INTO `sys_course` VALUES (3, '概率论', 2, '2022/12/11', NULL, 8);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'user', 'el-icon-user', 'icon');
INSERT INTO `sys_dict` VALUES (2, 'house', 'el-icon-house', 'icon');
INSERT INTO `sys_dict` VALUES (3, 'menu', 'el-icon-menu', 'icon');
INSERT INTO `sys_dict` VALUES (4, 's-custom', 'el-icon-s-custom', 'icon');
INSERT INTO `sys_dict` VALUES (5, 's-grid', 'el-icon-s-grid', 'icon');
INSERT INTO `sys_dict` VALUES (6, 'document', 'el-icon-document', 'icon');
INSERT INTO `sys_dict` VALUES (7, 'usersolid', 'el-icon-user-solid', 'icon');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件类型',
  `size` bigint NULL DEFAULT NULL COMMENT '文件大小(kb)',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '下载链接',
  `md5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'md5加密',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除(默认0，不删除）',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT '是否禁用链接（默认1，禁用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (1, 'banner01.jpg', 'jpg', 152961, NULL, NULL, 0, 1);
INSERT INTO `sys_file` VALUES (4, 'logo.png', 'png', 25, 'http://localhost:9090/file/20d569131fae422385295f9dd07aead0.png', '7c9273783aee918d320b8179e5527bdc', 0, 1);
INSERT INTO `sys_file` VALUES (5, '姜山中学.png', 'png', 25, 'http://localhost:9090/file/20d569131fae422385295f9dd07aead0.png', '7c9273783aee918d320b8179e5527bdc', 0, 1);
INSERT INTO `sys_file` VALUES (6, '附件1+浙江科技学院大学生课外科技创新与实践项目立项申报表(1).doc', 'doc', 606, 'http://localhost:9090/file/47806c570986437ebf5110274aff3f48.doc', 'bcc1a7e7f621e4da9eac0c428de87cbc', 1, 1);
INSERT INTO `sys_file` VALUES (7, 'CSDN_1668475981204_edit_10854642144697.jpg', 'jpg', 14, 'http://localhost:9090/file/53aa39cbc2564784bc1bf07c04764a63.jpg', 'e04fec3d31eea08d57c108ca67a7a064', 0, 0);
INSERT INTO `sys_file` VALUES (8, 'CSDN_1668475981204_edit_10854642144697.jpg', 'jpg', 14, 'http://localhost:9090/file/53aa39cbc2564784bc1bf07c04764a63.jpg', 'e04fec3d31eea08d57c108ca67a7a064', 0, 0);
INSERT INTO `sys_file` VALUES (9, 'CSDN_1668475981204_edit_10854642144697.jpg', 'jpg', 14, 'http://localhost:9090/file/53aa39cbc2564784bc1bf07c04764a63.jpg', 'e04fec3d31eea08d57c108ca67a7a064', 0, 1);
INSERT INTO `sys_file` VALUES (10, '姜山中学.png', 'png', 25, 'http://localhost:9090/file/20d569131fae422385295f9dd07aead0.png', '7c9273783aee918d320b8179e5527bdc', 0, 1);
INSERT INTO `sys_file` VALUES (11, 'CSDN_1668475981204_edit_10854642144697.jpg', 'jpg', 14, 'http://localhost:9090/file/53aa39cbc2564784bc1bf07c04764a63.jpg', 'e04fec3d31eea08d57c108ca67a7a064', 0, 0);
INSERT INTO `sys_file` VALUES (12, 'logo.png', 'png', 25, 'http://localhost:9090/file/20d569131fae422385295f9dd07aead0.png', '7c9273783aee918d320b8179e5527bdc', 0, 0);
INSERT INTO `sys_file` VALUES (13, 'CSDN_1668475981204_edit_10854642144697.jpg', 'jpg', 14, 'http://localhost:9090/file/53aa39cbc2564784bc1bf07c04764a63.jpg', 'e04fec3d31eea08d57c108ca67a7a064', 0, 0);
INSERT INTO `sys_file` VALUES (14, 'ai.jpg', 'jpg', 10, 'http://localhost:9090/file/2c51c95b2b5b40a4b3241b1ea2f946e4.jpg', '8a7995f17fb09eba53f79b9b88350322', 0, 0);
INSERT INTO `sys_file` VALUES (15, 'ai5.png', 'png', 52, 'http://127.0.0.1:9090/file/b658bf6b702041f1b34c5abcba3f8837.png', '8a8c5033db37d408d657c1317c13546b', 0, 0);
INSERT INTO `sys_file` VALUES (16, 'share_d7d08e1b8ce61787e3cea24274615a5e.mp4', 'mp4', 10380, 'http://127.0.0.1:9090/file/71f33fce1d704b6bb1fd34446615fd52.mp4', 'b70ba1279bbe0ac13d6b8248e7a58171', 0, 0);
INSERT INTO `sys_file` VALUES (17, 'mmexport1643384516425.mp4', 'mp4', 6568, 'http://127.0.0.1:9090/file/a6321115b2614b9a9784208945e04839.mp4', 'bc185b7306ba7f27d43fa7b1c24f8ef9', 0, 0);
INSERT INTO `sys_file` VALUES (18, 'ai5.png', 'png', 52, 'http://127.0.0.1:9090/file/b658bf6b702041f1b34c5abcba3f8837.png', '8a8c5033db37d408d657c1317c13546b', 0, 0);
INSERT INTO `sys_file` VALUES (19, 'ai5.png', 'png', 52, 'http://127.0.0.1:9090/file/b658bf6b702041f1b34c5abcba3f8837.png', '8a8c5033db37d408d657c1317c13546b', 0, 0);
INSERT INTO `sys_file` VALUES (20, 'ai4.jpg', 'jpg', 14, 'http://127.0.0.1:9090/file/fbbee6e8232b4252be9c07da0e895e55.jpg', '138e5f5f699630918bb05fed908e51c4', 0, 0);
INSERT INTO `sys_file` VALUES (21, 'ai3.jpg', 'jpg', 15, 'http://127.0.0.1:9090/file/bf87e8b8906e465685453bc68fd861bd.jpg', '0a58af568ed76db85e498186a68b24a1', 0, 0);
INSERT INTO `sys_file` VALUES (22, 'ai5.png', 'png', 52, 'http://127.0.0.1:9090/file/b658bf6b702041f1b34c5abcba3f8837.png', '8a8c5033db37d408d657c1317c13546b', 0, 0);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `pid` int NULL DEFAULT NULL COMMENT '父节点',
  `page_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '页面路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '主页', '/home', 'el-icon-user', 'Home', NULL, 'Home');
INSERT INTO `sys_menu` VALUES (2, '系统管理', '', 'el-icon-menu', 'system', NULL, NULL);
INSERT INTO `sys_menu` VALUES (4, '用户管理', '/user', 'el-icon-s-custom', '用户', 2, 'User');
INSERT INTO `sys_menu` VALUES (5, '角色管理', '/role', 'el-icon-user-solid', '角色', 2, 'Role');
INSERT INTO `sys_menu` VALUES (6, '菜单管理', '/menu', 'el-icon-menu', '菜单', 2, 'Menu');
INSERT INTO `sys_menu` VALUES (7, '文件管理', '/file', 'el-icon-document', '文件', 2, 'Files');
INSERT INTO `sys_menu` VALUES (8, '回收站', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (9, '新闻管理', '/news', 'el-icon-menu', NULL, NULL, 'News');
INSERT INTO `sys_menu` VALUES (11, '课程管理', '/course', 'el-icon-user-solid', '开放课程信息', NULL, 'Course');
INSERT INTO `sys_menu` VALUES (12, '高德地图', '/map', 'el-icon-house', '地理位置展示', NULL, 'Map');
INSERT INTO `sys_menu` VALUES (13, '文章管理', '/article', 'el-icon-document', '文章的发布和编辑', NULL, 'Article');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `flag` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '唯一标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'admin', '管理员', 'ROLE_ADMIN');
INSERT INTO `sys_role` VALUES (2, 'user', '普通用户', 'ROLE_USER');
INSERT INTO `sys_role` VALUES (3, 'teacher', '老师', 'ROLE_TEACHER');
INSERT INTO `sys_role` VALUES (4, 'student', '学生', 'ROLE_STUDENT');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int NOT NULL COMMENT '角色id',
  `menu_id` int NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 6);
INSERT INTO `sys_role_menu` VALUES (1, 7);
INSERT INTO `sys_role_menu` VALUES (1, 8);
INSERT INTO `sys_role_menu` VALUES (1, 9);
INSERT INTO `sys_role_menu` VALUES (1, 11);
INSERT INTO `sys_role_menu` VALUES (1, 12);
INSERT INTO `sys_role_menu` VALUES (1, 13);
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 5);
INSERT INTO `sys_role_menu` VALUES (2, 6);
INSERT INTO `sys_role_menu` VALUES (2, 8);
INSERT INTO `sys_role_menu` VALUES (3, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 4);
INSERT INTO `sys_role_menu` VALUES (3, 11);
INSERT INTO `sys_role_menu` VALUES (4, 1);
INSERT INTO `sys_role_menu` VALUES (4, 2);
INSERT INTO `sys_role_menu` VALUES (4, 4);
INSERT INTO `sys_role_menu` VALUES (4, 11);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像地址',
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '身份',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'zhang', 'zhang', 'dong', NULL, NULL, NULL, '2022-12-02 15:27:35', NULL, 'ROLE_ADMIN');
INSERT INTO `sys_user` VALUES (2, 'zhu', '123456', '猪八戒', 'zhu@163.com', '19858196146', '浙江杭州', '2022-01-21 13:47:55', 'http://localhost:9090/file/53aa39cbc2564784bc1bf07c04764a63.jpg', 'ROLE_STUDENT');
INSERT INTO `sys_user` VALUES (3, 'curry', '12345', '库里', NULL, NULL, NULL, '2022-12-13 14:34:34', 'http://localhost:9090/file/2c51c95b2b5b40a4b3241b1ea2f946e4.jpg', 'ROLE_STUDENT');
INSERT INTO `sys_user` VALUES (6, '哪吒', '222', '红孩儿', 'fenghuolun@126.com', NULL, '天庭', '2022-02-21 18:44:25', NULL, 'ROLE_ADMIN');
INSERT INTO `sys_user` VALUES (7, 'Zhu2', NULL, '故事', 'Zhu@126.com', '123894o009712', '宁波', '2022-03-21 21:49:12', NULL, 'ROLE_TEACHER');
INSERT INTO `sys_user` VALUES (8, 'dongjie', '12345', '善言', 'dong@126.com', '19858196146', '宁波', '2022-04-22 09:34:05', NULL, 'ROLE_TEACHER');
INSERT INTO `sys_user` VALUES (9, 'D', NULL, '南浔', 'D@126.com', '13875678900', '杭州', '2022-05-22 09:37:29', NULL, NULL);
INSERT INTO `sys_user` VALUES (13, 'Dong', '12345', '董小姐', 'dong_0908@163.com', '15757879354', '浙江宁波', '2022-06-22 13:10:33', NULL, 'ROLE_STUDENT');
INSERT INTO `sys_user` VALUES (19, 'Zhu3', NULL, '故事遗憾散场', NULL, NULL, NULL, '2022-07-22 13:35:39', NULL, 'ROLE_USER');
INSERT INTO `sys_user` VALUES (23, 'D', '', '颗秒男孩', 'D@126.com', '13875678900', '杭州', '2022-08-24 16:13:36', NULL, NULL);
INSERT INTO `sys_user` VALUES (24, 'D', '', '颗秒男孩', 'D@126.com', '13875678900', '杭州', '2022-11-24 16:25:02', NULL, 'ROLE_USER');
INSERT INTO `sys_user` VALUES (28, 'admin', 'admin', 'admin', NULL, NULL, NULL, '2022-11-29 17:59:47', NULL, 'ROLE_ADMIN');
INSERT INTO `sys_user` VALUES (29, '董杰', NULL, 'dong', NULL, NULL, '宁波', '2022-12-17 17:08:18', NULL, 'ROLE_STUDENT');

-- ----------------------------
-- Table structure for sys_wallpaper
-- ----------------------------
DROP TABLE IF EXISTS `sys_wallpaper`;
CREATE TABLE `sys_wallpaper`  (
  `id` int NOT NULL COMMENT 'id',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '壁纸链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_wallpaper
-- ----------------------------
INSERT INTO `sys_wallpaper` VALUES (1, 'https://th.wallhaven.cc/small/kx/kx98xd.jpg');
INSERT INTO `sys_wallpaper` VALUES (2, 'https://th.wallhaven.cc/small/zy/zygeko.jpg');
INSERT INTO `sys_wallpaper` VALUES (3, 'https://th.wallhaven.cc/small/kx/kx36mq.jpg');
INSERT INTO `sys_wallpaper` VALUES (4, 'https://th.wallhaven.cc/small/m9/m9xyg8.jpg');
INSERT INTO `sys_wallpaper` VALUES (5, 'https://th.wallhaven.cc/small/j3/j3m8y5.jpg');
INSERT INTO `sys_wallpaper` VALUES (6, 'https://th.wallhaven.cc/small/dp/dpl3x3.jpg');
INSERT INTO `sys_wallpaper` VALUES (7, 'https://th.wallhaven.cc/small/e7/e7jj6r.jpg');
INSERT INTO `sys_wallpaper` VALUES (8, 'https://th.wallhaven.cc/small/9m/9mjoy1.jpg');

SET FOREIGN_KEY_CHECKS = 1;
