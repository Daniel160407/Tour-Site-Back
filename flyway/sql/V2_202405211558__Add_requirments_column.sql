ALTER TABLE `tours`.`tour`
    ADD COLUMN `requirements` VARCHAR(1000) NOT NULL AFTER `history`;
