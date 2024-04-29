package com.toursiteback.messenger.controller;

import com.toursiteback.messenger.dto.MessageDto;
import com.toursiteback.messenger.dto.UserDto;
import com.toursiteback.messenger.service.admin.AdminMessengerServiceImpl;
import com.toursiteback.messenger.service.message.MessengerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<UserDto> getUsers() {
        return adminMessengerService.getUsers();
    }

    @GetMapping("/messages")
    @ResponseBody
    public List<MessageDto> getMessages(@RequestParam String email) {
        return messengerService.getMessages(email);
    }
}
