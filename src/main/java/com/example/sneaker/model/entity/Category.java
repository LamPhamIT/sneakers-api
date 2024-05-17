package com.example.sneaker.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tbl_category")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String name;

    @Column(name = "slug", unique = true, nullable = false)
    private String slug;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}