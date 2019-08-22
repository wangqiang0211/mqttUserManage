

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mqtt_acl
-- ----------------------------
DROP TABLE IF EXISTS `mqtt_acl`;
CREATE TABLE `mqtt_acl` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `allow` int(1) DEFAULT NULL COMMENT '0: deny, 1: allow',
  `ipaddr` varchar(60) DEFAULT NULL COMMENT 'IpAddress',
  `username` varchar(100) DEFAULT NULL COMMENT 'Username',
  `clientid` varchar(100) DEFAULT NULL COMMENT 'ClientId',
  `access` int(2) NOT NULL COMMENT '1: subscribe, 2: publish, 3: pubsub',
  `topic` varchar(100) NOT NULL DEFAULT '' COMMENT 'Topic Filter',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mqtt_acl
-- ----------------------------
INSERT INTO `mqtt_acl` VALUES ('1', '1', null, '$all', null, '2', '#');
INSERT INTO `mqtt_acl` VALUES ('2', '0', null, '$all', null, '1', '$SYS/#');
INSERT INTO `mqtt_acl` VALUES ('3', '0', null, '$all', null, '1', 'eq #');
INSERT INTO `mqtt_acl` VALUES ('5', '1', '127.0.0.1', null, null, '2', '$SYS/#');
INSERT INTO `mqtt_acl` VALUES ('6', '1', '127.0.0.1', null, null, '2', '#');
INSERT INTO `mqtt_acl` VALUES ('7', '1', null, 'dashboard', null, '1', '$SYS/#');

-- ----------------------------
-- Table structure for mqtt_user
-- ----------------------------
DROP TABLE IF EXISTS `mqtt_user`;
CREATE TABLE `mqtt_user` (
  `device_name` int(11) NOT NULL AUTO_INCREMENT,
  `appkey` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `device_nick` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `device_secret` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `device_secret_decode` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_enable` int(11) DEFAULT NULL,
  `is_superuser` int(11) DEFAULT NULL,
  `mqtt_user_rh_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`device_name`),
  KEY `FKmr7x29qxgjb2uugb7lde329r0` (`mqtt_user_rh_id`),
  KEY `appkey` (`appkey`,`device_name`,`device_nick`) USING BTREE,
  CONSTRAINT `FKmr7x29qxgjb2uugb7lde329r0` FOREIGN KEY (`mqtt_user_rh_id`) REFERENCES `mqtt_user_rh` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000000 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of mqtt_user
-- ----------------------------

-- ----------------------------
-- Table structure for mqtt_user_rh
-- ----------------------------
DROP TABLE IF EXISTS `mqtt_user_rh`;
CREATE TABLE `mqtt_user_rh` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `appkey` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `r_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `appkey` (`appkey`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of mqtt_user_rh
-- ----------------------------
