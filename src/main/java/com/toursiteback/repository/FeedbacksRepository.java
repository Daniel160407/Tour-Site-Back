package com.toursiteback.repository;

import com.toursiteback.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbacksRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> getAllBy();
    Feedback save(Feedback feedback);
    void deleteByComment(String comment);
}
