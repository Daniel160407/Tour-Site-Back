package com.toursiteback.service.states;

import com.toursiteback.dto.CountryDto;
import com.toursiteback.dto.StatisticDto;
import com.toursiteback.model.Country;
import com.toursiteback.model.Statistic;
import com.toursiteback.repository.CountriesRepository;
import com.toursiteback.repository.StatisticsRepository;
import com.toursiteback.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    private final StatisticsRepository statisticsRepository;
    private final CountriesRepository countriesRepository;
    private final ModelConverter modelConverter;

    @Autowired
    public StatisticsServiceImpl(StatisticsRepository statisticsRepository, CountriesRepository countriesRepository, ModelConverter modelConverter) {
        this.statisticsRepository = statisticsRepository;
        this.countriesRepository = countriesRepository;
        this.modelConverter = modelConverter;
    }

    @Override
    public void increaseTime(String time) {
        Statistic statistic = statisticsRepository.findById(1).orElse(null);
        if (statistic != null) {
            statistic.setTime(Double.toString(Double.parseDouble(statistic.getTime()) + Double.parseDouble(time)));
            statisticsRepository.save(statistic);
        }
    }

    @Override
    public void increaseClicks(Integer clicks) {
        Statistic statistic = statisticsRepository.findById(1).orElse(null);
        if (statistic != null) {
            statistic.setClicks(statistic.getClicks() + clicks);
            statisticsRepository.save(statistic);
        }
    }

    @Override
    public void increaseUsers() {
        Statistic statistic = statisticsRepository.findById(1).orElse(null);
        if (statistic != null) {
            statistic.setUsers(statistic.getUsers() + 1);
            statisticsRepository.save(statistic);
        }
    }

    @Override
    public void increaseMessages() {
        Statistic statistic = statisticsRepository.findById(1).orElse(null);
        if (statistic != null) {
            statistic.setMessages(statistic.getMessages() + 1);
            statisticsRepository.save(statistic);
        }
    }

    @Override
    public void increaseFeedbacks() {
        Statistic statistic = statisticsRepository.findById(1).orElse(null);
        if (statistic != null) {
            statistic.setFeedbacks(statistic.getFeedbacks() + 1);
            statisticsRepository.save(statistic);
        }
    }

    @Override
    public StatisticDto getStatistics() {
        return modelConverter.convert(Objects.requireNonNull(statisticsRepository.findById(1).orElse(null)));
    }

    @Override
    public StatisticDto clearStatistic(String stateName) {
        Statistic statistic = statisticsRepository.findById(1).orElse(null);
        assert statistic != null;
        switch (stateName) {
            case "time":
                statistic.setTime("0");
                break;
            case "clicks":
                statistic.setClicks(0);
                break;
            case "users":
                statistic.setUsers(0);
                break;
            case "messages":
                statistic.setMessages(0);
                break;
            case "feedbacks":
                statistic.setFeedbacks(0);
                break;
        }
        statisticsRepository.save(statistic);
        return modelConverter.convert(statistic);
    }

    @Override
    public void addCountry(CountryDto countryDto) {
        Country country = countriesRepository.findByIp(countryDto.getIp());
        if (country == null) {
            countriesRepository.save(modelConverter.convert(countryDto));
        }
    }

    @Override
    public List<CountryDto> getCountries() {
        return modelConverter.covertCountriesListToDto(countriesRepository.findAll());
    }

    @Override
    @Transactional
    public void clearCountries() {
        countriesRepository.deleteAllBy();
    }
}
