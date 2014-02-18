# ************************************************************
# Sequel Pro SQL dump
# Version 4004
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.10)
# Database: kumulus
# Generation Time: 2014-02-18 06:22:12 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table company
# ------------------------------------------------------------

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;

INSERT INTO `company` (`id`, `version`, `name`)
VALUES
	(1,0,'SmartSpace Pte Ltd'),
	(2,0,'TestCo Pte Ltd');

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
	(133,0,'S?o Tom? and Pr?ncipe Dobra','STD'),
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
	(156,0,'Communaut? Financi?re Africaine (BEAC) CFA Franc?BEAC','XAF'),
	(157,0,'East Caribbean Dollar','XCD'),
	(158,0,'International Monetary Fund (IMF) Special Drawing Rights','XDR'),
	(159,0,'Communaut? Financi?re Africaine (BCEAO) Franc','XOF'),
	(160,0,'Comptoirs Fran?ais du Pacifique (CFP) Franc','XPF'),
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



# Dump of table page
# ------------------------------------------------------------



# Dump of table project
# ------------------------------------------------------------



# Dump of table project_line_item
# ------------------------------------------------------------



# Dump of table scan_batch
# ------------------------------------------------------------



# Dump of table task
# ------------------------------------------------------------



# Dump of table ufile
# ------------------------------------------------------------

LOCK TABLES `ufile` WRITE;
/*!40000 ALTER TABLE `ufile` DISABLE KEYS */;

