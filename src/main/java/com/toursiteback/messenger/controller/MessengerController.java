package com.toursiteback.messenger.controller;

import com.toursiteback.messenger.dto.UserDto;
import com.toursiteback.messenger.service.users.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserDto login(@RequestBody UserDto user) {
        return messengerService.login(user);
    }
}
