-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Oct 06, 2025 at 04:30 PM
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
-- Database: `ead1-repeat-fix`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `emp_id` int(11) NOT NULL,
  `emp_name` varchar(100) NOT NULL,
  `emp_email` varchar(100) NOT NULL,
  `emp_role` varchar(50) NOT NULL,
  `emp_password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`emp_id`, `emp_name`, `emp_email`, `emp_role`, `emp_password`) VALUES
(1, 'Sandeep Kumar', 'sandeepkumar@gmail.com', 'Chef', 'sandycook'),
(2, 'Priya Sharma', 'priyasharma@gmail.com', 'Cashier', 'priya123'),
(3, 'Ravi Menon', 'ravimenon@gmail.com', 'Chef', 'ravi99'),
(4, 'Aisha Khan', 'aishakhan@gmail.com', 'Cashier', 'aisha456'),
(5, 'Vikram Singh', 'vikramsingh@gmail.com', 'Chef', 'vikram789'),
(6, 'Deepa Patil', 'deepapatil@gmail.com', 'Cashier', 'deepaabc'),
(7, 'Arjun Reddy', 'arjunreddy@gmail.com', 'Chef', 'arjuncook'),
(8, 'Sneha Das', 'snehadas@gmail.com', 'Cashier', 'sneha321'),
(9, 'Karthik Nair', 'karthiknair@gmail.com', 'Chef', 'karthik00'),
(10, 'Neha Verma', 'nehaverma@gmail.com', 'Cashier', 'neha2024'),
(11, 'Rohit Gupta', 'rohitgupta@gmail.com', 'Chef', 'rohitchef'),
(12, 'Anjali Rao', 'anjalirao@gmail.com', 'Cashier', 'anjaliop'),
(13, 'Ganesh Iyer', 'ganeshiyer@gmail.com', 'Chef', 'ganeshpass'),
(14, 'Kavita Joshi', 'kavitajoshi@gmail.com', 'Cashier', 'kavita11'),
(15, 'Mohan Pal', 'mohanpal@gmail.com', 'Chef', 'mohanfood'),
(16, 'Michael Johnson', 'michael.j@email.com', 'Chef', 'mikecooks'),
(17, 'Jessica Davis', 'jessica.davis@email.com', 'Cashier', 'jessdavi'),
(18, 'Robert Brown', 'robert.b@email.com', 'Chef', 'robbrown'),
(19, 'Emily Wilson', 'emily.wilson@email.com', 'Cashier', 'emilykey'),
(20, 'William Miller', 'william.m@email.com', 'Chef', 'willpass'),
(21, 'Sarahh Clark', 'sarah.clark@email.com', 'Cashier', 'sarahc456'),
(22, 'David Moore', 'david.moore@email.com', 'Chef', 'davefood'),
(23, 'Olivia Hall', 'olivia.hall@email.com', 'Cashier', 'oliviah11'),
(24, 'James Walker', 'james.walker@email.com', 'Chef', 'jameschef'),
(25, 'Sophia Lewis', 'sophia.lewis@email.com', 'Cashier', 'sophie321'),
(26, 'Charles Young', 'charles.y@email.com', 'Chef', 'charliey'),
(27, 'Ava King', 'ava.king@email.com', 'Cashier', 'avaking'),
(28, 'George Hill', 'george.h@email.com', 'Chef', 'georgepass'),
(29, 'Mia Scott', 'mia.scott@email.com', 'Cashier', 'miascott'),
(30, 'Thomas Baker', 'thomas.baker@email.com', 'Chef', 'tombake'),
(37, 'cashier1', 'cashier1@gmail.com', 'Cashier', 'cashier123'),
(38, 'cashier2', 'cashier2@gmail.com', 'Cashier', 'cashier123'),
(39, 'chef1', 'chef1@gmail.com', 'Chef', 'chef123');

-- --------------------------------------------------------

--
-- Table structure for table `menus`
--

CREATE TABLE `menus` (
  `menu_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `image_path` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menus`
--

INSERT INTO `menus` (`menu_id`, `name`, `price`, `image_path`) VALUES
(1, 'Spaghetti', 12.50, '/ead1/repeat/Resources/spaghetti.jpg'),
(2, 'Pizza', 15.00, '/ead1/repeat/Resources/pizza.jpg'),
(3, 'Burger', 10.00, '/ead1/repeat/Resources/burger.jpg'),
(4, 'Salad', 8.50, '/ead1/repeat/Resources/salad.jpg'),
(5, 'Sushi', 18.00, '/ead1/repeat/Resources/sushi.jpg'),
(6, 'Steak', 22.00, '/ead1/repeat/Resources/steak.jpg'),
(7, 'Pasta Carbonara', 13.50, '/ead1/repeat/Resources/pasta_carbonara.jpg'),
(8, 'Grilled Chicken', 14.00, '/ead1/repeat/Resources/grilled_chicken.jpg'),
(9, 'Ice Cream', 6.00, '/ead1/repeat/Resources/ice_cream.jpg'),
(10, 'French Fries', 5.50, '/ead1/repeat/Resources/french_fries.jpg'),
(16, 'No Image Menu', 12.00, NULL),
(18, 'asd', 123.00, NULL),
(19, 'qwe', 123.00, NULL),
(20, 'qwee', 120.00, NULL),
(21, 'example', 20.00, NULL),
(22, 'example2', 30.00, NULL),
(23, 'example3', 5.00, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `message_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `content` text NOT NULL,
  `timestamp` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`message_id`, `sender_id`, `content`, `timestamp`) VALUES
