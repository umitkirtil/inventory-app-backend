package com.inventory.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "product")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Setter
    private String name;

    @Setter
    private Integer quantity;

    @Setter
    private Integer minimumQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    //TODO Bundan emin olamadim. Bu sekilde tutulacaksa urun sayisi bilgisi bu orta tabloda tutulmalı ?
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_warehouse", joinColumns = {@JoinColumn(name = "fk_product")}, inverseJoinColumns = {@JoinColumn(name = "fk_warehouse")})
    private List<Warehouse> warehouseList = new ArrayList<>();

}