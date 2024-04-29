ALTER TABLE `tours`.`users`
DROP
COLUMN `status`;


ALTER TABLE `tours`.`messages`
    ADD COLUMN `receiver_email` VARCHAR(45) NOT NULL AFTER `sender_email`;
