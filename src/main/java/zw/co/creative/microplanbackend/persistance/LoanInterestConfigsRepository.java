package zw.co.creative.microplanbackend.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.creative.microplanbackend.domain.LoanInterestConfigs;
import zw.co.creative.microplanbackend.enums.CreationStatus;

public interface LoanInterestConfigsRepository extends JpaRepository<LoanInterestConfigs,Long> {

    LoanInterestConfigs findFirstByStatus(CreationStatus status);
}
