# ************************************************************
# Sequel Pro SQL dump
# Version 4004
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.10)
# Database: kumulus
# Generation Time: 2014-01-28 12:10:47 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table company
# ------------------------------------------------------------

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table currency
# ------------------------------------------------------------

DROP TABLE IF EXISTS `currency`;

CREATE TABLE `currency` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `short_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table document
# ------------------------------------------------------------

DROP TABLE IF EXISTS `document`;

CREATE TABLE `document` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `file_id` bigint(20) DEFAULT NULL,
  `identifier` varchar(30) DEFAULT NULL,
  `literal` varchar(255) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK335CD11BD24E14D9` (`file_id`),
  KEY `FK335CD11BDB59EAD3` (`company_id`),
  KEY `FK335CD11B4D5E1553` (`project_id`),
  KEY `FK335CD11B352A849C` (`type_id`),
  CONSTRAINT `FK335CD11B352A849C` FOREIGN KEY (`type_id`) REFERENCES `document_type` (`id`),
  CONSTRAINT `FK335CD11B4D5E1553` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`),
  CONSTRAINT `FK335CD11BD24E14D9` FOREIGN KEY (`file_id`) REFERENCES `ufile` (`id`),
  CONSTRAINT `FK335CD11BDB59EAD3` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table document_type
# ------------------------------------------------------------

DROP TABLE IF EXISTS `document_type`;

CREATE TABLE `document_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table image
# ------------------------------------------------------------

DROP TABLE IF EXISTS `image`;

CREATE TABLE `image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `compressed` bit(1) NOT NULL,
  `file_id` bigint(20) NOT NULL,
  `height` bigint(20) NOT NULL,
  `thumbnail` bit(1) NOT NULL,
  `width` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5FAA95BD24E14D9` (`file_id`),
  CONSTRAINT `FK5FAA95BD24E14D9` FOREIGN KEY (`file_id`) REFERENCES `ufile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table line_item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `line_item`;

CREATE TABLE `line_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `amount` float NOT NULL,
  `currency_id` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `page_id` bigint(20) NOT NULL,
  `price` float DEFAULT NULL,
  `quantity` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK94ED5C7E172ECD01` (`currency_id`),
  KEY `FK94ED5C7EBB855FC1` (`page_id`),
  CONSTRAINT `FK94ED5C7EBB855FC1` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`),
  CONSTRAINT `FK94ED5C7E172ECD01` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table node
# ------------------------------------------------------------

DROP TABLE IF EXISTS `node`;

CREATE TABLE `node` (
  `node_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `barcode` varchar(45) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `create_datetime` datetime DEFAULT NULL,
  `creator_id` varchar(255) NOT NULL,
  `internal_comment` varchar(200) DEFAULT NULL,
  `last_update_datetime` datetime DEFAULT NULL,
  `last_update_id` varchar(255) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `parent_node_id` bigint(20) DEFAULT NULL,
  `project_id` bigint(20) NOT NULL,
  `status` varchar(10) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`node_id`),
  KEY `FK33AE024D5E1553` (`project_id`),
  KEY `FK33AE0278962C4C` (`parent_node_id`),
  CONSTRAINT `FK33AE0278962C4C` FOREIGN KEY (`parent_node_id`) REFERENCES `node` (`node_id`),
  CONSTRAINT `FK33AE024D5E1553` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table page
# ------------------------------------------------------------

DROP TABLE IF EXISTS `page`;

CREATE TABLE `page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `document_id` bigint(20) NOT NULL,
  `first` bit(1) NOT NULL,
  `last` bit(1) NOT NULL,
  `literal` varchar(255) NOT NULL,
  `node_id` bigint(20) NOT NULL,
  `number` bigint(20) NOT NULL,
  `scan_batch_id` bigint(20) NOT NULL,
  `scan_image_id` bigint(20) NOT NULL,
  `thumbnail_image_id` bigint(20) NOT NULL,
  `view_image_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `scan_image_id` (`scan_image_id`),
  UNIQUE KEY `thumbnail_image_id` (`thumbnail_image_id`),
  UNIQUE KEY `view_image_id` (`view_image_id`),
  KEY `FK34628F6971C6E1` (`node_id`),
  KEY `FK34628F237BB8C1` (`document_id`),
  KEY `FK34628F1E4C2278` (`scan_batch_id`),
  KEY `FK34628F8332BA46` (`thumbnail_image_id`),
  KEY `FK34628FD477F7D5` (`scan_image_id`),
  KEY `FK34628FB852B0AD` (`view_image_id`),
  CONSTRAINT `FK34628FB852B0AD` FOREIGN KEY (`view_image_id`) REFERENCES `image` (`id`),
  CONSTRAINT `FK34628F1E4C2278` FOREIGN KEY (`scan_batch_id`) REFERENCES `scan_batch` (`id`),
  CONSTRAINT `FK34628F237BB8C1` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`),
  CONSTRAINT `FK34628F6971C6E1` FOREIGN KEY (`node_id`) REFERENCES `node` (`node_id`),
  CONSTRAINT `FK34628F8332BA46` FOREIGN KEY (`thumbnail_image_id`) REFERENCES `image` (`id`),
  CONSTRAINT `FK34628FD477F7D5` FOREIGN KEY (`scan_image_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table project
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `project_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_id` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `company` varchar(50) NOT NULL,
  `literal` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `project_name` varchar(50) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  KEY `FKED904B199C6248C5` (`client_id`),
  CONSTRAINT `FKED904B199C6248C5` FOREIGN KEY (`client_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table project_line_item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project_line_item`;

CREATE TABLE `project_line_item` (
  `project_line_items_id` bigint(20) DEFAULT NULL,
  `line_item_id` bigint(20) DEFAULT NULL,
  KEY `FK7256D823A5491` (`project_line_items_id`),
  KEY `FK7256D8EAC0028A` (`line_item_id`),
  CONSTRAINT `FK7256D8EAC0028A` FOREIGN KEY (`line_item_id`) REFERENCES `line_item` (`id`),
  CONSTRAINT `FK7256D823A5491` FOREIGN KEY (`project_line_items_id`) REFERENCES `project` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `authority` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `authority` (`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table scan_batch
# ------------------------------------------------------------

DROP TABLE IF EXISTS `scan_batch`;

CREATE TABLE `scan_batch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  `timestamp` datetime NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD2636EF84D5E1553` (`project_id`),
  CONSTRAINT `FKD2636EF84D5E1553` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table task
# ------------------------------------------------------------

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `batch_instanceid` varchar(255) DEFAULT NULL,
  `batch_instance_url_id` varchar(255) DEFAULT NULL,
  `created` datetime NOT NULL,
  `document_id` bigint(20) NOT NULL,
  `status` bigint(20) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK363585237BB8C1` (`document_id`),
  CONSTRAINT `FK363585237BB8C1` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table ufile
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ufile`;

CREATE TABLE `ufile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `date_uploaded` datetime NOT NULL,
  `downloads` int(11) NOT NULL,
  `extension` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `size` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `password_expired` bit(1) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FK143BF46A3D7D4261` (`role_id`),
  KEY `FK143BF46AE2A80641` (`user_id`),
  CONSTRAINT `FK143BF46AE2A80641` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK143BF46A3D7D4261` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
