-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 15, 2026 at 12:57 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `janaarogyasevahealthappdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `registerusertbl`
--

CREATE TABLE `registerusertbl` (
  `id` int(11) NOT NULL,
  `name` varchar(256) DEFAULT NULL,
  `mobile_no` varchar(256) DEFAULT NULL,
  `email_id` varchar(256) DEFAULT NULL,
  `username` varchar(256) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `conform_password` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `registerusertbl`
--

INSERT INTO `registerusertbl` (`id`, `name`, `mobile_no`, `email_id`, `username`, `password`, `conform_password`) VALUES
(4, 'Vedika Pande', '9056783457', 'pandeVedika86@gmail.com', 'Vedika', 'Light@549', 'Light@549'),
(13, 'GayatriPande', '9021585275', 'Pandegayatri86@gmail.com', 'Gayatri@123', 'Sun@123456', 'Sun@123456'),
(14, 'OM PANDE', '9322241073', 'Omp339488@gmail.com', 'Om@123', 'Omp@56789', 'Omp@56789');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `registerusertbl`
--
ALTER TABLE `registerusertbl`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `registerusertbl`
--
ALTER TABLE `registerusertbl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
