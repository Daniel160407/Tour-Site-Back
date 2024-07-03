ALTER TABLE `heroku_26deced418a261c`.`messages`
    CHANGE COLUMN `from` `cender` VARCHAR (45) NOT NULL,
    CHANGE COLUMN `to` `receiver` VARCHAR (45) NOT NULL;
