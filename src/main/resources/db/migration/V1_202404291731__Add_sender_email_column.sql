ALTER TABLE `heroku_26deced418a261c`.`messages`
    ADD COLUMN `sender_email` VARCHAR(45) NOT NULL AFTER `id`;
