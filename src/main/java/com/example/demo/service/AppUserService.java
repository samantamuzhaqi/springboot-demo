package com.example.demo.service;

import com.example.demo.entity.AppUser;
import com.example.demo.repository.AppOrdersRepository;
import com.example.demo.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor

public class AppUserService {
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    AppOrdersRepository appOrdersRepository;

    public AppUser saveUser(AppUser appUser) throws Exception {
        if (appUser.getPassword().length() > 5){
            throw new Exception("length must be less than 5");
        }
        return appUserRepository.save(appUser);
    }
    @Transactional
    public void deleteUser (Long id) throws Exception{
        Optional<AppUser> findAppUser = appUserRepository.findById(id);
        if(findAppUser.isEmpty()){
            throw new Exception("not_found");
        }
        appUserRepository.delete(findAppUser.get());
    }
    public AppUser retriveUser (Long id) throws Exception{
        Optional<AppUser> retrieveAppUser = appUserRepository.findById(id);
        if(retrieveAppUser.isEmpty()) {
            throw new Exception("this user does not exist");
        }
       return retrieveAppUser.get();
    }
    public AppUser retrieveEmailUser (String email) throws Exception{
        Optional<AppUser> retrieveAppUser = appUserRepository.findByEmail(email);
        if(retrieveAppUser.isEmpty()){
            throw new Exception("this email does not exist");
        }
        return retrieveAppUser.get();
    }
    public AppUser updateUser (AppUser appUser, Long id) throws Exception{
        Optional<AppUser> findAppUser = appUserRepository.findById(id);
        if(findAppUser.isEmpty()){
            throw new Exception("this user does not exist");
        }
        appUser.setId(id);
        return appUserRepository.save(appUser);
    }
}