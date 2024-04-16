package com.order.controller;

import com.order.dto.OrderRequest;
import com.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public String placeOrder(@RequestBody OrderRequest request)
    {
        service.placeOrder(request);
        return "Order placed successfully......";
    }
}
