package com.example.demo.dto;

import com.example.demo.entity.AppOrders;
import com.example.demo.entity.AppUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

public class AppOrdersDto {
    private String descriptions;
    private int quantity;
    private String price;
    @Enumerated(EnumType.STRING)
    private AppOrders.AppOrderStatus appOrderStatus;
    private AppUser appUser;
    private List<Long> appProducts;

    public AppOrdersDto(
            String descriptions,
            int quantity,
            String price,
            AppOrders.AppOrderStatus appOrderStatus,
            List<Long> appProducts
    )
    {
        this.descriptions = descriptions;
        this.quantity = quantity;
        this.price = price;
        this.appOrderStatus = appOrderStatus;
        this.appProducts = appProducts;
    }
}
