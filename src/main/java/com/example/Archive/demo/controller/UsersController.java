package com.example.Archive.demo.controller;

import com.example.Archive.demo.model.UsersModel;
import com.example.Archive.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {
    private final UsersService usersService;
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UsersModel());
        return "registration_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UsersModel());
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

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel) {
        System.out.println("register request:" + usersModel);
        UsersModel registeredUser = usersService.registerUser(usersModel.getLogin(),
                                                              usersModel.getPassword(),
                                                              usersModel.getEmail());

        return registeredUser == null ? "error_page" : "redirect:/login";

    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model) {
        System.out.println("login request:" + usersModel);
        UsersModel authenticated = usersService.authenticate(usersModel.getLogin(),
                                                             usersModel.getPassword());
        if(authenticated != null) {
            model.addAttribute("userLogin", authenticated.getLogin());
            return "account_page";
        }
        else return "error_page";

    }
}