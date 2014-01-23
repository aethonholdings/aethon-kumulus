# ************************************************************
# Sequel Pro SQL dump
# Version 4004
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.10)
# Database: kumulus
# Generation Time: 2014-01-23 02:36:13 +0000
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

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;

INSERT INTO `document` (`id`, `company_id`, `date`, `file_id`, `identifier`, `literal`, `status`, `type`)
VALUES
	(18,NULL,NULL,NULL,NULL,'13904423605413M',1,NULL),
	(19,NULL,NULL,NULL,NULL,'1390442520011HJ',1,NULL),
	(20,NULL,NULL,NULL,NULL,'1390442574244BR',1,NULL),
	(21,NULL,NULL,NULL,NULL,'1390442973594JZ',1,NULL);

/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table image
# ------------------------------------------------------------

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;

INSERT INTO `image` (`id`, `version`, `compressed`, `file_id`, `height`, `thumbnail`, `width`)
VALUES
	(11,0,0,71,2338,0,1653),
	(12,0,0,73,300,0,212),
	(13,0,0,72,2338,0,1653),
	(14,0,0,75,2338,0,1653),
	(15,0,0,77,300,0,212),
	(16,0,0,76,2338,0,1653),
	(17,0,0,79,2336,0,1652),
	(18,0,0,81,300,0,212),
	(19,0,0,80,2336,0,1652),
	(20,0,0,83,2336,0,1652),
	(21,0,0,85,300,0,212),
	(22,0,0,84,2336,0,1652);

/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table line_item
# ------------------------------------------------------------



# Dump of table nodes
# ------------------------------------------------------------

LOCK TABLES `nodes` WRITE;
/*!40000 ALTER TABLE `nodes` DISABLE KEYS */;

INSERT INTO `nodes` (`node_id`, `barcode`, `comment`, `create_datetime`, `creator_id`, `internal_comment`, `last_update_datetime`, `last_update_id`, `name`, `parent_node_id`, `project_id`, `status`, `type`, `uploaded`)
VALUES
	(24,'0001','','2014-01-14 16:14:00','kumulus','test','2014-01-14 16:14:00','kumulus','2013 invoices',NULL,1,'0','C',NULL),
	(29,'25031821ZHTW','','2014-01-21 15:38:22','kumulus',NULL,'2014-01-21 15:38:22','kumulus','Child 1',24,1,'0','C',0),
	(30,'18101940ZHTW','','2014-01-21 15:38:31','kumulus',NULL,'2014-01-21 15:38:31','kumulus','Child 2',24,1,'0','C',0),
	(31,NULL,NULL,'2014-01-21 15:38:31','kumulus',NULL,'2014-01-21 15:38:31','kumulus',NULL,29,1,NULL,'P',1),
	(32,NULL,NULL,'2014-01-21 15:38:31','kumulus',NULL,'2014-01-21 15:38:31','kumulus',NULL,24,1,NULL,'P',1),
	(33,NULL,NULL,'2014-01-21 15:38:31','kumulus',NULL,'2014-01-21 15:38:31','kumulus',NULL,24,1,NULL,'P',1),
	(34,NULL,NULL,'2014-01-21 15:38:31','kumulus',NULL,'2014-01-21 15:38:31','kumulus',NULL,24,1,NULL,'P',1);

/*!40000 ALTER TABLE `nodes` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table page
# ------------------------------------------------------------

LOCK TABLES `page` WRITE;
/*!40000 ALTER TABLE `page` DISABLE KEYS */;

INSERT INTO `page` (`id`, `version`, `document_id`, `first`, `last`, `literal`, `node_id`, `number`, `scan_image_id`, `thumbnail_image_id`, `view_image_id`)
VALUES
	(6,0,18,1,1,'13904423605413M',31,1,11,12,13),
	(7,0,19,1,1,'1390442520011HJ',32,1,14,15,16),
	(8,0,20,1,1,'1390442574244BR',33,1,17,18,19),
	(9,0,21,1,1,'1390442973594JZ',34,1,20,21,22);

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

INSERT INTO `project` (`project_id`, `project_name`, `status`, `comment`, `company`, `client_id`, `literal`, `path`)
VALUES
	(1,'test project','A','this is a great test','KUMULUS PTE LTD',2,'20140120200910ABCDE','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/');

/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table project_line_item
# ------------------------------------------------------------



# Dump of table role
# ------------------------------------------------------------



# Dump of table task
# ------------------------------------------------------------



# Dump of table ufile
# ------------------------------------------------------------

LOCK TABLES `ufile` WRITE;
/*!40000 ALTER TABLE `ufile` DISABLE KEYS */;

INSERT INTO `ufile` (`id`, `version`, `date_uploaded`, `downloads`, `extension`, `name`, `path`, `size`)
VALUES
	(69,0,'2014-01-23 09:58:19',0,'png','130723-Funding Request_42018820_20130723-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1390442298797/130723-Funding Request_42018820_20130723-page1 2.png',215750),
	(70,0,'2014-01-23 09:58:27',0,'png','130908-DIDLogic-Receipt-page2 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1390442306997/130908-DIDLogic-Receipt-page2 2.png',85411),
	(71,0,'2014-01-23 09:59:21',0,'tiff','13904423605413M-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13904423605413M/13904423605413M-S.tiff',0),
	(72,0,'2014-01-23 09:59:21',0,'jpg','13904423605413M-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13904423605413M/13904423605413M-V.jpg',0),
	(73,0,'2014-01-23 09:59:21',0,'jpg','13904423605413M-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13904423605413M/13904423605413M-T.jpg',0),
	(74,0,'2014-01-23 10:02:00',0,'png','130908-DIDLogic-Receipt-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1390442519962/130908-DIDLogic-Receipt-page1 2.png',424747),
	(75,0,'2014-01-23 10:02:00',0,'tiff','1390442520011HJ-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390442520011HJ/1390442520011HJ-S.tiff',0),
	(76,0,'2014-01-23 10:02:00',0,'jpg','1390442520011HJ-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390442520011HJ/1390442520011HJ-V.jpg',0),
	(77,0,'2014-01-23 10:02:00',0,'jpg','1390442520011HJ-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390442520011HJ/1390442520011HJ-T.jpg',0),
	(78,0,'2014-01-23 10:02:54',0,'png','130902-Google-Payment Receipt-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1390442574192/130902-Google-Payment Receipt-page1 2.png',173233),
	(79,0,'2014-01-23 10:02:54',0,'tiff','1390442574244BR-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390442574244BR/1390442574244BR-S.tiff',0),
	(80,0,'2014-01-23 10:02:54',0,'jpg','1390442574244BR-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390442574244BR/1390442574244BR-V.jpg',0),
	(81,0,'2014-01-23 10:02:54',0,'jpg','1390442574244BR-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390442574244BR/1390442574244BR-T.jpg',0),
	(82,0,'2014-01-23 10:09:34',0,'png','130902-Google-Payment Receipt-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1390442973541/130902-Google-Payment Receipt-page1 2.png',173233),
	(83,0,'2014-01-23 10:09:34',0,'tiff','1390442973594JZ-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390442973594JZ/1390442973594JZ-S.tiff',11579680),
	(84,0,'2014-01-23 10:09:34',0,'jpg','1390442973594JZ-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390442973594JZ/1390442973594JZ-V.jpg',127337),
	(85,0,'2014-01-23 10:09:34',0,'jpg','1390442973594JZ-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390442973594JZ/1390442973594JZ-T.jpg',4449);

/*!40000 ALTER TABLE `ufile` ENABLE KEYS */;
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
