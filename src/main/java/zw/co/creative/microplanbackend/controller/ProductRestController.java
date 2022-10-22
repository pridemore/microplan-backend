package zw.co.creative.microplanbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.dto.ProductDto;
import zw.co.creative.microplanbackend.service.ProductService;

import java.awt.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductRestController {

    private final ProductService productService;

    @PostMapping("/create")
    public CommonResponse createProduct(@RequestBody ProductDto productDto) {
        CommonResponse product = productService.createProduct(productDto);
        return product;
    }

    @PostMapping("/update/{id}")
    public CommonResponse updateProduct(@PathVariable("id") Long id,@RequestBody ProductDto productDto) {
        CommonResponse product = productService.updateProduct(id,productDto);
        return product;
    }

    @GetMapping(value = "/getAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllProducts() {
        CommonResponse allProducts = productService.getAllProducts();
        return allProducts;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResponse deleteProduct(@PathVariable("id") Long id) {
        CommonResponse product = productService.deleteProduct(id);
        return product;
    }

}
