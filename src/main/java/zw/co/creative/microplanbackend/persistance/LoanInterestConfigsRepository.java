package zw.co.creative.microplanbackend.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.creative.microplanbackend.domain.LoanInterestConfigs;

public interface LoanInterestConfigsRepository extends JpaRepository<LoanInterestConfigs,Long> {
}
