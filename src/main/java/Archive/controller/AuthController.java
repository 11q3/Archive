package Archive.controller;

import Archive.model.User;
import Archive.service.UserService;
import Archive.web.dto.UserRegistrationDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        UserRegistrationDto user = new UserRegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserRegistrationDto userRegistrationDto,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userRegistrationDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "Пользователь с таким адресом уже существует!");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", userRegistrationDto);
            return "register";
        }

        userService.saveUser(userRegistrationDto);
        return "redirect:/register?success";
    }

    @GetMapping("/docks")
    public String showDocksPage() {
        return "docks";
    }
}
