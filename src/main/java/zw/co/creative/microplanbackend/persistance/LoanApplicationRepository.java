package zw.co.creative.microplanbackend.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.creative.microplanbackend.domain.LoanApplication;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication,Long> {
}
