# ************************************************************
# Sequel Pro SQL dump
# Version 4004
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.10)
# Database: kumulus
# Generation Time: 2014-01-21 09:51:00 +0000
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



# Dump of table company
# ------------------------------------------------------------

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;

INSERT INTO `company` (`id`, `version`, `name`)
VALUES
	(1,0,'SmartSpace Pte Ltd'),
	(2,0,'TestCo Pte Ltd'),
	(3,0,'adf'),
	(4,0,'asdfd'),
	(5,0,'sdfsaf'),
	(8,0,'fdf'),
	(9,0,'hey'),
	(10,0,'sfdasdf'),
	(11,0,'dwse');

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

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;

INSERT INTO `document` (`id`, `date`, `identifier`, `node_id`, `type`, `company_id`, `file_id`, `literal`)
VALUES
	(1,'2013-09-19 00:00:00','INV-1821',25,'Invoice',1,1,'201401120200912ACDEF');

/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table file
# ------------------------------------------------------------

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;

INSERT INTO `file` (`id`, `version`, `name`, `path`, `type`, `project_id`)
VALUES
	(1,0,'201401120200912ACDEF.pdf','20140120200910ABCDE/docs','pdf',1),
	(2,0,'201401120200912WOHO.jpg','20140120200910ABCDE/pages/img','jpg',1),
	(3,0,'201401120200912WAHA.jpg','20140120200910ABCDE/pages/img','jpg',1),
	(4,0,'201401120200912WOHO.jpg','20140120200910ABCDE/pages/thumbs','jpg',1),
	(5,0,'201401120200912WAHA.jpg','20140120200910ABCDE/pages/thumbs','jpg',1),
	(6,0,'201401120200912WOHO.jpg','20140120200910ABCDE/pages/scans','tif',1),
	(7,0,'201401120200912WAHA.jpg','20140120200910ABCDE/pages/scans','tif',1);

/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table image
# ------------------------------------------------------------

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;

INSERT INTO `image` (`id`, `version`, `file_id`, `height`, `width`, `thumbnail`, `compressed`)
VALUES
	(1,0,4,472,328,1,0),
	(2,0,5,468,334,1,0),
	(3,0,6,2338,1652,0,0),
	(4,0,7,2338,1652,0,0),
	(5,0,8,2338,1652,0,1),
	(6,0,9,2338,1652,0,1);

/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table line_item
# ------------------------------------------------------------

LOCK TABLES `line_item` WRITE;
/*!40000 ALTER TABLE `line_item` DISABLE KEYS */;

INSERT INTO `line_item` (`id`, `version`, `amount`, `currency_id`, `date`, `description`, `price`, `quantity`, `page_id`)
VALUES
	(1,0,550,2,'2016-01-13 00:00:00','Rent',NULL,NULL,1);

/*!40000 ALTER TABLE `line_item` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table nodes
# ------------------------------------------------------------

LOCK TABLES `nodes` WRITE;
/*!40000 ALTER TABLE `nodes` DISABLE KEYS */;

INSERT INTO `nodes` (`node_id`, `project_id`, `actual_image_name`, `barcode`, `comment`, `create_datetime`, `creator_id`, `creatorldapuid`, `document_sequence_number`, `hierarchy`, `internal_comment`, `last_update_datetime`, `last_update_id`, `name`, `parent_node_id`, `status`, `thumbnail_image_name`, `type`, `uploaded`)
VALUES
	(24,1,NULL,'0001','','2014-01-14 16:14:00','kumulus',NULL,NULL,NULL,'test','2014-01-14 16:14:00','kumulus','2013 invoices',NULL,'0',NULL,'C',NULL),
	(25,1,NULL,NULL,NULL,'2014-01-14 16:14:00','kumulus',NULL,NULL,NULL,NULL,'2014-01-14 16:14:00','kumulus',NULL,29,'0',NULL,'D',0),
	(26,1,NULL,NULL,NULL,'2014-01-14 16:14:00','kumulus',NULL,NULL,NULL,NULL,'2014-01-14 16:14:00','kumulus',NULL,29,'0',NULL,'D',0),
	(29,1,NULL,'25031821ZHTW','','2014-01-21 15:38:22','kumulus',NULL,NULL,NULL,NULL,'2014-01-21 15:38:22','kumulus','Child 1',24,'0',NULL,'C',0),
	(30,1,NULL,'18101940ZHTW','','2014-01-21 15:38:31','kumulus',NULL,NULL,NULL,NULL,'2014-01-21 15:38:31','kumulus','Child 2',24,'0',NULL,'C',0);

/*!40000 ALTER TABLE `nodes` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table nodes_page
# ------------------------------------------------------------

LOCK TABLES `nodes_page` WRITE;
/*!40000 ALTER TABLE `nodes_page` DISABLE KEYS */;

INSERT INTO `nodes_page` (`nodes_pages_id`, `page_id`)
VALUES
	(25,1),
	(26,1);

/*!40000 ALTER TABLE `nodes_page` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table page
# ------------------------------------------------------------

LOCK TABLES `page` WRITE;
/*!40000 ALTER TABLE `page` DISABLE KEYS */;

INSERT INTO `page` (`id`, `version`, `document_id`, `first`, `last`, `number`, `scan_image_id`, `thumbnail_image_id`, `view_image_id`, `batch_id`, `node_id`, `literal`)
VALUES
	(1,0,1,1,0,1,3,1,5,0,25,'201401120200912WOHO'),
	(2,0,1,0,1,2,4,2,6,0,26,'201401120200912WAHA');

/*!40000 ALTER TABLE `page` ENABLE KEYS */;
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

INSERT INTO `project` (`project_id`, `project_name`, `status`, `comment`, `company`, `client_id`, `literal`)
VALUES
	(1,'test project','A','this is a great test','KUMULUS PTE LTD',2,'20140120200910ABCDE');

/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table project_line_item
# ------------------------------------------------------------

LOCK TABLES `project_line_item` WRITE;
/*!40000 ALTER TABLE `project_line_item` DISABLE KEYS */;

INSERT INTO `project_line_item` (`project_line_items_id`, `line_item_id`)
VALUES
	(1,1);

/*!40000 ALTER TABLE `project_line_item` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table role
# ------------------------------------------------------------



# Dump of table task
# ------------------------------------------------------------

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;

INSERT INTO `task` (`id`, `created`, `last_updated`, `status`, `batch_instance_id`, `user_id`, `reported`, `project_id`, `last_batch_instance_id`, `type`, `version`, `batch_instance_url_id`, `batch_instanceid`, `literal`)
VALUES
	(1,'2014-01-14 16:14:00','2014-01-14 16:14:00',5,NULL,'kumulus',0,1,NULL,'',0,'BI2D','','');

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
	(5,1,'2014-01-14 16:14:00',5,NULL);

/*!40000 ALTER TABLE `task_history` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table task_item
# ------------------------------------------------------------

LOCK TABLES `task_item` WRITE;
/*!40000 ALTER TABLE `task_item` DISABLE KEYS */;

INSERT INTO `task_item` (`id`, `version`, `page_id`, `sequence`, `task_id`)
VALUES
	(1,0,1,1,1),
	(2,0,2,2,1);

/*!40000 ALTER TABLE `task_item` ENABLE KEYS */;
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
