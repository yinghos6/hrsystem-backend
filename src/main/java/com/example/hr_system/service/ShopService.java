package com.example.hr_system.service;

import com.example.hr_system.entity.Shop;

import java.util.List;

public interface ShopService {

    List<Shop> getAllShop();

    Shop findShopById(Long id);
}
