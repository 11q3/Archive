package Archive.controller;

import Archive.model.ProfilePicture;
import Archive.model.User;
import Archive.repository.UserRepository;
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
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Objects;

@Controller
public class ImageController {
    private final UserRepository userRepository;

    public ImageController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/account")
    public String uploadFile(Model model, Principal principal) {
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
    public String uploadFile(@ModelAttribute("profilePicture") ProfilePicture profilePicture, Principal principal) throws IOException {


        User user = userRepository.findByEmail(principal.getName());

        String userId = user.getId().toString();
        String fileName = userId + "." + Objects.requireNonNull(profilePicture.getFile().getContentType()).split("/")[1];
        String Path_Directory="/home/elevenqtwo/Desktop/Archive/src/main/resources/static/images/profilepictures";

        BufferedImage image = ImageIO.read(profilePicture.getFile().getInputStream());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", byteArrayOutputStream);

        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray())) {
            Files.copy(
                    byteArrayInputStream,
                    Paths.get(
                            Path_Directory +
                                    File.separator +
                                    fileName),
                    StandardCopyOption.REPLACE_EXISTING);
        }

        user.setProfilePicture("static/images/profilepictures" + '/' + fileName);
        userRepository.save(user);

        return "redirect:/account";
    }
}
