package zw.co.creative.microplanbackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.common.response.ProductResponse;
import zw.co.creative.microplanbackend.domain.Product;
import zw.co.creative.microplanbackend.domain.dto.ProductDto;
import zw.co.creative.microplanbackend.enums.CreationStatus;
import zw.co.creative.microplanbackend.persistance.ProductRepository;
import zw.co.creative.microplanbackend.service.ProductService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Controller
@AllArgsConstructor
@RequestMapping("/products")
@Slf4j
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    private RestTemplate restTemplate;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createProduct(Model model) {
        return "pages/products/create";
    }

    @PostMapping("/add")
    public String addProduct(@Validated ProductDto productDto, Model model) {

        if (Objects.nonNull(productDto)) {
            String url = "http://localhost:8020/api/product/create";
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
            final URI resultUrl = builder.build().toUri();
            ResponseEntity<Object> exchange = restTemplate.postForEntity(resultUrl, productDto, Object.class);
            log.info("Saved Product response-----: {}", exchange);
            return "pages/products/view";
        } else {
            return "pages/products/create";
        }
    }


    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewAllProducts(Model model) {
        String url = "http://localhost:8020/api/product/getAll";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        final URI resultUrl = builder.build().toUri();
        ResponseEntity<CommonResponse> exchange = restTemplate.exchange(resultUrl, HttpMethod.GET, null, CommonResponse.class);
        //log.info("List of Products response-----: {}", exchange);
        List<ProductResponse>productList=(ArrayList<ProductResponse>)exchange.getBody().getResult();
        log.info("List of Products response-----: {}", productList);
        model.addAttribute("ProductList",productList);

        return "pages/products/view";
    }

    @GetMapping("/view/{id}")
    public String showProductPage(Model model, @PathVariable("id") Long id) {

        final Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {

            //Other attributes
            model.addAttribute("product", optionalProduct.get());
            model.addAttribute("statuses", CreationStatus.values());

            return "pages/products/edit";
        } else {
            return "pages/products/view";
        }
    }

    @PostMapping("/edit/{id}")
    public String editProductPage(Model model, @PathVariable("id") Long id, @Validated ProductDto productDto, Errors errors) {
        log.info("Update Creativeser Dto-----------: {}", productDto);
        if(errors.hasErrors()){
            return "redirect:products/view/"+id;
        }
        CommonResponse updateProduct = productService.updateProduct(id,productDto);
        log.info("Update Product response-----: {}",updateProduct);
        return "redirect:/products/view/"+id;
    }

    @GetMapping("/delete/{id}")
    public String deleteProductPage(Model model, @PathVariable("id") Long id) {
        log.info("Delete Product id-----------: {}",id);

        productService.deleteProduct(id);
        return "redirect:/products/view";
    }


}
