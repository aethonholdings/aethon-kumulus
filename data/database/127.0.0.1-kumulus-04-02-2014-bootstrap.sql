# ************************************************************
# Sequel Pro SQL dump
# Version 4004
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.10)
# Database: kumulus
# Generation Time: 2014-02-04 05:01:44 +0000
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
	(2,0,'TestCo Pte Ltd'),
	(12,0,'SMART TEST'),
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

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;

INSERT INTO `document` (`id`, `version`, `company_id`, `date`, `file_id`, `identifier`, `literal`, `project_id`, `status`, `type_id`, `text`)
VALUES
	(5,0,NULL,NULL,NULL,NULL,'1390911873701XZ',1,1,4,NULL),
	(8,0,NULL,NULL,NULL,NULL,'1390913111172JU',1,1,4,NULL),
	(9,0,NULL,NULL,NULL,NULL,'1390913304378JJ',1,1,4,NULL),
	(10,3,NULL,NULL,138,NULL,'1390913506475ND',1,1,4,'<?xml version=\"1.0\" encoding=\"UTF-8\"?><html xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n<meta name=\"xmpTPg:NPages\" content=\"2\"/>\n<meta name=\"Creation-Date\" content=\"2014-02-03T08:34:33Z\"/>\n<meta name=\"meta:creation-date\" content=\"2014-02-03T08:34:33Z\"/>\n<meta name=\"created\" content=\"Mon Feb 03 16:34:33 SGT 2014\"/>\n<meta name=\"dcterms:created\" content=\"2014-02-03T08:34:33Z\"/>\n<meta name=\"producer\" content=\"Apache FOP Version 0.95\"/>\n<meta name=\"Content-Type\" content=\"application/pdf\"/>\n<title/>\n</head>\n<body>\n<div class=\"page\">\n<p/>\n<p>* May include estimated US sales tax, VAT and GST\n</p>\n<p>** This is not a VAT invoice\n</p>\n<p>*** Check the GST statement attached at the end of this Invoice\n</p>\n<p>for details\n† Usage and recurring charges for this statement period will be charged on\n</p>\n<p>your next billing date. The amount of your actual charges for this statement\n</p>\n<p>period may differ from the charges shown on this page. The charges\n</p>\n<p>shown on this page do not include any additional usage charges accrued\n</p>\n<p>during this statement period after the date you are viewing this page. Also,\n</p>\n<p>one-time fees and subscription charges are assessed separately, on the\n</p>\n<p>date that they occur.\n</p>\n<p>All charges and prices are in US Dollars\n</p>\n<p>All AWS Services are sold by Amazon Web Services, Inc.\n</p>\n<p>Service Provider:\n</p>\n<p>(Not to be used for payment remittance)\n</p>\n<p>Amazon Web Services, Inc.\n</p>\n<p>410 Terry Ave North\n</p>\n<p>Seattle, WA  98109-5210, US\n</p>\n<p>1\n</p>\n<p>Account number:\n</p>\n<p>414703851067\n</p>\n<p>Bill to Address:\n</p>\n<p>ATTN: Konstantinos Dimitriou\n</p>\n<p>9 Ardmore Park # 27-02\n</p>\n<p>Singapore, Singapore, 25955, SG\n</p>\n<p>Amazon Web Services Invoice\nEmail or talk to us about your AWS account or bill, visit aws.amazon.com/contact-us/\n</p>\n<p>Invoice Summary\n</p>\n<p>Invoice Number: 35935099\n</p>\n<p>Invoice Date: February 3 , 2014\n</p>\n<p>TOTAL AMOUNT DUE ON February 3 , 2014 $220.29\n</p>\n<p>This invoice is for the billing period January 1 - January 31 , 2014\n</p>\n<p>Greetings from Amazon Web Services, we\'re writing to provide you with an electronic invoice for your use of AWS services. Additional information\n</p>\n<p>regarding your bill, individual service charge details, and your account history are available on the Account Activity Page.\n</p>\n<p>Summary\n</p>\n<p>AWS Service Charges $220.29\n</p>\n<p>Charges $220.29\n</p>\n<p>Credits $0.00\n</p>\n<p>Tax * $0.00\n</p>\n<p>Total for this invoice $220.29\n</p>\n<p>• Details of services from Japan on which consumption tax is included are provided on the Account Activity Page, visit aws.amazon.com/\n</p>\n<p>Detail\n</p>\n<p>Amazon Route 53 $1.51\n</p>\n<p>Charges $1.51\n</p>\n<p>VAT ** $0.00\n</p>\n<p>Amazon Simple Notification Service $0.00\n</p>\n<p>Charges $0.00\n</p>\n<p>VAT ** $0.00\n</p>\n<p>AWS Data Transfer $0.01\n</p>\n<p>Charges $0.01\n</p>\n<p>VAT ** $0.00\n</p>\n<p>Amazon Elastic Compute Cloud $166.90\n</p>\n<p>Charges $166.90\n</p>\n<p>VAT ** $0.00</p>\n<p/>\n</div>\n<div class=\"page\">\n<p/>\n<p>* May include estimated US sales tax, VAT and GST\n</p>\n<p>** This is not a VAT invoice\n</p>\n<p>*** Check the GST statement attached at the end of this Invoice\n</p>\n<p>for details\n† Usage and recurring charges for this statement period will be charged on\n</p>\n<p>your next billing date. The amount of your actual charges for this statement\n</p>\n<p>period may differ from the charges shown on this page. The charges\n</p>\n<p>shown on this page do not include any additional usage charges accrued\n</p>\n<p>during this statement period after the date you are viewing this page. Also,\n</p>\n<p>one-time fees and subscription charges are assessed separately, on the\n</p>\n<p>date that they occur.\n</p>\n<p>All charges and prices are in US Dollars\n</p>\n<p>All AWS Services are sold by Amazon Web Services, Inc.\n</p>\n<p>Service Provider:\n</p>\n<p>(Not to be used for payment remittance)\n</p>\n<p>Amazon Web Services, Inc.\n</p>\n<p>410 Terry Ave North\n</p>\n<p>Seattle, WA  98109-5210, US\n</p>\n<p>2\n</p>\n<p>Amazon RDS Service $51.87\n</p>\n<p>Charges $51.87\n</p>\n<p>VAT ** $0.00\n</p>\n<p>Amazon Simple Storage Service $0.00\n</p>\n<p>Charges $0.00\n</p>\n<p>VAT ** $0.00</p>\n<p/>\n</div>\n</body>\n</html>\n'),
	(11,0,NULL,NULL,NULL,NULL,'1390913514739QG',1,1,4,NULL),
	(12,0,NULL,NULL,NULL,NULL,'1390972810812BB',1,1,4,NULL),
	(13,0,NULL,NULL,NULL,NULL,'13909729489368N',1,1,4,NULL),
	(14,0,NULL,NULL,NULL,NULL,'1390972973203PO',1,1,4,NULL),
	(20,0,NULL,NULL,NULL,NULL,'139097952980399',1,1,4,NULL),
	(25,0,NULL,NULL,NULL,NULL,'1390980741621O9',1,1,4,NULL),
	(28,0,NULL,NULL,NULL,NULL,'1390980823212SH',1,1,4,NULL),
	(29,0,NULL,NULL,NULL,NULL,'1391420837615VJ',1,1,4,NULL),
	(30,0,NULL,NULL,NULL,NULL,'1391421428860BI',1,1,4,NULL),
	(31,0,NULL,NULL,NULL,NULL,'1391425844824LZ',1,1,4,NULL),
	(32,0,NULL,NULL,NULL,NULL,'1391425856443NK',1,1,4,NULL),
	(33,0,NULL,NULL,NULL,NULL,'1391426561317J7',1,1,4,NULL),
	(34,0,NULL,NULL,NULL,NULL,'1391426628542OL',1,1,4,NULL),
	(35,0,NULL,NULL,NULL,NULL,'1391489267612XY',1,1,4,NULL);

