package com.toursiteback.repository;

import com.toursiteback.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatesRepository extends JpaRepository<State, Integer> {
    State save(State state);
    Optional<State> findById(Integer id);
}
