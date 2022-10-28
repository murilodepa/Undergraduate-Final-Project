/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.controllers;

import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.database.Models.AttendanceModel;
import com.api.tcc.database.dtos.AttendanceDTO;
import com.api.tcc.repositories.ClientRepository;
import com.api.tcc.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private ClientRepository clientRepository;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @PostMapping("/insertAttendance")
    public ResponseEntity<Object> saveAttendance(@RequestBody @Valid AttendanceDTO attendanceDTO) {

        AttendanceModel attendanceModel = new AttendanceModel();
        long clientId = attendanceDTO.getClientId();

        if(clientId != 0) {
            Optional<ClientModel> clientModelOptional = clientRepository.findById(clientId);
            if (clientModelOptional.isPresent()) {
                attendanceModel.setClientModel(clientModelOptional.get());
            }
        }
        attendanceModel.setStartTime(LocalDateTime.parse(formatter.format(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))), formatter));
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceService.save(attendanceModel));
    }

    @GetMapping("/getAttendance")
    public ResponseEntity<List<AttendanceModel>> getAttendance() {
        return ResponseEntity.status(HttpStatus.OK).body(attendanceService.findAll());
    }

    @GetMapping("/getAttendance/{id}")
    public ResponseEntity<Object> getOneAttendance(@PathVariable(value = "id") UUID id) {
        Optional<AttendanceModel> clientSellerModelOptional = attendanceService.findById(id);
        if (!clientSellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientSellerModelOptional.get());
    }

    @DeleteMapping("/removeAttendance/{id}")
    public ResponseEntity<Object> deleteAttendance(@PathVariable(value = "id") UUID id) {
        Optional<AttendanceModel> clientSellerModelOptional = attendanceService.findById(id);
        if (!clientSellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance not found!");
        }
        attendanceService.delete(clientSellerModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Attendance deleted successfully!");
    }

    @DeleteMapping("/deleteAttendance/{sellerId}")
    public ResponseEntity<Object> deleteAttendance(@PathVariable(value = "sellerId") long sellerId) {
        attendanceService.deleteAttendance(sellerId);
        return ResponseEntity.status(HttpStatus.OK).body("Attendance between client and seller was deleted successfully");
    }

    @PutMapping("/finishingAttendance/{id}")
    public ResponseEntity<Object> finishingAttendance(@PathVariable(value = "id") long id) {
        List<AttendanceModel> attendanceModelList = attendanceService.findAll();
        Optional<AttendanceModel> clientSellerModelOptional = attendanceService.findClientSellerModelBySellerModel_Id(id);

        if (clientSellerModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance not found!");
        }
        AttendanceModel attendanceModel = clientSellerModelOptional.get();
        attendanceModel.setEndTime(LocalDateTime.parse(formatter.format(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))), formatter));
        return ResponseEntity.status(HttpStatus.OK).body(attendanceService.save(attendanceModel));
    }

    @GetMapping("/clientIsServed/{id}")
    public ResponseEntity<Object> verifyClientService(@PathVariable(value = "id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(attendanceService.isBeingAttended(id));
    }
}
