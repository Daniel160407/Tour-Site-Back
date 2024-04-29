CREATE TABLE `tours`.`messages`
(
    `id`       INT          NOT NULL AUTO_INCREMENT,
    `sender`   VARCHAR(45)  NOT NULL,
    `receiver` VARCHAR(45)  NOT NULL,
    `message`  VARCHAR(500) NOT NULL,
    PRIMARY KEY (`id`)
);
