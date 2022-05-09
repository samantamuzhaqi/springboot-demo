package com.example.demo.service;

import com.example.demo.entity.AppUser;
import com.example.demo.exception.customExceptions.EmailAlreadyExists;
import com.example.demo.exception.customExceptions.PasswordLengthException;
import com.example.demo.exception.customExceptions.UserDoesNotExist;
import com.example.demo.exception.customExceptions.UserNotFoundException;
import com.example.demo.repository.AppOrdersRepository;
import com.example.demo.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

import static java.lang.Math.log;

@Service
@AllArgsConstructor
public class AppUserService {
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    AppOrdersRepository appOrdersRepository;
    @Autowired
    MessageSource messageSource;

    private static final Logger LOG =   LoggerFactory.getLogger(AppUserService.class);

    public AppUser saveUser(AppUser appUser) throws Exception,
            EmailAlreadyExists, PasswordLengthException {
        LOG.info(String.valueOf(appUser.getPassword().length()));
        if (appUser.getPassword().length() > 5){
            throw new PasswordLengthException("Password length must be less than 5");
        }
        if (appUserRepository.findByEmail(appUser.getEmail()).isPresent()) {
            Locale locale = Locale.getDefault();

            throw new EmailAlreadyExists(
                    messageSource.getMessage("error.409.emailExists", null, locale)
            );
        }
        return appUserRepository.save(appUser);
    }
    @Transactional
    public void deleteUser (Long id) throws UserDoesNotExist {
        Optional<AppUser> findAppUser = appUserRepository.findById(id);
        if(findAppUser.isEmpty()){
            throw new UserDoesNotExist("Cannot delete user with id " +id+ " beacuse it does not exist");
        }
        appUserRepository.delete(findAppUser.get());
    }
    public AppUser retriveUser (Long id) throws UserNotFoundException{
        Optional<AppUser> retrieveAppUser = appUserRepository.findById(id);
        if(retrieveAppUser.isEmpty()) {
            throw new UserNotFoundException("User with id "+id+" does not exist.");
        }
       return retrieveAppUser.get();
    }
    public AppUser retrieveEmailUser (String email) throws UserNotFoundException {
        Optional<AppUser> retrieveAppUser = appUserRepository.findByEmail(email);
        if(retrieveAppUser.isEmpty()){
            throw new UserNotFoundException("no user with email: "+email+ " exists");
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