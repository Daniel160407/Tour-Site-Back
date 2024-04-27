package com.toursiteback.repository;

import com.toursiteback.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {
    List<Tour> findAllBy();

    Tour save(Tour tour);

    Tour findByName(String name);

    void delete(Tour tour);
}
