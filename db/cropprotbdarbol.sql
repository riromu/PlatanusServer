-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 15-11-2015 a las 23:59:16
-- Versión del servidor: 5.5.46-0ubuntu0.14.04.2
-- Versión de PHP: 5.5.9-1ubuntu4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `cropprotbdarbol`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `histogram_relation`
--

CREATE TABLE IF NOT EXISTS `histogram_relation` (
  `captura_1` int(11) NOT NULL,
  `captura_2` int(11) NOT NULL,
  `relation` double DEFAULT NULL,
  PRIMARY KEY (`captura_1`,`captura_2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Establishes de histogram comparison between images';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `platanus`
--

CREATE TABLE IF NOT EXISTS `platanus` (
  `indice` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` int(11) DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ip` varchar(15) DEFAULT NULL,
  `iddispositivo` int(11) DEFAULT NULL,
  `modelodispositivo` varchar(100) DEFAULT NULL,
  `coordenadas` varchar(50) DEFAULT NULL,
  `proveedor` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `captura` int(11) NOT NULL DEFAULT '0',
  `numcaptura` int(11) DEFAULT NULL,
  `R` varchar(2) NOT NULL,
  `G` varchar(2) NOT NULL,
  `B` varchar(2) NOT NULL,
  `fichero` varchar(100) DEFAULT NULL,
  `ficherohistogramatxt` varchar(50) DEFAULT NULL,
  `ficherocrop` varchar(50) DEFAULT NULL,
  `ficheromascara` varchar(50) DEFAULT NULL,
  `ficherohistograma` varchar(50) DEFAULT NULL,
  `version` varchar(10) NOT NULL,
  `observaciones` text NOT NULL,
  `publicado` tinyint(1) NOT NULL,
  PRIMARY KEY (`captura`),
  UNIQUE KEY `indice` (`indice`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=172 ;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
