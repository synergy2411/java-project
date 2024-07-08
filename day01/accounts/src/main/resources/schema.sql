CREATE TABLE IF NOT EXISTS `customer` (
    `customer_id` int AUTO_INCREMENT PRIMARY KEY,
     `name` varchar(200) NOT NULL,
     `email` varchar(100) NOT NULL,
     `mobileNumber` varchar(20) NOT NULL,
     `created_at` date NOT NULL,
     `created_by` varchar(30) NOT NULL,
     `updated_at` date DEFAULT NULL,
     `updated_by` varchar(30) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `accounts` (
    `account_id` int AUTO_INCREMENT PRIMARY KEY,
    `account_number` int NOT NULL,
    `customer_id` int NOT NULL,
    `account_type` varchar(30) NOT NULL,
    `branch_address` varchar(200) NOT NULL,
    `mobileNumber` varchar(20) NOT NULL,
     `created_at` date NOT NULL,
     `created_by` varchar(30) NOT NULL,
     `updated_at` date DEFAULT NULL,
   `updated_by` varchar(30) DEFAULT NULL
);