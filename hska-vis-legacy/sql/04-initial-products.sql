CREATE DATABASE IF NOT EXISTS `products`;
GRANT ALL PRIVILEGES ON `products`.* TO 'webshopuser'@'%';

USE `products`;

CREATE TABLE IF NOT EXISTS product (
	id INT NOT NULL AUTO_INCREMENT,
	details VARCHAR(255) DEFAULT '',
	name VARCHAR(255) NOT NULL,
	price DOUBLE,
	category_id INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE UNIQUE INDEX UK_sakjdhgouew7zgfru ON product(name);

USE `products`;

insert into product(name,price,category_id) VALUES ('Currywurst', 2.00, 3);
insert into product(name,price,category_id) VALUES ('Kartoffeleintopf', 2.60, 4);
insert into product(name,price,category_id) VALUES ('Schnitzel', 2.60, 6);
insert into product(name,price,category_id) VALUES ('Buffet', 0.83, 5);