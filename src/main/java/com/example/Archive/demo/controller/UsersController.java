package com.example.Archive.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {
    @GetMapping("/register")
    public String getRegisterPage() {
        return "registration_page";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login_page";
    }

    @GetMapping("/docks")
    public String getDocksPage() {
        return "docks_page";
    }

    @GetMapping("/starting_page")
    public String getStartingPage() {
        return "index";
    }
}
