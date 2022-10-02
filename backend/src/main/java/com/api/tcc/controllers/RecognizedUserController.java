/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.controllers;

import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.database.Models.ClientSellerModel;
import com.api.tcc.database.Models.SellerModel;
import com.api.tcc.services.ClientSellerService;
import com.api.tcc.services.ClientService;
import com.api.tcc.services.RecognizedUserService;
import com.api.tcc.services.SellerService;
import com.api.tcc.utils.IndexAndName;
import com.api.tcc.utils.ManipulatingImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Murilo de Paula Araujo
 */
@RestController
public class RecognizedUserController {

    @Autowired
    RecognizedUserService recognizedUserService;

    @Autowired
    ClientSellerService clientSellerService;

    @Autowired
    ClientService clientService;

    @Autowired
    SellerService sellerService;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    @PostMapping("/recognizedUser")
    public ResponseEntity<Object> recognizedUser(@RequestParam @Valid long userId) {

        IndexAndName indexAndName = recognizedUserService.verifyRecognizedClient(userId);

        if (indexAndName != null) {
            Optional<ClientModel> clientModelOptional = clientService.findById(userId);
            if (clientModelOptional.isPresent()) {
                System.out.println("The user is a client! - Id: " + indexAndName.getId() + ", Name: " + indexAndName.getName());
                if(clientSellerService.isBeingAttended(userId)) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The client is being attended or he is leaving the store!");
                } else {
                    ClientSellerModel clientSellerModel = new ClientSellerModel();
                    clientSellerModel.setStartTime(LocalDateTime.parse(formatter.format(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))), formatter));

                    SellerModel sellerModel = sellerService.matchMakingGetBestSeller(clientModelOptional.get());
                    if(sellerModel != null) {
                        sellerModel.setAvailable(false);
                        sellerService.save(sellerModel);
                        clientSellerModel.setSellerModel(sellerModel);
                    }

                    ClientModel clientModel = clientModelOptional.get();
                    clientService.save(clientModel);
                    clientSellerModel.setClientModel(clientModel);
                    return ResponseEntity.status(HttpStatus.CREATED).body(clientSellerService.save(clientSellerModel));
                }
            }
        } else {
            indexAndName = recognizedUserService.verifyRecognizedSeller(userId);
            if (indexAndName != null) {
                return ResponseEntity.status(HttpStatus.OK).body("Disregard facial recognition! The user is a seller! - Id: " + indexAndName.getId() + ", Name: " + indexAndName.getName() + ".");
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("Unknown User!");
    }

    @PostMapping("/unknownUserPhoto")
    public ResponseEntity<Object> postUnknownUserPhoto(@RequestPart(value = "image", required = false) MultipartFile data) {
        List<ClientSellerModel> clientSellerModelList = clientSellerService.findAll();
        List<Integer> clientSellerModelListAux = new ArrayList<>();
        ManipulatingImage manipulatingImage = new ManipulatingImage();
        int index = manipulatingImage.getLastIndexUnknownClientPhotos();

        // convert byte[] back to a BufferedImage
        if (data != null) {
            final String photoPath = "src\\main\\resources\\photos\\Unknown_Client\\UnknownClient_" + index + ".jpg";
            Path target = Paths.get(photoPath);
            try {
                Files.write(target, data.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ClientSellerModel clientSellerModel = new ClientSellerModel();
        clientSellerModel.setStartTime(LocalDateTime.parse(formatter.format(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))), formatter));
        SellerModel sellerModel = sellerService.getSellerWithLessAttendance();
        if (sellerModel != null) {
            sellerModel.setAvailable(false);
            sellerService.save(sellerModel);
            clientSellerModel.setSellerModel(sellerModel);
        }
        clientSellerService.save(clientSellerModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientSellerService.save(clientSellerModel));
    }
}
