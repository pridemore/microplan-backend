package zw.co.creative.microplanbackend.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.creative.microplanbackend.domain.Role;
import zw.co.creative.microplanbackend.enums.CreationStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
  /*  Optional<Role>findRoleByName(String name);
    List<Role> findRolesByStatus(CreationStatus status);
    */
  Optional<Role>findRoleByIdAndStatus(Long id,String status);
    List<Role> findAllByStatus(String status);

    Optional<Role> findByName(String name);

    Role findById(long id);
}
