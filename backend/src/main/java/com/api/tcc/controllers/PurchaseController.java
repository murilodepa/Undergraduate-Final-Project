/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.controllers;

import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.database.Models.ProductModel;
import com.api.tcc.database.Models.PurchaseModel;
import com.api.tcc.database.Models.PurchaseProductModel;
import com.api.tcc.database.dtos.PersonDataWithTagsToGiftDTO;
import com.api.tcc.database.dtos.PurchaseDTO;
import com.api.tcc.services.ClientService;
import com.api.tcc.services.ProductService;
import com.api.tcc.services.PurchaseProductService;
import com.api.tcc.services.PurchaseService;
import com.api.tcc.utils.CategorySizeAndDate;
import com.api.tcc.utils.FormattingDates;
import com.api.tcc.utils.NameAndKinship;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Murilo de Paula Araujo
 */
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseProductService purchaseProductService;

    @PostMapping("/insertPurchase")
    public ResponseEntity<Object> savePurchase(@RequestBody @Valid PurchaseDTO purchaseDTO) throws IOException {
        Optional<ClientModel> clientModelOptional = clientService.findById(purchaseDTO.getId_client());
        if (!clientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!");
        }

        Optional<ProductModel> productModelOptional = productService.findById(purchaseDTO.getId_product());

        if (!productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("product not found!");
        }
        ClientModel clientModel = clientModelOptional.get();
        ProductModel productModel = productModelOptional.get();
        PurchaseModel purchaseModel = new PurchaseModel();

        BeanUtils.copyProperties(purchaseDTO, purchaseModel);

        if (productModel.getQuantity() < purchaseDTO.getQuantity()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Quantidade do produto em estoque, é menor do que se deseja comprar!");
        }

        purchaseModel.setClientModel(clientModel);
        purchaseModel.setCategory(productModel.getCategory());
        purchaseModel.setSize(productModel.getSize());
        purchaseModel.setDate(LocalDate.now(ZoneId.of("America/Sao_Paulo")));
        purchaseModel.setDescription(productModel.getDescription());
        purchaseService.save(purchaseModel);

        productModel.setQuantity(productModelOptional.get().getQuantity() - purchaseDTO.getQuantity());
        productService.save(productModel);

        String result = purchaseService.getMostSuggestion(clientModel, null, null);
        clientModel.setAvailable(true);
        if (result != null && !result.equals("")) {
            clientModel.setPurchaseSuggestion(result);
        }
        clientService.save(clientModel);

        PurchaseProductModel purchaseProductModel = new PurchaseProductModel();
        purchaseProductModel.setProductModel(productModel);
        purchaseProductModel.setPurchaseModel(purchaseModel);

        return ResponseEntity.status(HttpStatus.OK).body(purchaseProductService.save(purchaseProductModel));
    }

    @PostMapping("/verifySuggestionWithTags")
    public String verifySuggestionWithTags(@RequestBody @Valid PersonDataWithTagsToGiftDTO personDataWithTagsToGiftDTO) throws IOException {
        return purchaseService.getProductToGiftWithCategory(personDataWithTagsToGiftDTO);
    }

    @GetMapping("/getPurchase")
    public ResponseEntity<List<PurchaseModel>> getPurchase() {
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.findAll());
    }

    @GetMapping("/getPurchase/{id}")
    public ResponseEntity<Object> getOnePurchase(@PathVariable(value = "id") UUID id) {
        Optional<PurchaseModel> purchaseModelOptional = purchaseService.findById(id);
        if (!purchaseModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(purchaseModelOptional.get());
    }

    @GetMapping("/getPurchaseList/{id}")
    public List<CategorySizeAndDate> getOnePurchase(@PathVariable(value = "id") long id) {
        return purchaseService.getPurchasedList(id, null, null);
    }

    @DeleteMapping("/removePurchase/{id}")
    public ResponseEntity<Object> deletePurchase(@PathVariable(value = "id") UUID id) {
        Optional<PurchaseModel> purchaseModelOptional = purchaseService.findById(id);
        if (!purchaseModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase not found!");
        }
        purchaseService.delete(purchaseModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Purchase deleted successfully!");
    }

    @PutMapping("/updatePurchase/{id}")
    public ResponseEntity<Object> updatePurchase(@PathVariable(value = "id") UUID id, @RequestBody @Valid PurchaseDTO purchaseDTO) throws ParseException {
        Optional<PurchaseModel> purchaseModelOptional = purchaseService.findById(id);
        if (!purchaseModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase not found!");
        }

        Optional<ClientModel> clientModelOptional = clientService.findById(purchaseModelOptional.get().getClientModel().getId());
        if (!clientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!");
        }
        PurchaseModel purchaseModel = new PurchaseModel();
        purchaseModel.setId(purchaseModelOptional.get().getId());
        purchaseModel.setCategory(purchaseDTO.getCategory());
        purchaseModel.setQuantity(purchaseDTO.getQuantity());
        purchaseModel.setSize(purchaseDTO.getSize());
        purchaseModel.setDescription(purchaseDTO.getDescription());
        purchaseModel.setKinship(purchaseDTO.getKinship());
        purchaseModel.setPersonsName(purchaseDTO.getPersonsName());
        FormattingDates formattingDates = new FormattingDates();
        purchaseModel.setDate(formattingDates.convertDateToDatabase(purchaseDTO.getDate()));
        purchaseModel.setClientModel(clientModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.save(purchaseModel));
    }

    @GetMapping("/getPeopleGifts/{id}")
    public List<NameAndKinship> getPeopleGifts(@PathVariable(value = "id") long id) {
        return purchaseService.getPeopleGifts(id);
    }

    @GetMapping("/getPurchaseGifts/{id}")
    public List<CategorySizeAndDate> getPurchaseGifts(@PathVariable(value = "id") long id, @NotNull @RequestParam String name, @NotNull @RequestParam String kinship) {
        return purchaseService.getPurchasedList(id, name, kinship);
    }

    @GetMapping("/getSuggestion/{id}")
    public String getSuggestion(@PathVariable(value = "id") long id) throws IOException {
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if (!clientModelOptional.isPresent()) {
            return "";
        }
        return purchaseService.getMostSuggestion(clientModelOptional.get(), null, null);
    }

    @GetMapping("/getSuggestionGifts/{id}")
    public String getSuggestionGifts(@PathVariable(value = "id") long id, @NotNull @RequestParam String name, @NotNull @RequestParam String kinship) throws IOException {
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if (!clientModelOptional.isPresent()) {
            return "";
        }
        return purchaseService.getMostSuggestion(clientModelOptional.get(), name, kinship);
    }

 /*   @GetMapping("/decisionTree")
    public ResponseEntity<String> getPurchaseSuggestion() throws IOException {
       return ResponseEntity.status(HttpStatus.OK).body(purchaseService.getPurchaseSuggestion());
    }*/
}

