CREATE TABLE IF NOT EXISTS `customer` (
    `customer_id` int AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(200) NOT NULL,
    `email` varchar(200) NOT NULL,
    `mobile_number` varchar NOT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(200) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(200) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `accounts` (
    `account_number` int AUTO_INCREMENT PRIMARY KEY,
    `customer_id` int NOT NULL,
    `account_tpe` varchar(200) NOT NULL,
    `branch_address` varchar(200) NOT NULL,
    `communication_status` boolean,
    `created_at` date NOT NULL,
    `created_by` varchar(200) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(200) DEFAULT NULL
);