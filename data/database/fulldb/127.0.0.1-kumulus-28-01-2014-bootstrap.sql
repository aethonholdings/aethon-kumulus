# ************************************************************
# Sequel Pro SQL dump
# Version 4004
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.10)
# Database: kumulus
# Generation Time: 2014-01-28 09:34:12 +0000
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
	(12,0,'dwse'),
	(14,0,'test'),
	(15,0,'asdfaf'),
	(16,0,'asdfdsf');

/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table currency
# ------------------------------------------------------------

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;

INSERT INTO `currency` (`id`, `version`, `full_name`, `short_name`)
VALUES
	(1,0,'United Arab Emirates Dirham','AED'),
	(2,0,'Afghanistan Afghani','AFN'),
	(3,0,'Albania Lek','ALL'),
	(4,0,'Armenia Dram','AMD'),
	(5,0,'Netherlands Antilles Guilder','ANG'),
	(6,0,'Angola Kwanza','AOA'),
	(7,0,'Argentina Peso','ARS'),
	(8,0,'Australia Dollar','AUD'),
	(9,0,'Aruba Guilder','AWG'),
	(10,0,'Azerbaijan New Manat','AZN'),
	(11,0,'Bosnia and Herzegovina Convertible Marka','BAM'),
	(12,0,'Barbados Dollar','BBD'),
	(13,0,'Bangladesh Taka','BDT'),
	(14,0,'Bulgaria Lev','BGN'),
	(15,0,'Bahrain Dinar','BHD'),
	(16,0,'Burundi Franc','BIF'),
	(17,0,'Bermuda Dollar','BMD'),
	(18,0,'Brunei Darussalam Dollar','BND'),
	(19,0,'Bolivia Boliviano','BOB'),
	(20,0,'Brazil Real','BRL'),
	(21,0,'Bahamas Dollar','BSD'),
	(22,0,'Bhutan Ngultrum','BTN'),
	(23,0,'Botswana Pula','BWP'),
	(24,0,'Belarus Ruble','BYR'),
	(25,0,'Belize Dollar','BZD'),
	(26,0,'Canada Dollar','CAD'),
	(27,0,'Congo/Kinshasa Franc','CDF'),
	(28,0,'Switzerland Franc','CHF'),
	(29,0,'Chile Peso','CLP'),
	(30,0,'China Yuan Renminbi','CNY'),
	(31,0,'Colombia Peso','COP'),
	(32,0,'Costa Rica Colon','CRC'),
	(33,0,'Cuba Convertible Peso','CUC'),
	(34,0,'Cuba Peso','CUP'),
	(35,0,'Cape Verde Escudo','CVE'),
	(36,0,'Czech Republic Koruna','CZK'),
	(37,0,'Djibouti Franc','DJF'),
	(38,0,'Denmark Krone','DKK'),
	(39,0,'Dominican Republic Peso','DOP'),
	(40,0,'Algeria Dinar','DZD'),
	(41,0,'Egypt Pound','EGP'),
	(42,0,'Eritrea Nakfa','ERN'),
	(43,0,'Ethiopia Birr','ETB'),
	(44,0,'Euro Member Countries','EUR'),
	(45,0,'Fiji Dollar','FJD'),
	(46,0,'Falkland Islands (Malvinas) Pound','FKP'),
	(47,0,'United Kingdom Pound','GBP'),
	(48,0,'Georgia Lari','GEL'),
	(49,0,'Guernsey Pound','GGP'),
	(50,0,'Ghana Cedi','GHS'),
	(51,0,'Gibraltar Pound','GIP'),
	(52,0,'Gambia Dalasi','GMD'),
	(53,0,'Guinea Franc','GNF'),
	(54,0,'Guatemala Quetzal','GTQ'),
	(55,0,'Guyana Dollar','GYD'),
	(56,0,'Hong Kong Dollar','HKD'),
	(57,0,'Honduras Lempira','HNL'),
	(58,0,'Croatia Kuna','HRK'),
	(59,0,'Haiti Gourde','HTG'),
	(60,0,'Hungary Forint','HUF'),
	(61,0,'Indonesia Rupiah','IDR'),
	(62,0,'Israel Shekel','ILS'),
	(63,0,'Isle of Man Pound','IMP'),
	(64,0,'India Rupee','INR'),
	(65,0,'Iraq Dinar','IQD'),
	(66,0,'Iran Rial','IRR'),
	(67,0,'Iceland Krona','ISK'),
	(68,0,'Jersey Pound','JEP'),
	(69,0,'Jamaica Dollar','JMD'),
	(70,0,'Jordan Dinar','JOD'),
	(71,0,'Japan Yen','JPY'),
	(72,0,'Kenya Shilling','KES'),
	(73,0,'Kyrgyzstan Som','KGS'),
	(74,0,'Cambodia Riel','KHR'),
	(75,0,'Comoros Franc','KMF'),
	(76,0,'Korea (North) Won','KPW'),
	(77,0,'Korea (South) Won','KRW'),
	(78,0,'Kuwait Dinar','KWD'),
	(79,0,'Cayman Islands Dollar','KYD'),
	(80,0,'Kazakhstan Tenge','KZT'),
	(81,0,'Laos Kip','LAK'),
	(82,0,'Lebanon Pound','LBP'),
	(83,0,'Sri Lanka Rupee','LKR'),
	(84,0,'Liberia Dollar','LRD'),
	(85,0,'Lesotho Loti','LSL'),
	(86,0,'Lithuania Litas','LTL'),
	(87,0,'Latvia Lat','LVL'),
	(88,0,'Libya Dinar','LYD'),
	(89,0,'Morocco Dirham','MAD'),
	(90,0,'Moldova Leu','MDL'),
	(91,0,'Madagascar Ariary','MGA'),
	(92,0,'Macedonia Denar','MKD'),
	(93,0,'Myanmar (Burma) Kyat','MMK'),
	(94,0,'Mongolia Tughrik','MNT'),
	(95,0,'Macau Pataca','MOP'),
	(96,0,'Mauritania Ouguiya','MRO'),
	(97,0,'Mauritius Rupee','MUR'),
	(98,0,'Maldives (Maldive Islands) Rufiyaa','MVR'),
	(99,0,'Malawi Kwacha','MWK'),
	(100,0,'Mexico Peso','MXN'),
	(101,0,'Malaysia Ringgit','MYR'),
	(102,0,'Mozambique Metical','MZN'),
	(103,0,'Namibia Dollar','NAD'),
	(104,0,'Nigeria Naira','NGN'),
	(105,0,'Nicaragua Cordoba','NIO'),
	(106,0,'Norway Krone','NOK'),
	(107,0,'Nepal Rupee','NPR'),
	(108,0,'New Zealand Dollar','NZD'),
	(109,0,'Oman Rial','OMR'),
	(110,0,'Panama Balboa','PAB'),
	(111,0,'Peru Nuevo Sol','PEN'),
	(112,0,'Papua New Guinea Kina','PGK'),
	(113,0,'Philippines Peso','PHP'),
	(114,0,'Pakistan Rupee','PKR'),
	(115,0,'Poland Zloty','PLN'),
	(116,0,'Paraguay Guarani','PYG'),
	(117,0,'Qatar Riyal','QAR'),
	(118,0,'Romania New Leu','RON'),
	(119,0,'Serbia Dinar','RSD'),
	(120,0,'Russia Ruble','RUB'),
	(121,0,'Rwanda Franc','RWF'),
	(122,0,'Saudi Arabia Riyal','SAR'),
	(123,0,'Solomon Islands Dollar','SBD'),
	(124,0,'Seychelles Rupee','SCR'),
	(125,0,'Sudan Pound','SDG'),
	(126,0,'Sweden Krona','SEK'),
	(127,0,'Singapore Dollar','SGD'),
	(128,0,'Saint Helena Pound','SHP'),
	(129,0,'Sierra Leone Leone','SLL'),
	(130,0,'Somalia Shilling','SOS'),
	(131,0,'Seborga Luigino','SPL*'),
	(132,0,'Suriname Dollar','SRD'),
	(133,0,'São Tomé and Príncipe Dobra','STD'),
	(134,0,'El Salvador Colon','SVC'),
	(135,0,'Syria Pound','SYP'),
	(136,0,'Swaziland Lilangeni','SZL'),
	(137,0,'Thailand Baht','THB'),
	(138,0,'Tajikistan Somoni','TJS'),
	(139,0,'Turkmenistan Manat','TMT'),
	(140,0,'Tunisia Dinar','TND'),
	(141,0,'Tonga Paanga','TOP'),
	(142,0,'Turkey Lira','TRY'),
	(143,0,'Trinidad and Tobago Dollar','TTD'),
	(144,0,'Tuvalu Dollar','TVD'),
	(145,0,'Taiwan New Dollar','TWD'),
	(146,0,'Tanzania Shilling','TZS'),
	(147,0,'Ukraine Hryvna','UAH'),
	(148,0,'Uganda Shilling','UGX'),
	(149,0,'United States Dollar','USD'),
	(150,0,'Uruguay Peso','UYU'),
	(151,0,'Uzbekistan Som','UZS'),
	(152,0,'Venezuela Bolivar','VEF'),
	(153,0,'Viet Nam Dong','VND'),
	(154,0,'Vanuatu Vatu','VUV'),
	(155,0,'Samoa Tala','WST'),
	(156,0,'Communauté Financière Africaine (BEAC) CFA Franc BEAC','XAF'),
	(157,0,'East Caribbean Dollar','XCD'),
	(158,0,'International Monetary Fund (IMF) Special Drawing Rights','XDR'),
	(159,0,'Communauté Financière Africaine (BCEAO) Franc','XOF'),
	(160,0,'Comptoirs Français du Pacifique (CFP) Franc','XPF'),
	(161,0,'Yemen Rial','YER'),
	(162,0,'South Africa Rand','ZAR'),
	(163,0,'Zambia Kwacha','ZMW'),
	(164,0,'Zimbabwe Dollar','ZWD');

