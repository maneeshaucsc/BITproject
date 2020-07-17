-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: orderandsales
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `purchasingtype_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_brand_purchasingtype1_idx` (`purchasingtype_id`),
  CONSTRAINT `fk_brand_purchasingtype1` FOREIGN KEY (`purchasingtype_id`) REFERENCES `purchasingtype` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'Ceypetco',1),(2,'Imuzu',3),(3,'Lockheed',4),(4,'Ceypetco',2);
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brandcategory`
--

DROP TABLE IF EXISTS `brandcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brandcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_brand_has_category_category1_idx` (`category_id`),
  KEY `fk_brand_has_category_brand1_idx` (`brand_id`),
  CONSTRAINT `fk_brand_has_category_brand1` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_brand_has_category_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brandcategory`
--

LOCK TABLES `brandcategory` WRITE;
/*!40000 ALTER TABLE `brandcategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `brandcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `purchasingtype_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category_purchasingtype1_idx` (`purchasingtype_id`),
  CONSTRAINT `fk_category_purchasingtype1` FOREIGN KEY (`purchasingtype_id`) REFERENCES `purchasingtype` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Petrol',1),(2,'Diesal',1),(3,'Petrol Engine Oil',2),(4,'Diesal Engine Oil',2),(5,'Grease',2),(6,'Gear Oil',2),(7,'Cans',4);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `civilstatus`
--

DROP TABLE IF EXISTS `civilstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `civilstatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `civilstatus`
--

LOCK TABLES `civilstatus` WRITE;
/*!40000 ALTER TABLE `civilstatus` DISABLE KEYS */;
INSERT INTO `civilstatus` VALUES (2,'Married');
/*!40000 ALTER TABLE `civilstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `designation`
--

DROP TABLE IF EXISTS `designation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `designation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `designation`
--

LOCK TABLES `designation` WRITE;
/*!40000 ALTER TABLE `designation` DISABLE KEYS */;
INSERT INTO `designation` VALUES (2,'Owner');
/*!40000 ALTER TABLE `designation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` char(4) DEFAULT NULL,
  `fullname` varchar(150) DEFAULT NULL,
  `callingname` varchar(45) DEFAULT NULL,
  `photo` mediumblob,
  `dobirth` date DEFAULT NULL,
  `nic` char(12) DEFAULT NULL,
  `address` text,
  `mobile` char(10) DEFAULT NULL,
  `land` char(10) DEFAULT NULL,
  `description` text,
  `doassignment` date DEFAULT NULL,
  `gender_id` int(11) NOT NULL,
  `designation_id` int(11) NOT NULL,
  `civilstatus_id` int(11) NOT NULL,
  `employeestatus_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_employee_gender_idx` (`gender_id`),
  KEY `fk_employee_designation1_idx` (`designation_id`),
  KEY `fk_employee_civilstatus1_idx` (`civilstatus_id`),
  KEY `fk_employee_employeestatus1_idx` (`employeestatus_id`),
  CONSTRAINT `fk_employee_civilstatus1` FOREIGN KEY (`civilstatus_id`) REFERENCES `civilstatus` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_designation1` FOREIGN KEY (`designation_id`) REFERENCES `designation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_employeestatus1` FOREIGN KEY (`employeestatus_id`) REFERENCES `employeestatus` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_gender` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,NULL,'Dissanayakefillingstation','Admin',NULL,NULL,'000000000V',NULL,NULL,NULL,NULL,NULL,2,2,2,2),(2,'1234','Maneesha','Maneesha',NULL,'1995-04-29','956200539V','Gampaha','0773719508','0332255136','owner','2019-03-03',2,2,2,2),(3,'2345','Shelton','Shelton',NULL,'1965-02-05','652356232V','Radawana','0775463251','0332244658','manager','2019-03-03',2,2,2,2);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeestatus`
--

DROP TABLE IF EXISTS `employeestatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employeestatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeestatus`
--

LOCK TABLES `employeestatus` WRITE;
/*!40000 ALTER TABLE `employeestatus` DISABLE KEYS */;
INSERT INTO `employeestatus` VALUES (2,'Operational');
/*!40000 ALTER TABLE `employeestatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gender`
--

DROP TABLE IF EXISTS `gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gender` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
INSERT INTO `gender` VALUES (2,'Male');
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grn`
--

DROP TABLE IF EXISTS `grn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `supplier_id` int(11) NOT NULL,
  `porder_id` int(11) NOT NULL,
  `grnno` char(6) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `totalvalue` decimal(10,2) DEFAULT NULL,
  `paidtotal` decimal(10,2) DEFAULT NULL,
  `grnstatus_id` int(11) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `employee_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grn_grnstatus1_idx` (`grnstatus_id`),
  KEY `fk_grn_porder1_idx` (`porder_id`),
  KEY `fk_grn_supplier1_idx` (`supplier_id`),
  KEY `fk_grn_employee1_idx` (`employee_id`),
  CONSTRAINT `fk_grn_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_grn_grnstatus1` FOREIGN KEY (`grnstatus_id`) REFERENCES `grnstatus` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_grn_porder1` FOREIGN KEY (`porder_id`) REFERENCES `porder` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_grn_supplier1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grn`
--

LOCK TABLES `grn` WRITE;
/*!40000 ALTER TABLE `grn` DISABLE KEYS */;
INSERT INTO `grn` VALUES (1,1,1,'G123','2019-02-21',1235.00,1000.00,1,NULL,1);
/*!40000 ALTER TABLE `grn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grnitem`
--

DROP TABLE IF EXISTS `grnitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grnitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grn_id` int(11) NOT NULL,
  `Item_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unitprice` decimal(10,2) DEFAULT NULL,
  `linetotal` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grn_has_Item_Item1_idx` (`Item_id`),
  KEY `fk_grn_has_Item_grn1_idx` (`grn_id`),
  CONSTRAINT `fk_grn_has_Item_Item1` FOREIGN KEY (`Item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_grn_has_Item_grn1` FOREIGN KEY (`grn_id`) REFERENCES `grn` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grnitem`
--

LOCK TABLES `grnitem` WRITE;
/*!40000 ALTER TABLE `grnitem` DISABLE KEYS */;
INSERT INTO `grnitem` VALUES (1,1,18,12,12.00,1234.00);
/*!40000 ALTER TABLE `grnitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grnstatus`
--

DROP TABLE IF EXISTS `grnstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grnstatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grnstatus`
--

LOCK TABLES `grnstatus` WRITE;
/*!40000 ALTER TABLE `grnstatus` DISABLE KEYS */;
INSERT INTO `grnstatus` VALUES (1,'Done'),(2,'Notdone');
/*!40000 ALTER TABLE `grnstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(4) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `subcategory_id` int(11) DEFAULT NULL,
  `salesprice` decimal(5,2) DEFAULT NULL,
  `purchaseprice` decimal(5,2) DEFAULT NULL,
  `volume` varchar(6) DEFAULT NULL,
  `entrydate` datetime DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `reorderlevel` varchar(45) DEFAULT NULL,
  `employee_id` int(11) NOT NULL,
  `brand_id` int(11) DEFAULT NULL,
  `purchasingtype_id` int(11) NOT NULL,
  `meter_id` int(11) DEFAULT NULL,
  `unittype_id` int(11) DEFAULT NULL,
  `tank_id` int(11) DEFAULT NULL,
  `itemstatus_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_fuel_subcategory1_idx` (`subcategory_id`),
  KEY `fk_fuel_employee1_idx` (`employee_id`),
  KEY `fk_fuel_brand1_idx1` (`brand_id`),
  KEY `fk_fuel_purchasingtype2_idx` (`purchasingtype_id`),
  KEY `fk_fuel_meter1_idx` (`meter_id`),
  KEY `fk_fuel_unittype1_idx` (`unittype_id`),
  KEY `fk_fuel_tank1_idx` (`tank_id`),
  KEY `fk_Item_itemstatus1_idx` (`itemstatus_id`),
  CONSTRAINT `fk_Item_itemstatus1` FOREIGN KEY (`itemstatus_id`) REFERENCES `itemstatus` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_fuel_brand1` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_fuel_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_fuel_meter1` FOREIGN KEY (`meter_id`) REFERENCES `meter` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_fuel_purchasingtype2` FOREIGN KEY (`purchasingtype_id`) REFERENCES `purchasingtype` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_fuel_subcategory1` FOREIGN KEY (`subcategory_id`) REFERENCES `subcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_fuel_tank1` FOREIGN KEY (`tank_id`) REFERENCES `tank` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_fuel_unittype1` FOREIGN KEY (`unittype_id`) REFERENCES `unittype` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (18,'2345','Diesal Super Diesal ',3,98.00,69.00,'2000L','2019-04-30 00:00:00',221,'12',3,1,2,1,1,1,1),(21,'7895','juuihiojo',36,47.00,85.00,'1234V','2019-10-02 00:00:00',23,'4',1,3,4,3,28,2,1),(22,'9658','Petrol-92 Octane',28,56.00,34.00,'1234V','2019-10-02 00:00:00',467,'4567',1,4,2,3,15,2,1),(23,'6598','Delo',37,98.00,85.00,'5660V','2019-10-02 00:00:00',865,'45151',1,3,4,3,29,5,1),(24,'7845','Petrol-92 Octane',28,34.00,85.00,'4565V','2019-10-02 00:00:00',895,'4845',1,4,2,2,14,3,1),(25,'1235','Engineoil',21,34.00,85.00,NULL,'2019-10-05 00:00:00',123,'23',1,4,2,NULL,20,NULL,1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemstatus`
--

DROP TABLE IF EXISTS `itemstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemstatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemstatus`
--

LOCK TABLES `itemstatus` WRITE;
/*!40000 ALTER TABLE `itemstatus` DISABLE KEYS */;
INSERT INTO `itemstatus` VALUES (1,'Available'),(2,'Not Available');
/*!40000 ALTER TABLE `itemstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meter`
--

DROP TABLE IF EXISTS `meter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `employee_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_meter_employee1_idx` (`employee_id`),
  CONSTRAINT `fk_meter_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meter`
--

LOCK TABLES `meter` WRITE;
/*!40000 ALTER TABLE `meter` DISABLE KEYS */;
INSERT INTO `meter` VALUES (1,'M01',1),(2,'M02',1),(3,'M03',2),(4,'M04',3);
/*!40000 ALTER TABLE `meter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `module`
--

DROP TABLE IF EXISTS `module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES (1,'EMPLOYEE'),(2,'USER'),(3,'PRIVILAGE'),(4,'DESIGNATION'),(5,'USERPASSWORDCHANGE'),(6,'ITEM'),(7,'SUPPLIER'),(8,'BRAND'),(9,'CATEGORY'),(10,'SUBCATEGORY'),(11,'METER'),(12,'PURCHASINGTYPE'),(13,'UNITTYPE'),(14,'TANK'),(15,'ITEMSTATUS'),(16,'SUPPLIERSTATUS'),(17,'PORDER');
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operationlog`
--

DROP TABLE IF EXISTS `operationlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operationlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL,
  `module_id` int(11) NOT NULL,
  `operation` tinyint(4) DEFAULT NULL,
  `description` text,
  `datetime` datetime DEFAULT NULL,
  `operationstatus_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_operationlog_employee1_idx` (`employee_id`),
  KEY `fk_operationlog_module1_idx` (`module_id`),
  KEY `fk_operationlog_operationstatus1_idx` (`operationstatus_id`),
  CONSTRAINT `fk_operationlog_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_operationlog_module1` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_operationlog_operationstatus1` FOREIGN KEY (`operationstatus_id`) REFERENCES `operationstatus` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operationlog`
--

LOCK TABLES `operationlog` WRITE;
/*!40000 ALTER TABLE `operationlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `operationlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operationstatus`
--

DROP TABLE IF EXISTS `operationstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operationstatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operationstatus`
--

LOCK TABLES `operationstatus` WRITE;
/*!40000 ALTER TABLE `operationstatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `operationstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `porder`
--

DROP TABLE IF EXISTS `porder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `porder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `porderno` char(6) DEFAULT NULL,
  `supplier_id` int(11) NOT NULL,
  `grandtotal` decimal(8,2) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `porderstatus_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_porder_porderstatus1_idx` (`porderstatus_id`),
  KEY `fk_porder_supplier1_idx` (`supplier_id`),
  KEY `fk_porder_employee1_idx` (`employee_id`),
  CONSTRAINT `fk_porder_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_porder_porderstatus1` FOREIGN KEY (`porderstatus_id`) REFERENCES `porderstatus` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_porder_supplier1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `porder`
--

LOCK TABLES `porder` WRITE;
/*!40000 ALTER TABLE `porder` DISABLE KEYS */;
INSERT INTO `porder` VALUES (1,'P12345',1,1234.00,'2019-12-09',1,1);
/*!40000 ALTER TABLE `porder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `porderitem`
--

DROP TABLE IF EXISTS `porderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `porderitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Qty` int(11) DEFAULT NULL,
  `Volume` varchar(45) DEFAULT NULL,
  `Item_id` int(11) DEFAULT NULL,
  `porder_id` int(11) DEFAULT NULL,
  `linetotal` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Item_has_porder_porder1_idx` (`porder_id`),
  KEY `fk_Item_has_porder_Item1_idx` (`Item_id`),
  CONSTRAINT `fk_Item_has_porder_Item1` FOREIGN KEY (`Item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_has_porder_porder1` FOREIGN KEY (`porder_id`) REFERENCES `porder` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `porderitem`
--

LOCK TABLES `porderitem` WRITE;
/*!40000 ALTER TABLE `porderitem` DISABLE KEYS */;
INSERT INTO `porderitem` VALUES (1,123,NULL,18,1,1234);
/*!40000 ALTER TABLE `porderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `porderstatus`
--

DROP TABLE IF EXISTS `porderstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `porderstatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `porderstatus`
--

LOCK TABLES `porderstatus` WRITE;
/*!40000 ALTER TABLE `porderstatus` DISABLE KEYS */;
INSERT INTO `porderstatus` VALUES (1,'Done'),(2,'Notdone');
/*!40000 ALTER TABLE `porderstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilage`
--

DROP TABLE IF EXISTS `privilage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `module_id` int(11) NOT NULL,
  `sel` int(11) DEFAULT NULL,
  `ins` int(11) DEFAULT NULL,
  `upd` int(11) DEFAULT NULL,
  `del` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_role_has_module_module1_idx` (`module_id`),
  KEY `fk_role_has_module_role1_idx` (`role_id`),
  CONSTRAINT `fk_role_has_module_module1` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_module_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilage`
--

LOCK TABLES `privilage` WRITE;
/*!40000 ALTER TABLE `privilage` DISABLE KEYS */;
INSERT INTO `privilage` VALUES (1,1,1,1,1,1,1),(2,2,2,2,2,2,2),(3,2,6,1,1,1,1),(4,1,6,1,1,1,1),(5,1,2,1,1,1,1);
/*!40000 ALTER TABLE `privilage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchasingtype`
--

DROP TABLE IF EXISTS `purchasingtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchasingtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchasingtype`
--

LOCK TABLES `purchasingtype` WRITE;
/*!40000 ALTER TABLE `purchasingtype` DISABLE KEYS */;
INSERT INTO `purchasingtype` VALUES (1,'Fuel'),(2,'Lubricants'),(3,'Battery water'),(4,'Break oil');
/*!40000 ALTER TABLE `purchasingtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin1'),(2,'Admin2');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessionlog`
--

DROP TABLE IF EXISTS `sessionlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sessionlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `token` varchar(45) DEFAULT NULL,
  `logintime` datetime DEFAULT NULL,
  `logouttime` datetime DEFAULT NULL,
  `description` text,
  `sessionstatus_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sessionlog_user1_idx` (`user_id`),
  KEY `fk_sessionlog_sessionstatus1_idx` (`sessionstatus_id`),
  CONSTRAINT `fk_sessionlog_sessionstatus1` FOREIGN KEY (`sessionstatus_id`) REFERENCES `sessionstatus` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sessionlog_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=268 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessionlog`
--

LOCK TABLES `sessionlog` WRITE;
/*!40000 ALTER TABLE `sessionlog` DISABLE KEYS */;
INSERT INTO `sessionlog` VALUES (1,1,NULL,'2019-03-02 15:20:29',NULL,NULL,3),(2,1,NULL,'2019-03-09 12:18:37',NULL,NULL,3),(3,1,NULL,'2019-03-09 12:25:44',NULL,NULL,3),(4,1,NULL,'2019-03-12 04:59:50',NULL,NULL,3),(5,1,NULL,'2019-03-12 10:31:33',NULL,NULL,3),(6,1,NULL,'2019-03-12 10:36:27',NULL,NULL,3),(7,1,NULL,'2019-03-12 10:54:21',NULL,NULL,3),(8,1,NULL,'2019-03-12 14:24:26',NULL,NULL,3),(9,1,NULL,'2019-03-12 14:41:34',NULL,NULL,3),(10,1,NULL,'2019-03-13 20:15:23',NULL,NULL,3),(11,1,NULL,'2019-03-14 19:36:21',NULL,NULL,3),(12,1,NULL,'2019-03-19 05:08:32',NULL,NULL,3),(13,1,NULL,'2019-03-19 05:08:32',NULL,NULL,3),(14,1,NULL,'2019-03-19 09:27:20',NULL,NULL,3),(15,2,NULL,'2019-03-19 09:37:04','2019-03-19 09:38:40',NULL,4),(16,2,NULL,'2019-03-19 09:41:33','2019-03-19 09:54:17',NULL,4),(17,1,NULL,'2019-03-19 09:56:13',NULL,NULL,3),(18,1,NULL,'2019-03-23 09:18:28',NULL,NULL,3),(19,1,NULL,'2019-03-26 10:01:25',NULL,NULL,3),(20,1,NULL,'2019-03-26 10:30:44',NULL,NULL,3),(21,1,NULL,'2019-03-30 15:41:47','2019-03-30 17:37:41',NULL,4),(22,1,NULL,'2019-03-30 17:37:56',NULL,NULL,3),(23,1,NULL,'2019-04-06 15:39:13',NULL,NULL,3),(24,1,NULL,'2019-04-15 22:58:51',NULL,NULL,3),(25,1,NULL,'2019-04-15 22:59:27',NULL,NULL,3),(26,1,NULL,'2019-04-15 23:04:48',NULL,NULL,3),(27,1,NULL,'2019-04-15 23:39:13',NULL,NULL,3),(28,1,NULL,'2019-04-15 23:40:11',NULL,NULL,3),(29,1,NULL,'2019-04-15 23:44:08',NULL,NULL,3),(30,1,NULL,'2019-04-16 10:05:01',NULL,NULL,3),(31,1,NULL,'2019-04-16 10:05:20',NULL,NULL,3),(32,1,NULL,'2019-04-16 10:08:13',NULL,NULL,3),(33,1,NULL,'2019-04-16 10:08:25',NULL,NULL,3),(34,2,NULL,'2019-04-16 10:08:34',NULL,NULL,3),(35,1,NULL,'2019-04-16 10:11:38',NULL,NULL,3),(36,1,NULL,'2019-04-16 10:12:56',NULL,NULL,3),(37,1,NULL,'2019-04-16 10:37:28',NULL,NULL,3),(38,1,NULL,'2019-04-16 10:39:45',NULL,NULL,3),(39,1,NULL,'2019-04-16 10:41:30',NULL,NULL,3),(40,1,NULL,'2019-04-16 11:04:39',NULL,NULL,3),(41,1,NULL,'2019-04-16 12:09:13',NULL,NULL,3),(42,1,NULL,'2019-04-16 12:09:39',NULL,NULL,3),(43,1,NULL,'2019-04-16 12:09:49',NULL,NULL,3),(44,1,NULL,'2019-04-16 12:10:02',NULL,NULL,3),(45,2,NULL,'2019-04-16 12:10:11',NULL,NULL,3),(46,1,NULL,'2019-04-16 12:11:08',NULL,NULL,3),(47,1,NULL,'2019-04-16 12:13:35',NULL,NULL,3),(48,1,NULL,'2019-04-20 12:03:16',NULL,NULL,3),(49,1,NULL,'2019-04-20 12:53:43',NULL,NULL,3),(50,1,NULL,'2019-04-29 16:50:36',NULL,NULL,3),(51,1,NULL,'2019-04-30 09:58:22',NULL,NULL,3),(52,1,NULL,'2019-04-30 11:13:43',NULL,NULL,3),(53,1,NULL,'2019-04-30 11:33:23',NULL,NULL,3),(54,1,NULL,'2019-05-05 14:39:59',NULL,NULL,3),(55,1,NULL,'2019-05-05 14:43:04',NULL,NULL,3),(56,1,NULL,'2019-05-05 14:48:59',NULL,NULL,3),(57,1,NULL,'2019-05-05 14:49:55',NULL,NULL,3),(58,1,NULL,'2019-05-05 14:52:56',NULL,NULL,3),(59,1,NULL,'2019-05-05 15:02:25',NULL,NULL,3),(60,1,NULL,'2019-05-05 15:06:11',NULL,NULL,3),(61,1,NULL,'2019-05-05 16:00:35',NULL,NULL,3),(62,1,NULL,'2019-05-05 16:05:03',NULL,NULL,3),(63,1,NULL,'2019-05-05 16:08:25',NULL,NULL,3),(64,1,NULL,'2019-05-05 16:17:12',NULL,NULL,3),(65,1,NULL,'2019-05-05 19:49:44',NULL,NULL,3),(66,1,NULL,'2019-05-05 20:22:57',NULL,NULL,3),(67,1,NULL,'2019-05-05 23:17:08',NULL,NULL,3),(68,1,NULL,'2019-05-06 15:53:24',NULL,NULL,3),(69,1,NULL,'2019-05-08 08:34:53',NULL,NULL,3),(70,1,NULL,'2019-05-09 14:00:26',NULL,NULL,3),(71,1,NULL,'2019-09-04 13:30:31',NULL,NULL,3),(72,1,NULL,'2019-09-04 14:17:38',NULL,NULL,3),(73,1,NULL,'2019-09-04 15:34:12',NULL,NULL,3),(74,1,NULL,'2019-09-11 14:49:06',NULL,NULL,3),(75,1,NULL,'2019-09-11 14:50:51',NULL,NULL,3),(76,1,NULL,'2019-09-11 15:14:48',NULL,NULL,3),(77,1,NULL,'2019-09-11 15:20:04',NULL,NULL,3),(78,1,NULL,'2019-09-11 15:32:08',NULL,NULL,3),(79,1,NULL,'2019-09-12 23:29:27',NULL,NULL,3),(80,1,NULL,'2019-09-13 09:42:51',NULL,NULL,3),(81,1,NULL,'2019-09-13 10:14:13',NULL,NULL,3),(82,1,NULL,'2019-09-13 10:29:28',NULL,NULL,3),(83,1,NULL,'2019-09-13 12:56:17',NULL,NULL,3),(84,1,NULL,'2019-09-13 14:11:01',NULL,NULL,3),(85,1,NULL,'2019-09-13 14:13:10',NULL,NULL,3),(86,1,NULL,'2019-09-13 15:39:59',NULL,NULL,3),(87,1,NULL,'2019-09-16 17:17:07',NULL,NULL,3),(88,1,NULL,'2019-09-16 17:29:56',NULL,NULL,3),(89,1,NULL,'2019-09-16 17:33:39',NULL,NULL,3),(90,1,NULL,'2019-09-16 22:17:33',NULL,NULL,3),(91,1,NULL,'2019-09-16 22:27:36',NULL,NULL,3),(92,1,NULL,'2019-09-16 22:36:50',NULL,NULL,3),(93,1,NULL,'2019-09-17 18:29:39',NULL,NULL,3),(94,1,NULL,'2019-09-18 21:34:07',NULL,NULL,3),(95,1,NULL,'2019-09-18 23:32:35',NULL,NULL,3),(96,1,NULL,'2019-09-19 15:16:55',NULL,NULL,3),(97,1,NULL,'2019-09-20 14:05:06',NULL,NULL,3),(98,1,NULL,'2019-09-21 20:24:22',NULL,NULL,3),(99,1,NULL,'2019-09-21 20:28:44',NULL,NULL,3),(100,1,NULL,'2019-09-21 20:34:26',NULL,NULL,3),(101,1,NULL,'2019-09-21 21:01:41',NULL,NULL,3),(102,1,NULL,'2019-09-21 21:05:18',NULL,NULL,3),(103,1,NULL,'2019-09-21 21:09:41',NULL,NULL,3),(104,1,NULL,'2019-09-21 21:48:47',NULL,NULL,3),(105,1,NULL,'2019-09-23 01:35:13',NULL,NULL,3),(106,1,NULL,'2019-09-23 01:45:09',NULL,NULL,3),(107,1,NULL,'2019-09-23 01:45:09',NULL,NULL,3),(108,1,NULL,'2019-09-23 12:30:39',NULL,NULL,3),(109,1,NULL,'2019-09-23 17:20:02',NULL,NULL,3),(110,1,NULL,'2019-09-23 17:24:19',NULL,NULL,3),(111,1,NULL,'2019-09-23 17:37:29',NULL,NULL,3),(112,1,NULL,'2019-09-23 17:41:58',NULL,NULL,3),(113,1,NULL,'2019-09-23 17:45:07',NULL,NULL,3),(114,1,NULL,'2019-09-23 20:40:17',NULL,NULL,3),(115,1,NULL,'2019-09-23 21:11:03',NULL,NULL,3),(116,1,NULL,'2019-09-24 13:51:37',NULL,NULL,3),(117,1,NULL,'2019-09-24 14:50:48',NULL,NULL,3),(118,1,NULL,'2019-09-24 15:08:17',NULL,NULL,3),(119,1,NULL,'2019-09-24 16:57:20',NULL,NULL,3),(120,1,NULL,'2019-09-24 16:59:35',NULL,NULL,3),(121,1,NULL,'2019-09-24 17:07:53',NULL,NULL,3),(122,1,NULL,'2019-09-24 22:25:51',NULL,NULL,3),(123,1,NULL,'2019-09-24 22:30:50',NULL,NULL,3),(124,1,NULL,'2019-09-24 22:39:48',NULL,NULL,3),(125,1,NULL,'2019-09-24 22:42:53',NULL,NULL,3),(126,1,NULL,'2019-09-25 16:17:16',NULL,NULL,3),(127,1,NULL,'2019-09-25 20:01:35',NULL,NULL,3),(128,1,NULL,'2019-09-25 20:19:10',NULL,NULL,3),(129,1,NULL,'2019-09-25 20:21:31',NULL,NULL,3),(130,1,NULL,'2019-09-25 20:37:40',NULL,NULL,3),(131,1,NULL,'2019-09-25 20:40:11',NULL,NULL,3),(132,1,NULL,'2019-09-25 20:42:05',NULL,NULL,3),(133,1,NULL,'2019-09-25 22:31:21',NULL,NULL,3),(134,1,NULL,'2019-09-25 22:47:44',NULL,NULL,3),(135,1,NULL,'2019-09-25 23:42:23',NULL,NULL,3),(136,1,NULL,'2019-09-26 00:04:22',NULL,NULL,3),(137,1,NULL,'2019-09-26 00:09:43',NULL,NULL,3),(138,1,NULL,'2019-09-26 00:42:48',NULL,NULL,3),(139,1,NULL,'2019-09-26 00:50:00',NULL,NULL,3),(140,1,NULL,'2019-09-26 00:53:06',NULL,NULL,3),(141,1,NULL,'2019-09-26 01:08:56',NULL,NULL,3),(142,1,NULL,'2019-09-26 01:12:13',NULL,NULL,3),(143,1,NULL,'2019-09-26 01:17:00',NULL,NULL,3),(144,1,NULL,'2019-09-26 01:19:19',NULL,NULL,3),(145,1,NULL,'2019-09-26 01:24:46',NULL,NULL,3),(146,1,NULL,'2019-09-26 01:32:36',NULL,NULL,3),(147,1,NULL,'2019-09-26 01:35:39',NULL,NULL,3),(148,1,NULL,'2019-09-26 01:40:31',NULL,NULL,3),(149,1,NULL,'2019-09-26 01:44:11',NULL,NULL,3),(150,1,NULL,'2019-09-26 01:46:15',NULL,NULL,3),(151,1,NULL,'2019-09-26 01:50:18',NULL,NULL,3),(152,1,NULL,'2019-09-26 01:51:28',NULL,NULL,3),(153,1,NULL,'2019-09-26 01:57:08',NULL,NULL,3),(154,1,NULL,'2019-09-26 02:04:32',NULL,NULL,3),(155,1,NULL,'2019-09-26 02:12:03',NULL,NULL,3),(156,1,NULL,'2019-09-26 02:39:32',NULL,NULL,3),(157,1,NULL,'2019-09-26 10:47:09',NULL,NULL,3),(158,1,NULL,'2019-09-26 10:49:54',NULL,NULL,3),(159,1,NULL,'2019-09-26 11:15:07',NULL,NULL,3),(160,1,NULL,'2019-09-26 11:47:39',NULL,NULL,3),(161,1,NULL,'2019-09-26 12:42:38',NULL,NULL,3),(162,1,NULL,'2019-09-26 12:58:03',NULL,NULL,3),(163,1,NULL,'2019-09-26 12:58:55',NULL,NULL,3),(164,1,NULL,'2019-09-26 13:26:30',NULL,NULL,3),(165,1,NULL,'2019-09-26 15:43:36',NULL,NULL,3),(166,1,NULL,'2019-09-26 16:03:05',NULL,NULL,3),(167,1,NULL,'2019-09-26 16:04:15',NULL,NULL,3),(168,1,NULL,'2019-09-26 16:05:57',NULL,NULL,3),(169,1,NULL,'2019-09-26 17:19:30',NULL,NULL,3),(170,1,NULL,'2019-09-26 17:31:03',NULL,NULL,3),(171,1,NULL,'2019-09-26 18:31:22',NULL,NULL,3),(172,1,NULL,'2019-09-27 10:20:25',NULL,NULL,3),(173,1,NULL,'2019-09-27 14:59:54',NULL,NULL,3),(174,1,NULL,'2019-09-27 14:59:55',NULL,NULL,3),(175,1,NULL,'2019-09-27 14:59:55',NULL,NULL,3),(176,1,NULL,'2019-09-27 14:59:55',NULL,NULL,3),(177,1,NULL,'2019-09-27 14:59:56',NULL,NULL,3),(178,1,NULL,'2019-09-27 15:04:19',NULL,NULL,3),(179,1,NULL,'2019-09-27 15:16:13',NULL,NULL,3),(180,1,NULL,'2019-09-27 15:23:08',NULL,NULL,3),(181,1,NULL,'2019-09-27 15:26:58',NULL,NULL,3),(182,1,NULL,'2019-09-28 09:45:13',NULL,NULL,3),(183,1,NULL,'2019-09-28 09:48:08',NULL,NULL,3),(184,1,NULL,'2019-09-28 09:49:41',NULL,NULL,3),(185,1,NULL,'2019-09-28 09:52:19',NULL,NULL,3),(186,1,NULL,'2019-09-28 10:27:13',NULL,NULL,3),(187,1,NULL,'2019-09-28 10:30:09',NULL,NULL,3),(188,1,NULL,'2019-09-28 10:32:19',NULL,NULL,3),(189,1,NULL,'2019-09-28 22:49:58',NULL,NULL,3),(190,1,NULL,'2019-09-28 22:58:36',NULL,NULL,3),(191,1,NULL,'2019-09-28 23:02:11',NULL,NULL,3),(192,1,NULL,'2019-09-28 23:05:07',NULL,NULL,3),(193,1,NULL,'2019-09-28 23:06:56',NULL,NULL,3),(194,1,NULL,'2019-09-28 23:08:49',NULL,NULL,3),(195,1,NULL,'2019-09-28 23:11:58',NULL,NULL,3),(196,1,NULL,'2019-09-28 23:14:00',NULL,NULL,3),(197,1,NULL,'2019-10-01 12:22:33',NULL,NULL,3),(198,1,NULL,'2019-10-01 13:15:06',NULL,NULL,3),(199,1,NULL,'2019-10-01 15:46:26',NULL,NULL,3),(200,1,NULL,'2019-10-01 15:49:03',NULL,NULL,3),(201,1,NULL,'2019-10-01 15:59:03',NULL,NULL,3),(202,1,NULL,'2019-10-01 16:02:16',NULL,NULL,3),(203,1,NULL,'2019-10-01 16:04:06',NULL,NULL,3),(204,1,NULL,'2019-10-01 16:24:05',NULL,NULL,3),(205,1,NULL,'2019-10-01 16:26:20',NULL,NULL,3),(206,1,NULL,'2019-10-01 16:30:20',NULL,NULL,3),(207,1,NULL,'2019-10-01 16:40:28',NULL,NULL,3),(208,1,NULL,'2019-10-01 21:05:50',NULL,NULL,3),(209,1,NULL,'2019-10-01 21:07:18',NULL,NULL,3),(210,1,NULL,'2019-10-01 21:16:26',NULL,NULL,3),(211,1,NULL,'2019-10-01 21:21:09',NULL,NULL,3),(212,1,NULL,'2019-10-01 21:47:38',NULL,NULL,3),(213,1,NULL,'2019-10-01 21:47:54',NULL,NULL,3),(214,1,NULL,'2019-10-01 21:57:25',NULL,NULL,3),(215,1,NULL,'2019-10-01 21:59:05',NULL,NULL,3),(216,1,NULL,'2019-10-01 23:46:44',NULL,NULL,3),(217,1,NULL,'2019-10-02 15:06:01',NULL,NULL,3),(218,1,NULL,'2019-10-02 15:34:37',NULL,NULL,3),(219,1,NULL,'2019-10-02 17:13:08',NULL,NULL,3),(220,1,NULL,'2019-10-02 17:17:45',NULL,NULL,3),(221,1,NULL,'2019-10-02 17:21:24',NULL,NULL,3),(222,1,NULL,'2019-10-02 17:24:49',NULL,NULL,3),(223,1,NULL,'2019-10-02 18:46:33',NULL,NULL,3),(224,1,NULL,'2019-10-02 18:49:14',NULL,NULL,3),(225,1,NULL,'2019-10-02 18:57:27',NULL,NULL,3),(226,1,NULL,'2019-10-02 19:03:30',NULL,NULL,3),(227,1,NULL,'2019-10-02 20:03:04',NULL,NULL,3),(228,1,NULL,'2019-10-02 21:35:52',NULL,NULL,3),(229,1,NULL,'2019-10-02 22:34:53',NULL,NULL,3),(230,1,NULL,'2019-10-02 22:42:01',NULL,NULL,3),(231,1,NULL,'2019-10-02 22:43:40',NULL,NULL,3),(232,1,NULL,'2019-10-02 22:47:15',NULL,NULL,3),(233,1,NULL,'2019-10-02 22:49:57',NULL,NULL,3),(234,1,NULL,'2019-10-02 22:53:54',NULL,NULL,3),(235,1,NULL,'2019-10-04 09:08:22',NULL,NULL,3),(236,1,NULL,'2019-10-04 09:46:50',NULL,NULL,3),(237,1,NULL,'2019-10-04 09:53:11',NULL,NULL,3),(238,1,NULL,'2019-10-04 09:59:42',NULL,NULL,3),(239,1,NULL,'2019-10-04 10:02:10',NULL,NULL,3),(240,1,NULL,'2019-10-04 10:05:02',NULL,NULL,3),(241,1,NULL,'2019-10-04 10:06:38',NULL,NULL,3),(242,1,NULL,'2019-10-04 10:15:18',NULL,NULL,3),(243,1,NULL,'2019-10-04 10:25:34',NULL,NULL,3),(244,1,NULL,'2019-10-04 10:28:44',NULL,NULL,3),(245,1,NULL,'2019-10-04 10:37:06',NULL,NULL,3),(246,1,NULL,'2019-10-04 10:47:08',NULL,NULL,3),(247,1,NULL,'2019-10-04 11:17:47',NULL,NULL,3),(248,1,NULL,'2019-10-04 11:22:28',NULL,NULL,3),(249,1,NULL,'2019-10-04 11:25:07',NULL,NULL,3),(250,1,NULL,'2019-10-04 11:32:57',NULL,NULL,3),(251,1,NULL,'2019-10-04 11:52:28',NULL,NULL,3),(252,1,NULL,'2019-10-04 11:56:20',NULL,NULL,3),(253,1,NULL,'2019-10-04 12:12:23',NULL,NULL,3),(254,1,NULL,'2019-10-04 12:13:34',NULL,NULL,3),(255,1,NULL,'2019-10-04 12:39:39',NULL,NULL,3),(256,1,NULL,'2019-10-04 14:04:01',NULL,NULL,3),(257,1,NULL,'2019-10-04 14:06:04',NULL,NULL,3),(258,1,NULL,'2019-10-04 14:18:25',NULL,NULL,3),(259,1,NULL,'2019-10-04 14:25:57',NULL,NULL,3),(260,1,NULL,'2019-10-04 14:28:28',NULL,NULL,3),(261,1,NULL,'2019-10-04 14:30:44',NULL,NULL,3),(262,1,NULL,'2019-10-04 14:36:07',NULL,NULL,3),(263,1,NULL,'2019-10-04 14:37:08',NULL,NULL,3),(264,1,NULL,'2019-10-04 14:37:25',NULL,NULL,3),(265,1,NULL,'2019-10-04 14:41:48',NULL,NULL,3),(266,1,NULL,'2019-10-04 14:51:46',NULL,NULL,3),(267,1,NULL,'2019-10-04 15:10:14',NULL,NULL,3);
/*!40000 ALTER TABLE `sessionlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessionstatus`
--

DROP TABLE IF EXISTS `sessionstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sessionstatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessionstatus`
--

LOCK TABLES `sessionstatus` WRITE;
/*!40000 ALTER TABLE `sessionstatus` DISABLE KEYS */;
INSERT INTO `sessionstatus` VALUES (3,'Login'),(4,'Logout');
/*!40000 ALTER TABLE `sessionstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategory`
--

DROP TABLE IF EXISTS `subcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_subcategory_category1_idx` (`category_id`),
  CONSTRAINT `fk_subcategory_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategory`
--

LOCK TABLES `subcategory` WRITE;
/*!40000 ALTER TABLE `subcategory` DISABLE KEYS */;
INSERT INTO `subcategory` VALUES (1,'92 Octane',1),(2,'95 Octane',1),(3,'Super Diesal',2),(20,'Normal Diesal',2),(21,'Loose/drum',3),(22,'Multigrade Canstock SAE 10 to 30',3),(23,'Multigrade Canstock SAE 20 to 40',3),(24,'Monograde Canstock SAE 30',3),(25,'Monograde Canstock SAE 40',3),(26,'Multigrade Canstock DS 15 to 30',4),(27,'Multigrade Canstock DS 20 to 40',4),(28,'Monograde Canstock DS 30',4),(29,'Monograde Canstock DS 40',4),(30,'Monograde Canstock DS 50',4),(31,'Loose/drum',4),(32,'Tinstock',5),(33,'Canstock SAE 90',6),(34,'Canstock SAE 140',6),(35,'Loose/drum',6),(36,'Dot 3',7),(37,'Dot 4',7);
/*!40000 ALTER TABLE `subcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company` varchar(45) DEFAULT NULL,
  `regno` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `contactname` varchar(45) DEFAULT NULL,
  `contactno` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `nic` varchar(12) DEFAULT NULL,
  `entereddate` date DEFAULT NULL,
  `employee_id` int(11) NOT NULL,
  `supplierstatus_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_supplier_employee1_idx` (`employee_id`),
  KEY `fk_supplier_supplierstatus1_idx` (`supplierstatus_id`),
  CONSTRAINT `fk_supplier_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_supplier_supplierstatus1` FOREIGN KEY (`supplierstatus_id`) REFERENCES `supplierstatus` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Sri Lanka Petrolium Co.','1234','Colombo','332255136','Ajith','0773719508','Ajith@gmail.com','652032154V','2019-02-12',1,1),(2,'Ceypetco','5623','Colombo','4561234564','Zai','0776587895','Cey@gmail.com','789456123V','2019-02-12',1,1),(3,'TVS Lanka pvt ltd','6985','Gampaha','0332256987','Sandi','9874563214','TVS@gmail.com','896123456V','2019-02-12',1,1),(4,'Imuzu Int . pvtLtd','6532','Henegama','8965471235','Palitha','9875236548','Palith @gmail.com','895623145V','2019-02-12',1,1);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplierstatus`
--

DROP TABLE IF EXISTS `supplierstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplierstatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplierstatus`
--

LOCK TABLES `supplierstatus` WRITE;
/*!40000 ALTER TABLE `supplierstatus` DISABLE KEYS */;
INSERT INTO `supplierstatus` VALUES (1,'Active'),(2,'Inactive');
/*!40000 ALTER TABLE `supplierstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supply`
--

DROP TABLE IF EXISTS `supply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Item_id` int(11) NOT NULL,
  `supplier_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Item_has_supplier_supplier1_idx` (`supplier_id`),
  KEY `fk_Item_has_supplier_Item1_idx` (`Item_id`),
  CONSTRAINT `fk_Item_has_supplier_Item1` FOREIGN KEY (`Item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_has_supplier_supplier1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supply`
--

LOCK TABLES `supply` WRITE;
/*!40000 ALTER TABLE `supply` DISABLE KEYS */;
INSERT INTO `supply` VALUES (2,18,1),(3,21,1),(4,22,2),(5,23,2),(6,24,3);
/*!40000 ALTER TABLE `supply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tank`
--

DROP TABLE IF EXISTS `tank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tank`
--

LOCK TABLES `tank` WRITE;
/*!40000 ALTER TABLE `tank` DISABLE KEYS */;
INSERT INTO `tank` VALUES (1,'T01'),(2,'T02'),(3,'T03'),(4,'T04'),(5,'T05');
/*!40000 ALTER TABLE `tank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unittype`
--

DROP TABLE IF EXISTS `unittype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unittype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `subcategory_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_unittype_subcategory1_idx` (`subcategory_id`),
  CONSTRAINT `fk_unittype_subcategory1` FOREIGN KEY (`subcategory_id`) REFERENCES `subcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unittype`
--

LOCK TABLES `unittype` WRITE;
/*!40000 ALTER TABLE `unittype` DISABLE KEYS */;
INSERT INTO `unittype` VALUES (1,'1L',22),(2,'4L',22),(4,'1L',23),(5,'4L',23),(6,'1L',24),(7,'4L',24),(8,'1L',25),(9,'4L',25),(10,'1L',26),(11,'5L',26),(12,'1L',27),(13,'5L',27),(14,'1L',28),(15,'5L',28),(16,'1L',29),(17,'5L',29),(18,'1L',30),(19,'5L',30),(20,'210L',21),(21,'210L',31),(22,'210L',35),(23,'1L',33),(24,'5L',33),(25,'1L',34),(26,'5L',34),(27,'250ml',36),(28,'500ml',36),(29,'250ml',37),(30,'500ml',37),(31,'250g',32),(32,'500g',32),(33,'1Kg',32),(34,'15Kg',32);
/*!40000 ALTER TABLE `unittype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(150) DEFAULT NULL,
  `docreation` date DEFAULT NULL,
  `description` text,
  `userstatus_id` int(11) NOT NULL,
  `employee_created_id` int(11) NOT NULL,
  `salt` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_userstatus1_idx` (`userstatus_id`),
  KEY `fk_user_employee1_idx` (`employee_created_id`),
  KEY `fk_user_employee2_idx` (`employee_id`),
  CONSTRAINT `fk_user_employee1` FOREIGN KEY (`employee_created_id`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_employee2` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_userstatus1` FOREIGN KEY (`userstatus_id`) REFERENCES `userstatus` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1,'admin','429be604607dc8398b73af8ad33fdd9ab5d832d2','2019-03-02','RootUser',2,1,'79409127a07fdf0f27d8429137acb9afb90c5046'),(2,2,'maneesha','9e9bef7e7d523c15719e7f7ba8e5af674ecabc1d','2019-03-03','owner',2,1,'7817f5d51a18814a4188da69db98887d29d79d84'),(3,3,'shelton','95a3c0a2b7c5ac1628167ba85650e171fe004ca2','2019-03-03','manager',2,1,'3ec019831da7e39bf351a91c11b78a20879b5c42');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_role_has_user_user1_idx` (`user_id`),
  KEY `fk_role_has_user_role1_idx` (`role_id`),
  CONSTRAINT `fk_role_has_user_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES (1,1,2),(2,2,3);
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userstatus`
--

DROP TABLE IF EXISTS `userstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userstatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userstatus`
--

LOCK TABLES `userstatus` WRITE;
/*!40000 ALTER TABLE `userstatus` DISABLE KEYS */;
INSERT INTO `userstatus` VALUES (2,'Operational');
/*!40000 ALTER TABLE `userstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'orderandsales'
--

--
-- Dumping routines for database 'orderandsales'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-04 16:28:56
