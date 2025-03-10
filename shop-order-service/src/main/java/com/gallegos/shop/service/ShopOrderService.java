package com.gallegos.shop.service;

import com.gallegos.shop.dto.ShopOrderDto;
import com.gallegos.shop.mapper.ShopOrderMapper;
import com.gallegos.shop.producer.ShopOrderProducer;
import org.springframework.stereotype.Service;

@Service
public class ShopOrderService {

    private ShopOrderMapper shopOrderMapper;
    private ShopOrderProducer shopOrderProducer;

    public ShopOrderService(ShopOrderMapper shopOrderMapper, ShopOrderProducer shopOrderProducer) {
        this.shopOrderMapper = shopOrderMapper;
        this.shopOrderProducer = shopOrderProducer;
    }

    public ShopOrderDto createNewOrder(ShopOrderDto shopOrderDto) {
        var shopOrderAvro = shopOrderMapper.mapToShopOrder(shopOrderDto);
        shopOrderDto.setId(shopOrderAvro.getId().toString());
        shopOrderProducer.sendMessage(shopOrderAvro);
        return shopOrderDto;
    }
}
