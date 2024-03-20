CREATE TABLE `tours`.`tour_cards`
(
    `id`          INT         NOT NULL AUTO_INCREMENT,
    `title`       VARCHAR(45) NOT NULL,
    `description` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `tours`.`tour_info`
(
    `id`        INT         NOT NULL AUTO_INCREMENT,
    `direction` VARCHAR(45) NOT NULL,
    `duration`  VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);
