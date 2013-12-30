# ************************************************************
# Sequel Pro SQL dump
# Version 4004
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.10)
# Database: kumulus
# Generation Time: 2013-12-30 05:50:21 +0000
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



# Dump of table application_tot
# ------------------------------------------------------------



# Dump of table nodes
# ------------------------------------------------------------

LOCK TABLES `nodes` WRITE;
/*!40000 ALTER TABLE `nodes` DISABLE KEYS */;

INSERT INTO `nodes` (`node_id`, `actual_image_name`, `barcode`, `comment`, `create_datetime`, `creatorldapuid`, `document_sequence_number`, `hierarchy`, `internal_comment`, `last_update_datetime`, `name`, `parent_node_id`, `project_id`, `status`, `thumbnail_image_name`, `type`, `uploaded`)
VALUES
	(1,NULL,NULL,'no comment',NULL,NULL,NULL,NULL,NULL,'2013-12-30 13:48:29','root node',NULL,1,NULL,NULL,'ROOT',NULL),
	(2,NULL,'AE18101978','no comment',NULL,'kumulus',NULL,NULL,NULL,'2013-12-30 13:48:29','Container 1',1,1,NULL,NULL,'CONTAINER',NULL),
	(3,NULL,'AE26051979','no comment',NULL,'kumulus',NULL,NULL,NULL,'2013-12-30 13:48:29','Container 2',1,1,NULL,NULL,'CONTAINER',NULL),
	(4,NULL,NULL,'no comment',NULL,'kumulus',NULL,NULL,NULL,'2013-12-30 13:48:29','Document 1',3,1,NULL,NULL,'DOCUMENT',NULL),
	(5,NULL,NULL,'no comment',NULL,'kumulus',NULL,NULL,NULL,'2013-12-30 13:48:29','Document 2',3,1,NULL,NULL,'DOCUMENT',NULL),
	(6,NULL,NULL,'no comment',NULL,'kumulus',NULL,NULL,NULL,'2013-12-30 13:48:29','Document 3',3,1,NULL,NULL,'DOCUMENT',NULL);

/*!40000 ALTER TABLE `nodes` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table param_names
# ------------------------------------------------------------



# Dump of table project
# ------------------------------------------------------------

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;

INSERT INTO `project` (`project_id`, `clientldapid`, `project_name`, `status`)
VALUES
	(1,'Kumulus Pte Ltd','test project','001');

/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table role
# ------------------------------------------------------------



# Dump of table task
# ------------------------------------------------------------

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;

INSERT INTO `task` (`id`, `version`, `created`, `ended`, `ownerldapuid`, `started`, `status`, `type`)
VALUES
	(1,0,'2013-12-30 13:48:29',NULL,'kumulus',NULL,'PENDING','TASK_VALIDATE'),
	(2,0,'2013-12-30 13:48:29',NULL,'kumulus',NULL,'PENDING','TASK_CLASSIFY');

/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table task_nodes
# ------------------------------------------------------------

LOCK TABLES `task_nodes` WRITE;
/*!40000 ALTER TABLE `task_nodes` DISABLE KEYS */;

INSERT INTO `task_nodes` (`task_nodes_id`, `nodes_id`)
VALUES
	(1,5),
	(1,4),
	(2,6);

/*!40000 ALTER TABLE `task_nodes` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------



# Dump of table user_role
# ------------------------------------------------------------




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
