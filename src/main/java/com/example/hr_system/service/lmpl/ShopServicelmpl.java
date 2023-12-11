package com.example.hr_system.service.lmpl;

import com.example.hr_system.entity.Shop;
import com.example.hr_system.exception.ResourceNotFoundException;
import com.example.hr_system.repository.ShopRepository;
import com.example.hr_system.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServicelmpl implements ShopService {
    @Autowired
    private ShopRepository shopRepository;
    @Override
    public List<Shop> getAllShop() {
        return shopRepository.findAll();
    }

    @Override
    public Shop findShopById(Long id) {
        Shop shop = shopRepository.findById(id).orElseThrow(()->new RuntimeException("The shop is not found"));
        return shop;
    }


}
