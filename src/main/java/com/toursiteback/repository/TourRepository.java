package com.toursiteback.repository;

import com.toursiteback.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {
    List<Tour> findAllByLanguage(String language);

    List<Tour> findAllByNameStartingWith(String prefix);
    List<Tour>findAllByImgUrl(String imgUrl);

    Tour save(Tour tour);

    Tour findByName(String name);

    void delete(Tour tour);
}