(8, 0, 'Hello. This is Admin', '2025-10-06 14:26:09'),
(9, 0, 'Hello again', '2025-10-06 14:26:21'),
(10, 37, 'Hello Admin... I\'m Cashier1', '2025-10-06 14:27:00'),
(11, 37, 'Hello again', '2025-10-06 14:31:08'),
(12, 0, 'Hello... Hi Hi....', '2025-10-06 14:35:29'),
(13, 37, 'Hi again', '2025-10-06 14:35:51'),
(14, 0, 'Go and work without saying hi nonstop', '2025-10-06 14:36:07'),
(15, 0, 'Test', '2025-10-06 14:36:15'),
(16, 37, 'Yes, boss', '2025-10-06 14:36:54'),
(17, 38, 'Hello... What\'s going on', '2025-10-06 14:37:33'),
(18, 38, 'Hi Admin, Cashier1', '2025-10-06 14:37:41'),
(19, 38, 'I\'m Cashier2', '2025-10-06 14:37:52'),
(20, 37, 'Oh... Hello hello', '2025-10-06 14:38:47'),
(21, 39, 'Hello... I\'m Chef1, obviously a chef', '2025-10-06 14:43:31'),
(22, 0, 'Hello everyone', '2025-10-06 14:45:43'),
(23, 37, 'Hello Chef1', '2025-10-06 14:46:42'),
(24, 38, 'Hello Chef1', '2025-10-06 14:47:07'),
(25, 0, 'Test1', '2025-10-06 15:02:10'),
(26, 37, 'Test2', '2025-10-06 15:03:46'),
(27, 39, 'Test3', '2025-10-06 15:13:36');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `order_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `total_amount` decimal(10,2) NOT NULL,
  `cashier_id` int(11) NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'incomplete'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `order_date`, `total_amount`, `cashier_id`, `status`) VALUES
(1, '2025-10-05 14:29:14', 340.50, 37, 'complete'),
(2, '2025-10-05 14:30:24', 183.00, 37, 'complete'),
(3, '2025-10-05 14:30:48', 60.00, 37, 'complete'),
(4, '2025-10-05 14:31:01', 45.00, 37, 'complete'),
(5, '2025-11-06 14:31:40', 229.50, 37, 'incomplete'),
(6, '2025-11-06 14:31:53', 22.00, 37, 'incomplete'),
(7, '2025-12-07 14:32:20', 227.00, 37, 'incomplete'),
(8, '2025-10-05 14:33:38', 143.00, 38, 'complete'),
(9, '2025-10-05 14:33:56', 72.00, 38, 'incomplete'),
(10, '2025-10-05 14:34:06', 45.00, 38, 'incomplete'),
(11, '2025-10-05 14:34:15', 75.00, 38, 'incomplete'),
(12, '2025-10-05 15:53:38', 235.00, 37, 'incomplete'),
(13, '2025-11-08 15:55:06', 367.50, 37, 'incomplete'),
(14, '2025-10-05 15:57:06', 407.50, 37, 'incomplete'),
(15, '2025-10-05 15:58:53', 594.00, 38, 'complete'),
(16, '2025-10-05 15:59:42', 600.00, 38, 'complete'),
(17, '2025-10-05 16:01:35', 950.50, 37, 'incomplete'),
(19, '2025-10-05 16:04:38', 756.00, 37, 'incomplete'),
(22, '2025-10-05 19:58:17', 40.00, 38, 'incomplete'),
(23, '2025-10-05 19:58:23', 15.00, 38, 'incomplete'),
(24, '2025-10-05 19:58:31', 32.50, 38, 'incomplete'),
(25, '2025-10-05 19:59:49', 503.00, 38, 'incomplete'),
(26, '2025-10-05 20:01:49', 250.00, 37, 'incomplete'),
(27, '2025-10-05 20:37:35', 1080.00, 37, 'incomplete'),
(28, '2025-10-06 09:34:14', 30.00, 37, 'complete');

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `item_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `subtotal_price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order_items`
--

