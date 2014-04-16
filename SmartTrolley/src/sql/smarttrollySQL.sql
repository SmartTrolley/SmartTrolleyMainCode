CREATE DATABASE  IF NOT EXISTS `smarttrolley` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `smarttrolley`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: localhost    Database: smarttrolley
-- ------------------------------------------------------
-- Server version	5.6.17

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `CategoryID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  PRIMARY KEY (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Bakery'),(2,'Fruit & Vegetables'),(3,'Dairy & Eggs'),(4,'Meat & Seafood'),(5,'Frozen'),(6,'Drinks'),(7,'Snacks & Sweets'),(8,'Desserts'),(9,'Poultry');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lists`
--

DROP TABLE IF EXISTS `lists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lists` (
  `ListID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  PRIMARY KEY (`ListID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lists`
--

LOCK TABLES `lists` WRITE;
/*!40000 ALTER TABLE `lists` DISABLE KEYS */;
INSERT INTO `lists` VALUES (1,'Sea'),(2,'Party');
/*!40000 ALTER TABLE `lists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lists_products`
--

DROP TABLE IF EXISTS `lists_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lists_products` (
  `ProductID` int(11) NOT NULL,
  `ListID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ProductID`,`ListID`),
  KEY `FK_Lists_idx` (`ListID`),
  CONSTRAINT `FK_List` FOREIGN KEY (`ListID`) REFERENCES `lists` (`ListID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_Product` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lists_products`
--

LOCK TABLES `lists_products` WRITE;
/*!40000 ALTER TABLE `lists_products` DISABLE KEYS */;
INSERT INTO `lists_products` VALUES (1,2,3),(9,2,1);
/*!40000 ALTER TABLE `lists_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offers`
--

DROP TABLE IF EXISTS `offers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offers` (
  `OfferID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductID` int(11) NOT NULL,
  `OfferPrice` double NOT NULL,
  PRIMARY KEY (`OfferID`),
  KEY `FK_Offer_Product_idx` (`ProductID`),
  CONSTRAINT `FK_Offer_Product` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offers`
--

LOCK TABLES `offers` WRITE;
/*!40000 ALTER TABLE `offers` DISABLE KEYS */;
/*!40000 ALTER TABLE `offers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `ProductID` int(11) NOT NULL AUTO_INCREMENT,
  `Image` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Price` double NOT NULL,
  `CategoryID` int(11) NOT NULL,
  `IsFavourite` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ProductID`),
  KEY `FK_Category_idx` (`CategoryID`),
  CONSTRAINT `FK_Category` FOREIGN KEY (`CategoryID`) REFERENCES `categories` (`CategoryID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'img/SampleProducts/ariel.jpg','Ariel',2.99,1,'\0'),(2,'img/SampleProducts/cravendale_2L_milk.jpg','Cravendale 2L',3.99,2,'\0'),(3,'img/SampleProducts/holme_farmed_venison_steak.jpg','Holme Farmed Venison Steak',4.99,1,'\0'),(4,'img/SampleProducts/hovis_bread.jpg','Hovis Bread',5.99,2,'\0'),(5,'img/SampleProducts/innocent_noodle_pot.jpg','Innocent Noodle Pot',6.99,2,'\0'),(6,'img/SampleProducts/lavazza_espresso.jpg','Lavazza Espresso',7.99,3,'\0'),(7,'img/SampleProducts/nivea_shower_cream.jpg','Nivea Shower Creme',8.99,3,'\0'),(8,'img/SampleProducts/pink_lady_apple.jpg','Pink Lady Apple',9.99,3,'\0'),(9,'img/SampleProducts/star-wars-lollies.jpg','Star Wars Lollies',10.99,1,'\0'),(10,'img/SampleProducts/strawberry_conserve.jpg','Strawberry Conserve',8,2,'\0'),(11,'img/SampleProducts/sugar_puffs.jpg','Sugar Puffs',5,1,'\0'),(12,'img/SampleProducts/yorkie.jpg','Yorkie',4,3,'\0'),(14,'img/SampleProducts/cathedral_city.png','Cathedral',5.99,3,'\0'),(15,'img/SampleProducts/cadbury-dairy-milk-new-packaging.jpg','Dairy Milk',6,1,'\0');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-14 23:07:06
