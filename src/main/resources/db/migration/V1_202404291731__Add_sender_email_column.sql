ALTER TABLE `tours`.`messages`
    ADD COLUMN `sender_email` VARCHAR(45) NOT NULL AFTER `id`;
