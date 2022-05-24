package com.api.tcc.database.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class PurchaseProductDTO {
    private UUID id_purchase;
    private UUID id_product;
}
