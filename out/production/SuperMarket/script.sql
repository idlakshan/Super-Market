DROP DATABASE IF EXISTS supermarket;
CREATE DATABASE IF NOT EXISTS supermarket;
SHOW DATABASES;

USE supermarket;

DROP TABLE IF EXISTS customer;
CREATE TABLE IF NOT EXISTS customer(
    id VARCHAR(15),
    name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    address TEXT,
    tp VARCHAR(12),
    CONSTRAINT PRIMARY KEY (id)
    );
SHOW TABLES ;
DESCRIBE customer;
#---------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order`(
    oId VARCHAR(15),
    cId VARCHAR(15),
    date DATE,
    time VARCHAR(15),
    cost DOUBLE,
    CONSTRAINT PRIMARY KEY (oId),
    CONSTRAINT FOREIGN KEY (cId) REFERENCES customer(id) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE `order`;
#-----------------------
DROP TABLE IF EXISTS item;
CREATE TABLE IF NOT EXISTS item(
    code VARCHAR(15),
    name TEXT,
    qtyOnHand INT DEFAULT 0,
    price DOUBLE DEFAULT 0.00,
    CONSTRAINT PRIMARY KEY (code)
    );
SHOW TABLES ;
DESCRIBE item;
#------------------------
DROP TABLE IF EXISTS `order detail`;
CREATE TABLE IF NOT EXISTS `order detail`(
    itemCode VARCHAR(15),
    orderId VARCHAR(15),
    qty INT,
    price DOUBLE,
    CONSTRAINT PRIMARY KEY (itemCode, orderId),
    CONSTRAINT FOREIGN KEY (itemCode) REFERENCES item(code) ON DELETE CASCADE ON UPDATE CASCADE ,
    CONSTRAINT FOREIGN KEY (orderId) REFERENCES `order`(oId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE `order detail`;
