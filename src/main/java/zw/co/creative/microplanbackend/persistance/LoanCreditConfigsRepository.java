package zw.co.creative.microplanbackend.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.creative.microplanbackend.domain.LoanCreditConfigs;
import zw.co.creative.microplanbackend.enums.CreationStatus;

public interface LoanCreditConfigsRepository extends JpaRepository<LoanCreditConfigs,Long> {

    LoanCreditConfigs findFirstByStatus(CreationStatus status);
}
