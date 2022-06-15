package com.api.tcc.controllers;

import com.api.tcc.database.Models.ClientImageModel;
import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.database.Models.ClientSellerModel;
import com.api.tcc.database.Models.SellerModel;
import com.api.tcc.database.dtos.ClientSellerDTO;
import com.api.tcc.services.ClientSellerService;
import com.api.tcc.services.ClientService;
import com.api.tcc.services.SellerService;
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
@RequestMapping("/clientSeller")
public class ClientSellerController {

    @Autowired
    private ClientSellerService clientSellerService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private SellerService sellerService;

    @PostMapping("/insertAttendance")
    public ResponseEntity<Object> saveAttendance(@RequestBody @Valid ClientSellerDTO clientSellerDTO) throws ParseException {
        Optional<ClientModel> clientModelOptional = clientService.findById(clientSellerDTO.getId_client());
        Optional<SellerModel> sellerModelOptional = sellerService.findById(clientSellerDTO.getId_seller());
        if (!clientModelOptional.isPresent() || !sellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!");
        }
        ClientSellerModel clientSellerModel = new ClientSellerModel();
        clientSellerModel.setStartTime(LocalDateTime.now(ZoneId.of("UTC")));
        clientSellerModel.setClientModel(clientModelOptional.get());
        clientSellerModel.setSellerModel(sellerModelOptional.get());
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
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid ClientSellerDTO clientSellerDTO) {
        Optional<ClientSellerModel> clientSellerModelOptional = clientSellerService.findById(id);
        Optional<ClientModel> clientModelOptional = clientService.findById(clientSellerDTO.getId_client());
        Optional<SellerModel> sellerModelOptional = sellerService.findById(clientSellerDTO.getId_seller());
        if (!clientSellerModelOptional.isPresent() || !clientModelOptional.isPresent() || !sellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance not found!");
        }
        ClientSellerModel clientSellerModel = new ClientSellerModel();
        clientSellerModel.setId(clientSellerModelOptional.get().getId());
        clientSellerModel.setStartTime(clientSellerModelOptional.get().getStartTime());
        clientSellerModel.setClientModel(clientModelOptional.get());
        clientSellerModel.setSellerModel(sellerModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(clientSellerService.save(clientSellerModel));
    }

    @GetMapping("/clientIsServed/{id}")
    public ResponseEntity<Object> verifyClientService(@PathVariable(value = "id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clientSellerService.isBeingAttended(id));
    }
}
