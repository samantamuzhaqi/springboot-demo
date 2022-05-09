package com.example.demo.dto.mapper;

import com.example.demo.dto.AppOrdersDto;
import com.example.demo.entity.AppOrders;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapOrdersDto {
    public AppOrdersDto buildAppOrdersToAppOrdersDto (AppOrders appOrders){
        List<Long> appProductsId = new ArrayList();
        appOrders.getAppProducts().forEach(
                appProducts -> {
                    appProductsId.add(appProducts.getId());
                }
                );

        return new AppOrdersDto(
                appOrders.getDescriptions(),
                appOrders.getQuantity(),
                appOrders.getPrice(),
                appOrders.getAppOrderStatus(),
                appProductsId
        );

    }
}
