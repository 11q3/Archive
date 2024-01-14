package Archive.controller;

import Archive.model.Document;
import Archive.model.User;
import Archive.repository.UserRepository;
import Archive.repository.DocumentRepository;
import Archive.service.DocumentService;
import Archive.util.Paths;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;

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
    public String showDocksPage(Model model, Principal principal, @RequestParam(value = "page", defaultValue = "0") int page) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication.isAuthenticated();
        model.addAttribute("isAuthenticated", isAuthenticated);

        Pageable pageable = PageRequest.of(page, 16); // 8 documents per page
        Page<Document> documentsPage = documentRepository.findAll(pageable);
        model.addAttribute("documents", documentsPage);

        if(principal != null) {
            User user = userRepository.findByEmail(principal.getName());
            model.addAttribute("first_name", user.getFirstName());
        }

        return "docks";
    }

    @PostMapping("/docks")
    public String uploadDocument(@ModelAttribute("file") MultipartFile file) throws IOException {
        return documentService.saveDocument(file);
    }

    @GetMapping("/downloadDocument")
    public ResponseEntity<InputStreamResource> downloadDocument(@RequestParam String fileName) throws FileNotFoundException {
        String uploadedFilesDir = Paths.DOCUMENTS.getPath();

        File file = new File(uploadedFilesDir, fileName);

        if (!file.exists())  return ResponseEntity.notFound().build();


        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + fileName);
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @PostMapping("/deleteDocument")
    public String deleteDocument(@RequestParam String fileName, RedirectAttributes redirectAttributes) {
        documentService.deleteDocument(fileName);
        redirectAttributes.addFlashAttribute("message", "Document deleted successfully!");
        return "redirect:/docks";
    }
}
