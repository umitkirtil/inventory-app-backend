package com.inventory.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category;

    //TODO Bundan emin olamadim. Bu sekilde tutulacaksa urun sayisi bilgisi bu orta tabloda tutulmalı ?
    @ManyToMany(targetEntity = Warehouse.class)
    @JoinTable(name = "product_warehouse", joinColumns = {@JoinColumn(name = "fk_product")}, inverseJoinColumns = {@JoinColumn(name = "fk_warehouse")})
    private Set<Warehouse> warehouseList;

}