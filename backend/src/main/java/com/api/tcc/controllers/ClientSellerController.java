/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.controllers;

import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.database.Models.ClientSellerModel;
import com.api.tcc.database.Models.SellerModel;
import com.api.tcc.database.dtos.ClientSellerDTO;
import com.api.tcc.database.dtos.ClientsWaitingAttendanceDTO;
import com.api.tcc.repositories.ClientRepository;
import com.api.tcc.repositories.SellerRepository;
import com.api.tcc.services.ClientSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Murilo de Paula Araujo
 */
@RestController
@RequestMapping("/clientSeller")
public class ClientSellerController {

    @Autowired
    private ClientSellerService clientSellerService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SellerRepository sellerRepository;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @PostMapping("/insertAttendance")
    public ResponseEntity<Object> saveAttendance(@RequestBody @Valid ClientSellerDTO clientSellerDTO) {

        ClientSellerModel clientSellerModel = new ClientSellerModel();
        long clientId = clientSellerDTO.getClientId();
        long sellerId = clientSellerDTO.getSellerId();

        if(clientId != 0) {
            Optional<ClientModel> clientModelOptional = clientRepository.findById(clientId);
            if (clientModelOptional.isPresent()) {
                clientSellerModel.setClientModel(clientModelOptional.get());
            }
        }

        if(sellerId != 0) {
            Optional<SellerModel> sellerModelOptional = sellerRepository.findById(sellerId);
            if (sellerModelOptional.isPresent()) {
                clientSellerModel.setSellerModel(sellerModelOptional.get());
            }
        }

        clientSellerModel.setStartTime(LocalDateTime.parse(formatter.format(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))), formatter));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientSellerService.save(clientSellerModel));
    }

    @GetMapping("/getAttendance")
    public ResponseEntity<List<ClientSellerModel>> getAttendance() {
        return ResponseEntity.status(HttpStatus.OK).body(clientSellerService.findAll());
    }

    @GetMapping("/getAttendance/{id}")
    public ResponseEntity<Object> getOneAttendance(@PathVariable(value = "id") UUID id) {
        Optional<ClientSellerModel> clientSellerModelOptional = clientSellerService.findById(id);
        if (!clientSellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientSellerModelOptional.get());
    }

    @DeleteMapping("/removeAttendance/{id}")
    public ResponseEntity<Object> deleteAttendance(@PathVariable(value = "id") UUID id) {
        Optional<ClientSellerModel> clientSellerModelOptional = clientSellerService.findById(id);
        if (!clientSellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance not found!");
        }
        clientSellerService.delete(clientSellerModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Attendance deleted successfully!");
    }

    @PutMapping("/updateAttendance/{id}")
    public ResponseEntity<Object> updateAttendance(@PathVariable(value = "id") UUID id, @RequestBody @Valid ClientSellerDTO clientSellerDTO) {
        Optional<ClientSellerModel> clientSellerModelOptional = clientSellerService.findById(id);

        if (!clientSellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance not found!");
        }

        ClientSellerModel clientSellerModel = new ClientSellerModel();
        long clientId = clientSellerDTO.getClientId();
        long sellerId = clientSellerDTO.getSellerId();

        if(clientId != 0) {
            Optional<ClientModel> clientModelOptional = clientRepository.findById(clientId);
            if (clientModelOptional.isPresent()) {
                clientSellerModel.setClientModel(clientModelOptional.get());
            }
        }

        if(sellerId != 0) {
            Optional<SellerModel> sellerModelOptional = sellerRepository.findById(sellerId);
            if (sellerModelOptional.isPresent()) {
                clientSellerModel.setSellerModel(sellerModelOptional.get());
            }
        }

        clientSellerModel.setId(clientSellerModelOptional.get().getId());
        clientSellerModel.setStartTime(clientSellerDTO.getStartTime());
        clientSellerModel.setEndTime(clientSellerDTO.getEndTime());
        return ResponseEntity.status(HttpStatus.OK).body(clientSellerService.save(clientSellerModel));
    }

    @PutMapping("/updateStatus/{sellerId}")
    public ResponseEntity<Object> updateStatus(@PathVariable(value = "sellerId") long sellerId) {
        Optional<SellerModel> sellerModelOptional = sellerRepository.findById(sellerId);

        if (!sellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seller not found!");
        }
        SellerModel sellerModel = sellerModelOptional.get();
        sellerModel.setAvailable(false);
        sellerRepository.save(sellerModel);
        return ResponseEntity.status(HttpStatus.OK).body("Seller status changed successfully");
    }

    @PutMapping("/updateStatusAndEndTime/{sellerId}")
    public ResponseEntity<Object> updateStatusAndEndTime(@PathVariable(value = "sellerId") long sellerId) {
        Optional<SellerModel> sellerModelOptional = sellerRepository.findById(sellerId);

        if (!sellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seller not found!");
        }
        clientSellerService.endAttendance(sellerId);
        return ResponseEntity.status(HttpStatus.OK).body("Seller status changed successfully");
    }

    @DeleteMapping("/deleteAttendance/{sellerId}")
    public ResponseEntity<Object> deleteAttendance(@PathVariable(value = "sellerId") long sellerId) {
        Optional<SellerModel> sellerModelOptional = sellerRepository.findById(sellerId);

        if (!sellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seller not found!");
        }
        clientSellerService.deleteAttendance(sellerId);
        return ResponseEntity.status(HttpStatus.OK).body("Attendance between client and seller was deleted successfully");
    }

    @PutMapping("/finishingAttendance/{id}")
    public ResponseEntity<Object> finishingAttendance(@PathVariable(value = "id") long id) {
        List<ClientSellerModel> clientSellerModelList = clientSellerService.findAll();
        Optional<ClientSellerModel> clientSellerModelOptional = clientSellerService.findClientSellerModelBySellerModel_Id(id);

        if (clientSellerModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance not found!");
        }
        ClientSellerModel clientSellerModel = clientSellerModelOptional.get();
        clientSellerModel.setEndTime(LocalDateTime.parse(formatter.format(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))), formatter));
        return ResponseEntity.status(HttpStatus.OK).body(clientSellerService.save(clientSellerModel));
    }

    @GetMapping("/clientIsServed/{id}")
    public ResponseEntity<Object> verifyClientService(@PathVariable(value = "id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clientSellerService.isBeingAttended(id));
    }

    @GetMapping("/clientWaitingAttendance/{sellerId}")
    public ResponseEntity<ClientsWaitingAttendanceDTO> clientWaitingAttendance(@PathVariable(value = "sellerId") long sellerId) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(clientSellerService.clientWaitingAttendance(sellerId));
    }
}