/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;


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

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;

INSERT INTO `image` (`id`, `version`, `compressed`, `file_id`, `height`, `thumbnail`, `width`)
VALUES
	(7,0,0,10,2338,0,1653),
	(8,0,0,12,300,0,212),
	(9,0,0,11,2338,0,1653),
	(10,0,0,14,2336,0,1652),
	(11,0,0,16,300,0,212),
	(12,0,0,15,2336,0,1652),
	(13,0,0,18,2338,0,1653),
	(14,0,0,20,300,0,212),
	(15,0,0,19,2338,0,1653),
	(16,0,0,22,2336,0,1652),
	(17,0,0,24,300,0,212),
	(18,0,0,23,2336,0,1652),
	(19,0,0,26,2338,0,1653),
	(20,0,0,28,300,0,212),
	(21,0,0,27,2338,0,1653),
	(22,0,0,30,2338,0,1653),
	(23,0,0,32,300,0,212),
	(24,0,0,31,2338,0,1653),
	(25,0,0,34,2338,0,1653),
	(26,0,0,36,300,0,212),
	(27,0,0,35,2338,0,1653),
	(28,0,0,38,2338,0,1653),
	(29,0,0,40,300,0,212),
	(30,0,0,39,2338,0,1653),
	(31,0,0,42,2338,0,1653),
	(32,0,0,44,300,0,212),
	(33,0,0,43,2338,0,1653),
	(34,0,0,46,2338,0,1653),
	(35,0,0,48,300,0,212),
	(36,0,0,47,2338,0,1653),
	(37,0,0,50,2338,0,1653),
	(38,0,0,52,300,0,212),
	(39,0,0,51,2338,0,1653),
	(40,0,0,54,2336,0,1652),
	(41,0,0,56,300,0,212),
	(42,0,0,55,2336,0,1652),
	(43,0,0,58,2338,0,1653),
	(44,0,0,60,300,0,212),
	(45,0,0,59,2338,0,1653),
	(46,0,0,62,2336,0,1652),
	(47,0,0,64,300,0,212),
	(48,0,0,63,2336,0,1652),
	(49,0,0,66,2338,0,1653),
	(50,0,0,68,300,0,212),
	(51,0,0,67,2338,0,1653),
	(52,0,0,70,2338,0,1653),
	(53,0,0,72,300,0,212),
	(54,0,0,71,2338,0,1653),
	(55,0,0,74,2338,0,1653),
	(56,0,0,76,300,0,212),
	(57,0,0,75,2338,0,1653),
	(58,0,0,78,2336,0,1652),
	(59,0,0,80,300,0,212),
	(60,0,0,79,2336,0,1652),
	(61,0,0,82,2338,0,1653),
	(62,0,0,84,300,0,212),
	(63,0,0,83,2338,0,1653),
	(64,0,0,86,2336,0,1652),
	(65,0,0,88,300,0,212),
	(66,0,0,87,2336,0,1652),
	(67,0,0,90,2338,0,1653),
	(68,0,0,92,300,0,212),
	(69,0,0,91,2338,0,1653),
	(70,0,0,94,2336,0,1652),
	(71,0,0,96,300,0,212),
	(72,0,0,95,2336,0,1652),
	(73,0,0,98,2338,0,1653),
	(74,0,0,100,300,0,212),
	(75,0,0,99,2338,0,1653),
	(76,0,0,102,2338,0,1653),
	(77,0,0,104,300,0,212),
	(78,0,0,103,2338,0,1653),
	(79,0,0,106,2336,0,1652),
	(80,0,0,108,300,0,212),
	(81,0,0,107,2336,0,1652),
	(82,0,0,110,2336,0,1652),
	(83,0,0,112,300,0,212),
	(84,0,0,111,2336,0,1652),
	(85,0,0,114,2338,0,1653),
	(86,0,0,116,300,0,212),
	(87,0,0,115,2338,0,1653),
	(88,0,0,140,2338,0,1653),
	(89,0,0,142,300,0,212),
	(90,0,0,141,2338,0,1653);

