# ************************************************************
# Sequel Pro SQL dump
# Version 4004
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.10)
# Database: kumulus
# Generation Time: 2014-01-16 06:45:01 +0000
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



# Dump of table currency
# ------------------------------------------------------------

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;

INSERT INTO `currency` (`id`, `version`, `full_name`, `short_name`)
VALUES
	(1,1,'US dollar','USD'),
	(2,1,'Singapore dollar','SGD');

/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table document
# ------------------------------------------------------------

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;

INSERT INTO `document` (`id`, `company`, `date`, `identifier`, `node_id`, `type`)
VALUES
	(1,'SmartSpace','2014-01-16 14:42:38','INV-1298',26,'INVOICE');

/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table document_image
# ------------------------------------------------------------

LOCK TABLES `document_image` WRITE;
/*!40000 ALTER TABLE `document_image` DISABLE KEYS */;

INSERT INTO `document_image` (`document_images_id`, `image_id`, `document_thumbnails_id`)
VALUES
	(1,1,NULL),
	(NULL,2,1);

/*!40000 ALTER TABLE `document_image` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table image
# ------------------------------------------------------------

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;

INSERT INTO `image` (`id`, `version`, `filename`, `path`)
VALUES
	(1,0,'imageFilename','imagePath'),
	(2,0,'thumbFilename','thumbPath');

/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table line_item
# ------------------------------------------------------------



# Dump of table nodes
# ------------------------------------------------------------

LOCK TABLES `nodes` WRITE;
/*!40000 ALTER TABLE `nodes` DISABLE KEYS */;

INSERT INTO `nodes` (`node_id`, `actual_image_name`, `barcode`, `comment`, `create_datetime`, `creator_id`, `creatorldapuid`, `document_sequence_number`, `hierarchy`, `internal_comment`, `last_update_datetime`, `last_update_id`, `name`, `parent_node_id`, `project_id`, `status`, `thumbnail_image_name`, `type`, `uploaded`)
VALUES
	(24,NULL,'0001','','2014-01-14 16:14:00','kumulus',NULL,NULL,NULL,'test','2014-01-14 16:14:00','kumulus','2013 invoices',NULL,1,'0',NULL,'C',NULL),
	(25,NULL,NULL,NULL,'2014-01-14 16:14:00','kumulus',NULL,NULL,NULL,NULL,'2014-01-14 16:14:00','kumulus',NULL,24,1,'0',NULL,'D',0),
	(26,NULL,NULL,NULL,'2014-01-14 16:14:00','kumulus',NULL,NULL,NULL,NULL,'2014-01-14 16:14:00','kumulus',NULL,24,1,'0',NULL,'D',0);

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

INSERT INTO `project` (`project_id`, `clientldapid`, `project_name`, `status`)
VALUES
	(1,'Kumulus Pte Ltd','test project','A');

/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table project_line_item
# ------------------------------------------------------------



# Dump of table role
# ------------------------------------------------------------



# Dump of table task
# ------------------------------------------------------------

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;

INSERT INTO `task` (`id`, `created`, `last_updated`, `status`, `batch_instance_id`, `user_id`, `reported`, `project_id`, `last_batch_instance_id`, `type`, `version`, `batch_instance_url_id`, `batch_instanceid`)
VALUES
	(1,'2014-01-14 16:14:00','2014-01-14 16:14:00',5,NULL,'kumulus',0,1,NULL,'',0,'BI2D',''),
	(2,'2014-01-14 21:33:00','2014-01-14 21:33:00',5,NULL,'kumulus',0,1,NULL,'',0,'BI2C','');

/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table task_history
# ------------------------------------------------------------

LOCK TABLES `task_history` WRITE;
/*!40000 ALTER TABLE `task_history` DISABLE KEYS */;

INSERT INTO `task_history` (`id`, `task_id`, `last_updated`, `status`, `reported`)
VALUES
	(1,1,'2014-01-14 16:14:00',1,NULL),
	(2,1,'2014-01-14 16:14:00',2,NULL),
	(3,1,'2014-01-14 16:14:00',3,NULL),
	(4,1,'2014-01-14 16:14:00',4,NULL),
	(5,1,'2014-01-14 16:14:00',5,NULL),
	(6,2,'2014-01-14 21:33:00',1,NULL),
	(7,2,'2014-01-14 21:33:00',2,NULL),
	(8,2,'2014-01-14 21:33:00',3,NULL),
	(9,2,'2014-01-14 21:33:00',4,NULL),
	(10,2,'2014-01-14 21:33:00',5,NULL);

/*!40000 ALTER TABLE `task_history` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table task_nodes
# ------------------------------------------------------------

LOCK TABLES `task_nodes` WRITE;
/*!40000 ALTER TABLE `task_nodes` DISABLE KEYS */;

INSERT INTO `task_nodes` (`task_nodes_id`, `nodes_id`, `task_id`)
VALUES
	(1,25,1),
	(2,26,2);

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
