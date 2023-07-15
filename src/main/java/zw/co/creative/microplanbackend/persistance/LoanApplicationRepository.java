package zw.co.creative.microplanbackend.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zw.co.creative.microplanbackend.domain.LoanApplication;
import zw.co.creative.microplanbackend.domain.dto.LoanApplicationDTO;
import zw.co.creative.microplanbackend.enums.CreationStatus;

import java.util.List;
import java.util.Optional;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {

    List<LoanApplication> findByAgentIdAndStatus(Long agent_id, CreationStatus status);

    Optional<LoanApplication> findByUniqueRefAndAgentId(String uniqref, Long agentId);

    @Query(value = "select * from loanapplication l order by l.dateCreated desc", nativeQuery = true)
    List<LoanApplication> getAllLoans();




}
