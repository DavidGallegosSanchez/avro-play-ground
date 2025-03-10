package com.gallegos.shop.controller;

import com.gallegos.shop.dto.ShopOrderDto;
import com.gallegos.shop.service.ShopOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class ShopOrderController {

    private ShopOrderService shopOrderService;

    public ShopOrderController(ShopOrderService shopOrderService) {
        this.shopOrderService = shopOrderService;
    }

    @PostMapping("createOrder")
    public ResponseEntity<ShopOrderDto> createOrder(@RequestBody ShopOrderDto shopOrderDto) {
        log.info("Recived the request to create the order : {}", shopOrderDto);
        shopOrderService.createNewOrder(shopOrderDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(shopOrderDto);
    }
}
