ALTER TABLE `heroku_26deced418a261c`.`messages`
    CHANGE COLUMN `sender` `from` VARCHAR (45) NOT NULL,
    CHANGE COLUMN `receiver` `to` VARCHAR (45) NOT NULL;