INSERT INTO `ufile` (`id`, `version`, `date_uploaded`, `downloads`, `extension`, `name`, `path`, `size`)
VALUES
	(10,163,'2014-01-28 20:20:45',163,'tiff','1390911644628EP-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390911644628EP/1390911644628EP-S.tiff',11596614),
	(11,103,'2014-01-28 20:20:45',103,'jpg','1390911644628EP-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390911644628EP/1390911644628EP-V.jpg',162043),
	(12,378,'2014-01-28 20:20:45',378,'jpg','1390911644628EP-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390911644628EP/1390911644628EP-T.jpg',6986),
	(14,154,'2014-01-28 20:20:57',154,'tiff','1390911656998Q6-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390911656998Q6/1390911656998Q6-S.tiff',11579680),
	(15,105,'2014-01-28 20:20:57',105,'jpg','1390911656998Q6-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390911656998Q6/1390911656998Q6-V.jpg',190079),
	(16,379,'2014-01-28 20:20:57',379,'jpg','1390911656998Q6-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390911656998Q6/1390911656998Q6-T.jpg',9005),
	(18,6,'2014-01-28 20:44:54',6,'tiff','13909130943305W-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13909130943305W/13909130943305W-S.tiff',11596614),
	(19,10,'2014-01-28 20:44:54',10,'jpg','13909130943305W-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13909130943305W/13909130943305W-V.jpg',162043),
	(20,13,'2014-01-28 20:44:54',13,'jpg','13909130943305W-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13909130943305W/13909130943305W-T.jpg',6986),
	(22,4,'2014-01-28 20:45:03',4,'tiff','1390913103000TD-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390913103000TD/1390913103000TD-S.tiff',11579680),
	(23,8,'2014-01-28 20:45:03',8,'jpg','1390913103000TD-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390913103000TD/1390913103000TD-V.jpg',190079),
	(24,13,'2014-01-28 20:45:03',13,'jpg','1390913103000TD-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390913103000TD/1390913103000TD-T.jpg',9005),
	(26,0,'2014-01-28 20:48:24',0,'tiff','1390913304378JJ-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390913304378JJ/1390913304378JJ-S.tiff',11596614),
	(27,1,'2014-01-28 20:48:24',1,'jpg','1390913304378JJ-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390913304378JJ/1390913304378JJ-V.jpg',162043),
	(28,1,'2014-01-28 20:48:24',1,'jpg','1390913304378JJ-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390913304378JJ/1390913304378JJ-T.jpg',6986),
	(30,0,'2014-01-28 20:51:46',0,'tiff','1390913506475ND-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390913506475ND/1390913506475ND-S.tiff',11596614),
	(31,1,'2014-01-28 20:51:46',1,'jpg','1390913506475ND-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390913506475ND/1390913506475ND-V.jpg',81901),
	(32,1,'2014-01-28 20:51:46',1,'jpg','1390913506475ND-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390913506475ND/1390913506475ND-T.jpg',2514),
	(34,0,'2014-01-28 20:51:55',0,'tiff','1390913514739QG-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390913514739QG/1390913514739QG-S.tiff',11596614),
	(35,1,'2014-01-28 20:51:55',1,'jpg','1390913514739QG-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390913514739QG/1390913514739QG-V.jpg',255102),
	(36,4,'2014-01-28 20:51:55',4,'jpg','1390913514739QG-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390913514739QG/1390913514739QG-T.jpg',10395),
	(38,0,'2014-01-29 13:20:11',0,'tiff','1390972810812BB-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390972810812BB/1390972810812BB-S.tiff',11596614),
	(39,1,'2014-01-29 13:20:11',1,'jpg','1390972810812BB-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390972810812BB/1390972810812BB-V.jpg',162043),
	(40,1,'2014-01-29 13:20:11',1,'jpg','1390972810812BB-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390972810812BB/1390972810812BB-T.jpg',6986),
	(42,0,'2014-01-29 13:22:29',0,'tiff','13909729489368N-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13909729489368N/13909729489368N-S.tiff',11596614),
	(43,0,'2014-01-29 13:22:29',0,'jpg','13909729489368N-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13909729489368N/13909729489368N-V.jpg',162043),
	(44,1,'2014-01-29 13:22:29',1,'jpg','13909729489368N-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13909729489368N/13909729489368N-T.jpg',6986),
	(46,0,'2014-01-29 13:22:53',0,'tiff','1390972973203PO-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390972973203PO/1390972973203PO-S.tiff',11596614),
	(47,1,'2014-01-29 13:22:53',1,'jpg','1390972973203PO-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390972973203PO/1390972973203PO-V.jpg',162043),
	(48,1,'2014-01-29 13:22:53',1,'jpg','1390972973203PO-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390972973203PO/1390972973203PO-T.jpg',6986),
	(50,0,'2014-01-29 15:07:43',0,'tiff','1390979263432W7-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390979263432W7/1390979263432W7-S.tiff',11596614),
	(51,1,'2014-01-29 15:07:43',1,'jpg','1390979263432W7-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390979263432W7/1390979263432W7-V.jpg',162043),
	(52,7,'2014-01-29 15:07:43',7,'jpg','1390979263432W7-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390979263432W7/1390979263432W7-T.jpg',6986),
	(54,0,'2014-01-29 15:07:52',0,'tiff','1390979272061JB-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390979272061JB/1390979272061JB-S.tiff',11579680),
	(55,1,'2014-01-29 15:07:52',1,'jpg','1390979272061JB-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390979272061JB/1390979272061JB-V.jpg',190079),
	(56,7,'2014-01-29 15:07:52',7,'jpg','1390979272061JB-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390979272061JB/1390979272061JB-T.jpg',9005),
	(58,0,'2014-01-29 15:07:59',0,'tiff','13909792793520L-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13909792793520L/13909792793520L-S.tiff',11596614),
	(59,3,'2014-01-29 15:07:59',3,'jpg','13909792793520L-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13909792793520L/13909792793520L-V.jpg',64462),
	(60,7,'2014-01-29 15:07:59',7,'jpg','13909792793520L-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13909792793520L/13909792793520L-T.jpg',1832),
	(62,0,'2014-01-29 15:08:06',0,'tiff','1390979285847ED-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390979285847ED/1390979285847ED-S.tiff',11579680),
	(63,3,'2014-01-29 15:08:06',3,'jpg','1390979285847ED-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390979285847ED/1390979285847ED-V.jpg',127337),
	(64,7,'2014-01-29 15:08:06',7,'jpg','1390979285847ED-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390979285847ED/1390979285847ED-T.jpg',4449),
	(66,0,'2014-01-29 15:08:13',0,'tiff','1390979292619KE-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390979292619KE/1390979292619KE-S.tiff',11596614),
	(67,4,'2014-01-29 15:08:13',4,'jpg','1390979292619KE-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390979292619KE/1390979292619KE-V.jpg',251360),
	(68,7,'2014-01-29 15:08:13',7,'jpg','1390979292619KE-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390979292619KE/1390979292619KE-T.jpg',10252),
	(70,0,'2014-01-29 15:28:08',0,'tiff','1390980487842BU-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390980487842BU/1390980487842BU-S.tiff',11596614),
	(71,16,'2014-01-29 15:28:08',16,'jpg','1390980487842BU-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390980487842BU/1390980487842BU-V.jpg',162043),
	(72,110,'2014-01-29 15:28:08',110,'jpg','1390980487842BU-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390980487842BU/1390980487842BU-T.jpg',6986),
	(74,0,'2014-01-29 15:32:04',0,'tiff','13909807238057S-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13909807238057S/13909807238057S-S.tiff',11596614),
	(75,19,'2014-01-29 15:32:04',19,'jpg','13909807238057S-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13909807238057S/13909807238057S-V.jpg',162043),
	(76,108,'2014-01-29 15:32:04',108,'jpg','13909807238057S-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13909807238057S/13909807238057S-T.jpg',6986),
	(78,0,'2014-01-29 15:32:09',0,'tiff','1390980728860VM-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390980728860VM/1390980728860VM-S.tiff',11579680),
	(79,20,'2014-01-29 15:32:09',20,'jpg','1390980728860VM-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390980728860VM/1390980728860VM-V.jpg',190079),
	(80,108,'2014-01-29 15:32:09',108,'jpg','1390980728860VM-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390980728860VM/1390980728860VM-T.jpg',9005),
	(82,0,'2014-01-29 15:32:16',0,'tiff','1390980736094XU-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390980736094XU/1390980736094XU-S.tiff',11596614),
	(83,15,'2014-01-29 15:32:16',15,'jpg','1390980736094XU-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390980736094XU/1390980736094XU-V.jpg',64462),
	(84,108,'2014-01-29 15:32:16',108,'jpg','1390980736094XU-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390980736094XU/1390980736094XU-T.jpg',1832),
	(86,0,'2014-01-29 15:32:22',0,'tiff','1390980741621O9-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390980741621O9/1390980741621O9-S.tiff',11579680),
	(87,10,'2014-01-29 15:32:22',10,'jpg','1390980741621O9-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390980741621O9/1390980741621O9-V.jpg',127337),
	(88,13,'2014-01-29 15:32:22',13,'jpg','1390980741621O9-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390980741621O9/1390980741621O9-T.jpg',4449),
	(90,0,'2014-01-29 15:32:27',0,'tiff','1390980747322YI-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390980747322YI/1390980747322YI-S.tiff',11596614),
	(91,9,'2014-01-29 15:32:27',9,'jpg','1390980747322YI-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390980747322YI/1390980747322YI-V.jpg',251360),
	(92,14,'2014-01-29 15:32:27',14,'jpg','1390980747322YI-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390980747322YI/1390980747322YI-T.jpg',10252),
	(94,0,'2014-01-29 15:32:34',0,'tiff','1390980753870A7-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390980753870A7/1390980753870A7-S.tiff',11579680),
	(95,8,'2014-01-29 15:32:34',8,'jpg','1390980753870A7-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390980753870A7/1390980753870A7-V.jpg',127337),
	(96,14,'2014-01-29 15:32:34',14,'jpg','1390980753870A7-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390980753870A7/1390980753870A7-T.jpg',4449),
	(98,0,'2014-02-03 17:47:18',0,'tiff','1391420837615VJ-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391420837615VJ/1391420837615VJ-S.tiff',11596614),
	(99,2,'2014-02-03 17:47:18',2,'jpg','1391420837615VJ-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391420837615VJ/1391420837615VJ-V.jpg',162043),
	(100,12,'2014-02-03 17:47:18',12,'jpg','1391420837615VJ-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391420837615VJ/1391420837615VJ-T.jpg',6986),
	(102,0,'2014-02-03 19:10:45',0,'tiff','1391425844824LZ-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391425844824LZ/1391425844824LZ-S.tiff',11596614),
	(103,1,'2014-02-03 19:10:45',1,'jpg','1391425844824LZ-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391425844824LZ/1391425844824LZ-V.jpg',81901),
	(104,9,'2014-02-03 19:10:45',9,'jpg','1391425844824LZ-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391425844824LZ/1391425844824LZ-T.jpg',2514),
	(106,0,'2014-02-03 19:10:56',0,'tiff','1391425856443NK-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391425856443NK/1391425856443NK-S.tiff',11579680),
	(107,2,'2014-02-03 19:10:56',2,'jpg','1391425856443NK-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391425856443NK/1391425856443NK-V.jpg',190270),
	(108,11,'2014-02-03 19:10:56',11,'jpg','1391425856443NK-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391425856443NK/1391425856443NK-T.jpg',8931),
	(110,0,'2014-02-03 19:22:41',0,'tiff','1391426561317J7-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391426561317J7/1391426561317J7-S.tiff',11579680),
	(111,1,'2014-02-03 19:22:41',1,'jpg','1391426561317J7-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391426561317J7/1391426561317J7-V.jpg',190079),
	(112,3,'2014-02-03 19:22:41',3,'jpg','1391426561317J7-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391426561317J7/1391426561317J7-T.jpg',9005),
	(114,0,'2014-02-03 19:23:49',0,'tiff','1391426628542OL-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391426628542OL/1391426628542OL-S.tiff',11596614),
	(115,1,'2014-02-03 19:23:49',1,'jpg','1391426628542OL-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391426628542OL/1391426628542OL-V.jpg',162043),
	(116,3,'2014-02-03 19:23:49',3,'jpg','1391426628542OL-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391426628542OL/1391426628542OL-T.jpg',6986),
	(117,0,'2014-02-03 19:51:38',0,'png','130902-Google-Payment Receipt-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391428297937/130902-Google-Payment Receipt-page1 2.png',173233),
	(118,0,'2014-02-03 19:54:24',0,'png','130822-Surveymonkey-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391428463525/130822-Surveymonkey-page1 2.png',306560),
	(119,0,'2014-02-03 19:56:18',0,'png','130922-Surveymonkey-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391428578034/130922-Surveymonkey-page1 2.png',307470),
	(120,0,'2014-02-03 20:36:49',0,'png','130902-[GitHub] Payment Receipt-page2 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391431009400/130902-[GitHub] Payment Receipt-page2 2.png',65326),
	(121,0,'2014-02-03 20:38:10',0,'png','130902-Google-Payment Receipt-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391431090050/130902-Google-Payment Receipt-page1 2.png',173233),
	(122,0,'2014-02-03 20:40:17',0,'png','130902-Google-Payment Receipt-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391431216944/130902-Google-Payment Receipt-page1 2.png',173233),
	(123,0,'2014-02-03 20:41:47',0,'png','130902-[GitHub] Payment Receipt-page2 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391431306771/130902-[GitHub] Payment Receipt-page2 2.png',65326),
	(124,0,'2014-02-03 20:47:04',0,'png','130916-Sparkfun-Receipt-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391431624058/130916-Sparkfun-Receipt-page1 2.png',430550),
	(125,0,'2014-02-03 20:52:57',0,'png','130908-DIDLogic-Receipt-page2 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391431976558/130908-DIDLogic-Receipt-page2 2.png',85411),
	(126,0,'2014-02-03 20:53:57',0,'png','130908-DIDLogic-Receipt-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391432036544/130908-DIDLogic-Receipt-page1 2.png',424747),
	(127,0,'2014-02-03 20:54:22',0,'png','130902-Google-Payment Receipt-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391432061741/130902-Google-Payment Receipt-page1 2.png',173233),
	(128,0,'2014-02-03 20:55:32',0,'png','130916-Sparkfun-Receipt-page2 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391432131848/130916-Sparkfun-Receipt-page2 2.png',85411),
	(129,0,'2014-02-03 20:55:50',0,'png','130922-Surveymonkey-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391432150215/130922-Surveymonkey-page1 2.png',307470),
	(130,0,'2014-02-03 20:57:53',0,'png','130908-DIDLogic-Receipt-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391432273320/130908-DIDLogic-Receipt-page1 2.png',424747),
	(131,0,'2014-02-03 20:58:09',0,'png','130920-Invoice INV-1298-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391432289373/130920-Invoice INV-1298-page1 2.png',289692),
	(132,0,'2014-02-03 21:01:45',0,'png','130908-DIDLogic-Receipt-page2 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391432504818/130908-DIDLogic-Receipt-page2 2.png',85411),
	(133,0,'2014-02-03 21:13:58',0,'pdf','DOC_SIM900_AT Command Manual_V1.03.pdf','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391433238224/DOC_SIM900_AT Command Manual_V1.03.pdf',2079897),
	(134,0,'2014-02-03 21:14:27',0,'pdf','DOC_SIM900_AT Command Manual_V1.03.pdf','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391433266581/DOC_SIM900_AT Command Manual_V1.03.pdf',2079897),
	(135,0,'2014-02-03 21:19:30',0,'pdf','DOC_SIM900_AT Command Manual_V1.03.pdf','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391433570274/DOC_SIM900_AT Command Manual_V1.03.pdf',2079897),
	(136,0,'2014-02-03 21:27:30',0,'pdf','DOC_SIM900_AT Command Manual_V1.03.pdf','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391434050008/DOC_SIM900_AT Command Manual_V1.03.pdf',2079897),
	(137,0,'2014-02-03 21:29:46',0,'pdf','DOC_SIM900_AT Command Manual_V1.03.pdf','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391434185535/DOC_SIM900_AT Command Manual_V1.03.pdf',2079897),
	(138,0,'2014-02-04 10:14:38',0,'pdf','invoice35935099.pdf','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391480077655/invoice35935099.pdf',156293),
	(140,0,'2014-02-04 12:47:48',0,'tiff','1391489267612XY-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391489267612XY/1391489267612XY-S.tiff',11596614),
	(141,5,'2014-02-04 12:47:48',5,'jpg','1391489267612XY-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391489267612XY/1391489267612XY-V.jpg',162043),
	(142,4,'2014-02-04 12:47:48',4,'jpg','1391489267612XY-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391489267612XY/1391489267612XY-T.jpg',6986),
	(143,0,'2014-02-04 12:49:23',0,'png','130723-Funding Request_42018820_20130723-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391489362885/130723-Funding Request_42018820_20130723-page1 2.png',215750),
	(144,0,'2014-02-04 12:58:17',0,'png','130916-Sparkfun-Receipt-page2 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391489896709/130916-Sparkfun-Receipt-page2 2.png',85411),
	(145,0,'2014-02-04 13:06:53',0,'png','130902-Google-Payment Receipt-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391490413351/130902-Google-Payment Receipt-page1 2.png',173233),
	(146,0,'2014-02-04 13:09:39',0,'png','130902-Google-Payment Receipt-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391490578666/130902-Google-Payment Receipt-page1 2.png',173233),
	(147,0,'2014-02-04 15:39:03',0,'png','130902-[GitHub] Payment Receipt-page2 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391499542556/130902-[GitHub] Payment Receipt-page2 2.png',65326),
	(148,0,'2014-02-15 01:47:37',0,'png','130723-Funding Request_42018820_20130723-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1392409056738/130723-Funding Request_42018820_20130723-page1 2.png',215750),
	(149,0,'2014-02-15 01:47:57',0,'png','130822-Surveymonkey-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1392409076755/130822-Surveymonkey-page1 2.png',306560),
	(150,0,'2014-02-15 16:55:42',0,'png','130822-Surveymonkey-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1392463541765/130822-Surveymonkey-page1 2.png',306560),
	(151,0,'2014-02-15 16:56:24',0,'png','130723-Funding Request_42018820_20130723-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1392463583892/130723-Funding Request_42018820_20130723-page1 2.png',215750),
	(152,0,'2014-02-15 17:02:15',0,'png','130723-Funding Request_42018820_20130723-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1392463934800/130723-Funding Request_42018820_20130723-page1 2.png',215750),
	(154,0,'2014-02-15 17:03:39',0,'tiff','1392464019059FH-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392464019059FH/1392464019059FH-S.tiff',11596614),
	(155,5,'2014-02-15 17:03:39',5,'jpg','1392464019059FH-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392464019059FH/1392464019059FH-V.jpg',162043),
	(156,2,'2014-02-15 17:03:39',2,'jpg','1392464019059FH-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392464019059FH/1392464019059FH-T.jpg',6986),
	(158,0,'2014-02-15 17:04:41',0,'tiff','1392464081285SP-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392464081285SP/1392464081285SP-S.tiff',11596614),
	(159,8,'2014-02-15 17:04:41',8,'jpg','1392464081285SP-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392464081285SP/1392464081285SP-V.jpg',162043),
	(160,5,'2014-02-15 17:04:41',5,'jpg','1392464081285SP-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392464081285SP/1392464081285SP-T.jpg',6986),
	(162,0,'2014-02-15 17:07:03',0,'tiff','1392464222760TQ-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392464222760TQ/1392464222760TQ-S.tiff',11596614),
	(163,0,'2014-02-15 17:07:03',0,'jpg','1392464222760TQ-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392464222760TQ/1392464222760TQ-V.jpg',162043),
	(164,5,'2014-02-15 17:07:03',5,'jpg','1392464222760TQ-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392464222760TQ/1392464222760TQ-T.jpg',6986),
	(166,0,'2014-02-15 17:10:33',0,'tiff','1392464433060NB-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392464433060NB/1392464433060NB-S.tiff',11596614),
	(167,0,'2014-02-15 17:10:33',0,'jpg','1392464433060NB-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392464433060NB/1392464433060NB-V.jpg',162043),
	(168,5,'2014-02-15 17:10:33',5,'jpg','1392464433060NB-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392464433060NB/1392464433060NB-T.jpg',6986),
	(170,0,'2014-02-15 17:17:07',0,'tiff','1392464827411OP-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392464827411OP/1392464827411OP-S.tiff',11579680),
	(171,3,'2014-02-15 17:17:07',3,'jpg','1392464827411OP-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392464827411OP/1392464827411OP-V.jpg',190079),
	(172,2,'2014-02-15 17:17:07',2,'jpg','1392464827411OP-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392464827411OP/1392464827411OP-T.jpg',9005),
	(174,0,'2014-02-15 17:21:04',0,'tiff','1392465063502OD-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465063502OD/1392465063502OD-S.tiff',11596614),
	(175,3,'2014-02-15 17:21:04',3,'jpg','1392465063502OD-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465063502OD/1392465063502OD-V.jpg',162043),
	(176,2,'2014-02-15 17:21:04',2,'jpg','1392465063502OD-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465063502OD/1392465063502OD-T.jpg',6986),
	(178,0,'2014-02-15 17:21:39',0,'tiff','1392465098944D2-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465098944D2/1392465098944D2-S.tiff',11596614),
	(179,2,'2014-02-15 17:21:39',2,'jpg','1392465098944D2-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465098944D2/1392465098944D2-V.jpg',162043),
	(180,2,'2014-02-15 17:21:39',2,'jpg','1392465098944D2-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465098944D2/1392465098944D2-T.jpg',6986),
	(182,0,'2014-02-15 17:22:24',0,'tiff','1392465144160NU-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465144160NU/1392465144160NU-S.tiff',11596614),
	(183,0,'2014-02-15 17:22:24',0,'jpg','1392465144160NU-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465144160NU/1392465144160NU-V.jpg',162043),
	(184,5,'2014-02-15 17:22:24',5,'jpg','1392465144160NU-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465144160NU/1392465144160NU-T.jpg',6986),
	(186,0,'2014-02-15 17:23:41',0,'tiff','1392465221092TS-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465221092TS/1392465221092TS-S.tiff',11596614),
	(187,0,'2014-02-15 17:23:41',0,'jpg','1392465221092TS-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465221092TS/1392465221092TS-V.jpg',162043),
	(188,5,'2014-02-15 17:23:41',5,'jpg','1392465221092TS-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465221092TS/1392465221092TS-T.jpg',6986),
	(190,0,'2014-02-15 17:25:40',0,'tiff','1392465340265R0-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465340265R0/1392465340265R0-S.tiff',11579680),
	(191,4,'2014-02-15 17:25:40',4,'jpg','1392465340265R0-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465340265R0/1392465340265R0-V.jpg',190079),
	(192,2,'2014-02-15 17:25:40',2,'jpg','1392465340265R0-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465340265R0/1392465340265R0-T.jpg',9005),
	(194,0,'2014-02-15 17:25:57',0,'tiff','1392465357408RP-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465357408RP/1392465357408RP-S.tiff',11596614),
	(195,1,'2014-02-15 17:25:57',1,'jpg','1392465357408RP-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465357408RP/1392465357408RP-V.jpg',64462),
	(196,2,'2014-02-15 17:25:57',2,'jpg','1392465357408RP-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465357408RP/1392465357408RP-T.jpg',1832),
	(197,0,'2014-02-15 17:26:32',0,'png','130822-Surveymonkey-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1392465391705/130822-Surveymonkey-page1 2.png',306560),
	(199,0,'2014-02-15 17:26:51',0,'tiff','1392465410649IC-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465410649IC/1392465410649IC-S.tiff',11596614),
	(200,0,'2014-02-15 17:26:51',0,'jpg','1392465410649IC-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465410649IC/1392465410649IC-V.jpg',81901),
	(201,5,'2014-02-15 17:26:51',5,'jpg','1392465410649IC-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392465410649IC/1392465410649IC-T.jpg',2514),
	(203,0,'2014-02-15 17:27:45',0,'tiff','139246546549832-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/139246546549832/139246546549832-S.tiff',11579680),
	(204,4,'2014-02-15 17:27:45',4,'jpg','139246546549832-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/139246546549832/139246546549832-V.jpg',190079),
	(205,2,'2014-02-15 17:27:45',2,'jpg','139246546549832-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/139246546549832/139246546549832-T.jpg',9005),
	(207,0,'2014-02-16 12:20:38',0,'tiff','1392533438242WG-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392533438242WG/1392533438242WG-S.tiff',11596614),
	(208,4,'2014-02-16 12:20:38',4,'jpg','1392533438242WG-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392533438242WG/1392533438242WG-V.jpg',64462),
	(209,1,'2014-02-16 12:20:38',1,'jpg','1392533438242WG-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1392533438242WG/1392533438242WG-T.jpg',1832),
	(210,0,'2014-02-17 18:00:08',0,'png','130908-DIDLogic-Receipt-page2 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem//staging/1392640207850/130908-DIDLogic-Receipt-page2 2.png',85411),
	(211,0,'2014-02-17 20:29:32',0,'png','130902-[GitHub] Payment Receipt-page2 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem//staging/1392649172304/130902-[GitHub] Payment Receipt-page2 2.png',65326),
	(212,0,'2014-02-17 20:58:53',0,'png','130916-Sparkfun-Receipt-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem//staging/1392650932557/130916-Sparkfun-Receipt-page1 2.png',430550),
	(213,0,'2014-02-17 21:00:21',0,'png','130822-Surveymonkey-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem//staging/1392651021123/130822-Surveymonkey-page1 2.png',306560),
	(214,0,'2014-02-17 21:01:30',0,'png','130822-Surveymonkey-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem//staging/1392651089703/130822-Surveymonkey-page1 2.png',306560),
	(215,0,'2014-02-17 21:01:49',0,'png','130908-DIDLogic-Receipt-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem//staging/1392651109436/130908-DIDLogic-Receipt-page1 2.png',424747),
	(216,0,'2014-02-17 21:03:13',0,'png','130902-[GitHub] Payment Receipt-page2 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem//staging/1392651192758/130902-[GitHub] Payment Receipt-page2 2.png',65326),
	(217,0,'2014-02-17 21:04:12',0,'png','130902-[GitHub] Payment Receipt-page2 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem//staging/1392651252454/130902-[GitHub] Payment Receipt-page2 2.png',65326),
	(218,0,'2014-02-17 21:05:06',0,'png','130723-Funding Request_42018820_20130723-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem//staging/1392651306362/130723-Funding Request_42018820_20130723-page1 2.png',215750),
	(219,0,'2014-02-17 21:10:36',0,'png','130822-Surveymonkey-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem//staging/1392651635907/130822-Surveymonkey-page1 2.png',306560),
	(220,0,'2014-02-17 21:13:16',0,'png','130908-DIDLogic-Receipt-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem//staging/1392651796159/130908-DIDLogic-Receipt-page1 2.png',424747),
	(221,0,'2014-02-17 21:27:16',0,'png','130902-[GitHub] Payment Receipt-page2 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem//staging/1392652635731/130902-[GitHub] Payment Receipt-page2 2.png',65326),
	(223,0,'2014-02-17 21:28:03',0,'tiff','13926526826939T-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13926526826939T/13926526826939T-S.tiff',11596614),
	(224,0,'2014-02-17 21:28:03',0,'jpg','13926526826939T-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13926526826939T/13926526826939T-V.jpg',64462),
	(225,3,'2014-02-17 21:28:03',3,'jpg','13926526826939T-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13926526826939T/13926526826939T-T.jpg',1832),
	(226,0,'2014-02-17 22:57:33',0,'png','130723-Funding Request_42018820_20130723-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1392658052617/130723-Funding Request_42018820_20130723-page1 2.png',215750),
	(227,0,'2014-02-17 22:59:41',0,'png','130902-[GitHub] Payment Receipt-page2 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1392658181239/130902-[GitHub] Payment Receipt-page2 2.png',65326),
	(228,0,'2014-02-17 23:01:06',0,'png','130723-Funding Request_42018820_20130723-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1392658266083/130723-Funding Request_42018820_20130723-page1 2.png',215750),
	(229,0,'2014-02-17 23:02:03',0,'png','130822-Surveymonkey-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1392658322831/130822-Surveymonkey-page1 2.png',306560),
	(230,0,'2014-02-17 23:05:59',0,'png','130908-DIDLogic-Receipt-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1392658559072/130908-DIDLogic-Receipt-page1 2.png',424747),
	(231,0,'2014-02-17 23:08:59',0,'png','130902-[GitHub] Payment Receipt-page2 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1392658739162/130902-[GitHub] Payment Receipt-page2 2.png',65326),
	(232,0,'2014-02-17 23:09:25',0,'png','130822-Surveymonkey-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1392658765323/130822-Surveymonkey-page1 2.png',306560),
	(233,0,'2014-02-17 23:14:20',0,'png','130902-Google-Payment Receipt-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1392659059825/130902-Google-Payment Receipt-page1 2.png',173233),
	(235,0,'2014-02-17 23:25:22',0,'tiff','13926597220235V-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13926597220235V/13926597220235V-S.tiff',11596614),
	(236,0,'2014-02-17 23:25:22',0,'jpg','13926597220235V-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13926597220235V/13926597220235V-V.jpg',64462),
	(237,0,'2014-02-17 23:25:22',0,'jpg','13926597220235V-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13926597220235V/13926597220235V-T.jpg',1832),
	(239,0,'2014-02-17 23:25:51',0,'tiff','13926597509640F-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13926597509640F/13926597509640F-S.tiff',11596614),
	(240,0,'2014-02-17 23:25:51',0,'jpg','13926597509640F-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13926597509640F/13926597509640F-V.jpg',81901),
	(241,0,'2014-02-17 23:25:51',0,'jpg','13926597509640F-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/13926597509640F/13926597509640F-T.jpg',2514);

/*!40000 ALTER TABLE `ufile` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
