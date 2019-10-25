-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: localhost    Database: quemepongo
-- ------------------------------------------------------
-- Server version	5.7.27

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
-- Table structure for table `atuendo`
--

DROP TABLE IF EXISTS `atuendo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `atuendo` (
  `atuendo_id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`atuendo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atuendo`
--

LOCK TABLES `atuendo` WRITE;
/*!40000 ALTER TABLE `atuendo` DISABLE KEYS */;
INSERT INTO `atuendo` VALUES (14,'atuendo veraniego'),(16,'atuendo veraniego'),(18,'atuendo veraniego'),(20,'atuendo veraniego');
/*!40000 ALTER TABLE `atuendo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atuendo_estado`
--

DROP TABLE IF EXISTS `atuendo_estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `atuendo_estado` (
  `atuendo_id` int(11) NOT NULL,
  `estado_id` int(11) NOT NULL,
  `fecha_actualizacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atuendo_estado`
--

LOCK TABLES `atuendo_estado` WRITE;
/*!40000 ALTER TABLE `atuendo_estado` DISABLE KEYS */;
/*!40000 ALTER TABLE `atuendo_estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calendario`
--

DROP TABLE IF EXISTS `calendario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendario` (
  `calendario_id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`calendario_id`),
  KEY `usuario_FK` (`usuario_id`),
  CONSTRAINT `usuario_FK` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usuario_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendario`
--

LOCK TABLES `calendario` WRITE;
/*!40000 ALTER TABLE `calendario` DISABLE KEYS */;
INSERT INTO `calendario` VALUES (70,'Vacaciones norte de Argentina',NULL),(71,'calendarioLaboral',NULL),(72,'Vacaciones norte de Argentina',NULL),(73,'calendarioLaboral',NULL),(74,'Vacaciones norte de Argentina',NULL),(75,'calendarioLaboral',NULL),(76,'Vacaciones norte de Argentina',NULL),(77,'calendarioLaboral',NULL);
/*!40000 ALTER TABLE `calendario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evento` (
  `evento_id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `nombre` varchar(50) NOT NULL,
  `ubicacion` varchar(50) NOT NULL,
  `antelacion_horas` int(11) DEFAULT NULL,
  `periodo` varchar(8) NOT NULL,
  `tiene_sugerencia` tinyint(1) DEFAULT NULL,
  `calendario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`evento_id`),
  KEY `calendario_FK` (`calendario_id`),
  CONSTRAINT `calendario_FK` FOREIGN KEY (`calendario_id`) REFERENCES `calendario` (`calendario_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES (40,'2019-10-25 02:55:14','Partido Boca-River','La Boca',2,'NINGUNO',0,NULL),(41,'2019-10-25 03:01:39','Partido Boca-River','La Boca',2,'NINGUNO',0,NULL),(42,'2019-10-25 04:22:29','Partido Boca-River','La Boca',2,'NINGUNO',0,NULL),(43,'2019-10-25 15:37:48','Partido Boca-River','La Boca',2,'NINGUNO',0,NULL);
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guardarropa`
--

DROP TABLE IF EXISTS `guardarropa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guardarropa` (
  `guardarropa_id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_guardarropa` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`guardarropa_id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guardarropa`
--

LOCK TABLES `guardarropa` WRITE;
/*!40000 ALTER TABLE `guardarropa` DISABLE KEYS */;
INSERT INTO `guardarropa` VALUES (76,NULL),(78,NULL),(79,NULL),(81,NULL),(82,NULL),(84,NULL),(85,NULL),(87,NULL);
/*!40000 ALTER TABLE `guardarropa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prenda`
--

DROP TABLE IF EXISTS `prenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prenda` (
  `prenda_id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `material` varchar(40) NOT NULL,
  `tipo_prenda_id` int(11) DEFAULT NULL,
  `trama` varchar(50) DEFAULT NULL,
  `imagen` blob,
  `impermeable` tinyint(1) DEFAULT NULL,
  `disponibilidad` tinyint(1) DEFAULT NULL,
  `rojo` int(11) DEFAULT NULL,
  `verde` int(11) DEFAULT NULL,
  `azul` int(11) DEFAULT NULL,
  `rojo_1` int(11) DEFAULT NULL,
  `verde_1` int(11) DEFAULT NULL,
  `azul_1` int(11) DEFAULT NULL,
  `guardarropa_id` int(11) DEFAULT NULL,
  `atuendo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`prenda_id`),
  KEY `prenda_FK` (`atuendo_id`),
  CONSTRAINT `prenda_FK` FOREIGN KEY (`atuendo_id`) REFERENCES `atuendo` (`atuendo_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prenda`
--

LOCK TABLES `prenda` WRITE;
/*!40000 ALTER TABLE `prenda` DISABLE KEYS */;
INSERT INTO `prenda` VALUES (98,'musculosa','ALGODON',105,'CUADROS',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,76,14),(99,'short de jean','JEAN',106,'LISA',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,76,14),(100,'ojotas havaianas','GOMA',107,'NINGUNO',NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL,76,14),(101,'guantes','CUERO',108,'LISA',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,76,14),(102,'bufanda','LANA',109,'LISA',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,76,14),(103,'anteojos','PLASTICO',110,'LISA',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,76,14),(104,'musculosa','ALGODON',112,'CUADROS',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,79,16),(105,'short de jean','JEAN',113,'LISA',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,79,16),(106,'ojotas havaianas','GOMA',114,'NINGUNO',NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL,79,16),(107,'guantes','CUERO',115,'LISA',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,79,16),(108,'bufanda','LANA',116,'LISA',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,79,16),(109,'anteojos','PLASTICO',117,'LISA',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,79,16),(110,'musculosa','ALGODON',119,'CUADROS',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,82,18),(111,'short de jean','JEAN',120,'LISA',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,82,18),(112,'ojotas havaianas','GOMA',121,'NINGUNO',NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL,82,18),(113,'guantes','CUERO',122,'LISA',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,82,18),(114,'bufanda','LANA',123,'LISA',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,82,18),(115,'anteojos','PLASTICO',124,'LISA',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,82,18),(116,'musculosa','ALGODON',126,'CUADROS',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,85,20),(117,'short de jean','JEAN',127,'LISA',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,85,20),(118,'ojotas havaianas','GOMA',128,'NINGUNO',NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL,85,20),(119,'guantes','CUERO',129,'LISA',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,85,20),(120,'bufanda','LANA',130,'LISA',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,85,20),(121,'anteojos','PLASTICO',131,'LISA',NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,85,20);
/*!40000 ALTER TABLE `prenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_prenda`
--

DROP TABLE IF EXISTS `tipo_prenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_prenda` (
  `tipo_prenda_id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_tipo_prenda` varchar(100) DEFAULT NULL,
  `temperatura_max` double NOT NULL,
  `temperatura_min` double NOT NULL,
  `categoria` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`tipo_prenda_id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_prenda`
--

LOCK TABLES `tipo_prenda` WRITE;
/*!40000 ALTER TABLE `tipo_prenda` DISABLE KEYS */;
INSERT INTO `tipo_prenda` VALUES (104,'crocs y ojotas',40,18,'CALZADO'),(105,NULL,30,20,'PARTE_SUPERIOR_ABAJO'),(106,NULL,35,20,'PARTE_INFERIOR'),(107,'crocs y ojotas',40,18,'CALZADO'),(108,NULL,10,-5,'ACCESORIO_MANOS'),(109,NULL,10,-10,'ACCESORIO_CUELLO'),(110,NULL,0,0,'ACCESORIO'),(111,'crocs y ojotas',40,18,'CALZADO'),(112,NULL,30,20,'PARTE_SUPERIOR_ABAJO'),(113,NULL,35,20,'PARTE_INFERIOR'),(114,'crocs y ojotas',40,18,'CALZADO'),(115,NULL,10,-5,'ACCESORIO_MANOS'),(116,NULL,10,-10,'ACCESORIO_CUELLO'),(117,NULL,0,0,'ACCESORIO'),(118,'crocs y ojotas',40,18,'CALZADO'),(119,NULL,30,20,'PARTE_SUPERIOR_ABAJO'),(120,NULL,35,20,'PARTE_INFERIOR'),(121,'crocs y ojotas',40,18,'CALZADO'),(122,NULL,10,-5,'ACCESORIO_MANOS'),(123,NULL,10,-10,'ACCESORIO_CUELLO'),(124,NULL,0,0,'ACCESORIO'),(125,'crocs y ojotas',40,18,'CALZADO'),(126,NULL,30,20,'PARTE_SUPERIOR_ABAJO'),(127,NULL,35,20,'PARTE_INFERIOR'),(128,'crocs y ojotas',40,18,'CALZADO'),(129,NULL,10,-5,'ACCESORIO_MANOS'),(130,NULL,10,-10,'ACCESORIO_CUELLO'),(131,NULL,0,0,'ACCESORIO');
/*!40000 ALTER TABLE `tipo_prenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `usuario_id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(40) NOT NULL,
  `contrasenia` varchar(40) NOT NULL,
  `numero_celular` varchar(20) DEFAULT NULL,
  `tiempo_anticipacion` int(11) DEFAULT NULL,
  `calendario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`usuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (38,'messi','1234','+15847777',0,70),(39,'alexis','1234','+54911651651',0,72),(40,'alexis','1234','+54911651651',0,74),(41,'alexis','1234','+54911651651',0,76);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_guardarropa`
--

DROP TABLE IF EXISTS `usuario_guardarropa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_guardarropa` (
  `guardarropa_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`guardarropa_id`,`usuario_id`),
  KEY `usuario_guardarropa_FK_1` (`usuario_id`),
  CONSTRAINT `usuario_guardarropa_FK` FOREIGN KEY (`guardarropa_id`) REFERENCES `guardarropa` (`guardarropa_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usuario_guardarropa_FK_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usuario_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_guardarropa`
--

LOCK TABLES `usuario_guardarropa` WRITE;
/*!40000 ALTER TABLE `usuario_guardarropa` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_guardarropa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'quemepongo'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-25 13:14:33
