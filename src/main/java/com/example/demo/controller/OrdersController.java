package com.example.demo.controller;

import com.example.demo.entity.AppOrders;
import com.example.demo.entity.AppUser;
import com.example.demo.repository.AppOrdersRepository;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.service.AppOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.util.List;
import java.util.Optional;

@RestController
public class OrdersController {
    @Autowired
    AppOrdersRepository appOrdersRepository;
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    AppOrdersService appOrdersService;

    @PostMapping("/add/appOrder")
    public ResponseEntity<AppOrders> addNewAppOrders(@RequestBody AppOrders appOrders) throws Exception{
        AppOrders savedAppOrders = appOrdersService.saveOrder(appOrders);
        return new ResponseEntity(savedAppOrders, HttpStatus.OK);
    }

    @GetMapping ("/retrieve/appOrder/{id}")
    public ResponseEntity<AppOrders> retrieveAppOrders(@PathVariable(name = "id") Long id) throws Exception {
        AppOrders retrievedAppOrders = appOrdersService.retrieveOrder(id);
        return new ResponseEntity(retrievedAppOrders, HttpStatus.OK);
    }
    @PutMapping ("/update/appOrder/{id}")
    public ResponseEntity<AppOrders> updateAppOrders(
            @RequestBody AppOrders updatedAppOrders,
            @PathVariable(name = "id") Long id) throws Exception{
        return new ResponseEntity(appOrdersService.updateAppOrder(updatedAppOrders, id),HttpStatus.OK);
    }
    @DeleteMapping("/delete/appOrder/{id}")
    public void deleteAppOrder (@PathVariable(name = "id", required = true) Long id) throws Exception {
        appOrdersService.deleteOrder(id);
    }
//    @GetMapping("/findUser/{id}/findOrders")
//    public List<AppOrders> allOrders(@PathVariable (name = "id") Long id)throws Exception{
//        return appOrdersRepository.findById(id);
//    }
    }
//    @PostMapping("/appOrder/changeStatus/{id}")
//    public ResponseEntity<AppOrders> changeStatusToActive(@PathVariable(name = "id") Long id){
//        Optional<AppOrders> appOrders = appOrdersRepository.findById(id);
//        if (appOrders.isEmpty()) {
//            return new ResponseEntity("doesn't exist", HttpStatus.NOT_FOUND);
//        }
//        appOrders.get().setAppOrderStatus(AppOrders.AppOrderStatus.ACTIVE);
//        return new ResponseEntity(appOrdersRepository.save(appOrders.get()), HttpStatus.OK);
//    }
