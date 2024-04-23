package com.toursiteback.controllers;

import com.toursiteback.dto.TourDto;
import com.toursiteback.model.Admin;
import com.toursiteback.service.adminPanel.AdminPanelService;
import com.toursiteback.service.exception.InvalidEmailOrPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public TourDto addTour(@RequestBody TourDto tourDto) {
        return adminPanelService.addTour(tourDto);
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.OPTIONS).build();
    }
}
