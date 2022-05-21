package com.admin.database.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
    @GetMapping("/")
    public String hello() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {return "auth/login";}
}
