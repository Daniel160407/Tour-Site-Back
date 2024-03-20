ALTER TABLE `tours`.`tour_cards`
    ADD CONSTRAINT `id`
        FOREIGN KEY (`id`)
            REFERENCES `tours`.`tour_info` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;
