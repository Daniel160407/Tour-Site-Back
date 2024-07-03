ALTER TABLE `heroku_26deced418a261c`.`users`
    ADD COLUMN `email` VARCHAR(45) NOT NULL AFTER `name`,
ADD COLUMN `password` VARCHAR(45) NOT NULL AFTER `email`;
