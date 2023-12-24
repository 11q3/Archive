package Archive.controller;

import Archive.model.ProfilePicture;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class ImageController {

    @GetMapping("/account")
    public String uploadFile(Model model) {
        model.addAttribute("profilePicture", new ProfilePicture());
        return "account";
    }

    @PostMapping("/account")
    public String uploadFile(@ModelAttribute("profilePicture") ProfilePicture profilePicture) throws IOException {

        String Path_Directory="/home/elevenqtwo/Desktop/Archive/src/main/resources/static/images/profilepictures";

        Files.copy(profilePicture.getFile().getInputStream(), Paths.get(Path_Directory + File.separator + profilePicture.getFile().getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

        System.out.println("success");

        return "account";
    }
}
