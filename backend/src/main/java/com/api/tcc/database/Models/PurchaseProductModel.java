package com.api.tcc.database.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "TB_PURCHASE_PRODUCT")
public class PurchaseProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_purchase_product", updatable = false, unique = true, nullable = false)
    private UUID id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_purchase")
    private PurchaseModel purchaseModel;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductModel productModel;
}