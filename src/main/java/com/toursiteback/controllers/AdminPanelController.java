package com.toursiteback.controllers;

import com.toursiteback.dto.TourDto;
import com.toursiteback.model.Admin;
import com.toursiteback.model.TourWithImg;
import com.toursiteback.service.adminPanel.AdminPanelService;
import com.toursiteback.service.exception.InvalidEmailOrPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/tours/adminpanel")
@CrossOrigin(origins = "*")
public class AdminPanelController {
    private final AdminPanelService adminPanelService;

    @Autowired
    public AdminPanelController(AdminPanelService adminPanelService) {
        this.adminPanelService = adminPanelService;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Void> login(@RequestBody Admin admin) {
        try {
            adminPanelService.login(admin);
            return ResponseEntity.ok().build();
        } catch (InvalidEmailOrPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping
    @ResponseBody
    public TourDto addTour(@RequestPart("title") String title,
                           @RequestPart("description") String description,
                           @RequestPart("direction") String direction,
                           @RequestPart("history") String history,
                           @RequestPart("price") String price,
                           @RequestPart("language") String language,
                           @RequestPart("image") MultipartFile image) {

        return adminPanelService.addTour(
                TourWithImg.builder()
                        .name(title)
                        .description(description)
                        .direction(direction)
                        .history(history)
                        .price(price)
                        .language(language)
                        .image(image)
                        .build()
        );
    }

    @PutMapping
    @ResponseBody
    public TourDto updateTour(@RequestBody TourDto tour) {
        return adminPanelService.updateTour(tour);
    }

    @DeleteMapping
    @ResponseBody
    public TourDto deleteTour(@RequestParam String name) {
        return adminPanelService.deleteTour(name);
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS).build();
    }
}
