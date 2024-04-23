package com.toursiteback.controllers;

import com.toursiteback.dto.TourDto;
import com.toursiteback.service.adminPanel.AdminPanelService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    @ResponseBody
    public TourDto addTour(@RequestBody TourDto tourDto) {
        return adminPanelService.addTour(tourDto);
    }
}
