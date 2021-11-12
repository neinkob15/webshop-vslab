CREATE DATABASE IF NOT EXISTS `products`;

GRANT ALL PRIVILEGES ON *.* TO 'webshopuser'@'%';

USE `products`;

CREATE TABLE IF NOT EXISTS product (
	id INT NOT NULL AUTO_INCREMENT,
	details VARCHAR(255),
	name VARCHAR(255),
	price DOUBLE,
	category_id INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE UNIQUE INDEX UK_sakjdhgouew7zgfru ON product(name);

CREATE DATABASE IF NOT EXISTS `categories`;

USE `categories`;

CREATE TABLE IF NOT EXISTS category (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;
