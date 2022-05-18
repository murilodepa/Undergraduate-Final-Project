package com.api.tcc.controllers;

import com.api.tcc.Utils.ManipulatingDates;
import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.database.dtos.ClientDTO;
import com.api.tcc.services.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/insertClient")
    public ResponseEntity<Object> saveClient(@RequestBody @Valid ClientDTO clientDTO) throws ParseException {
        ManipulatingDates manipulatingDates = new ManipulatingDates();
        ClientModel clientModel = new ClientModel();
        BeanUtils.copyProperties(clientDTO, clientModel);
        clientModel.setBirth(manipulatingDates.ConvertDateToDatabase(clientDTO.getBirth()));
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
        clientService.delete(clientModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client delected successfully!");
    }

    @PutMapping("/updateClient/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") long id, @RequestBody @Valid ClientDTO clientDTO) {
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if (!clientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!");
        }
        ClientModel clientModel = new ClientModel();
        BeanUtils.copyProperties(clientDTO, clientModel);
        clientModel.setId(clientModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(clientService.save(clientModel));
    }
}