-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 16, 2021 at 02:40 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vending_machine`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `idproduct` varchar(10) NOT NULL,
  `nameproduct` varchar(50) NOT NULL,
  `price` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `image` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`idproduct`, `nameproduct`, `price`, `stock`, `image`) VALUES
('be01', 'Lays', 10000, 5, 'lays.png'),
('ch01', 'Chitato', 15000, 1, 'chitato.png'),
('co01', 'Coca cola', 5000, 43, 'cocacola.png'),
('do01', 'Doritos', 25000, 50, 'doritos.png'),
('fa01', 'Fanta', 10000, 50, 'fanta.png'),
('po01', 'Pocky', 20000, 41, 'pocky.png'),
('pr01', 'Pringles', 25000, 48, 'pringles.png'),
('sp01', 'Sprite', 5000, 50, 'sprite.png'),
('tg01', 'Tango', 15000, 44, 'tango.png');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `idtransaction` int(11) NOT NULL,
  `product_idproduct` varchar(10) NOT NULL,
  `price` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `transactiondate` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`idtransaction`, `product_idproduct`, `price`, `qty`, `transactiondate`) VALUES
(26, 'be01', 5000, 1, '2021-01-16 15:06:24'),
(27, 'ch01', 50000, 1, '2021-01-16 15:44:37'),
(28, 'co01', 50000, 3, '2021-01-16 15:49:13'),
(29, 'po01', 100000, 5, '2021-01-16 15:49:24'),
(30, 'po01', 100000, 3, '2021-01-16 15:49:31'),
(31, 'tg01', 100000, 6, '2021-01-16 15:49:37'),
(32, 'pr01', 100000, 2, '2021-01-16 15:49:40'),
(33, 'po01', 100000, 1, '2021-01-16 15:49:58'),
(34, 'ch01', 50000, 1, '2021-01-16 16:40:19'),
(35, 'co01', 50000, 2, '2021-01-16 16:54:06'),
(36, 'co01', 50000, 2, '2021-01-16 17:00:10');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `iduser` int(11) NOT NULL,
  `nameuser` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`iduser`, `nameuser`, `username`, `password`) VALUES
(1, 'ryan_aldrich', 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`idproduct`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`idtransaction`),
  ADD KEY `fk_product_transaction` (`product_idproduct`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`iduser`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `idtransaction` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `fk_product_transaction` FOREIGN KEY (`product_idproduct`) REFERENCES `product` (`idproduct`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
