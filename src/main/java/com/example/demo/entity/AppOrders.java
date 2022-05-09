package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppOrders {
    public enum AppOrderStatus{
        ACTIVE,
        PENDING
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descriptions;
    private int quantity;
    private String price;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AppOrderStatus appOrderStatus;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private AppUser appUser;
    @OneToMany (mappedBy = "appOrders", cascade = CascadeType.ALL)
    private List<AppProducts> appProducts;


    public AppOrders(Long id, String descriptions, int quantity, String price) {
        this.id = id;
        this.descriptions = descriptions;
        this.quantity = quantity;
        this.price = price;
    }
}
