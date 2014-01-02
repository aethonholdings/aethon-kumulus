# ************************************************************
# Sequel Pro SQL dump
# Version 4004
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.10)
# Database: kumulus
# Generation Time: 2014-01-02 14:16:20 +0000
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
  `project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`param_id`),
  KEY `project_id` (`project_id`),
  CONSTRAINT `application_parameter_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`),
  CONSTRAINT `application_parameter_ibfk_2` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;



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
  `project_id` int(11) DEFAULT NULL,
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



# Dump of table nodes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `nodes`;

CREATE TABLE `nodes` (
  `node_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `barcode` varchar(45) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `internal_comment` varchar(200) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `parent_node_id` bigint(20) DEFAULT NULL,
  `hierarchy` varchar(200) DEFAULT NULL,
  `thumbnail_image_name` varchar(1000) DEFAULT NULL,
  `actual_image_name` varchar(1000) DEFAULT NULL,
  `creator_id` varchar(45) DEFAULT NULL,
  `create_datetime` datetime DEFAULT NULL,
  `last_update_id` varchar(45) DEFAULT NULL,
  `last_update_datetime` datetime DEFAULT NULL,
  `document_sequence_number` int(11) DEFAULT NULL,
  `creatorldapuid` varchar(45) DEFAULT NULL,
  `uploaded` bit(1) DEFAULT b'0',
  PRIMARY KEY (`node_id`),
  KEY `FK64212B14D5E1553` (`project_id`),
  KEY `FK64212B1EFDC50D` (`parent_node_id`),
  CONSTRAINT `FK64212B1EFDC50D` FOREIGN KEY (`parent_node_id`) REFERENCES `nodes` (`node_id`),
  CONSTRAINT `FK64212B14D5E1553` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;



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
  `project_id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(50) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `clientldapid` varchar(50) NOT NULL,
  PRIMARY KEY (`project_id`),
  UNIQUE KEY `project_name_UNIQUE` (`project_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;



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
  `project_id` int(11) NOT NULL,
  `last_batch_instance_id` bigint(20) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `batch_instance_id_UNIQUE` (`batch_instance_id`),
  KEY `fk_4_idx` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;



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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;



# Dump of table task_nodes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `task_nodes`;

CREATE TABLE `task_nodes` (
  `task_nodes_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nodes_id` bigint(20) NOT NULL,
  `task_id` bigint(20) NOT NULL,
  PRIMARY KEY (`task_nodes_id`),
  KEY `FK4DF5B3772EED58CF` (`task_nodes_id`),
  KEY `FK4DF5B377C4D00893` (`nodes_id`),
  CONSTRAINT `FK4DF5B377C4D00893` FOREIGN KEY (`nodes_id`) REFERENCES `nodes` (`node_id`),
  CONSTRAINT `FK4DF5B3772EED58CF` FOREIGN KEY (`task_nodes_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;



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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;



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
