package com.example.demo.dto;

import com.example.demo.entity.enums.AppUserRole;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Getter
@Setter
public class AppUserDto {
    private String name;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked;
    private Boolean enabled;

    public AppUserDto(
            String name,
            String username,
            String password,
            String email,
            AppUserRole appUserRole,
            Boolean locked,
            Boolean enabled
    ) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.appUserRole = appUserRole;
        this.locked = locked;
        this.enabled = enabled;
    }
}
