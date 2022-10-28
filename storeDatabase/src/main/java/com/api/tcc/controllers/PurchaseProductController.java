/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.controllers;

import com.api.tcc.database.Models.ProductModel;
import com.api.tcc.database.Models.PurchaseModel;
import com.api.tcc.database.Models.PurchaseProductModel;
import com.api.tcc.database.dtos.PurchaseProductDTO;
import com.api.tcc.services.ProductService;
import com.api.tcc.services.PurchaseProductService;
import com.api.tcc.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Murilo de Paula Araujo
 */
@RestController
@RequestMapping("/purchaseProduct")
public class PurchaseProductController {

    @Autowired
    private PurchaseProductService purchaseProductService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductService productService;

    @PostMapping("/insertPurchaseProduct")
    public ResponseEntity<Object> savePurchaseProduct(@RequestBody @Valid PurchaseProductDTO purchaseProductDTO) throws ParseException {
        Optional<PurchaseModel> purchaseModelOptional = purchaseService.findById(purchaseProductDTO.getId_purchase());
        Optional<ProductModel> productModelOptional = productService.findById(purchaseProductDTO.getId_product());
        if (!purchaseModelOptional.isPresent() || !productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase or Product not found!");
        }
        PurchaseProductModel purchaseProductModel = new PurchaseProductModel();
        purchaseProductModel.setPurchaseModel(purchaseModelOptional.get());
        purchaseProductModel.setProductModel(productModelOptional.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseProductService.save(purchaseProductModel));
    }

    @GetMapping("/getPurchaseProduct")
    public ResponseEntity<List<PurchaseProductModel>> getPurchaseProduct() {
        return ResponseEntity.status(HttpStatus.OK).body(purchaseProductService.findAll());
    }

    @GetMapping("/getPurchaseProduct/{id}")
    public ResponseEntity<Object> getOnePurchaseProduct(@PathVariable(value = "id") UUID id) {
        Optional<PurchaseProductModel> purchaseProductModelOptional = purchaseProductService.findById(id);
        if (!purchaseProductModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase Product not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(purchaseProductModelOptional.get());
    }

    @DeleteMapping("/removePurchaseProduct/{id}")
    public ResponseEntity<Object> deletePurchaseProduct(@PathVariable(value = "id") UUID id) {
        Optional<PurchaseProductModel> purchaseProductModelOptional = purchaseProductService.findById(id);
        if (!purchaseProductModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase Product not found!");
        }
        purchaseProductService.delete(purchaseProductModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Purchase Product deleted successfully!");
    }

    @PutMapping("/updatePurchaseProduct/{id}")
    public ResponseEntity<Object> updatePurchaseProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid PurchaseProductDTO purchaseProductDTO) {
        Optional<PurchaseProductModel> purchaseProductModelOptional = purchaseProductService.findById(id);
        Optional<PurchaseModel> purchaseModelOptional = purchaseService.findById(purchaseProductDTO.getId_purchase());
        Optional<ProductModel> productModelOptional = productService.findById(purchaseProductDTO.getId_product());
        if (!purchaseProductModelOptional.isPresent() || !purchaseModelOptional.isPresent() || !productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase Product not found!");
        }
        PurchaseProductModel purchaseProductModel = new PurchaseProductModel();
        purchaseProductModel.setId(purchaseProductModelOptional.get().getId());
        purchaseProductModel.setPurchaseModel(purchaseModelOptional.get());
        purchaseProductModel.setProductModel(productModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(purchaseProductService.save(purchaseProductModel));
    }
}
