package com.example.demo.controller.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String name;
    private String price;
    private Long orderId;

    public ProductResponse(Long id, String name, String price, Long orderId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.orderId = orderId;
    }

}
