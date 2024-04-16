package com.order.service;


import com.order.client.InventoryClient;
import com.order.dto.OrderRequest;
import com.order.entity.Order;
import com.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository repository;

    private final InventoryClient client;
    public void placeOrder(OrderRequest request) {
        boolean stockPresent = client.isInStock(request.skuCode(),request.quantity());
        log.info("Stock is available ? "+ stockPresent);
        if(stockPresent) {
            Order order = mapToOrder(request);
            repository.save(order);
        }
        else {
            throw new RuntimeException("Product with skuCode "+request.skuCode()+" stock not available....");
        }
    }

    private static Order mapToOrder(OrderRequest request) {

        Order order= new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(request.price());
        order.setQuantity(request.quantity());
        order.setSkuCode(request.skuCode());
        return order;
    }
}
