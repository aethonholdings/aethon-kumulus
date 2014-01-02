# ************************************************************
# Sequel Pro SQL dump
# Version 4004
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.10)
# Database: kumulus
# Generation Time: 2014-01-02 05:35:44 +0000
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

LOCK TABLES `application_parameter` WRITE;
/*!40000 ALTER TABLE `application_parameter` DISABLE KEYS */;

INSERT INTO `application_parameter` (`param_id`, `param_name`, `param_val`, `project_id`)
VALUES
	(1,'refresh_interval','10',NULL),
	(2,'breath_interval','5',NULL),
	(3,'targetKPI','5000',NULL),
	(4,'version','v1.1.3',NULL),
	(5,'total_upload_images_at_once','21',NULL);

/*!40000 ALTER TABLE `application_parameter` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table application_tot
# ------------------------------------------------------------

LOCK TABLES `application_tot` WRITE;
/*!40000 ALTER TABLE `application_tot` DISABLE KEYS */;

INSERT INTO `application_tot` (`object_type`, `object_name`, `object_value`)
VALUES
	('STATUS','In Progress','0'),
	('STATUS','Done','1'),
	('NODE_TYPE','Container','C'),
	('NODE_TYPE','Box','B'),
	('NODE_TYPE','Document','D'),
	('STATUS','Sealed','2');

/*!40000 ALTER TABLE `application_tot` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table attendance
# ------------------------------------------------------------



# Dump of table nodes
# ------------------------------------------------------------

LOCK TABLES `nodes` WRITE;
/*!40000 ALTER TABLE `nodes` DISABLE KEYS */;

INSERT INTO `nodes` (`node_id`, `project_id`, `name`, `type`, `barcode`, `comment`, `internal_comment`, `status`, `parent_node_id`, `hierarchy`, `thumbnail_image_name`, `actual_image_name`, `creator_id`, `create_datetime`, `last_update_id`, `last_update_datetime`, `document_sequence_number`, `creatorldapuid`, `uploaded`)
VALUES
	(1,1,'root node','ROOT',NULL,'no comment',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-12-30 13:48:29',NULL,NULL,NULL),
	(2,1,'Container 1','CONTAINER','AE18101978','no comment',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,'2013-12-30 13:48:29',NULL,'kumulus',NULL),
	(3,1,'Container 2','CONTAINER','AE26051979','no comment',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,'2013-12-30 13:48:29',NULL,'kumulus',NULL),
	(4,1,'Document 1','DOCUMENT',NULL,'no comment',NULL,NULL,3,NULL,NULL,NULL,NULL,NULL,NULL,'2013-12-30 13:48:29',NULL,'kumulus',NULL),
	(5,1,'Document 2','DOCUMENT',NULL,'no comment',NULL,NULL,3,NULL,NULL,NULL,NULL,NULL,NULL,'2013-12-30 13:48:29',NULL,'kumulus',NULL),
	(6,1,'Document 3','DOCUMENT',NULL,'no comment',NULL,NULL,3,NULL,NULL,NULL,NULL,NULL,NULL,'2013-12-30 13:48:29',NULL,'kumulus',NULL);

/*!40000 ALTER TABLE `nodes` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table param_names
# ------------------------------------------------------------

LOCK TABLES `param_names` WRITE;
/*!40000 ALTER TABLE `param_names` DISABLE KEYS */;

INSERT INTO `param_names` (`param`)
VALUES
	('breath_interval'),
	('refresh_interval'),
	('targetKPI');

/*!40000 ALTER TABLE `param_names` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table project
# ------------------------------------------------------------

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;

INSERT INTO `project` (`project_id`, `project_name`, `status`, `clientldapid`)
VALUES
	(1,'test project','001','Kumulus Pte Ltd'),
	(2,'Inventory','001','A'),
	(3,'scannerapp','001','A'),
	(4,'warehouse','001','I');

/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table role
# ------------------------------------------------------------



# Dump of table task
# ------------------------------------------------------------

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;

INSERT INTO `task` (`id`, `created`, `last_updated`, `status`, `batch_instance_id`, `user_id`, `reported`, `project_id`, `last_batch_instance_id`, `type`, `version`)
VALUES
	(1,'2013-12-30 13:48:29','2013-12-30 13:48:29',0,5,'kumulus',0,1,NULL,'TASK_VALIDATE',0),
	(2,'2013-12-30 13:50:00','2013-12-30 13:50:00',0,6,'kumulus',0,1,NULL,'TASK_CLASSIFY',0);

/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table task_history
# ------------------------------------------------------------

LOCK TABLES `task_history` WRITE;
/*!40000 ALTER TABLE `task_history` DISABLE KEYS */;

INSERT INTO `task_history` (`id`, `task_id`, `last_updated`, `status`, `reported`)
VALUES
	(1,1,'2013-12-30 13:48:29',0,NULL),
	(2,2,'2013-12-30 13:50:00',0,NULL);

/*!40000 ALTER TABLE `task_history` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table task_nodes
# ------------------------------------------------------------

LOCK TABLES `task_nodes` WRITE;
/*!40000 ALTER TABLE `task_nodes` DISABLE KEYS */;

INSERT INTO `task_nodes` (`task_nodes_id`, `nodes_id`, `task_id`)
VALUES
	(2,4,1),
	(3,5,1),
	(4,6,2);

/*!40000 ALTER TABLE `task_nodes` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `version`, `account_expired`, `account_locked`, `enabled`, `password`, `password_expired`, `username`, `user_id`, `userid_password`, `status`, `collection_right`, `import_right`, `separation_right`, `import_kpi_target`, `separation_kpi_target`, `user_email`)
VALUES
	(1,0,0,0,1,'',0,'ADMIN','ADMIN','a3d95763b3d68d3ea1d5644e250a59de1826c9c7','A','Y','Y','N',1000,0,'');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_role
# ------------------------------------------------------------




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;