/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table document
# ------------------------------------------------------------



# Dump of table document_type
# ------------------------------------------------------------

LOCK TABLES `document_type` WRITE;
/*!40000 ALTER TABLE `document_type` DISABLE KEYS */;

INSERT INTO `document_type` (`id`, `version`, `name`)
VALUES
	(1,0,'Invoice'),
	(2,0,'Receipt'),
	(3,0,'Account statement'),
	(4,0,'Unknown');

/*!40000 ALTER TABLE `document_type` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table image
# ------------------------------------------------------------



# Dump of table line_item
# ------------------------------------------------------------



# Dump of table node
# ------------------------------------------------------------

LOCK TABLES `node` WRITE;
/*!40000 ALTER TABLE `node` DISABLE KEYS */;

INSERT INTO `node` (`node_id`, `parent_node_id`, `project_id`, `name`, `type`, `barcode`, `comment`, `create_datetime`, `creator_id`, `internal_comment`, `last_update_datetime`, `last_update_id`, `status`)
VALUES
	(24,NULL,1,'2013 invoices','C','0001','','2014-01-14 16:14:00','kumulus','test','2014-01-14 16:14:00','kumulus','0'),
	(29,24,1,'Child 1','C','25031821ZHTW','','2014-01-21 15:38:22','kumulus',NULL,'2014-01-21 15:38:22','kumulus','0'),
	(30,24,1,'Child 2','C','18101940ZHTW','','2014-01-21 15:38:31','kumulus',NULL,'2014-01-21 15:38:31','kumulus','0');

/*!40000 ALTER TABLE `node` ENABLE KEYS */;
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
