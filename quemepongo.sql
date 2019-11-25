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
-- Table structure for table `atuendos`
--

DROP TABLE IF EXISTS `atuendos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `atuendos` (
                            `atuendo_id` int(11) NOT NULL AUTO_INCREMENT,
                            `nombre` varchar(40) DEFAULT NULL,
                            `fecha_actualizacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`atuendo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=544 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atuendos`
--

LOCK TABLES `atuendos` WRITE;
/*!40000 ALTER TABLE `atuendos` DISABLE KEYS */;
INSERT INTO `atuendos` VALUES (543,'atuendo veraniego','2019-11-25 20:05:57');
/*!40000 ALTER TABLE `atuendos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atuendos_estados`
--

DROP TABLE IF EXISTS `atuendos_estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `atuendos_estados` (
                                    `atuendo_id` int(11) NOT NULL,
                                    `estado_id` int(11) NOT NULL,
                                    `fecha_actualizacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atuendos_estados`
--

LOCK TABLES `atuendos_estados` WRITE;
/*!40000 ALTER TABLE `atuendos_estados` DISABLE KEYS */;
/*!40000 ALTER TABLE `atuendos_estados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventos`
--

DROP TABLE IF EXISTS `eventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eventos` (
                           `evento_id` int(11) NOT NULL AUTO_INCREMENT,
                           `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           `nombre` varchar(50) NOT NULL,
                           `ubicacion` varchar(50) NOT NULL,
                           `antelacion_horas` int(11) DEFAULT NULL,
                           `periodo` varchar(8) NOT NULL,
                           `tiene_sugerencia` tinyint(1) DEFAULT NULL,
                           `usuario_id` int(11) DEFAULT NULL,
                           `fecha_actualizacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`evento_id`)
) ENGINE=InnoDB AUTO_INCREMENT=343 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventos`
--

LOCK TABLES `eventos` WRITE;
/*!40000 ALTER TABLE `eventos` DISABLE KEYS */;
INSERT INTO `eventos` VALUES (338,'2019-11-25 20:05:58','Recital de la mona Jimenez','Luna Park',0,'NINGUNO',0,307,'2019-11-25 20:05:58'),(339,'2019-11-25 20:05:58','Partido Boca-River','La Boca',2,'NINGUNO',0,308,'2019-11-25 20:05:58'),(340,'2019-11-25 20:05:58','Pizzas a la parrilla','Carapachay',0,'MENSUAL',0,308,'2019-11-25 20:05:58'),(341,'2019-11-25 20:05:58','Casamiento','Pilar',1,'NINGUNO',0,309,'2019-11-25 20:05:58'),(342,'2019-11-25 20:05:58','Asado de domingo al mediodia','Los talas del Entrerriano',1,'MENSUAL',0,310,'2019-11-25 20:05:58');
/*!40000 ALTER TABLE `eventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guardarropas`
--

DROP TABLE IF EXISTS `guardarropas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guardarropas` (
                                `guardarropa_id` int(11) NOT NULL AUTO_INCREMENT,
                                `nombre_guardarropa` varchar(100) DEFAULT NULL,
                                `limite_prendas` int(11) DEFAULT NULL,
                                `fecha_actualizacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (`guardarropa_id`)
) ENGINE=InnoDB AUTO_INCREMENT=490 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guardarropas`
--

LOCK TABLES `guardarropas` WRITE;
/*!40000 ALTER TABLE `guardarropas` DISABLE KEYS */;
INSERT INTO `guardarropas` VALUES (486,'guardarropa formal',0,'2019-11-25 20:05:57'),(487,'Guardarropa de verano',100,'2019-11-25 20:05:58'),(488,'Guardarropa de verano',100,'2019-11-25 20:05:58'),(489,'Guardarropa de invierno',0,'2019-11-25 20:05:58');
/*!40000 ALTER TABLE `guardarropas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagenes`
--

DROP TABLE IF EXISTS `imagenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imagenes` (
                            `imagen_id` int(11) NOT NULL AUTO_INCREMENT,
                            `alto` int(11) NOT NULL,
                            `ancho` int(11) NOT NULL,
                            `archivo` tinyblob NOT NULL,
                            `prenda_id` int(11) NOT NULL,
                            PRIMARY KEY (`imagen_id`),
                            KEY `imagenes_FK` (`prenda_id`),
                            CONSTRAINT `imagenes_FK` FOREIGN KEY (`prenda_id`) REFERENCES `prendas` (`prenda_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagenes`
--

LOCK TABLES `imagenes` WRITE;
/*!40000 ALTER TABLE `imagenes` DISABLE KEYS */;
/*!40000 ALTER TABLE `imagenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prendas`
--

DROP TABLE IF EXISTS `prendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prendas` (
                           `prenda_id` int(11) NOT NULL AUTO_INCREMENT,
                           `nombre` varchar(100) NOT NULL,
                           `material` varchar(40) NOT NULL,
                           `tipo_prenda_id` int(11) DEFAULT NULL,
                           `trama` varchar(50) DEFAULT NULL,
                           `impermeable` tinyint(1) DEFAULT NULL,
                           `disponibilidad` tinyint(1) DEFAULT NULL,
                           `rojo_primario` int(11) DEFAULT NULL,
                           `verde_primario` int(11) DEFAULT NULL,
                           `azul_primario` int(11) DEFAULT NULL,
                           `rojo_secundario` int(11) DEFAULT NULL,
                           `verde_secundario` int(11) DEFAULT NULL,
                           `azul_secundario` int(11) DEFAULT NULL,
                           `guardarropa_id` int(11) DEFAULT NULL,
                           `fecha_actualizacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           `atuendo_id` int(11) DEFAULT NULL,
                           PRIMARY KEY (`prenda_id`)
) ENGINE=InnoDB AUTO_INCREMENT=815 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prendas`
--

LOCK TABLES `prendas` WRITE;
/*!40000 ALTER TABLE `prendas` DISABLE KEYS */;
INSERT INTO `prendas` VALUES (800,'Musculosa','ALGODON',703,'CUADROS',0,1,10,11,12,NULL,NULL,NULL,486,'2019-11-25 20:05:57',543),(801,'Short','JEAN',704,'LISA',0,1,10,11,12,NULL,NULL,NULL,486,'2019-11-25 20:05:57',NULL),(802,'ojotas havaianas','GOMA',705,'NINGUNO',1,1,10,86,88,82,16,88,486,'2019-11-25 20:05:57',NULL),(803,'Guantes de Cuero','CUERO',706,'LISA',0,1,10,11,12,NULL,NULL,NULL,486,'2019-11-25 20:05:57',NULL),(804,'Bufanda','LANA',707,'LISA',0,1,10,11,12,NULL,NULL,NULL,486,'2019-11-25 20:05:57',NULL),(805,'Anteojos','PLASTICO',708,'LISA',0,1,10,11,12,NULL,NULL,NULL,486,'2019-11-25 20:05:57',NULL),(806,'Remera lisa','ALGODON',709,'LISA',0,1,10,5,0,20,0,2,487,'2019-11-25 20:05:58',NULL),(807,'Alpargatas','ALGODON',710,'LISA',1,1,88,50,25,1,20,35,487,'2019-11-25 20:05:58',NULL),(808,'Gorra de sol','POLYESTER',711,'LISA',0,1,20,0,2,NULL,NULL,NULL,487,'2019-11-25 20:05:58',NULL),(809,'Remera manga larga a cuadros','ALGODON',712,'CUADROS',0,1,10,11,12,NULL,NULL,NULL,487,'2019-11-25 20:05:58',NULL),(810,'pantalon de jean','JEAN',713,'LISA',0,1,10,11,12,NULL,NULL,NULL,487,'2019-11-25 20:05:58',NULL),(811,'Gorra de lana','LANA',711,'LISA',0,1,10,11,12,NULL,NULL,NULL,487,'2019-11-25 20:05:58',NULL),(812,'Remera lisa','ALGODON',709,'LISA',0,1,10,11,12,NULL,NULL,NULL,487,'2019-11-25 20:05:58',NULL),(813,'zapatillas','ALGODON',710,'LISA',1,1,10,11,12,NULL,NULL,NULL,487,'2019-11-25 20:05:58',NULL),(814,'Campera uniqlo','POLYESTER',714,'CUADROS',0,1,10,11,12,NULL,NULL,NULL,487,'2019-11-25 20:05:58',NULL);
/*!40000 ALTER TABLE `prendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prendas_atuendos`
--

DROP TABLE IF EXISTS `prendas_atuendos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prendas_atuendos` (
                                    `prenda_id` int(11) NOT NULL,
                                    `atuendo_id` int(11) NOT NULL,
                                    PRIMARY KEY (`prenda_id`,`atuendo_id`),
                                    KEY `prendas_atuendos_FK` (`atuendo_id`),
                                    CONSTRAINT `prendas_atuendos_FK` FOREIGN KEY (`atuendo_id`) REFERENCES `atuendos` (`atuendo_id`) ON DELETE CASCADE,
                                    CONSTRAINT `prendas_atuendos_FK_1` FOREIGN KEY (`prenda_id`) REFERENCES `prendas` (`prenda_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prendas_atuendos`
--

LOCK TABLES `prendas_atuendos` WRITE;
/*!40000 ALTER TABLE `prendas_atuendos` DISABLE KEYS */;
/*!40000 ALTER TABLE `prendas_atuendos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_prendas`
--

DROP TABLE IF EXISTS `tipo_prendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_prendas` (
                                `tipo_prenda_id` int(11) NOT NULL AUTO_INCREMENT,
                                `nombre_tipo_prenda` varchar(100) DEFAULT NULL,
                                `temperatura_max` double NOT NULL,
                                `temperatura_min` double NOT NULL,
                                `categoria` varchar(40) DEFAULT NULL,
                                `fecha_actualizacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (`tipo_prenda_id`)
) ENGINE=InnoDB AUTO_INCREMENT=715 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_prendas`
--

LOCK TABLES `tipo_prendas` WRITE;
/*!40000 ALTER TABLE `tipo_prendas` DISABLE KEYS */;
INSERT INTO `tipo_prendas` VALUES (703,'Musculosa',30,20,'PARTE_SUPERIOR_ABAJO','2019-11-25 20:05:57'),(704,'Short',35,20,'PARTE_INFERIOR','2019-11-25 20:05:57'),(705,'Crocs',40,18,'CALZADO','2019-11-25 20:05:57'),(706,'Guantes',10,-5,'ACCESORIO_MANOS','2019-11-25 20:05:57'),(707,'Bufanda',10,-10,'ACCESORIO_CUELLO','2019-11-25 20:05:57'),(708,'Anteojos',0,0,'ACCESORIO','2019-11-25 20:05:57'),(709,'Remera',35,20,'PARTE_SUPERIOR_ABAJO','2019-11-25 20:05:58'),(710,'Zapato',35,-5,'CALZADO','2019-11-25 20:05:58'),(711,'Gorra',10,-10,'ACCESORIO','2019-11-25 20:05:58'),(712,'Remera manga larga',26,18,'PARTE_SUPERIOR_ABAJO','2019-11-25 20:05:58'),(713,'Pantalón de invierno',-5,18,'PARTE_INFERIOR','2019-11-25 20:05:58'),(714,'Campera',20,5,'PARTE_SUPERIOR_ARRIBA','2019-11-25 20:05:58');
/*!40000 ALTER TABLE `tipo_prendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
                            `usuario_id` int(11) NOT NULL AUTO_INCREMENT,
                            `nombre` varchar(40) NOT NULL,
                            `apellido` varchar(40) DEFAULT NULL,
                            `email` varchar(100) DEFAULT NULL,
                            `contrasenia` varchar(40) NOT NULL,
                            `numero_celular` varchar(20) DEFAULT NULL,
                            `tiempo_anticipacion` int(11) DEFAULT NULL,
                            `fecha_actualizacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`usuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=311 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (307,'Alexis','Dona','alexis.dona@gmail.com','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','+54911651651',0,'2019-11-25 20:05:58'),(308,'Diego','Peccia','diegoignaciopeccia@gmail.com','d5f12e53a182c062b6bf30c1445153faff12269a','+54911687895',0,'2019-11-25 20:05:58'),(309,'Marina','Posru','maruposru@gmail.com','ac05f5ed6c3c5c3f51a5911d33826a47434ea6ff','+5491131458855',0,'2019-11-25 20:05:58'),(310,'Daiana','Swimmer','daiu.szwimer@gmail.com','77a76c6f7ff39060ad4adc659f3c054811654cdc','+5491154632210',0,'2019-11-25 20:05:58');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_guardarropas`
--

DROP TABLE IF EXISTS `usuarios_guardarropas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios_guardarropas` (
                                         `guardarropa_id` int(11) NOT NULL,
                                         `usuario_id` int(11) NOT NULL,
                                         `fecha_actualizacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                         PRIMARY KEY (`guardarropa_id`,`usuario_id`),
                                         KEY `usuario_guardarropa_FK_1` (`usuario_id`),
                                         CONSTRAINT `usuario_guardarropa_FK` FOREIGN KEY (`guardarropa_id`) REFERENCES `guardarropas` (`guardarropa_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                         CONSTRAINT `usuario_guardarropa_FK_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`usuario_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_guardarropas`
--

LOCK TABLES `usuarios_guardarropas` WRITE;
/*!40000 ALTER TABLE `usuarios_guardarropas` DISABLE KEYS */;
INSERT INTO `usuarios_guardarropas` VALUES (488,307,'2019-11-25 20:05:58'),(488,308,'2019-11-25 20:05:58'),(489,309,'2019-11-25 20:05:58'),(489,310,'2019-11-25 20:05:58');
/*!40000 ALTER TABLE `usuarios_guardarropas` ENABLE KEYS */;
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

-- Dump completed on 2019-11-25 17:13:37