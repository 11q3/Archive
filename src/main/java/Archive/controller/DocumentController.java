package Archive.controller;

import Archive.model.Document;
import Archive.model.User;
import Archive.repository.UserRepository;
import Archive.repository.DocumentRepository;
import Archive.service.DocumentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class DocumentController {
    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;

    private final DocumentService documentService;


    public DocumentController(UserRepository userRepository, DocumentService documentService, DocumentRepository documentRepository) {
        this.userRepository = userRepository;
        this.documentService = documentService;
        this.documentRepository = documentRepository;
    }


    @GetMapping("/docks")
    public String showDocksPage(Model model, Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication.isAuthenticated();
        model.addAttribute("isAuthenticated", isAuthenticated);

        if(principal != null) {
            User user = userRepository.findByEmail(principal.getName());
            model.addAttribute("first_name", user.getFirstName());
        }

        List<Document> documents = documentRepository.findAll();
        model.addAttribute("documents", documents);
        return "docks";
    }

    @PostMapping("/docks")
    public String uploadDocument(@ModelAttribute("file") MultipartFile file) throws IOException {
        return documentService.saveDocument(file);
    }
}
