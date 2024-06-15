package com.toursiteback.controllers;

import com.toursiteback.dto.TourCollectionDto;
import com.toursiteback.service.tour.TourService;
import com.toursiteback.util.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tours/tour")
@CrossOrigin(origins = "*", exposedHeaders = "Authorization")
public class TourController {
    private final TourService tourService;
    private final JwtUtils jwtUtils;

    @Autowired
    public TourController(TourService tourService, JwtUtils jwtUtils) {
        this.tourService = tourService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<TourCollectionDto> getTours(@RequestParam String language) {
        return ResponseEntity.ok().body(tourService.getTours(language));
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<TourCollectionDto> getSearchedTours(@RequestParam String prefix) {
        return ResponseEntity.ok().body(tourService.getSearchedTours(prefix));
    }

    @GetMapping("/token")
    public ResponseEntity<Void> generateToken(HttpServletResponse response) {
        response.addHeader(jwtUtils.JWT_HEADER, jwtUtils.JWT_PREFIX + jwtUtils.generateToken("tempUser"));
        return ResponseEntity.ok().build();
    }
}
