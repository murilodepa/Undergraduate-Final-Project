package com.api.tcc.controllers;

import com.api.tcc.database.Models.ClientSellerModel;
import com.api.tcc.database.dtos.ClientSellerDTO;
import com.api.tcc.services.ClientSellerService;
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
@RequestMapping("/clientSeller")
public class ClientSellerController {

    private final ClientSellerService clientSellerService;

    public ClientSellerController(ClientSellerService clientSellerService) {
        this.clientSellerService = clientSellerService;
    }

    @PostMapping("/insertAttendance")
    public ResponseEntity<Object> saveAttendance(@RequestBody @Valid ClientSellerDTO clientSellerDTO) throws ParseException {
        ClientSellerModel clientSellerModel = new ClientSellerModel();
        BeanUtils.copyProperties(clientSellerDTO, clientSellerModel);
        clientSellerModel.setDateTime(LocalDateTime.now(ZoneId.of("UTC")));
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
        if (!clientSellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance not found!");
        }
        ClientSellerModel clientSellerModel = new ClientSellerModel();
        BeanUtils.copyProperties(clientSellerDTO, clientSellerModel);
        clientSellerModel.setId(clientSellerModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(clientSellerService.save(clientSellerModel));
    }
}
