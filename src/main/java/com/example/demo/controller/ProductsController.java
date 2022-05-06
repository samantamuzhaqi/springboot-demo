package com.example.demo.controller;

import com.example.demo.controller.model.ProductResponse;
import com.example.demo.entity.AppOrders;
import com.example.demo.entity.AppProducts;
import com.example.demo.repository.AppOrdersRepository;
import com.example.demo.service.AppProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@RestController

public class ProductsController {
    @Autowired
    AppOrdersRepository appOrdersRepository;
    @Autowired
    AppProductsService appProductsService;

    @PostMapping ("/add/appProduct")
       public ResponseEntity<ProductResponse> addNewAppProducts(@RequestBody AppProducts appProducts) throws Exception {
        ProductResponse savedAppProducts = appProductsService.saveProduct(appProducts);
        return new ResponseEntity(savedAppProducts, HttpStatus.OK);
    }
    @DeleteMapping ("/delete/appProduct/{id}")
    public void deleteAppProduct (@PathVariable (name= "id") Long id) throws Exception{
        appProductsService.deleteProduct(id);
    }
    @GetMapping("/retrieve/appProduct/{id}")
    public ResponseEntity<AppProducts> retrieveAppProduct (@PathVariable (name = "id") Long id)throws Exception{
        AppProducts retrievedAppProducts = appProductsService.retrieveProduct(id);
        return new ResponseEntity(new ProductResponse(
                retrievedAppProducts.getId(),
                retrievedAppProducts.getName(),
                retrievedAppProducts.getPrice(),
                retrievedAppProducts.getAppOrders().getId()
                ), HttpStatus.OK);
    }
    @PutMapping ("/update/appProduct/{id}")
    public ResponseEntity<AppProducts> updateAppProduct
            (@RequestBody AppProducts updatedAppProduct,
             @PathVariable (name = "id") Long id) throws Exception{
        return new ResponseEntity(appProductsService.updateProduct(updatedAppProduct, id), HttpStatus.OK);
    }
}
