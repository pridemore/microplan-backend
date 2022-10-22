package zw.co.creative.microplanbackend.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.creative.microplanbackend.domain.Loan;

public interface LoanRepository extends JpaRepository<Loan,Long> {
}
