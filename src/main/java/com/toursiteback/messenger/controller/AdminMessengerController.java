package com.toursiteback.messenger.controller;

import com.toursiteback.messenger.dto.MessageDto;
import com.toursiteback.messenger.dto.UserDto;
import com.toursiteback.messenger.service.admin.AdminMessengerServiceImpl;
import com.toursiteback.messenger.service.message.MessengerServiceImpl;
import com.toursiteback.messenger.service.users.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tours/adminpanel/messenger")
@CrossOrigin(origins = "*")
public class AdminMessengerController {
    private final AdminMessengerServiceImpl adminMessengerService;
    private final MessengerServiceImpl messengerService;
    private final UserServiceImpl userService;

    @Autowired
    public AdminMessengerController(AdminMessengerServiceImpl adminMessengerService, MessengerServiceImpl messengerService, UserServiceImpl userService) {
        this.adminMessengerService = adminMessengerService;
        this.messengerService = messengerService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok().body(adminMessengerService.getUsers());
    }

    @GetMapping("/messages")
    public ResponseEntity<List<MessageDto>> getMessages(@RequestParam String email) {
        return ResponseEntity.ok().body(messengerService.getMessages(email));
    }

    @DeleteMapping
    public ResponseEntity<List<UserDto>> deleteUser(@RequestParam String email) {
        messengerService.deleteMessages(email);
        adminMessengerService.deleteUser(email);
        userService.decreaseUserPositions();
        return ResponseEntity.ok().body(adminMessengerService.getUsers());
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS).build();
    }
}
