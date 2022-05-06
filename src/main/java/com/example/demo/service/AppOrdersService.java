package com.example.demo.service;

import com.example.demo.entity.AppOrders;
import com.example.demo.repository.AppOrdersRepository;
import com.example.demo.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppOrdersService {
    @Autowired
    AppOrdersRepository appOrdersRepository;
    @Autowired
    AppUserRepository appUserRepository;


    public AppOrders saveOrder(AppOrders appOrders) throws Exception {
        if (appOrders.getDescriptions() == null || appOrders.getDescriptions().isEmpty()) {
            throw new Exception("add descriptions");
        }
        Long userId = appOrders.getAppUser().getId();
        if (appUserRepository.findById(userId).isEmpty()) {
            throw new Exception("doesn't exist");
        }
        return appOrdersRepository.save(appOrders);
    }
    public AppOrders retrieveOrder(Long id) throws Exception {
        Optional<AppOrders> retrieveAppOrder = appOrdersRepository.findById(id);
        if (retrieveAppOrder.isEmpty()) {
            throw new Exception ("Order does not exist");
        }
        return retrieveAppOrder.get();
    }
    public AppOrders updateAppOrder(AppOrders appOrders, Long id) throws Exception{
        Optional<AppOrders> updatedAppOrder = appOrdersRepository.findById(id);
        if (updatedAppOrder.isEmpty()){
            throw new Exception("this order does not exist ");
        }
        appOrders.setId(id);
        return appOrdersRepository.save(appOrders);
    }
    public void deleteOrder (Long id) throws Exception{
        Optional<AppOrders> findAppOrder = appOrdersRepository.findById(id);
        if(findAppOrder.isEmpty()){
            throw new Exception("not_found");
        }
        appOrdersRepository.delete(findAppOrder.get());
    }
}

