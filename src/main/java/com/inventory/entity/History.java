package com.inventory.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "history")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Product product;

    @OneToOne
    private Warehouse warehouse;

    @OneToOne
    private Category category;

    private ProcessType processType;
}