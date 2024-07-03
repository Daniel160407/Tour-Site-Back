CREATE TABLE `heroku_26deced418a261c`.`feedbacks`
(
    `id`      INT           NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(45)   NOT NULL,
    `time`    VARCHAR(45)   NOT NULL,
    `comment` VARCHAR(2000) NOT NULL,
    PRIMARY KEY (`id`)
);
