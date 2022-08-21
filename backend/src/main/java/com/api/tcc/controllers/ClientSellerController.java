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
import com.api.tcc.services.ClientSellerService;
import com.api.tcc.services.ClientService;
import com.api.tcc.services.SellerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
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
    private ClientService clientService;

    @Autowired
    private SellerService sellerService;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @PostMapping("/insertAttendance")
    public ResponseEntity<Object> saveAttendance(@RequestBody @Valid ClientSellerDTO clientSellerDTO) {
        ClientSellerModel clientSellerModel = new ClientSellerModel();
        BeanUtils.copyProperties(clientSellerDTO, clientSellerModel);
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
        BeanUtils.copyProperties(clientSellerDTO, clientSellerModel);
        clientSellerModel.setId(clientSellerModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(clientSellerService.save(clientSellerModel));
    }

    @PutMapping("/updateStatus/{clientId}/{sellerId}")
    public ResponseEntity<Object> updateStatus(@PathVariable(value = "clientId") long clientId, @PathVariable(value = "sellerId") long sellerId) {
        Optional<ClientModel> clientModelOptional = clientService.findById(clientId);
        Optional<SellerModel> sellerModelOptional = sellerService.findById(sellerId);

        if (!clientModelOptional.isPresent() || !sellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client or Seller not found!");
        }
        clientModelOptional.get().setAvailable(false);
        sellerModelOptional.get().setAvailable(false);
        return ResponseEntity.status(HttpStatus.OK).body("Client and seller status changed successfully");
    }

    @PutMapping("/updateStatusAndEndTime/{clientId}/{sellerId}") //TO DO percorrer lista se tem cliente esperando atendimento e indicar o vendedor livre para aquela pessoa
    public ResponseEntity<Object> updateStatusAndEndTime(@PathVariable(value = "clientId") long clientId, @PathVariable(value = "sellerId") long sellerId) {
        Optional<ClientModel> clientModelOptional = clientService.findById(clientId);
        Optional<SellerModel> sellerModelOptional = sellerService.findById(sellerId);

        if (!clientModelOptional.isPresent() || !sellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client or Seller not found!");
        }
        clientSellerService.endAttendance(clientId, sellerId);
        return ResponseEntity.status(HttpStatus.OK).body("Client and seller status changed successfully");
    }

    @DeleteMapping("/deleteAttendance/{clientId}/{sellerId}")
    public ResponseEntity<Object> deleteAttendance(@PathVariable(value = "clientId") long clientId, @PathVariable(value = "sellerId") long sellerId) {
        Optional<ClientModel> clientModelOptional = clientService.findById(clientId);
        Optional<SellerModel> sellerModelOptional = sellerService.findById(sellerId);

        if (!clientModelOptional.isPresent() || !sellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client or Seller not found!");
        }
        clientSellerService.deleteAttendance(clientId, sellerId);
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
    public ResponseEntity<ClientsWaitingAttendanceDTO> clientWaitingAttendance(@PathVariable(value = "sellerId") long sellerId) throws ParseException, IOException {
        return ResponseEntity.status(HttpStatus.OK).body(clientSellerService.clientWaitingAttendance(sellerId));
    }
}
