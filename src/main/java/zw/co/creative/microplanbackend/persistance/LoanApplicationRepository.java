package zw.co.creative.microplanbackend.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.creative.microplanbackend.domain.LoanApplication;
import zw.co.creative.microplanbackend.enums.CreationStatus;

import java.util.List;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication,Long> {

    List<LoanApplication>findByAgentIdAndStatus(Long agent_id,CreationStatus status);
}
