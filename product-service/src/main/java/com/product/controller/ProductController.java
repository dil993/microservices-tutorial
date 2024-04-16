package com.product.controller;

import com.product.dto.ProductRequest;
import com.product.dto.ProductResponse;
import com.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    @Autowired
    private final ProductService service;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        log.info("****************************************************CONTROLLER Create Product*********************");
       return service.createProduct(productRequest);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts()
    {
        log.info("Products fetched successfully........");
        return service.getAllProducts();

    }
}
