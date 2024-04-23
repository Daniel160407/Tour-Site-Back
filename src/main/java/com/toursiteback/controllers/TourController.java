package com.toursiteback.controllers;

import com.toursiteback.dto.TourCollectionDto;
import com.toursiteback.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public TourCollectionDto getTours() {
        return tourService.getTours();
    }
}
