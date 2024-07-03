ALTER TABLE `heroku_26deced418a261c`.`users`
DROP
COLUMN `status`;


ALTER TABLE `tours`.`messages`
    ADD COLUMN `receiver_email` VARCHAR(45) NOT NULL AFTER `sender_email`;
