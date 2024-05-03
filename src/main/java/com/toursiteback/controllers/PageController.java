package com.toursiteback.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class PageController {

    @GetMapping(value = "/tours")
    public String getToursPage() {
        return "index.html";
    }

    @GetMapping(value = "/adminpanel")
    public String getAdminPanelPage() {
        return "index.html";
    }

    @GetMapping(value = "{path:(?!messenger)[^\\.]*}")
    public String redirectToIndex() {
        return "index.html";
    }

}
