package zw.co.creative.microplanbackend.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.creative.microplanbackend.domain.DocumentUpload;

public interface DocumentsUploadRepository extends JpaRepository<DocumentUpload,Long> {
}
