package zw.co.creative.microplanbackend.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zw.co.creative.microplanbackend.domain.DocumentUpload;

import java.util.List;

public interface DocumentsUploadRepository extends JpaRepository<DocumentUpload,Long> {
DocumentUpload findByLoanUniqueRef(String uniqRef);

@Query(value = "SELECT * FROM creative_microplan_db.documentupload where loanUniqueRef=:uniqRef",nativeQuery = true)
List<DocumentUpload>findAllByLoanUniqueRef(String uniqRef);
}
