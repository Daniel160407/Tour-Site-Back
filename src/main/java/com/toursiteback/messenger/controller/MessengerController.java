package com.toursiteback.messenger.controller;

import com.toursiteback.messenger.dto.UserDto;
import com.toursiteback.messenger.service.users.UserServiceImpl;
import com.toursiteback.service.exception.InvalidEmailOrPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tours/messenger")
@CrossOrigin(origins = "*")
public class MessengerController {

    private final UserServiceImpl messengerService;

    @Autowired
    public MessengerController(UserServiceImpl messengerService) {
        this.messengerService = messengerService;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<UserDto> login(@RequestBody UserDto user) {
        try {
            return ResponseEntity.accepted().body(messengerService.login(user));
        } catch (InvalidEmailOrPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
