package com.toursiteback.messenger.controller;

import com.toursiteback.messenger.dto.UserDto;
import com.toursiteback.messenger.service.users.UserServiceImpl;
import com.toursiteback.service.exception.UserNotAccessibleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tours/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
        try {
            return ResponseEntity.ok().body(userService.getUserByEmail(email));
        } catch (UserNotAccessibleException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
