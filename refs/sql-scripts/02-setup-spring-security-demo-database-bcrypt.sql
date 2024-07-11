USE `accountsdb`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.bcryptcalculator.com/


INSERT INTO `users` 
VALUES 
('monica','{bcrypt}$2a$10$pS4p4jIici2WX76tXQ0trurLeccr.BlnuhiCauW2GQbMLmT4.ZzBS',1),
('ross','{bcrypt}$2a$10$IGfGYskDaNkiXjDZxf/Z8utlEE1nzqBQ6xPqrGILsU7yTuD7W/ZAK',1),
('rachel','{bcrypt}$2a$10$1CBFXQ5boK9ap1Ht2CPcLOtHoKTkjNSCtUvOlhAAgnNsLh9Dh3wu2',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities4_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities4_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('monica','ROLE_EMPLOYEE'),
('ross','ROLE_EMPLOYEE'),
('ross','ROLE_MANAGER'),
('rachel','ROLE_EMPLOYEE'),
('rachel','ROLE_MANAGER'),
('rachel','ROLE_ADMIN');