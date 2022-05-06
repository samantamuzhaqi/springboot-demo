package com.example.demo.entity;

import com.example.demo.entity.enums.AppUserRole;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked;
    private Boolean enabled;
    @OneToMany (mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<AppOrders> appOrders;



    public AppUser(String name,
                   String username,
                   String password,
                   String email,
                   AppUserRole appUserRole,
                   Boolean locked,
                   Boolean enabled) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.appUserRole = appUserRole;
        this.locked = locked;
        this.enabled = enabled;
    }
}