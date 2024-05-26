package com.toursiteback.messenger.controller;

import com.toursiteback.messenger.dto.MessageDto;
import com.toursiteback.messenger.dto.UserDto;
import com.toursiteback.messenger.service.admin.AdminMessengerServiceImpl;
import com.toursiteback.messenger.service.message.MessengerServiceImpl;
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

    @Autowired
    public AdminMessengerController(AdminMessengerServiceImpl adminMessengerService, MessengerServiceImpl messengerService) {
        this.adminMessengerService = adminMessengerService;
        this.messengerService = messengerService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok().body(adminMessengerService.getUsers());
    }

    @GetMapping("/messages")
    @ResponseBody
    public ResponseEntity<List<MessageDto>> getMessages(@RequestParam String email) {
        return ResponseEntity.ok().body(messengerService.getMessages(email));
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<List<UserDto>> deleteUser(@RequestParam String email) {
        messengerService.deleteMessages(email);
        adminMessengerService.deleteUser(email);
        return ResponseEntity.ok().body(adminMessengerService.getUsers());
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS).build();
    }
}
