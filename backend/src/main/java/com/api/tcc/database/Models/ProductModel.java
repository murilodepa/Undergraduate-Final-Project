package com.api.tcc.database.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "TB_PRODUCT")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_purchase", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "description")
    @Size(min = 3, max = 50)
    private String description;

    @Column(name = "category")
    @Size(min = 3, max = 50)
    private String category;

    @Column(name = "size")
    private int size;

    @Column(name = "brand")
    @Size(min = 3, max = 15)
    private String brand;

    @Column(name = "price")
    private Double price;
}
