ALTER TABLE `heroku_26deced418a261c`.`states`
    ADD COLUMN `users` INT NOT NULL AFTER `clicks`, RENAME TO  `tours`.`statistics`;
