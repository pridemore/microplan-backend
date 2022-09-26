package zw.co.creative.microplanbackend.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.creative.microplanbackend.domain.CreativeUser;
import zw.co.creative.microplanbackend.enums.CreationStatus;

import java.util.Optional;

public interface CreativeUserRepository extends JpaRepository<CreativeUser,Long> {
    Optional<CreativeUser> findCreativeUserByEmailAndStatus(String email, CreationStatus status);
}
