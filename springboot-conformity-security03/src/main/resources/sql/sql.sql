CREATE TABLE `t_user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(20) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '用户名',
  `password` varchar(128) COLLATE utf8mb4_german2_ci DEFAULT NULL COMMENT '用户密码',
  `enabled` int DEFAULT '1',
  `locked` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_USER_USERNAME` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

CREATE TABLE `t_role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) COLLATE utf8mb4_german2_ci DEFAULT NULL COMMENT '角色名',
  `remark` varchar(50) COLLATE utf8mb4_german2_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_ROLE_NAME` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

CREATE TABLE `t_menu` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pattern` varchar(200) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

CREATE TABLE `t_user_role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `role_id` int DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

CREATE TABLE `t_menu_role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_id` int DEFAULT NULL COMMENT 'menu_id',
  `role_id` int DEFAULT NULL COMMENT 'role_id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

INSERT INTO `t_role` VALUES (1, 'ROLE_dba', '数据库管理员');
INSERT INTO `t_role` VALUES (2, 'ROLE_admin', '系统管理员');
INSERT INTO `t_role` VALUES (3, 'ROLE_user', '用户');

--

INSERT INTO `t_user` VALUES (1, 'root', 'master', 1, 0);
INSERT INTO `t_user` VALUES (2, 'admin', 'master', 1, 0);
INSERT INTO `t_user` VALUES (3, 'sang', 'master', 1, 0);

--

INSERT INTO `t_user_role` VALUES (1, 1, 1);
INSERT INTO `t_user_role` VALUES (2, 1, 2);
INSERT INTO `t_user_role` VALUES (3, 2, 2);
INSERT INTO `t_user_role` VALUES (4, 3, 3);

--
COMMIT;