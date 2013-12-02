
CREATE DATABASE  IF NOT EXISTS `scannerapp` ;
USE `scannerapp`;

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `project_id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(50) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  UNIQUE KEY `project_name_UNIQUE` (`project_name`)
);

INSERT INTO `project` VALUES (1,'Inventory','A'),(2,'scannerapp','A'),(3,'warehouse','I');

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` varchar(45) NOT NULL,
  `userid_password` varchar(100) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `collection_right` varchar(1) DEFAULT NULL,
  `import_right` varchar(1) DEFAULT NULL,
  `separation_right` varchar(1) DEFAULT NULL,
  `import_kpi_target` int(11) DEFAULT NULL,
  `separation_kpi_target` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

INSERT INTO `user` VALUES ('ADMIN','a3d95763b3d68d3ea1d5644e250a59de1826c9c7','A','Y','Y','N',1000,0);

CREATE TABLE `nodes` (
  `node_id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `barcode` varchar(45) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `internal_comment` varchar(200) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `parent_node_id` int(11) DEFAULT NULL,
  `hierarchy` varchar(200) DEFAULT NULL,
  `thumbnail_image_name` varchar(1000) DEFAULT NULL,
  `actual_image_name` varchar(1000) DEFAULT NULL,
  `creator_id` varchar(45) DEFAULT NULL,
  `create_datetime` datetime DEFAULT NULL,
  `last_update_id` varchar(45) DEFAULT NULL,
  `last_update_datetime` datetime DEFAULT NULL,
  `document_sequence_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`node_id`),
  KEY `fk_project` (`project_id`),
  KEY `fk_user` (`creator_id`),
  KEY `parent_node_id` (`parent_node_id`),
  KEY `last_update_id` (`last_update_id`),
  CONSTRAINT `fk_project` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`),
  CONSTRAINT `nodes_ibfk_1` FOREIGN KEY (`parent_node_id`) REFERENCES `nodes` (`node_id`),
  CONSTRAINT `nodes_ibfk_2` FOREIGN KEY (`last_update_id`) REFERENCES `user` (`user_id`)
);

ALTER TABLE nodes AUTO_INCREMENT=10000;

DROP TABLE IF EXISTS `attendance`;

CREATE TABLE `attendance` (
  `attendance_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `login_from_time` datetime DEFAULT NULL,
  `login_to_time` datetime DEFAULT NULL,
  PRIMARY KEY (`attendance_id`),
  KEY `fk_attendance_project` (`project_id`),
  KEY `fk_attendance_user` (`user_id`),
  CONSTRAINT `fk_attendance_project` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`),
  CONSTRAINT `fk_attendance_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

CREATE TABLE `application_parameter` (
  `param_id` int(11) NOT NULL AUTO_INCREMENT,
  `param_name` varchar(50) DEFAULT NULL,
  `param_val` varchar(150) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`param_id`),
  KEY `project_id` (`project_id`),
  CONSTRAINT `application_parameter_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`),
  CONSTRAINT `application_parameter_ibfk_2` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`)
);


INSERT INTO `application_parameter` VALUES (1,'refresh_interval','10',NULL),(2,'breath_interval','5',NULL),(3,'targetKPI','5000',NULL),(4,'version','v1.1.3',NULL),(5,'total_upload_images_at_once','21',NULL);


CREATE TABLE `application_tot` (
  `object_type` varchar(45) DEFAULT NULL,
  `object_name` varchar(45) DEFAULT NULL,
  `object_value` varchar(45) DEFAULT NULL
);


INSERT INTO `application_tot` VALUES ('STATUS','In Progress','0'),('STATUS','Done','1'),('NODE_TYPE','Container','C'),('NODE_TYPE','Box','B'),('NODE_TYPE','Document','D'),('STATUS','Sealed','2');

DROP TABLE IF EXISTS `param_names`;

CREATE TABLE `param_names` (
  `param` varchar(20) NOT NULL,
  PRIMARY KEY (`param`)
);


INSERT INTO `param_names` VALUES ('breath_interval'),('refresh_interval'),('targetKPI');

CREATE INDEX NODES_ID ON nodes (node_id);
CREATE INDEX NODES_PROJECT_ID ON nodes (project_id);
CREATE INDEX NODES_PARENT_NODE_ID ON nodes (parent_node_id);
CREATE INDEX NODES_CREATOR_ID ON nodes (creator_id);

CREATE INDEX ATTENDANCE_ID ON attendance (attendance_id);
CREATE INDEX ATTENDANCE_USER_ID ON attendance (user_id);
CREATE INDEX ATTENDANCE_PROJECT_ID ON attendance (project_id);

CREATE INDEX USER_ID ON user (user_id);

ALTER TABLE `scannerapp`.`nodes` ADD COLUMN `uploaded` BIT NULL DEFAULT False  AFTER `document_sequence_number` ;
