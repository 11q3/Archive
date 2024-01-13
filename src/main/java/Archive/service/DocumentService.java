package Archive.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface DocumentService {
    String saveDocument(MultipartFile file) throws IOException;

    boolean documentExists(String fileName);

    void deleteDocument(String fileName);
}