/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table line_item
# ------------------------------------------------------------



# Dump of table node
# ------------------------------------------------------------

LOCK TABLES `node` WRITE;
/*!40000 ALTER TABLE `node` DISABLE KEYS */;

INSERT INTO `node` (`node_id`, `barcode`, `comment`, `create_datetime`, `creator_id`, `internal_comment`, `last_update_datetime`, `last_update_id`, `name`, `parent_node_id`, `project_id`, `status`, `type`)
VALUES
	(24,'0001','','2014-01-14 16:14:00','kumulus','test','2014-01-14 16:14:00','kumulus','2013 invoices',NULL,1,'0','C'),
	(29,'25031821ZHTW','','2014-01-21 15:38:22','kumulus',NULL,'2014-01-21 15:38:22','kumulus','Child 1',24,1,'0','C'),
	(30,'18101940ZHTW','','2014-01-21 15:38:31','kumulus',NULL,'2014-01-21 15:38:31','kumulus','Child 2',24,1,'0','C'),
	(33,NULL,NULL,'2014-01-28 20:20:45','kumulus',NULL,'2014-01-28 20:20:45','kumulus','1390911644628EP',29,1,'0','P'),
	(34,NULL,NULL,'2014-01-28 20:20:57','kumulus',NULL,'2014-01-28 20:20:57','kumulus','1390911656998Q6',29,1,'0','P'),
	(35,NULL,NULL,'2014-01-28 20:44:54','kumulus',NULL,'2014-01-28 20:44:54','kumulus','13909130943305W',29,1,'0','P'),
	(36,NULL,NULL,'2014-01-28 20:45:03','kumulus',NULL,'2014-01-28 20:45:03','kumulus','1390913103000TD',29,1,'0','P'),
	(37,NULL,NULL,'2014-01-28 20:48:24','kumulus',NULL,'2014-01-28 20:48:24','kumulus','1390913304378JJ',29,1,'0','P'),
	(38,NULL,NULL,'2014-01-28 20:51:46','kumulus',NULL,'2014-01-28 20:51:46','kumulus','1390913506475ND',29,1,'0','P'),
	(39,NULL,NULL,'2014-01-28 20:51:55','kumulus',NULL,'2014-01-28 20:51:55','kumulus','1390913514739QG',29,1,'0','P'),
	(40,NULL,NULL,'2014-01-29 13:20:11','kumulus',NULL,'2014-01-29 13:20:11','kumulus','1390972810812BB',29,1,'0','P'),
	(41,NULL,NULL,'2014-01-29 13:22:29','kumulus',NULL,'2014-01-29 13:22:29','kumulus','13909729489368N',29,1,'0','P'),
	(42,NULL,NULL,'2014-01-29 13:22:53','kumulus',NULL,'2014-01-29 13:22:53','kumulus','1390972973203PO',29,1,'0','P'),
	(43,NULL,NULL,'2014-01-29 15:07:43','kumulus',NULL,'2014-01-29 15:07:43','kumulus','1390979263432W7',29,1,'0','P'),
	(44,NULL,NULL,'2014-01-29 15:07:52','kumulus',NULL,'2014-01-29 15:07:52','kumulus','1390979272061JB',29,1,'0','P'),
	(45,NULL,NULL,'2014-01-29 15:07:59','kumulus',NULL,'2014-01-29 15:07:59','kumulus','13909792793520L',29,1,'0','P'),
	(46,NULL,NULL,'2014-01-29 15:08:06','kumulus',NULL,'2014-01-29 15:08:06','kumulus','1390979285847ED',29,1,'0','P'),
	(47,NULL,NULL,'2014-01-29 15:08:13','kumulus',NULL,'2014-01-29 15:08:13','kumulus','1390979292619KE',29,1,'0','P'),
	(48,NULL,NULL,'2014-01-29 15:28:08','kumulus',NULL,'2014-01-29 15:28:08','kumulus','1390980487842BU',29,1,'0','P'),
	(49,NULL,NULL,'2014-01-29 15:32:04','kumulus',NULL,'2014-01-29 15:32:04','kumulus','13909807238057S',24,1,'0','P'),
	(50,NULL,NULL,'2014-01-29 15:32:09','kumulus',NULL,'2014-01-29 15:32:09','kumulus','1390980728860VM',24,1,'0','P'),
	(51,NULL,NULL,'2014-01-29 15:32:16','kumulus',NULL,'2014-01-29 15:32:16','kumulus','1390980736094XU',24,1,'0','P'),
	(52,NULL,NULL,'2014-01-29 15:32:22','kumulus',NULL,'2014-01-29 15:32:22','kumulus','1390980741621O9',24,1,'0','P'),
	(53,NULL,NULL,'2014-01-29 15:32:27','kumulus',NULL,'2014-01-29 15:32:27','kumulus','1390980747322YI',24,1,'0','P'),
	(54,NULL,NULL,'2014-01-29 15:32:34','kumulus',NULL,'2014-01-29 15:32:34','kumulus','1390980753870A7',24,1,'0','P'),
	(56,NULL,NULL,'2014-02-03 17:47:18','kumulus',NULL,'2014-02-03 17:47:18','kumulus','1391420837615VJ',29,1,'0','P'),
	(57,NULL,NULL,'2014-02-03 19:10:45','kumulus',NULL,'2014-02-03 19:10:45','kumulus','1391425844824LZ',29,1,'0','P'),
	(58,NULL,NULL,'2014-02-03 19:10:56','kumulus',NULL,'2014-02-03 19:10:56','kumulus','1391425856443NK',29,1,'0','P'),
	(59,NULL,NULL,'2014-02-03 19:22:41','kumulus',NULL,'2014-02-03 19:22:41','kumulus','1391426561317J7',29,1,'0','P'),
	(60,NULL,NULL,'2014-02-03 19:23:49','kumulus',NULL,'2014-02-03 19:23:49','kumulus','1391426628542OL',29,1,'0','P'),
	(61,NULL,NULL,'2014-02-04 12:47:48','kumulus',NULL,'2014-02-04 12:47:48','kumulus','1391489267612XY',29,1,'0','P'),
	(62,'asdf','asdfasdf','2014-02-04 12:50:31','kumulus',NULL,'2014-02-04 12:50:31','kumulus','asdfasdf',29,1,'0','C');

