package zw.co.creative.microplanbackend.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.common.response.ProductResponse;
import zw.co.creative.microplanbackend.domain.Product;
import zw.co.creative.microplanbackend.domain.dto.ProductDto;
import zw.co.creative.microplanbackend.enums.CreationStatus;
import zw.co.creative.microplanbackend.persistance.ProductRepository;
import zw.co.creative.microplanbackend.service.ProductService;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProductServiceImpl  implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public CommonResponse createProduct(ProductDto productDto) {
        log.info("ProductDto --------: {}",productDto);
        Product productToBeSaved=Product.builder()
                .productName(productDto.getProductName())
                .price(productDto.getPrice())
                .status(CreationStatus.ACTIVE)
                .dateCreated(OffsetDateTime.now())
                .lastUpdated(OffsetDateTime.now())
                .build();
        Product savedProduct = productRepository.save(productToBeSaved);

        return new CommonResponse().buildSuccessResponse("Success", savedProduct);
    }

    @Override
    public CommonResponse getAllProducts() {
        List<ProductResponse> productList=new ArrayList<>();
        List<Product> allByStatus = productRepository.findAllByStatus(CreationStatus.ACTIVE);
        for (Product product:allByStatus){
            ProductResponse productResponse=ProductResponse.builder()
                    .name(product.getProductName())
                    .price(product.getPrice())
                    .build();
            productList.add(productResponse);
        }
        return new CommonResponse().buildSuccessResponse("Success", productList);
    }
}
