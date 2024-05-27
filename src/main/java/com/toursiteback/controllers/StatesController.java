package com.toursiteback.controllers;

import com.toursiteback.dto.CountryDto;
import com.toursiteback.dto.StateDto;
import com.toursiteback.model.Country;
import com.toursiteback.service.states.StatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/states")
@CrossOrigin(origins = "*")
public class StatesController {
    private final StatesService statesService;

    @Autowired
    public StatesController(StatesService statesService) {
        this.statesService = statesService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<StateDto> getStates() {
        return ResponseEntity.ok().body(statesService.getStates());
    }

    @GetMapping("/countries")
    @ResponseBody
    public ResponseEntity<List<CountryDto>> getCountries() {
        return ResponseEntity.ok().body(statesService.getCountries());
    }

    @PostMapping
    public ResponseEntity<Void> AddCountry(@RequestBody CountryDto countryDto) {
        statesService.addCountry(countryDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateStates(@RequestParam String time, @RequestParam Integer clicks) {
        statesService.increaseTime(time);
        statesService.increaseClicks(clicks);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/countries")
    public ResponseEntity<Void> clearCountries() {
        statesService.clearCountries();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<StateDto> clearState(@RequestParam String state) {
        return ResponseEntity.ok().body(statesService.clearState(state));
    }
}