/*!40000 ALTER TABLE `node` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table page
# ------------------------------------------------------------

LOCK TABLES `page` WRITE;
/*!40000 ALTER TABLE `page` DISABLE KEYS */;

INSERT INTO `page` (`id`, `version`, `document_id`, `first`, `last`, `literal`, `node_id`, `number`, `scan_batch_id`, `scan_image_id`, `thumbnail_image_id`, `view_image_id`)
VALUES
	(3,1,5,1,0,'1390911644628EP',33,1,3,7,8,9),
	(4,1,5,0,1,'1390911656998Q6',34,2,4,10,11,12),
	(5,1,8,1,0,'13909130943305W',35,1,5,13,14,15),
	(6,1,8,0,1,'1390913103000TD',36,2,6,16,17,18),
	(7,0,9,1,1,'1390913304378JJ',37,1,7,19,20,21),
	(8,0,10,1,1,'1390913506475ND',38,1,8,22,23,24),
	(9,0,11,1,1,'1390913514739QG',39,1,9,25,26,27),
	(10,0,12,1,1,'1390972810812BB',40,1,10,28,29,30),
	(11,0,13,1,1,'13909729489368N',41,1,11,31,32,33),
	(12,0,14,1,1,'1390972973203PO',42,1,12,34,35,36),
	(13,1,20,1,0,'1390979263432W7',43,1,13,37,38,39),
	(14,1,20,0,0,'1390979272061JB',44,2,14,40,41,42),
	(15,1,20,0,0,'13909792793520L',45,3,15,43,44,45),
	(16,1,20,0,0,'1390979285847ED',46,4,16,46,47,48),
	(17,1,20,0,1,'1390979292619KE',47,5,17,49,50,51),
	(18,1,28,1,0,'1390980487842BU',48,1,18,52,53,54),
	(19,1,28,0,0,'13909807238057S',49,2,19,55,56,57),
	(20,1,28,0,0,'1390980728860VM',50,3,20,58,59,60),
	(21,1,28,0,1,'1390980736094XU',51,4,21,61,62,63),
	(22,0,25,1,1,'1390980741621O9',52,1,22,64,65,66),
	(23,1,30,1,0,'1390980747322YI',53,1,23,67,68,69),
	(24,1,30,0,1,'1390980753870A7',54,2,24,70,71,72),
	(25,0,29,1,1,'1391420837615VJ',56,1,25,73,74,75),
	(26,0,31,1,1,'1391425844824LZ',57,1,26,76,77,78),
	(27,0,32,1,1,'1391425856443NK',58,1,27,79,80,81),
	(28,0,33,1,1,'1391426561317J7',59,1,28,82,83,84),
	(29,0,34,1,1,'1391426628542OL',60,1,29,85,86,87),
	(30,0,35,1,1,'1391489267612XY',61,1,30,88,89,90);

