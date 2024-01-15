package Archive.service.impl;

import Archive.model.Document;
import Archive.repository.DocumentRepository;
import Archive.service.DocumentService;

import java.io.IOException;
import java.nio.file.*;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public String saveDocument(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        if (documentRepository.findByName(fileName).isPresent()) {
            return "redirect:/docks?fileAlreadyExists";
        }

        Path targetLocation = Paths.get(Archive.util.Paths.DOCUMENTS.getPath() + fileName);

        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        Document document = new Document();
        document.setFilePath(targetLocation + fileName);
        document.setName(fileName);
        documentRepository.save(document);

        return "redirect:/docks?" + (long) documentRepository.findAll().size();
    }

    @Override
    public void deleteDocument(String fileName) {
        Optional<Document> documentOptional = documentRepository.findByName(fileName);

        if (documentOptional.isEmpty()) {
            System.out.println("Document with name " + fileName + " not found");
            return;
        }

        Document document = documentOptional.get();
        try {
            Files.deleteIfExists(Paths.get(document.getFilePath()));
        } catch (IOException e) {
            System.out.println("Failed to delete document " + fileName + ": " + e.getMessage());
        }
        documentRepository.delete(document);
    }
}
