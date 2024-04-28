ALTER TABLE `tours`.`users`
    ADD COLUMN `email` VARCHAR(45) NOT NULL AFTER `name`,
ADD COLUMN `password` VARCHAR(45) NOT NULL AFTER `email`;
