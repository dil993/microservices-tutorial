package com.product.service;


import com.product.dto.ProductRequest;
import com.product.dto.ProductResponse;
import com.product.model.Product;
import com.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest request)
    {
        Product product= Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .build();

        productRepository.save(product);
        log.info("Product saved successfully.....");

        return new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice());
    }

    public List<ProductResponse> getAllProducts(){

     return  productRepository.findAll().stream()
              .map(product -> new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice()))
             .toList();
    }

}