/*!40000 ALTER TABLE `page` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table project
# ------------------------------------------------------------

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;

INSERT INTO `project` (`project_id`, `client_id`, `comment`, `company`, `literal`, `path`, `project_name`, `status`)
VALUES
	(1,2,'this is a great test','KUMULUS PTE LTD','20140120200910ABCDE','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/','test project','A');

/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table project_line_item
# ------------------------------------------------------------



# Dump of table scan_batch
# ------------------------------------------------------------

LOCK TABLES `scan_batch` WRITE;
/*!40000 ALTER TABLE `scan_batch` DISABLE KEYS */;

INSERT INTO `scan_batch` (`id`, `version`, `project_id`, `timestamp`, `user_id`)
VALUES
	(3,0,1,'2014-01-28 20:20:45','kumulus'),
	(4,0,1,'2014-01-28 20:20:57','kumulus'),
	(5,0,1,'2014-01-28 20:44:54','kumulus'),
	(6,0,1,'2014-01-28 20:45:03','kumulus'),
	(7,0,1,'2014-01-28 20:48:24','kumulus'),
	(8,0,1,'2014-01-28 20:51:46','kumulus'),
	(9,0,1,'2014-01-28 20:51:55','kumulus'),
	(10,0,1,'2014-01-29 13:20:11','kumulus'),
	(11,0,1,'2014-01-29 13:22:29','kumulus'),
	(12,0,1,'2014-01-29 13:22:53','kumulus'),
	(13,0,1,'2014-01-29 15:07:43','kumulus'),
	(14,0,1,'2014-01-29 15:07:52','kumulus'),
	(15,0,1,'2014-01-29 15:07:59','kumulus'),
	(16,0,1,'2014-01-29 15:08:06','kumulus'),
	(17,0,1,'2014-01-29 15:08:13','kumulus'),
	(18,0,1,'2014-01-29 15:28:08','kumulus'),
	(19,0,1,'2014-01-29 15:32:04','kumulus'),
	(20,0,1,'2014-01-29 15:32:09','kumulus'),
	(21,0,1,'2014-01-29 15:32:16','kumulus'),
	(22,0,1,'2014-01-29 15:32:22','kumulus'),
	(23,0,1,'2014-01-29 15:32:27','kumulus'),
	(24,0,1,'2014-01-29 15:32:34','kumulus'),
	(25,0,1,'2014-02-03 17:47:18','kumulus'),
	(26,0,1,'2014-02-03 19:10:45','kumulus'),
	(27,0,1,'2014-02-03 19:10:56','kumulus'),
	(28,0,1,'2014-02-03 19:22:41','kumulus'),
	(29,0,1,'2014-02-03 19:23:49','kumulus'),
	(30,0,1,'2014-02-04 12:47:47','kumulus');

