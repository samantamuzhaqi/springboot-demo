package com.example.demo.dto.mapper;

import com.example.demo.dto.AppUserDto;
import com.example.demo.entity.AppUser;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapUserDto {

    public AppUserDto buildAppUserToAppUserDto (AppUser appUser){
        return new AppUserDto(
                appUser.getName(),
                appUser.getUsername(),
                appUser.getPassword(),
                appUser.getEmail(),
                appUser.getAppUserRole(),
                appUser.getLocked(),
                appUser.getEnabled()
        );
    }
}
