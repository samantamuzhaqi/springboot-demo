package com.example.demo.controller.model;

import lombok.Data;

@Data

public class OrderInfoResponse {
    private int orderSize;
    private int orderPrice;

    public OrderInfoResponse(int size, int totalPrice) {
        this.orderSize = size;
        this.orderPrice = totalPrice;
    }
}
