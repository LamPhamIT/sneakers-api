package com.example.sneaker.model.entity;

import com.example.sneaker.model.enums.StatusCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_order")
public class Order extends Base{

    @Column(name = "total")
    private Long total;

    @Column(name = "is_payed", nullable = false)
    private Boolean isPayed;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private StatusCode status;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<Cart> carts = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}