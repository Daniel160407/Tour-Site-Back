package com.toursiteback.controllers;

import com.toursiteback.dto.TourCollectionDto;
import com.toursiteback.service.tour.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tours/tour")
@CrossOrigin(origins = "*")
public class TourController {
    private final TourService tourService;

    @Autowired
    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping
    @ResponseBody
    public TourCollectionDto getTours(@RequestParam String language) {
        return tourService.getTours(language);
    }

    @GetMapping("/search")
    @ResponseBody
    public TourCollectionDto getSearchedTours(@RequestParam String prefix) {
        return tourService.getSearchedTours(prefix);
    }
}
