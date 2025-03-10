package com.gallegos.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopOrderDto {
    private String id;
    private String nameShop;
    private String address;
    private String city;
    private String cp;
    private ClientDto client;
    private List<ItemDto> items;
}
