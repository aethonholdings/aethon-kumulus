# ************************************************************
# Sequel Pro SQL dump
# Version 4004
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.10)
# Database: kumulus
# Generation Time: 2014-01-23 06:04:20 +0000
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
	('STATUS','Sealed','2'),
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



# Dump of table company
# ------------------------------------------------------------

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;

INSERT INTO `company` (`id`, `version`, `name`)
VALUES
	(1,0,'SmartSpace Pte Ltd'),
	(2,0,'TestCo Pte Ltd'),
	(12,0,'dwse');

/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;


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



# Dump of table image
# ------------------------------------------------------------



# Dump of table line_item
# ------------------------------------------------------------



# Dump of table nodes
# ------------------------------------------------------------

LOCK TABLES `nodes` WRITE;
/*!40000 ALTER TABLE `nodes` DISABLE KEYS */;

INSERT INTO `nodes` (`node_id`, `parent_node_id`, `project_id`, `name`, `type`, `barcode`, `comment`, `create_datetime`, `creator_id`, `internal_comment`, `last_update_datetime`, `last_update_id`, `status`)
VALUES
	(24,NULL,1,'2013 invoices','C','0001','','2014-01-14 16:14:00','kumulus','test','2014-01-14 16:14:00','kumulus','0'),
	(29,24,1,'Child 1','C','25031821ZHTW','','2014-01-21 15:38:22','kumulus',NULL,'2014-01-21 15:38:22','kumulus','0'),
	(30,24,1,'Child 2','C','18101940ZHTW','','2014-01-21 15:38:31','kumulus',NULL,'2014-01-21 15:38:31','kumulus','0');

/*!40000 ALTER TABLE `nodes` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table page
# ------------------------------------------------------------



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

INSERT INTO `project` (`project_id`, `project_name`, `status`, `comment`, `company`, `client_id`, `literal`, `path`)
VALUES
	(1,'test project','A','this is a great test','KUMULUS PTE LTD',2,'20140120200910ABCDE','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/');

/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table project_line_item
# ------------------------------------------------------------



# Dump of table role
# ------------------------------------------------------------



# Dump of table scan_batch
# ------------------------------------------------------------



# Dump of table task
# ------------------------------------------------------------



# Dump of table ufile
# ------------------------------------------------------------



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
