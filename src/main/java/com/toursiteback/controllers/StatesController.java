package com.toursiteback.controllers;

import com.toursiteback.dto.StateDto;
import com.toursiteback.service.states.StatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<StateDto> getStates() {
        return ResponseEntity.ok().body(statesService.getStates());
    }

    @PutMapping
    public ResponseEntity<Void> updateStates(@RequestParam String time, @RequestParam Integer clicks) {
        statesService.increaseTime(time);
        statesService.increaseClicks(clicks);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
