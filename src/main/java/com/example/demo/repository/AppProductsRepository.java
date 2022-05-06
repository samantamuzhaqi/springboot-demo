package com.example.demo.repository;

import com.example.demo.entity.AppOrders;
import com.example.demo.entity.AppProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppProductsRepository extends JpaRepository <AppProducts, Long> {
    public List<AppProducts> findAllByAppOrders (AppOrders appOrders);
}
