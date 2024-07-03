CREATE TABLE `heroku_26deced418a261c`.`messages`
(
    `id`       INT          NOT NULL AUTO_INCREMENT,
    `sender`   VARCHAR(45)  NOT NULL,
    `receiver` VARCHAR(45)  NOT NULL,
    `message`  VARCHAR(500) NOT NULL,
    PRIMARY KEY (`id`)
);
