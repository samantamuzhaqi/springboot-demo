package com.example.demo.repository;

import com.example.demo.entity.AppOrders;
import com.example.demo.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppOrdersRepository extends JpaRepository <AppOrders,Long>{
  public List<AppOrders> findAllByAppUser(AppUser appUser);
  }

