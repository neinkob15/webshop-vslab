CREATE DATABASE IF NOT EXISTS `categories`;
GRANT ALL PRIVILEGES ON `categories`.* TO 'webshopuser'@'%';

USE `categories`;

CREATE TABLE IF NOT EXISTS category (
                                        id INT NOT NULL AUTO_INCREMENT,
                                        name VARCHAR(255) NOT NULL,
                                        PRIMARY KEY (id)
) ENGINE=InnoDB;

USE `categories`;

insert into category (id,name) VALUES (1, 'Wahlessen 1');
insert into category (id,name) VALUES (2, 'Wahlessen 2');
insert into category (id,name) VALUES (3, 'Aktionstheke');
insert into category (id,name) VALUES (4, 'Gut & Günstig');
insert into category (id,name) VALUES (5, 'Buffet');
insert into category (id,name) VALUES (6, 'Schnitzelbar');
insert into category (id,name) VALUES (7, 'Curry Queen');