package zw.co.creative.microplanbackend.service;

import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.dto.ProductDto;

public interface ProductService {
    CommonResponse createProduct(ProductDto productDto);
    CommonResponse getAllProducts();

}
