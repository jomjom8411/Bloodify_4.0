-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 02 déc. 2018 à 16:43
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bloodify`
--

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `Id` int(30) NOT NULL AUTO_INCREMENT,
  `nom` varchar(40) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `tel` int(20) NOT NULL,
  `region` varchar(40) NOT NULL,
  `grpsanguin` varchar(10) NOT NULL,
  `age` int(10) NOT NULL,
  `datedonation` varchar(30) NOT NULL,
  `password` varchar(40) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `post`;
CREATE TABLE IF NOT EXISTS `post` (
  `Id` int(30) NOT NULL AUTO_INCREMENT,
  `id_user` int(20) NOT NULL,
  `region` varchar(40) NOT NULL,
  `grpsanguin` varchar(10) NOT NULL,
  `slots` int(10) NOT NULL,
  `donors_number` int(10) NOT NULL,
  PRIMARY KEY (`Id`),
 FOREIGN KEY (`id_user`) REFERENCES user(`Id`)
  ) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;



DROP TABLE IF EXISTS `donate`;
CREATE TABLE IF NOT EXISTS `donate` (
  `Id` int(30) NOT NULL AUTO_INCREMENT,
  `id_user` int(20) NOT NULL,
  `id_post` int(20) NOT NULL,
  `etat` int (20)  DEFAULT '0',
  PRIMARY KEY (`Id`),
 FOREIGN KEY (`id_user`) REFERENCES user(`Id`),
  FOREIGN KEY (`id_post`) REFERENCES post(`Id`)
  ) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;













--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`Id`, `nom`, `prenom`, `Email`, `tel`, `region`, `grpsanguin`, `age`, `datedonation`, `password`) VALUES
(1, 'hello', 'hi', 'helli@hi.com', 554456, 'ben arous', 'ab+', 23, '554488', '123'),
(2, 'hellooooo', 'hi', 'helli@hi.com', 55445655, 'ben aroussss', 'ab+', 2355, '554488jhh', '123'),
(3, 'hellooooo', 'hi', 'helli@hi.com', 55445655, 'ben aroussss', 'ab+', 2355, '554488jhh', '12344'),
(4, 'hellooooo', 'hi', 'helli@hi.com', 55445655, 'ben aroussss', 'ab+', 2355, '554488jhh', '12344'),
(5, 'hellooooo', 'hi', 'helli@hi.com', 55445655, 'ben aroussss', 'ab+', 2355, '554488jhh', '12344'),
(6, 'uu', 'jj', 'hj', 66, 'hh', 'hh', 66, 'jj', 'jj'),
(7, 'hh', 'hh', 'gg', 55, 'hh', 'hh', 55, 'hg', '55'),
(8, 'a', 'a', 'a', 1, 'a', 'a', 1, 'a', '11'),
(9, 'n', 'n', 'n', 7, 'n', 'n', 7, 'n', '77'),
(10, 'omar', 'dhouib', 'dhouib60@gmail.com', 58686901, 'tunis', 'AB+', 23, 'nsit', '12345'),
(11, 'oo', 'oo', 'oo', 77, 'oo', 'oo', 77, 'oo', 'oo'),
(12, 'mm', 'mm', 'mm', 66, 'mm', 'mm', 66, 'mm', '77'),
(13, 'hh', 'hh', 'dh@dh.com', 66, 'hh', 'hh', 55, 'hh', '66'),
(14, 'jomjom', 'jomjom', 'jomjom', 0, 'jomjom', 'o', 0, '00fois', '000'),
(15, 'pp', 'pp', 'pp', 0, 'pp', 'Hi', 0, 'mm', 'mmm'),
(16, 'opm', 'opm', 'opm', 0, 'opm', 'AB+', 99, '24/11/2018', 'mm'),
(17, 'dhouib', 'omar', 'omar.dhouib@esprit.tn', 58686901, 'Sfax', 'AB+', 23, '24/11/2018', '000');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
