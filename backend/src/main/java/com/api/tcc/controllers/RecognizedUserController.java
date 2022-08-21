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
import com.api.tcc.utils.IndexAndName;
import com.api.tcc.utils.ManipulatingImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    @PostMapping("/recognizedUser") //TO DO analisar vendedor disponivel, melhor vendendor, indicar um vendedor PROBLEMA SE não tiver vendedor disponivel
    public ResponseEntity<Object> recognizedUser(@RequestParam @Valid long userId) {

        IndexAndName indexAndName = recognizedUserService.verifyRecognizedClient(userId);

        if (indexAndName != null) {
            System.out.println("The user is a client! - Id: " + indexAndName.getId() + ", Name: " + indexAndName.getName());

            Optional<ClientModel> clientModelOptional = clientService.findById(userId);
            if (clientModelOptional.isPresent()) {
                if(clientSellerService.isBeingAttended(userId)) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The client is being attended or he is leaving the store!");
                } else {
                    ClientSellerModel clientSellerModel = new ClientSellerModel();
                    clientSellerModel.setStartTime(LocalDateTime.parse(formatter.format(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))), formatter));
                    clientSellerModel.setClientModel(clientModelOptional.get());
                    System.out.println("The user was registered in entity of attendance... Now, notify a seller available to server the client!");
                    Optional<SellerModel> sellerModelOptional = clientSellerService.getBestSeller();
                    if(!sellerModelOptional.isEmpty()) {
                        clientSellerModel.setSellerModel(sellerModelOptional.get());
                    }
                    return ResponseEntity.status(HttpStatus.CREATED).body(clientSellerService.save(clientSellerModel));
                }
            }
        } else {
            indexAndName = recognizedUserService.verifyRecognizedSeller(userId);
            if (indexAndName != null) {
                return ResponseEntity.status(HttpStatus.OK).body("Disregard facial recognition! The user is a seller! - Id: " + indexAndName.getId() + ", Name: " + indexAndName.getName() + "."); //TO DO testar se no front end vai entender o comportamento, se não alterar para not found
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("Unregistered user in system!");
    }

    @PostMapping("/unknownUserPhoto")
    public ResponseEntity<Object> postUnknownUserPhoto(@RequestParam @Valid byte[] unknownUserPhoto) throws IOException {
        ManipulatingImage manipulatingImage = new ManipulatingImage();
        String decodeImage;
        if (unknownUserPhoto != null) {
            decodeImage = manipulatingImage.decodeImage(unknownUserPhoto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error - image is null!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(decodeImage);
    }
}