/*!40000 ALTER TABLE `scan_batch` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table task
# ------------------------------------------------------------

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;

INSERT INTO `task` (`id`, `version`, `batch_instanceid`, `batch_instance_url_id`, `created`, `document_id`, `status`, `type`, `user_id`)
VALUES
	(5,0,NULL,NULL,'2014-01-28 20:24:34',5,2,2,'kumulus'),
	(8,0,NULL,NULL,'2014-01-28 20:45:11',8,2,2,'kumulus'),
	(10,0,NULL,NULL,'2014-01-28 20:48:31',9,2,2,'kumulus'),
	(13,0,NULL,NULL,'2014-01-28 20:52:04',10,2,2,'kumulus'),
	(14,0,NULL,NULL,'2014-01-28 20:53:06',11,2,2,'kumulus'),
	(16,0,NULL,NULL,'2014-01-29 13:20:41',12,2,2,'kumulus'),
	(18,0,NULL,NULL,'2014-01-29 13:22:38',13,2,2,'kumulus'),
	(20,0,NULL,NULL,'2014-01-29 13:23:01',14,2,2,'kumulus'),
	(26,0,NULL,NULL,'2014-01-29 15:12:10',20,2,2,'kumulus'),
	(34,0,NULL,NULL,'2014-01-29 15:33:43',28,2,2,'kumulus'),
	(36,0,NULL,NULL,'2014-02-03 17:47:32',25,2,2,'kumulus'),
	(37,0,NULL,NULL,'2014-02-03 17:57:09',30,2,2,'kumulus'),
	(39,0,NULL,NULL,'2014-02-03 19:10:56',32,0,1,'kumulus'),
	(40,0,NULL,NULL,'2014-02-03 19:22:41',33,0,1,'kumulus'),
	(41,0,NULL,NULL,'2014-02-03 19:23:49',34,0,1,'kumulus'),
	(42,0,NULL,NULL,'2014-02-04 12:47:48',35,0,1,'kumulus'),
	(43,0,NULL,NULL,'2014-02-04 12:53:15',29,2,2,'kumulus'),
	(44,0,NULL,NULL,'2014-02-04 12:53:20',31,2,2,'kumulus');

/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ufile
# ------------------------------------------------------------

LOCK TABLES `ufile` WRITE;
/*!40000 ALTER TABLE `ufile` DISABLE KEYS */;

