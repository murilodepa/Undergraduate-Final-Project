package com.api.tcc.controllers;

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
import java.util.Optional;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping("/insertSeller")
    public ResponseEntity<Object> saveSeller(@RequestBody @Valid SellerDTO sellerDTO) throws ParseException {
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
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") long id, @RequestBody @Valid SellerDTO sellerDTO) throws ParseException {
        Optional<SellerModel> sellerModelOptional = sellerService.findById(id);
        if (!sellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!");
        }
        SellerModel sellerModel = new SellerModel();
        BeanUtils.copyProperties(sellerDTO, sellerModel);
        sellerModel.setId(sellerModelOptional.get().getId());
        FormattingDates formattingDates = new FormattingDates();
        sellerModel.setBirth(formattingDates.ConvertDateToDatabase(sellerDTO.getBirth()));
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.save(sellerModel));
    }
}
