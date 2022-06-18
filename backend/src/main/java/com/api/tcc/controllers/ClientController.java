/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.controllers;

import com.api.tcc.services.ClientImageService;
import com.api.tcc.utils.FormattingDates;
import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.database.dtos.ClientDTO;
import com.api.tcc.services.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

/**
 * @author Murilo de Paula Araujo
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientImageService clientImageService;

    @PostMapping("/insertClient")
    public ResponseEntity<Object> saveClient(@RequestBody @Valid ClientDTO clientDTO) throws ParseException {
        ClientModel clientModel = new ClientModel();
        BeanUtils.copyProperties(clientDTO, clientModel);
        FormattingDates formattingDates = new FormattingDates();
        clientModel.setBirth(formattingDates.ConvertDateToDatabase(clientDTO.getBirth()));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(clientModel));
    }

    @GetMapping("/getClient")
    public ResponseEntity<List<ClientModel>> getClient() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll());
    }

    @GetMapping("/getClient/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value = "id") long id) {
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if (!clientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientModelOptional.get());
    }

    @DeleteMapping("/removeClient/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") long id) {
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if (!clientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!");
        }
        clientImageService.deleteClientImage(clientModelOptional.get().getId());
        clientService.delete(clientModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully!");
    }

    @PutMapping("/updateClient/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") long id, @RequestBody @Valid ClientDTO clientDTO) throws ParseException {
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if (!clientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!");
        }
        ClientModel clientModel = new ClientModel();
        BeanUtils.copyProperties(clientDTO, clientModel);
        clientModel.setId(clientModelOptional.get().getId());
        FormattingDates formattingDates = new FormattingDates();
        clientModel.setBirth(formattingDates.ConvertDateToDatabase(clientDTO.getBirth()));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.save(clientModel));
    }
}