-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 24, 2017 at 07:39 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shcrud`
--

-- --------------------------------------------------------

--
-- Table structure for table `banks`
--

CREATE TABLE `banks` (
  `bankid` int(11) NOT NULL,
  `bankname` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `banks`
--

INSERT INTO `banks` (`bankid`, `bankname`) VALUES
(1, 'ICICI'),
(2, 'SBI');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userid` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `bankid` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userid`, `first_name`, `last_name`, `email`, `bankid`, `username`, `password`, `enabled`) VALUES
(6, 'satya', 'ch', 'satya@gmail.com', 1, 'admin', 'admin@123', 1),
(7, 'srinidhi', 'ch', 'srinidhi@gmail.com', 2, 'srinidhi', 'srinidhi@123', 0),
(8, 'sony', 'n', 'sony@gmail.com', 1, 'sony', 'sony@123', 0),
(9, 'srujana', 'ch', 'srujana@gmail.com', 2, 'srujana', 'srujana@123', 0),
(10, 'Srinivesh', 'ch', 'srinivesh@gmail.com', 1, 'sriniviesh', 'sriniviesh@123', 0);

-- --------------------------------------------------------

--
-- Table structure for table `users_balance`
--

CREATE TABLE `users_balance` (
  `id` int(11) NOT NULL,
  `addamount` double NOT NULL DEFAULT '0',
  `withdrawamount` double NOT NULL DEFAULT '0',
  `userid` int(11) NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `typeoftxn` enum('M','T','W','WT') DEFAULT 'M',
  `withdrawfee` int(5) NOT NULL,
  `transferfee` int(5) NOT NULL,
  `transferid` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users_balance`
--

INSERT INTO `users_balance` (`id`, `addamount`, `withdrawamount`, `userid`, `date`, `typeoftxn`, `withdrawfee`, `transferfee`, `transferid`) VALUES
(1, 100, 0, 6, '2017-04-15 16:38:19', 'M', 0, 0, 0),
(2, 0, 20, 6, '2017-04-22 22:24:57', 'WT', 0, 3, 0),
(3, 20, 0, 7, '2017-04-22 22:24:57', 'T', 0, 0, 6),
(4, 0, 2, 6, '2017-04-22 23:07:35', 'W', 0, 0, 0),
(5, 0, 3, 6, '2017-04-22 23:07:37', 'W', 0, 0, 0),
(6, 0, 4, 6, '2017-04-22 23:07:40', 'W', 0, 0, 0),
(7, 0, 5, 6, '2017-04-22 23:07:45', 'W', 0, 0, 0),
(8, 0, 6, 6, '2017-04-22 23:07:48', 'W', 10, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `role_id` int(11) NOT NULL,
  `role` varchar(45) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`role_id`, `role`, `user_id`) VALUES
(1, 'ROLE_ADMIN', 6),
(2, 'ROLE_ADMIN', 6),
(3, 'ROLE_USER', 7),
(4, 'ROLE_USER', 8),
(5, 'ROLE_USER', 9),
(6, 'ROLE_USER', 10);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `banks`
--
ALTER TABLE `banks`
  ADD PRIMARY KEY (`bankid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `users_balance`
--
ALTER TABLE `users_balance`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `banks`
--
ALTER TABLE `banks`
  MODIFY `bankid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `users_balance`
--
ALTER TABLE `users_balance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
