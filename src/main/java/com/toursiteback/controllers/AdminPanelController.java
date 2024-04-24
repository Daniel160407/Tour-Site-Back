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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
                           @RequestPart("image") MultipartFile image) {

        return adminPanelService.addTour(
                TourWithImg.builder()
                        .name(title)
                        .description(description)
                        .direction(direction)
                        .image(image)
                        .build()
        );
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.OPTIONS).build();
    }
}
