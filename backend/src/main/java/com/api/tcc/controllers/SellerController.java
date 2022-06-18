/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.controllers;

import com.api.tcc.services.SellerImageService;
import com.api.tcc.utils.FormattingDates;
import com.api.tcc.database.Models.SellerModel;
import com.api.tcc.database.dtos.SellerDTO;
import com.api.tcc.services.SellerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Murilo de Paula Araujo
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    SellerImageService sellerImageService;

    @PostMapping("/insertSeller")
    public ResponseEntity<Object> saveSeller(@RequestBody @Valid SellerDTO sellerDTO) throws ParseException {
        if(sellerService.existsByEmail(sellerDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Seller email is already in use!");
        }
        SellerModel sellerModel = new SellerModel();
        BeanUtils.copyProperties(sellerDTO, sellerModel);
        sellerModel.setAvailable(true);
        sellerModel.setAttendances(0);
        FormattingDates formattingDates = new FormattingDates();
        sellerModel.setBirth(formattingDates.ConvertDateToDatabase(sellerDTO.getBirth()));
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerService.save(sellerModel));
    }

    @GetMapping("/getSeller")
    public ResponseEntity<List<SellerModel>> getSeller() {
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.findAll());
    }

    @GetMapping("/getSeller/{id}")
    public ResponseEntity<Object> getOneSeller(@PathVariable(value = "id") long id) {
        Optional<SellerModel> sellerModelOptional = sellerService.findById(id);
        if (!sellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(sellerModelOptional.get());
    }

    @GetMapping("/getAllSellerAvailable")
    public ResponseEntity<List<SellerModel>> getAllSellerAvailable() {
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.findAllSellerAvailable());
    }


    @DeleteMapping("/removeSeller/{id}")
    public ResponseEntity<Object> deleteSeller(@PathVariable(value = "id") long id) {
        Optional<SellerModel> sellerModelOptional = sellerService.findById(id);
        if (!sellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!");
        }
        sellerImageService.deleteSellerImage(sellerModelOptional.get().getId());
        sellerService.delete(sellerModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully!");
    }

    @PutMapping("/updateSeller/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") long id, @RequestBody @Valid SellerDTO sellerDTO) throws ParseException {
        Optional<SellerModel> sellerModelOptional = sellerService.findById(id);
        if (!sellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seller not found!");
        }
        if(!Objects.equals(sellerModelOptional.get().getEmail(), sellerDTO.getEmail()) && sellerService.existsByEmail(sellerDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Seller email is already in use!");
        }
        SellerModel sellerModel = new SellerModel();
        BeanUtils.copyProperties(sellerDTO, sellerModel);
        if(sellerModel.getPassword() == null) {
            sellerModel.setPassword(sellerModelOptional.get().getPassword());

        }
        sellerModel.setId(sellerModelOptional.get().getId());
        FormattingDates formattingDates = new FormattingDates();
        sellerModel.setBirth(formattingDates.ConvertDateToDatabase(sellerDTO.getBirth()));
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.save(sellerModel));
    }
}
