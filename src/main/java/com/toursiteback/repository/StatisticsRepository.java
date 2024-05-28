package com.toursiteback.repository;

import com.toursiteback.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistic, Integer> {
    Statistic save(Statistic statistic);
    Optional<Statistic> findById(Integer id);
}
