package com.toursiteback.messenger.controller;

import com.toursiteback.messenger.dto.UserDto;
import com.toursiteback.messenger.service.users.UserServiceImpl;
import com.toursiteback.service.exception.InvalidEmailOrPasswordException;
import com.toursiteback.service.states.StatisticsService;
import com.toursiteback.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tours/messenger")
@CrossOrigin(origins = "*")
public class MessengerController {

    private final UserServiceImpl userService;
    private final StatisticsService statisticsService;
    private final JwtUtils jwtUtils;

    @Autowired
    public MessengerController(UserServiceImpl userService, StatisticsService statisticsService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.statisticsService = statisticsService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto user) {
        try {
            if (!user.getEmail().isEmpty()) {
                statisticsService.increaseUsers(user.getEmail());
            }
            UserDto loggedInUser = userService.login(user);
            String token = jwtUtils.generateToken(loggedInUser.getEmail());
            return ResponseEntity.ok(token);
        } catch (InvalidEmailOrPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
