package com.example.sneaker.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends Base {

    @Email()
    @Size(min = 5, max = 254)
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Size(min = 60, max = 60)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @NotBlank()
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "reset_key")
    private String resetKey;

    @ManyToMany
    @JoinTable(name = "tbl_user_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> authorities = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "token_manager_id")
    private TokenManager tokenManager;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Order> orders = new LinkedHashSet<>();

}