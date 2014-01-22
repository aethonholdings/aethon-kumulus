# ************************************************************
# Sequel Pro SQL dump
# Version 4004
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.10)
# Database: kumulus
# Generation Time: 2014-01-22 02:01:08 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table application_parameter
# ------------------------------------------------------------

DROP TABLE IF EXISTS `application_parameter`;

CREATE TABLE `application_parameter` (
  `param_id` int(11) NOT NULL AUTO_INCREMENT,
  `param_name` varchar(50) DEFAULT NULL,
  `param_val` varchar(150) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`param_id`),
  KEY `project_id` (`project_id`),
  CONSTRAINT `application_parameter_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`),
  CONSTRAINT `application_parameter_ibfk_2` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table application_tot
# ------------------------------------------------------------

DROP TABLE IF EXISTS `application_tot`;

CREATE TABLE `application_tot` (
  `object_type` varchar(45) DEFAULT NULL,
  `object_name` varchar(45) DEFAULT NULL,
  `object_value` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table attendance
# ------------------------------------------------------------

DROP TABLE IF EXISTS `attendance`;

CREATE TABLE `attendance` (
  `attendance_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  `login_from_time` datetime DEFAULT NULL,
  `login_to_time` datetime DEFAULT NULL,
  PRIMARY KEY (`attendance_id`),
  KEY `fk_attendance_project` (`project_id`),
  KEY `fk_attendance_user` (`user_id`),
  KEY `ATTENDANCE_ID` (`attendance_id`),
  KEY `ATTENDANCE_USER_ID` (`user_id`),
  KEY `ATTENDANCE_PROJECT_ID` (`project_id`),
  CONSTRAINT `fk_attendance_project` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`),
  CONSTRAINT `fk_attendance_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



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
  `date` datetime NOT NULL,
  `identifier` varchar(30) DEFAULT NULL,
  `node_id` bigint(20) NOT NULL,
  `type` varchar(10) NOT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  `file_id` bigint(20) NOT NULL,
  `literal` varchar(255) NOT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK335CD11BFFD95FA2` (`node_id`),
  KEY `FK335CD11BB86FDCA1` (`file_id`),
  KEY `FK335CD11BDB59EAD3` (`company_id`),
  KEY `FK335CD11BD24E14D9` (`file_id`),
  CONSTRAINT `FK335CD11BD24E14D9` FOREIGN KEY (`file_id`) REFERENCES `ufile` (`id`),
  CONSTRAINT `FK335CD11BDB59EAD3` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FK335CD11BFFD95FA2` FOREIGN KEY (`node_id`) REFERENCES `nodes` (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table image
# ------------------------------------------------------------

DROP TABLE IF EXISTS `image`;

CREATE TABLE `image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `file_id` bigint(20) NOT NULL,
  `height` bigint(20) NOT NULL,
  `width` bigint(20) NOT NULL,
  `thumbnail` bit(1) NOT NULL,
  `compressed` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5FAA95BB86FDCA1` (`file_id`),
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
  `price` float DEFAULT NULL,
  `quantity` float DEFAULT NULL,
  `page_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK94ED5C7E172ECD01` (`currency_id`),
  KEY `FK94ED5C7EBB855FC1` (`page_id`),
  CONSTRAINT `FK94ED5C7E172ECD01` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`id`),
  CONSTRAINT `FK94ED5C7EBB855FC1` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table nodes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `nodes`;

CREATE TABLE `nodes` (
  `node_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_id` bigint(20) NOT NULL,
  `actual_image_name` longtext,
  `barcode` varchar(45) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `create_datetime` datetime DEFAULT NULL,
  `creator_id` varchar(255) NOT NULL,
  `creatorldapuid` varchar(45) DEFAULT NULL,
  `document_sequence_number` int(11) DEFAULT NULL,
  `hierarchy` varchar(200) DEFAULT NULL,
  `internal_comment` varchar(200) DEFAULT NULL,
  `last_update_datetime` datetime DEFAULT NULL,
  `last_update_id` varchar(255) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `parent_node_id` bigint(20) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `thumbnail_image_name` longtext,
  `type` varchar(45) DEFAULT NULL,
  `uploaded` bit(1) DEFAULT NULL,
  PRIMARY KEY (`node_id`),
  KEY `FK64212B14D5E1553` (`project_id`),
  KEY `FK64212B1EFDC50D` (`parent_node_id`),
  CONSTRAINT `FK64212B14D5E1553` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`),
  CONSTRAINT `FK64212B1EFDC50D` FOREIGN KEY (`parent_node_id`) REFERENCES `nodes` (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table nodes_page
# ------------------------------------------------------------

DROP TABLE IF EXISTS `nodes_page`;

CREATE TABLE `nodes_page` (
  `nodes_pages_id` bigint(20) DEFAULT NULL,
  `page_id` bigint(20) DEFAULT NULL,
  KEY `FK7DB20F5DAF38446E` (`nodes_pages_id`),
  KEY `FK7DB20F5DBB855FC1` (`page_id`),
  CONSTRAINT `FK7DB20F5DBB855FC1` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`),
  CONSTRAINT `FK7DB20F5DAF38446E` FOREIGN KEY (`nodes_pages_id`) REFERENCES `nodes` (`node_id`)
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
  `number` bigint(20) NOT NULL,
  `scan_image_id` bigint(20) NOT NULL,
  `thumbnail_image_id` bigint(20) NOT NULL,
  `view_image_id` bigint(20) NOT NULL,
  `batch_id` bigint(20) NOT NULL,
  `node_id` bigint(20) NOT NULL,
  `literal` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK34628F237BB8C1` (`document_id`),
  KEY `FK34628F8332BA46` (`thumbnail_image_id`),
  KEY `FK34628FD477F7D5` (`scan_image_id`),
  KEY `FK34628FB852B0AD` (`view_image_id`),
  KEY `FK34628FFFD95FA2` (`node_id`),
  CONSTRAINT `FK34628F237BB8C1` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`),
  CONSTRAINT `FK34628F8332BA46` FOREIGN KEY (`thumbnail_image_id`) REFERENCES `image` (`id`),
  CONSTRAINT `FK34628FB852B0AD` FOREIGN KEY (`view_image_id`) REFERENCES `image` (`id`),
  CONSTRAINT `FK34628FD477F7D5` FOREIGN KEY (`scan_image_id`) REFERENCES `image` (`id`),
  CONSTRAINT `FK34628FFFD95FA2` FOREIGN KEY (`node_id`) REFERENCES `nodes` (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table param_names
# ------------------------------------------------------------

DROP TABLE IF EXISTS `param_names`;

CREATE TABLE `param_names` (
  `param` varchar(20) NOT NULL,
  PRIMARY KEY (`param`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table project
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `project_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(50) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `company` varchar(255) NOT NULL,
  `client_id` bigint(20) NOT NULL,
  `literal` varchar(255) NOT NULL,
  PRIMARY KEY (`project_id`),
  UNIQUE KEY `project_name` (`project_name`),
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



# Dump of table task
# ------------------------------------------------------------

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `status` bigint(20) NOT NULL,
  `batch_instance_id` bigint(20) DEFAULT NULL,
  `user_id` varchar(255) NOT NULL,
  `reported` bit(1) NOT NULL DEFAULT b'0',
  `project_id` bigint(20) NOT NULL,
  `last_batch_instance_id` bigint(20) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `version` bigint(20) NOT NULL,
  `batch_instance_url_id` varchar(5) DEFAULT NULL,
  `batch_instanceid` varchar(255) NOT NULL,
  `literal` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `batch_instance_id_UNIQUE` (`batch_instance_id`),
  KEY `fk_4_idx` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table task_history
# ------------------------------------------------------------

DROP TABLE IF EXISTS `task_history`;

CREATE TABLE `task_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_id` bigint(20) NOT NULL,
  `last_updated` datetime NOT NULL,
  `status` bigint(20) NOT NULL,
  `reported` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_1_idx` (`task_id`),
  KEY `fk_3_idx` (`task_id`),
  CONSTRAINT `fk_3` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table task_item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `task_item`;

CREATE TABLE `task_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `page_id` bigint(20) NOT NULL,
  `sequence` bigint(20) NOT NULL,
  `task_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAC3A98D8FCBF401` (`task_id`),
  KEY `FKAC3A98DBB855FC1` (`page_id`),
  CONSTRAINT `FKAC3A98DBB855FC1` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`),
  CONSTRAINT `FKAC3A98D8FCBF401` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
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
  `user_id` varchar(45) NOT NULL,
  `userid_password` varchar(100) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `collection_right` varchar(1) DEFAULT NULL,
  `import_right` varchar(1) DEFAULT NULL,
  `separation_right` varchar(1) DEFAULT NULL,
  `import_kpi_target` int(11) DEFAULT NULL,
  `separation_kpi_target` int(11) DEFAULT NULL,
  `user_email` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `user_id` (`user_id`)
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
  CONSTRAINT `FK143BF46A3D7D4261` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK143BF46AE2A80641` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
