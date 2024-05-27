package com.toursiteback.repository;

import com.toursiteback.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountriesRepository extends JpaRepository<Country, Integer> {
    Country save(Country country);

    List<Country> findAll();
    Country findByIp(String ip);
    void deleteAllBy();
}