INSERT INTO `order_items` (`item_id`, `order_id`, `menu_id`, `quantity`, `subtotal_price`) VALUES
(1, 1, 1, 3, 37.50),
(2, 1, 2, 10, 150.00),
(3, 1, 3, 3, 30.00),
(4, 1, 18, 1, 123.00),
(5, 2, 7, 1, 13.50),
(6, 2, 2, 4, 60.00),
(7, 2, 3, 1, 10.00),
(8, 2, 6, 4, 88.00),
(9, 2, 10, 1, 5.50),
(10, 2, 9, 1, 6.00),
(11, 3, 2, 4, 60.00),
(12, 4, 4, 2, 17.00),
(13, 4, 8, 2, 28.00),
(14, 5, 6, 3, 66.00),
(15, 5, 7, 1, 13.50),
(16, 5, 3, 3, 30.00),
(17, 5, 2, 8, 120.00),
(18, 6, 6, 1, 22.00),
(19, 7, 3, 1, 10.00),
(20, 7, 2, 5, 75.00),
(21, 7, 6, 1, 22.00),
(22, 7, 5, 5, 90.00),
(23, 7, 9, 5, 30.00),
(24, 8, 2, 5, 75.00),
(25, 8, 3, 3, 30.00),
(26, 8, 7, 2, 27.00),
(27, 8, 10, 2, 11.00),
(28, 9, 8, 2, 28.00),
(29, 9, 4, 2, 17.00),
(30, 9, 7, 2, 27.00),
(31, 10, 2, 3, 45.00),
(32, 11, 2, 5, 75.00),
(33, 12, 1, 10, 125.00),
(34, 12, 2, 1, 15.00),
(35, 12, 3, 1, 10.00),
(36, 12, 4, 10, 85.00),
(37, 13, 2, 8, 120.00),
(38, 13, 3, 10, 100.00),
(39, 13, 10, 5, 27.50),
(40, 13, 9, 20, 120.00),
(41, 14, 9, 20, 120.00),
(42, 14, 7, 5, 67.50),
(43, 14, 6, 10, 220.00),
(44, 15, 9, 99, 594.00),
(45, 16, 2, 40, 600.00),
(46, 17, 10, 30, 165.00),
(47, 17, 8, 20, 280.00),
(48, 17, 4, 20, 170.00),
(49, 17, 5, 11, 198.00),
(50, 17, 1, 11, 137.50),
(55, 19, 16, 2, 24.00),
(56, 19, 18, 2, 246.00),
(57, 19, 20, 2, 240.00),
(58, 19, 19, 2, 246.00),
(64, 22, 2, 2, 30.00),
(65, 22, 3, 1, 10.00),
(66, 23, 2, 1, 15.00),
(67, 24, 3, 1, 10.00),
(68, 24, 4, 1, 8.50),
(69, 24, 8, 1, 14.00),
(70, 25, 1, 1, 12.50),
(71, 25, 2, 1, 15.00),
(72, 25, 3, 1, 10.00),
(73, 25, 4, 1, 8.50),
(74, 25, 8, 1, 14.00),
(75, 25, 7, 1, 13.50),
(76, 25, 6, 1, 22.00),
(77, 25, 5, 1, 18.00),
(78, 25, 9, 1, 6.00),
(79, 25, 10, 1, 5.50),
(80, 25, 16, 1, 12.00),
(81, 25, 18, 1, 123.00),
(82, 25, 20, 1, 120.00),
(83, 25, 19, 1, 123.00),
(84, 26, 21, 4, 80.00),
(85, 26, 22, 5, 150.00),
(86, 26, 23, 4, 20.00),
(87, 27, 20, 9, 1080.00),
(88, 28, 2, 2, 30.00);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`emp_id`),
  ADD UNIQUE KEY `emp_email` (`emp_email`);

--
-- Indexes for table `menus`
--
ALTER TABLE `menus`
  ADD PRIMARY KEY (`menu_id`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `sender_id` (`sender_id`),
  ADD KEY `idx_message_timestamp` (`timestamp`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `cashier_id` (`cashier_id`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`item_id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `menu_id` (`menu_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `emp_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `menus`
--
ALTER TABLE `menus`
  MODIFY `menu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`cashier_id`) REFERENCES `employee` (`emp_id`);

--
-- Constraints for table `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  ADD CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `menus` (`menu_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
