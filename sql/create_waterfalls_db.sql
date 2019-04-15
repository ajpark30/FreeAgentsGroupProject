
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `waterfalls`;
--
-- Table structure for table `waterfall`
--

DROP TABLE IF EXISTS `waterfall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `waterfall` (
  `waterfall_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `region` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `location_info` varchar(100) DEFAULT NULL,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL,
  `preserve` varchar(100) DEFAULT NULL,
  `river` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`waterfall_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `waterfall`
--

LOCK TABLES `waterfall` WRITE;
/*!40000 ALTER TABLE `waterfall` DISABLE KEYS */;
/*!40000 ALTER TABLE `waterfall` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-12 13:43:13

DROP TABLE IF EXISTS photo;
CREATE TABLE photo (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	waterfall_id INT,
  dateAcquired DATETIME,
	sourceURL VARCHAR(300),
	linkedFromURL VARCHAR(300),
	attribution VARCHAR(1000),
  dateTaken DATETIME,
	title VARCHAR(100),
	caption VARCHAR(100),
	description VARCHAR(100),
	localPath VARCHAR(300),
	height INT,
	width INT,
  CONSTRAINT waterfall_photo_cx FOREIGN KEY (waterfall_id) REFERENCES user (id)
);