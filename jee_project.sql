-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 02, 2021 at 05:06 AM
-- Server version: 8.0.21
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jee_project`
--
CREATE DATABASE IF NOT EXISTS `jee_project` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `jee_project`;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `idCategory` int NOT NULL AUTO_INCREMENT,
  `label` varchar(250) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idCategory`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`idCategory`, `label`, `description`) VALUES
(1, 'Mouse', 'A mouse designed for gamers. Gaming mice have adjustable sensitivity, which is configurable as the number of dots per inch (DPI). The greater the DPI, the farther the cursor moves on screen with the same amount of mouse movement.'),
(2, 'Keyboard', 'A computer keyboard is an input device used to enter characters and functions into the computer system by pressing buttons, or keys.');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `idPorduct` int NOT NULL AUTO_INCREMENT,
  `label` varchar(250) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` varchar(250) DEFAULT NULL,
  `VAT` varchar(250) DEFAULT NULL,
  `idCategory` int DEFAULT NULL,
  `idProvider` int DEFAULT NULL,
  PRIMARY KEY (`idPorduct`),
  KEY `fk_category` (`idCategory`),
  KEY `fk_provider` (`idProvider`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`idPorduct`, `label`, `description`, `quantity`, `price`, `VAT`, `idCategory`, `idProvider`) VALUES
(1, 'Glorious Model O (Matte WHITE)', 'Envisioned by a community of passionate gamers, and developed by a team who accepts nothing less than perfection - Model O will elevate your play to unimaginable heights.', 150, '145.000', '0.5', 1, 1),
(7, 'GMMK GLORIOUS MODULAR MECHANICAL KEYBOARD', 'Worlds first RGB, modular mechanical keyboard. Comes preinstalled with Gateron brown switches & black keycaps. Ready to go - requires no setup. Perfect for someone new to mechanical keyboards.', 165, '260', '0.3', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
CREATE TABLE IF NOT EXISTS `provider` (
  `idProvider` int NOT NULL AUTO_INCREMENT,
  `name` varchar(250) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `mobile` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`idProvider`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `provider`
--

INSERT INTO `provider` (`idProvider`, `name`, `address`, `mobile`) VALUES
(1, 'OMAR BEN OMRANE', 'Ben Arous, Tunisia', '+21690205980');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `idUser` int NOT NULL AUTO_INCREMENT,
  `username` varchar(250) DEFAULT NULL,
  `password` varchar(250) DEFAULT NULL,
  `role` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`idUser`, `username`, `password`, `role`) VALUES
(0, 'OmarBO', 'admin', '0'),
(1, 'aladdin', '0000', '0'),
(2, 'User', 'user', '1'),
(3, 'user', 'user', '1'),
(4, 'admin', 'admin', '0');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
