package com.toursiteback.controllers;

import com.toursiteback.dto.CountryDto;
import com.toursiteback.dto.StatisticDto;
import com.toursiteback.service.states.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/statistics")
@CrossOrigin(origins = "*")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<StatisticDto> getStatistics() {
        return ResponseEntity.ok().body(statisticsService.getStatistics());
    }

    @GetMapping("/countries")
    @ResponseBody
    public ResponseEntity<List<CountryDto>> getCountries() {
        return ResponseEntity.ok().body(statisticsService.getCountries());
    }

    @PostMapping
    public ResponseEntity<Void> AddCountry(@RequestBody CountryDto countryDto) {
        statisticsService.addCountry(countryDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateStatistics(@RequestParam String time, @RequestParam Integer clicks) {
        statisticsService.increaseTime(time);
        statisticsService.increaseClicks(clicks);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/countries")
    public ResponseEntity<Void> clearCountries() {
        statisticsService.clearCountries();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<StatisticDto> clearStatistics(@RequestParam String statistic) {
        return ResponseEntity.ok().body(statisticsService.clearStatistic(statistic));
    }
}
