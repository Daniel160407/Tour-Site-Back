CREATE TABLE `heroku_26deced418a261c`.`states`
(
    `id`        INT         NOT NULL AUTO_INCREMENT,
    `time`      VARCHAR(45) NOT NULL,
    `clicks`    INT         NOT NULL,
    `messages`  INT         NOT NULL,
    `feedbacks` INT         NOT NULL,
    PRIMARY KEY (`id`)
);
