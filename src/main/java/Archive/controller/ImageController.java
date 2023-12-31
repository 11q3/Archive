package Archive.controller;

import Archive.model.ProfilePicture;
import Archive.model.User;
import Archive.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

@Controller
public class ImageController {
    private final UserRepository userRepository;

    public ImageController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/account")
    public String uploadFile(Model model, Principal principal) {
        if(principal !=null) {
            User user = userRepository.findByEmail(principal.getName());
            model.addAttribute("first_name", user.getFirstName());
            model.addAttribute("last_name", user.getLastName());
            model.addAttribute("email", user.getEmail());

            model.addAttribute("profilePicture", user.getProfilePicture());
        }
        return "account";
    }

    @PostMapping("/account")
    public String uploadFile(@ModelAttribute("profilePicture") ProfilePicture profilePicture, Principal principal) throws IOException {
        User user = userRepository.findByEmail(principal.getName());

        String fileName = profilePicture.getFile().getOriginalFilename();
        String Path_Directory="/home/elevenqtwo/Desktop/Archive/src/main/resources/static/images/profilepictures";

        Files.copy(
                profilePicture.getFile().getInputStream(),
                Paths.get(
                        Path_Directory +
                        File.separator +
                        profilePicture.getFile().getOriginalFilename()),
                        StandardCopyOption.REPLACE_EXISTING);

        user.setProfilePicture("static/images/profilepictures" + '/' + fileName);
        userRepository.save(user);



        return "redirect:/account";
    }


}
