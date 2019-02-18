DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user 
	(id,username,name,age,balance)
	values 
	(1,'account1','张三',20,100.00);
	
INSERT INTO user 
	(id,username,name,age,balance)
	values 
	(2,'account2','李四',28,180.00);
	
INSERT INTO user 
	(id,username,name,age,balance)
	values 
	(3,'account3','王六',32,280.00);