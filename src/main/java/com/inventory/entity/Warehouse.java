package com.inventory.entity;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "warehouse")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String city;

    private String state;

    private String address;

    // bunu da jointable olarak degistir dene. cascade calismiyor. BUYUK HATA
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "warehouseList")
    private Set<Product> productList;
}