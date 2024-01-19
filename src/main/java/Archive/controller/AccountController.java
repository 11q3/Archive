package Archive.controller;

import Archive.model.Document;
import Archive.model.ProfilePicture;
import Archive.model.User;
import Archive.repository.UserRepository;
import Archive.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Objects;

@Controller
public class AccountController {
    private final UserRepository userRepository;
    private final UserService userService;

    public AccountController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/account")
    public String showAccount(Model model, Principal principal) {
        if(principal != null) {
            User user = userRepository.findByEmail(principal.getName());
            model.addAttribute("first_name", user.getFirstName());
            model.addAttribute("last_name", user.getLastName());
            model.addAttribute("email", user.getEmail());

            model.addAttribute("profilePicture", user.getProfilePicture());
        }
        return "account";
    }

    @PostMapping("/account")
    public String uploadProfilePicture(@ModelAttribute("profilePicture") ProfilePicture profilePicture, Principal principal) throws IOException {
        return userService.uploadProfilePicture(profilePicture, principal);
    }
}
