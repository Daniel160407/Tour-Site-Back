package com.toursiteback.controllers;

import com.toursiteback.dto.TourCollectionDto;
import com.toursiteback.service.tour.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tours/tour")
@CrossOrigin(origins = "*", exposedHeaders = "Authorization")
public class TourController {
    private final TourService tourService;

    @Autowired
    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping
    public ResponseEntity<TourCollectionDto> getTours(@RequestParam String language) {
        return ResponseEntity.ok().body(tourService.getTours(language));
    }

    @GetMapping("/search")
    public ResponseEntity<TourCollectionDto> getSearchedTours(@RequestParam String prefix) {
        return ResponseEntity.ok().body(tourService.getSearchedTours(prefix));
    }
}
