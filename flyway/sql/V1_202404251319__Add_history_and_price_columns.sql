ALTER TABLE `tours`.`tour`
    ADD COLUMN `history` VARCHAR(1000) NOT NULL AFTER `direction`,
ADD COLUMN `price` VARCHAR(45) NOT NULL AFTER `history`;