INSERT INTO `ufile` (`id`, `version`, `date_uploaded`, `downloads`, `extension`, `name`, `path`, `size`)
VALUES
	(10,163,'2014-01-28 20:20:45',163,'tiff','1390911644628EP-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390911644628EP/1390911644628EP-S.tiff',11596614),
	(11,97,'2014-01-28 20:20:45',97,'jpg','1390911644628EP-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390911644628EP/1390911644628EP-V.jpg',162043),
	(12,374,'2014-01-28 20:20:45',374,'jpg','1390911644628EP-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390911644628EP/1390911644628EP-T.jpg',6986),
	(14,154,'2014-01-28 20:20:57',154,'tiff','1390911656998Q6-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390911656998Q6/1390911656998Q6-S.tiff',11579680),
	(15,101,'2014-01-28 20:20:57',101,'jpg','1390911656998Q6-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390911656998Q6/1390911656998Q6-V.jpg',190079),
	(16,375,'2014-01-28 20:20:57',375,'jpg','1390911656998Q6-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1390911656998Q6/1390911656998Q6-T.jpg',9005),
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
	(107,0,'2014-02-03 19:10:56',0,'jpg','1391425856443NK-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391425856443NK/1391425856443NK-V.jpg',190270),
	(108,9,'2014-02-03 19:10:56',9,'jpg','1391425856443NK-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391425856443NK/1391425856443NK-T.jpg',8931),
	(110,0,'2014-02-03 19:22:41',0,'tiff','1391426561317J7-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391426561317J7/1391426561317J7-S.tiff',11579680),
	(111,0,'2014-02-03 19:22:41',0,'jpg','1391426561317J7-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391426561317J7/1391426561317J7-V.jpg',190079),
	(112,1,'2014-02-03 19:22:41',1,'jpg','1391426561317J7-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391426561317J7/1391426561317J7-T.jpg',9005),
	(114,0,'2014-02-03 19:23:49',0,'tiff','1391426628542OL-S.tiff','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391426628542OL/1391426628542OL-S.tiff',11596614),
	(115,0,'2014-02-03 19:23:49',0,'jpg','1391426628542OL-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391426628542OL/1391426628542OL-V.jpg',162043),
	(116,1,'2014-02-03 19:23:49',1,'jpg','1391426628542OL-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391426628542OL/1391426628542OL-T.jpg',6986),
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
	(141,0,'2014-02-04 12:47:48',0,'jpg','1391489267612XY-V.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391489267612XY/1391489267612XY-V.jpg',162043),
	(142,1,'2014-02-04 12:47:48',1,'jpg','1391489267612XY-T.jpg','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/main/20140120200910ABCDE/pages/1391489267612XY/1391489267612XY-T.jpg',6986),
	(143,0,'2014-02-04 12:49:23',0,'png','130723-Funding Request_42018820_20130723-page1 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391489362885/130723-Funding Request_42018820_20130723-page1 2.png',215750),
	(144,0,'2014-02-04 12:58:17',0,'png','130916-Sparkfun-Receipt-page2 2.png','/Users/cocodinos/Documents/Work/11-Aethon/00-Aethon Holdings/01-Projects/01-Bucephalus/05-Software/kumulus/data/filesystem/staging/1391489896709/130916-Sparkfun-Receipt-page2 2.png',85411);

/*!40000 ALTER TABLE `ufile` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
