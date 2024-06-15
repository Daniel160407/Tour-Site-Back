package com.toursiteback.controllers;

import com.toursiteback.dto.TourDto;
import com.toursiteback.model.Admin;
import com.toursiteback.model.TourWithImg;
import com.toursiteback.service.adminPanel.AdminPanelService;
import com.toursiteback.service.exception.InvalidEmailOrPasswordException;
import com.toursiteback.util.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/tours/adminpanel")
@CrossOrigin(origins = "*", exposedHeaders = "Authorization")
public class AdminPanelController {
    private final AdminPanelService adminPanelService;
    private final JwtUtils jwtUtils;

    @Autowired
    public AdminPanelController(AdminPanelService adminPanelService, JwtUtils jwtUtils) {
        this.adminPanelService = adminPanelService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Void> login(@RequestBody Admin admin, HttpServletResponse response) {
        try {
            adminPanelService.login(admin);
            val token = jwtUtils.generateToken(admin.getEmail());
            response.addHeader(jwtUtils.JWT_HEADER, jwtUtils.JWT_PREFIX + token);
            return ResponseEntity.ok().build();
        } catch (InvalidEmailOrPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<TourDto> addTour(@RequestPart("title") String title,
                                           @RequestPart("description") String description,
                                           @RequestPart("direction") String direction,
                                           @RequestPart("duration") String duration,
                                           @RequestPart("history") String history,
                                           @RequestPart("requirements") String requirements,
                                           @RequestPart("price") String price,
                                           @RequestPart("language") String language,
                                           @RequestPart("image") MultipartFile image) {

        return ResponseEntity.status(HttpStatus.CREATED).body(adminPanelService.addTour(
                TourWithImg.builder()
                        .name(title)
                        .description(description)
                        .direction(direction)
                        .duration(duration)
                        .history(history)
                        .requirements(requirements)
                        .price(price)
                        .language(language)
                        .image(image)
                        .build()
        ));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<TourDto> updateTour(@RequestBody TourDto tour) {
        return ResponseEntity.ok().body(adminPanelService.updateTour(tour));
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<TourDto> deleteTour(@RequestParam String name) {
        return ResponseEntity.ok().body(adminPanelService.deleteTour(name));
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS).build();
    }
}
