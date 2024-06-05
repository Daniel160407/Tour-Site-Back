package com.toursiteback.service.states;

import com.toursiteback.dto.CountryDto;
import com.toursiteback.dto.StatisticDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatisticsService {
    void increaseTime(String time);

    void increaseClicks(Integer clicks);
    void increaseUsers(String email);

    void increaseMessages();

    void increaseFeedbacks();

    StatisticDto getStatistics();

    StatisticDto clearStatistic(String field);

    void addCountry(CountryDto countryDto);

    List<CountryDto> getCountries();
    void clearCountries();
}
