package com.example.demo.controller;
import com.example.demo.controller.model.OrderInfoResponse;
import com.example.demo.dto.mapper.MapUserDto;
import com.example.demo.entity.AppOrders;
import com.example.demo.entity.AppUser;
import com.example.demo.exception.customExceptions.EmailAlreadyExists;
import com.example.demo.exception.customExceptions.PasswordLengthException;
import com.example.demo.exception.customExceptions.UserDoesNotExist;
import com.example.demo.exception.customExceptions.UserNotFoundException;
import com.example.demo.repository.AppOrdersRepository;
import com.example.demo.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    AppUserService appUserService;
    @Autowired
    AppOrdersRepository appOrdersRepository;
    @Autowired
    MapUserDto mapUserDto;

    @PostMapping("/add/appUser")
    public ResponseEntity<AppUser> addNewAppUser(@RequestBody AppUser appUser) throws Exception, EmailAlreadyExists, PasswordLengthException {
        AppUser savedAppUser = appUserService.saveUser(appUser);
        return new ResponseEntity(savedAppUser, HttpStatus.OK);
    }
    @DeleteMapping("/delete/appUser/{id}")
    public void deleteAppUser (@PathVariable(name = "id", required = true) Long id) throws Exception, UserDoesNotExist {
        appUserService.deleteUser(id);
    }
    @GetMapping("/retrieve/appUser/{id}")
    public ResponseEntity<AppUser> retrieveAppUser(@PathVariable(name = "id") Long id) throws Exception, UserNotFoundException {
        AppUser  retrieveAppUser = appUserService.retriveUser(id);
        return new ResponseEntity (mapUserDto.buildAppUserToAppUserDto(retrieveAppUser), HttpStatus.OK);
    }
    @GetMapping("/retrieveEmail/appUser/{email}")
    public ResponseEntity<AppUser> retrieveEmailAppUser (@PathVariable(name = "email") String email) throws Exception, UserNotFoundException {
        AppUser retrieveEmailuser = appUserService.retrieveEmailUser(email);
        return new ResponseEntity(retrieveEmailuser,HttpStatus.OK);
    }
    @PutMapping("/update/appUser/{id}")
    public ResponseEntity<AppUser> updateAppUser(
            @RequestBody AppUser updatedAppUser,
            @PathVariable(name = "id") Long id) throws Exception{
        return new ResponseEntity(appUserService.updateUser(updatedAppUser, id),HttpStatus.OK);
    }

    @GetMapping("/retrieve/orderInfo/{id}")
    public ResponseEntity<OrderInfoResponse> retrieveOrderInfo (@PathVariable (name = "id") Long id) throws Exception, UserNotFoundException {
        AppUser retrieveUser = appUserService.retriveUser(id);
        //
        List<AppOrders> retrieveOrders = appOrdersRepository.findAllByAppUser(retrieveUser);
        final int[] totalPrice = new int[1];
        retrieveOrders.forEach(appOrder -> {
            if(appOrder.getPrice() == null){
                totalPrice[0] = totalPrice[0] + 0;
            }else {
                totalPrice[0] = totalPrice[0] + Integer.parseInt(appOrder.getPrice());
            }
        });
        return new ResponseEntity(new OrderInfoResponse(
                retrieveOrders.size(),
                totalPrice[0]),
                HttpStatus.OK
        );
    }
}
