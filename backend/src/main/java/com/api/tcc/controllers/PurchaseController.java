package com.api.tcc.controllers;

import com.api.tcc.database.Models.PurchaseModel;
import com.api.tcc.database.dtos.PurchaseDTO;
import com.api.tcc.services.PurchaseService;
import org.springframework.beans.BeanUtils;
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
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/insertPurchase")
    public ResponseEntity<Object> saveAttendance(@RequestBody @Valid PurchaseDTO purchaseDTO) throws ParseException {
        PurchaseModel purchaseModel = new PurchaseModel();
        BeanUtils.copyProperties(purchaseDTO, purchaseModel);
        purchaseModel.setLocalDateTime(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.save(purchaseModel));
    }

    @GetMapping("/getPurchase")
    public ResponseEntity<List<PurchaseModel>> getAttendance() {
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
        return ResponseEntity.status(HttpStatus.OK).body("Attendance deleted successfully!");
    }

    @PutMapping("/updatePurchase/{id}")
    public ResponseEntity<Object> updatePurchase(@PathVariable(value = "id") UUID id, @RequestBody @Valid PurchaseDTO purchaseDTO) {
        Optional<PurchaseModel> purchaseModelOptional = purchaseService.findById(id);
        if (!purchaseModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance not found!");
        }
        PurchaseModel purchaseModel = new PurchaseModel();
        BeanUtils.copyProperties(purchaseDTO, purchaseModel);
        purchaseModel.setId(purchaseModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.save(purchaseModel));
    }
}

