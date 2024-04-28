package com.toursiteback.messenger.controller;

import com.toursiteback.messenger.model.User;
import com.toursiteback.messenger.service.admin.AdminMessengerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/tours/adminpanel/messenger")
public class AdminMessengerController {
    private AdminMessengerServiceImpl messengerService;

    @Autowired
    public void setMessengerService(AdminMessengerServiceImpl messengerService) {
        this.messengerService = messengerService;
    }

    @GetMapping
    @ResponseBody
    public List<User> getUsers() {
        return messengerService.getUsers();
    }
}
