# ************************************************************
# Sequel Pro SQL dump
# Version 4004
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: kumulus.cokd1jwuhqlu.ap-southeast-1.rds.amazonaws.com (MySQL 5.6.13-log)
# Database: kumulus
# Generation Time: 2013-12-30 05:42:23 +0000
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

CREATE TABLE `application_parameter` (
  `param_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_name` varchar(50) DEFAULT NULL,
  `param_val` varchar(150) DEFAULT NULL,
  `project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`param_id`),
  KEY `FKFA594F7A4D5E1553` (`project_id`),
  CONSTRAINT `FKFA594F7A4D5E1553` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table application_tot
# ------------------------------------------------------------

CREATE TABLE `application_tot` (
  `object_type` varchar(45) NOT NULL DEFAULT '',
  `object_name` varchar(45) NOT NULL DEFAULT '',
  `object_value` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`object_type`,`object_name`,`object_value`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table nodes
# ------------------------------------------------------------

CREATE TABLE `nodes` (
  `node_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `actual_image_name` longtext,
  `barcode` varchar(45) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `create_datetime` datetime DEFAULT NULL,
  `creatorldapuid` varchar(45) DEFAULT NULL,
  `document_sequence_number` int(11) DEFAULT NULL,
  `hierarchy` varchar(200) DEFAULT NULL,
  `internal_comment` varchar(200) DEFAULT NULL,
  `last_update_datetime` datetime DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `parent_node_id` bigint(20) DEFAULT NULL,
  `project_id` bigint(20) NOT NULL,
  `status` varchar(10) DEFAULT NULL,
  `thumbnail_image_name` longtext,
  `type` varchar(45) DEFAULT NULL,
  `uploaded` bit(1) DEFAULT NULL,
  PRIMARY KEY (`node_id`),
  KEY `FK64212B14D5E1553` (`project_id`),
  KEY `FK64212B1EFDC50D` (`parent_node_id`),
  CONSTRAINT `FK64212B1EFDC50D` FOREIGN KEY (`parent_node_id`) REFERENCES `nodes` (`node_id`),
  CONSTRAINT `FK64212B14D5E1553` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `nodes` WRITE;
/*!40000 ALTER TABLE `nodes` DISABLE KEYS */;

INSERT INTO `nodes` (`node_id`, `actual_image_name`, `barcode`, `comment`, `create_datetime`, `creatorldapuid`, `document_sequence_number`, `hierarchy`, `internal_comment`, `last_update_datetime`, `name`, `parent_node_id`, `project_id`, `status`, `thumbnail_image_name`, `type`, `uploaded`)
VALUES
	(1,NULL,NULL,'no comment',NULL,NULL,NULL,NULL,NULL,'2013-12-30 01:36:00','root node',NULL,1,NULL,NULL,'ROOT',NULL),
	(2,NULL,'AE18101978','no comment',NULL,'kumulus',NULL,NULL,NULL,'2013-12-30 01:36:01','Container 1',1,1,NULL,NULL,'CONTAINER',NULL),
	(3,NULL,'AE26051979','no comment',NULL,'kumulus',NULL,NULL,NULL,'2013-12-30 01:36:01','Container 2',1,1,NULL,NULL,'CONTAINER',NULL),
	(4,NULL,NULL,'no comment',NULL,'kumulus',NULL,NULL,NULL,'2013-12-30 01:36:01','Document 1',3,1,NULL,NULL,'DOCUMENT',NULL),
	(5,NULL,NULL,'no comment',NULL,'kumulus',NULL,NULL,NULL,'2013-12-30 01:36:01','Document 2',3,1,NULL,NULL,'DOCUMENT',NULL),
	(6,NULL,NULL,'no comment',NULL,'kumulus',NULL,NULL,NULL,'2013-12-30 01:36:01','Document 3',3,1,NULL,NULL,'DOCUMENT',NULL);

/*!40000 ALTER TABLE `nodes` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table param_names
# ------------------------------------------------------------

CREATE TABLE `param_names` (
  `param` varchar(20) NOT NULL,
  PRIMARY KEY (`param`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table project
# ------------------------------------------------------------

CREATE TABLE `project` (
  `project_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clientldapid` varchar(50) NOT NULL,
  `project_name` varchar(50) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  UNIQUE KEY `project_name` (`project_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;

INSERT INTO `project` (`project_id`, `clientldapid`, `project_name`, `status`)
VALUES
	(1,'Kumulus Pte Ltd','test project','001');

/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table role
# ------------------------------------------------------------

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `authority` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `authority` (`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table task
# ------------------------------------------------------------

CREATE TABLE `task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `ended` datetime DEFAULT NULL,
  `ownerldapuid` varchar(255) NOT NULL,
  `started` datetime DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;

INSERT INTO `task` (`id`, `version`, `created`, `ended`, `ownerldapuid`, `started`, `status`, `type`)
VALUES
	(1,0,'2013-12-30 01:36:01',NULL,'kumulus',NULL,'PENDING','TASK_VALIDATE'),
	(2,0,'2013-12-30 01:36:01',NULL,'kumulus',NULL,'PENDING','TASK_CLASSIFY');

/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table task_nodes
# ------------------------------------------------------------

CREATE TABLE `task_nodes` (
  `task_nodes_id` bigint(20) DEFAULT NULL,
  `nodes_id` bigint(20) DEFAULT NULL,
  KEY `FK4DF5B3772EED58CF` (`task_nodes_id`),
  KEY `FK4DF5B377C4D00893` (`nodes_id`),
  CONSTRAINT `FK4DF5B377C4D00893` FOREIGN KEY (`nodes_id`) REFERENCES `nodes` (`node_id`),
  CONSTRAINT `FK4DF5B3772EED58CF` FOREIGN KEY (`task_nodes_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `task_nodes` WRITE;
/*!40000 ALTER TABLE `task_nodes` DISABLE KEYS */;

INSERT INTO `task_nodes` (`task_nodes_id`, `nodes_id`)
VALUES
	(1,4),
	(1,5),
	(2,6);

/*!40000 ALTER TABLE `task_nodes` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

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
