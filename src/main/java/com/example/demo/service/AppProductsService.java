package com.example.demo.service;

import com.example.demo.controller.model.ProductResponse;
import com.example.demo.entity.AppOrders;
import com.example.demo.entity.AppProducts;
import com.example.demo.repository.AppOrdersRepository;
import com.example.demo.repository.AppProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppProductsService {
    @Autowired
    AppProductsRepository appProductsRepository;
    @Autowired
    AppOrdersRepository appOrdersRepository;

    public ProductResponse saveProduct (AppProducts appProducts) throws Exception{
        if (appProducts.getName() == null || appProducts.getName().isEmpty()){
            throw new Exception("Please add a product name");
        }
        Long orderId = appProducts.getAppOrders().getId();
        if (appOrdersRepository.findById(orderId).isEmpty()){
            throw new Exception("this order does not exist");
        }
        Optional<AppOrders> getAppOrder = appOrdersRepository.findById(orderId);
        String price = getAppOrder.get().getPrice();
        int totalPrice = 0;
        if(price == null || appProducts.getPrice() == null){
            totalPrice = totalPrice + 0;
        }else {
            totalPrice = Integer.parseInt(price) + Integer.parseInt(appProducts.getPrice());
        }
        getAppOrder.get().setPrice(String.valueOf(totalPrice));
        appOrdersRepository.save(getAppOrder.get());
        appProductsRepository.save(appProducts);
        return new ProductResponse(
                appProducts.getId(),
                appProducts.getName(),
                appProducts.getPrice(),
                appProducts.getAppOrders().getId()
        );
    }
    public void deleteProduct (Long id) throws Exception{
        Optional<AppProducts> findAppProduct = appProductsRepository.findById(id);
        appProductsRepository.delete(findAppProduct.get());
    }
    public AppProducts retrieveProduct (Long id) throws Exception{
        Optional<AppProducts> retrieveAppProduct = appProductsRepository.findById(id);
        return retrieveAppProduct.get();
    }
    public AppProducts updateProduct (AppProducts appProducts, Long id) throws Exception{
        Optional<AppProducts> updatedAppProduct = appProductsRepository.findById(id);
        appProducts.setId(id);
        return appProductsRepository.save(appProducts);
    }
}
