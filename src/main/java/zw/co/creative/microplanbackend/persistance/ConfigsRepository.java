package zw.co.creative.microplanbackend.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.creative.microplanbackend.domain.Configs;

public interface ConfigsRepository extends JpaRepository<Configs,Long> {
}
