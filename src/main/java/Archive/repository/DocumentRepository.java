package Archive.repository;

import Archive.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}