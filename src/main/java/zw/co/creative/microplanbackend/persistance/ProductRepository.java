package zw.co.creative.microplanbackend.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.creative.microplanbackend.domain.Product;
import zw.co.creative.microplanbackend.enums.CreationStatus;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product>findAllByStatus(CreationStatus Status);
}
