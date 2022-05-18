package com.api.tcc.controllers;

import com.api.tcc.Utils.ManipulatingDates;
import com.api.tcc.database.Models.SellerModel;
import com.api.tcc.database.dtos.SellerDTO;
import com.api.tcc.services.SellerService;
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
@RequestMapping("/seller")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping("/insertSeller")
    public ResponseEntity<Object> saveSeller(@RequestBody @Valid SellerDTO sellerDTO) throws ParseException {
        ManipulatingDates manipulatingDates = new ManipulatingDates();
        SellerModel sellerModel = new SellerModel();
        BeanUtils.copyProperties(sellerDTO, sellerModel);
        sellerModel.setAvailable(true);
        sellerModel.setAttendances(0);
        sellerModel.setBirth(manipulatingDates.ConvertDateToDatabase(sellerDTO.getBirth()));
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

    @DeleteMapping("/removeSeller/{id}")
    public ResponseEntity<Object> deleteSeller(@PathVariable(value = "id") long id) {
        Optional<SellerModel> sellerModelOptional = sellerService.findById(id);
        if (!sellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!");
        }
        sellerService.delete(sellerModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client delected successfully!");
    }

    @PutMapping("/updateSeller/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") long id, @RequestBody @Valid SellerDTO sellerDTO) {
        Optional<SellerModel> sellerModelOptional = sellerService.findById(id);
        if (!sellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!");
        }
        SellerModel sellerModel = new SellerModel();
        BeanUtils.copyProperties(sellerDTO, sellerModel);
        sellerModel.setId(sellerModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.save(sellerModel));
    }
}
