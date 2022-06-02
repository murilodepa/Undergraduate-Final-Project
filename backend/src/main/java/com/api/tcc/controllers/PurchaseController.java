package com.api.tcc.controllers;

import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.database.Models.PurchaseModel;
import com.api.tcc.database.dtos.PurchaseDTO;
import com.api.tcc.services.ClientService;
import com.api.tcc.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ClientService clientService;

    @PostMapping("/insertPurchase")
    public ResponseEntity<Object> savePurchase(@RequestBody @Valid PurchaseDTO purchaseDTO) throws ParseException {
        Optional<ClientModel> clientModelOptional = clientService.findById(purchaseDTO.getId_client());
        if (!clientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!");
        }
        PurchaseModel purchaseModel = new PurchaseModel();
        purchaseModel.setClientModel(clientModelOptional.get());
        purchaseModel.setCategory(purchaseDTO.getCategory());
        purchaseModel.setLocalDateTime(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.save(purchaseModel));
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
    public ResponseEntity<Object> updatePurchase(@PathVariable(value = "id") UUID id, @RequestBody @Valid PurchaseDTO purchaseDTO) {
        Optional<PurchaseModel> purchaseModelOptional = purchaseService.findById(id);
        Optional<ClientModel> clientModelOptional = clientService.findById(purchaseDTO.getId_client());
        if (!purchaseModelOptional.isPresent() || !clientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase or Client not found!");
        }
        PurchaseModel purchaseModel = new PurchaseModel();
        purchaseModel.setId(purchaseModelOptional.get().getId());
        purchaseModel.setLocalDateTime(purchaseModelOptional.get().getLocalDateTime());
        purchaseModel.setClientModel(clientModelOptional.get());
        purchaseModel.setCategory(purchaseDTO.getCategory());
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.save(purchaseModel));
    }
}

