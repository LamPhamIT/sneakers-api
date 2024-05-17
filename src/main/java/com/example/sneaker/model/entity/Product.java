package com.example.sneaker.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_product")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String name;

    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;

    @Column(name = "desciption", nullable = false, length = 1000)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String desciption;

    @Column(name = "image_desc", nullable = false)
    private String imageDesc;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "discount_percent")
    private Integer discountPercent;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Size> sizes = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Cart> carts = new ArrayList<>();

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}