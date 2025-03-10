package com.gallegos.shop.mapper;

import com.gallegos.shop.domain.generated.Client;
import com.gallegos.shop.domain.generated.Item;
import com.gallegos.shop.domain.generated.ShopOrder;
import com.gallegos.shop.dto.ClientDto;
import com.gallegos.shop.dto.ItemDto;
import com.gallegos.shop.dto.ShopOrderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ShopOrderMapper {
    public ShopOrder mapToShopOrder(ShopOrderDto shopOrderDto) {
        return ShopOrder.newBuilder()
                .setId(UUID.randomUUID())
                .setNameShop(shopOrderDto.getNameShop())
                .setAddress(shopOrderDto.getAddress())
                .setCity(shopOrderDto.getCity())
                .setCp(shopOrderDto.getCp())
                .setClient(buildClient(shopOrderDto.getClient()))
                .setItems(buildItems(shopOrderDto.getItems()))
                .setTotalPrice(getTotalPrice(shopOrderDto.getItems()))
                .build();
    }

    private float getTotalPrice(List<ItemDto> items) {
        return items.stream()
                .reduce(0f, (sum, item) -> sum + item.getPrice() * item.getQuantity(), Float::sum);
    }

    private List<Item> buildItems(List<ItemDto> items) {
        return items.stream()
                .map(item ->
                        new Item(
                                UUID.randomUUID(),
                                item.getName(),
                                item.getQuantity(),
                                item.getPrice()
                        )
                        ).toList();
    }

    private Client buildClient(ClientDto client) {
        return Client.newBuilder()
                .setId(UUID.randomUUID())
                .setName(client.getName())
                .setCardNumber(client.getCardNumber())
                .build();
    }
}
