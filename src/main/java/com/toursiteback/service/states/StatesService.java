package com.toursiteback.service.states;

import com.toursiteback.dto.CountryDto;
import com.toursiteback.dto.StateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatesService {
    void increaseTime(String time);

    void increaseClicks(Integer clicks);

    void increaseMessages();

    void increaseFeedbacks();

    StateDto getStates();

    StateDto clearState(String field);

    void addCountry(CountryDto countryDto);

    List<CountryDto> getCountries();
    void clearCountries();
}
