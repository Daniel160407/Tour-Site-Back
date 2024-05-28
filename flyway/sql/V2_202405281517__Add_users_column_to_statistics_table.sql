ALTER TABLE `tours`.`states`
    ADD COLUMN `users` INT NOT NULL AFTER `clicks`, RENAME TO  `tours`.`statistics`;
