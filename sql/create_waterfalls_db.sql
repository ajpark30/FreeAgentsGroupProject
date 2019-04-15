DROP DATABASE waterfalls;
CREATE DATABASE waterfalls;
USE waterfalls;
--
-- Table structure for table `waterfall`
--

DROP TABLE IF EXISTS waterfall;
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

--
-- Dumping data for table `waterfall`
--

LOCK TABLES `waterfall` WRITE;
/*!40000 ALTER TABLE `waterfall` DISABLE KEYS */;
INSERT INTO waterfall (waterfall_id, name, latitude, longitude) VALUES (1, 'Kalandula Falls', -9.075833, 16.003333);
INSERT INTO waterfall (waterfall_id, name, latitude, longitude) VALUES (2, 'Sedudo Waterfall', -7.780216, 111.758159);
INSERT INTO waterfall (waterfall_id, name, latitude, longitude) VALUES (3, 'Grandfather Falls', 45.313056, -89.784722);
/*!40000 ALTER TABLE `waterfall` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS photo;
CREATE TABLE photo (
	photo_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
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
	height INT DEFAULT 0,
	width INT DEFAULT 0,
  CONSTRAINT waterfall_photo_cx FOREIGN KEY (waterfall_id) REFERENCES waterfall (waterfall_id)
);


LOCK TABLES `photo` WRITE;
/*!40000 ALTER TABLE `photo` DISABLE KEYS */;
INSERT INTO photo (photo_id, waterfall_id, sourceURL) VALUES (1, 3, 'https://upload.wikimedia.org/wikipedia/commons/3/3a/GrandfatherExposedPreCambrian.jpg');
/*!40000 ALTER TABLE `photo` ENABLE KEYS */;
UNLOCK TABLES;