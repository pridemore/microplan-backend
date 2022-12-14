package zw.co.creative.microplanbackend.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.creative.microplanbackend.domain.CreativeUser;
import zw.co.creative.microplanbackend.enums.CreationStatus;

import java.util.List;
import java.util.Optional;

public interface CreativeUserRepository extends JpaRepository<CreativeUser,Long> {
    CreativeUser findCreativeUserByEmail(String email);
    Optional<CreativeUser> findCreativeUserByEmailAndStatus(String email, CreationStatus status);
    Optional<CreativeUser>findByIdAndStatus(Long id,CreationStatus status);
    CreativeUser findCreativeUserByResetPasswordToken(String resetPasswordToken);

    List<CreativeUser> findAllByStatusAndRole(CreationStatus status,String role);
}
