package com.toursiteback.messenger.controller;

import com.toursiteback.messenger.dto.UserDto;
import com.toursiteback.messenger.service.users.MessengerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tours/messenger")
public class MessengerController {

    private final MessengerServiceImpl messengerService;

    @Autowired
    public MessengerController(MessengerServiceImpl messengerService) {
        this.messengerService = messengerService;
    }

    @PostMapping("/login")
    @ResponseBody
    public UserDto login(@RequestBody UserDto user) {
        return messengerService.login(user);
    }
}
