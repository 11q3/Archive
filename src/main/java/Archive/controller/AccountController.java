package Archive.controller;

import Archive.model.User;
import Archive.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;


@Controller
public class AccountController {
    private final UserRepository userRepository;
    public AccountController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/account")
    public String showAccount(Model model, Principal principal) {
        if(principal != null) {
            User user = userRepository.findByEmail(principal.getName());
            model.addAttribute("first_name", user.getFirstName());
            model.addAttribute("last_name", user.getLastName());
            model.addAttribute("email", user.getEmail());
        }
        return "account";
    }
}
