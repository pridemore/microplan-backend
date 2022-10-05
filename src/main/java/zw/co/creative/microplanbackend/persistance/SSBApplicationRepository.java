package zw.co.creative.microplanbackend.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.creative.microplanbackend.domain.SSBApplication;
import zw.co.creative.microplanbackend.enums.CreationStatus;

import java.util.List;

public interface SSBApplicationRepository extends JpaRepository<SSBApplication,Long> {

    List<SSBApplication> findAllByStatus(CreationStatus status);
    SSBApplication findById(long id);
}
