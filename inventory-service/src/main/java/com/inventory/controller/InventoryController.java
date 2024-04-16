package com.inventory.controller;

import com.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean inInStock(@RequestParam String skuCode, @RequestParam Integer quantity){
       log.info("*************Stock request for *************** " + skuCode);
        return service.isInStock(skuCode,quantity);
    }
}
