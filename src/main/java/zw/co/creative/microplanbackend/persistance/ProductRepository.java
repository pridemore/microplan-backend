package zw.co.creative.microplanbackend.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.creative.microplanbackend.domain.Product;
import zw.co.creative.microplanbackend.enums.CreationStatus;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductNameAndStatus(String productName, CreationStatus status);

    List<Product> findAllByStatus(CreationStatus Status);

    Optional<Product> findByIdAndStatus(Long id, CreationStatus status);
}
