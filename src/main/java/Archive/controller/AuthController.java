package Archive.controller;

import Archive.model.User;
import Archive.repository.UserRepository;
import Archive.service.UserService;
import Archive.web.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class AuthController {
    private final UserService userService;

    private final UserRepository userRepository;

    public AuthController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication.isAuthenticated();
        model.addAttribute("isAuthenticated", isAuthenticated);

        if(principal != null) {
            User user = userRepository.findByEmail(principal.getName());
            model.addAttribute("first_name", user.getFirstName());
        }

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", "409", "Пользователь с таким адресом уже существует!");
            return "register";
        }
        if (userDto.getFirstName().length() <2 || userDto.getFirstName().length() > 10) {
            result.rejectValue("firstName", "400", "Неверное имя пользователя (2-10 символов)");
            return "register";
        }
        if (userDto.getLastName().length() <2 || userDto.getLastName().length() > 10) {
            result.rejectValue("lastName", "400", "Неверное имя пользователя (2-10 символов)");
            return "register";
        }

        userService.saveUser(userDto);
        return "redirect:/login?success";
    }

}
