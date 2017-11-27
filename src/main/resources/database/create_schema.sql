CREATE SCHEMA `mpdemo` DEFAULT CHARACTER SET utf8mb4 ;

USE mpdemo;

CREATE FUNCTION `UUIDTOBIN`(my_uuid char(36)) RETURNS binary(16)
  BEGIN
    #     DECLARE my_uuid char(36);
    #     SET my_uuid = UUID();
    RETURN CONCAT(UNHEX(LEFT(my_uuid,8)),UNHEX(MID(my_uuid,10,4)),UNHEX(MID(my_uuid,15,4)),UNHEX(MID(my_uuid,20,4)),UNHEX(RIGHT(my_uuid,12)));
  END;

CREATE FUNCTION `BINTOUUID`(UUID BINARY(16)) RETURNS char(36)
  BEGIN
    RETURN CONCAT(HEX(LEFT(uuid,4)),'-', HEX(MID(uuid,5,2)),'-', HEX(MID(uuid,7,2)),'-',HEX(MID(uuid,9,2)),'-',HEX(RIGHT(uuid,6)));
  END;

# DROP TABLE IF EXISTS `order_head`;
# DROP TABLE IF EXISTS `seller`;
# DROP TABLE IF EXISTS `buyer`;

CREATE TABLE `buyer`(
  `buyerID`    BINARY(16) PRIMARY KEY ,
  `openID`     VARCHAR(50) UNIQUE NOT NULL ,
  `unionID`    VARCHAR(50) ,
  `buyerName`  VARCHAR(100) NOT NULL ,
  `buyerPass`  VARCHAR(100) NOT NULL ,
  `accountBalance` DECIMAL(10,2) DEFAULT 0.0
);

CREATE TABLE `seller`(
  `sellerID`   BINARY(16) PRIMARY KEY ,
  `sellerName` VARCHAR(100) NOT NULL ,
  `sellerPass` VARCHAR(100) ,
  `sellerType` INTEGER(4) NOT NULL DEFAULT 0
);

CREATE TABLE `order_head`(
  `orderID`   BINARY(16) PRIMARY KEY ,
  `orderType` INTEGER(4) NOT NULL ,
  `orderContent` VARCHAR(255) ,
  `totalPrice` DECIMAL(10,2) DEFAULT 0.0 ,
  `orderStatus` INTEGER(4) NOT NULL ,

  `buyerID`    BINARY(16) ,
  `sellerID`  BINARY(16) ,

  `createTime` TIMESTAMP DEFAULT current_timestamp,

  FOREIGN KEY (`buyerID`) REFERENCES `buyer`(`buyerID`) ,
  FOREIGN KEY (`sellerID`) REFERENCES `seller`(`sellerID`)
);


