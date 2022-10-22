package zw.co.creative.microplanbackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.Product;
import zw.co.creative.microplanbackend.domain.dto.ProductDto;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Controller
@AllArgsConstructor
@RequestMapping("/products")
@Slf4j
public class ProductController {
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
        log.info("List of Products response-----: {}", exchange);

        model.addAttribute("ProductList",exchange.getBody().getResult());

        return "pages/products/view";
    }


}
