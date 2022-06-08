package com.api.tcc.controllers;

import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.database.Models.ClientSellerModel;
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
import java.util.Optional;

@RestController
public class RecognizedUserController {

    @Autowired
    RecognizedUserService recognizedUserService;

    @Autowired
    ClientSellerService clientSellerService;

    @Autowired
    ClientService clientService;

    @PostMapping("/recognizedUser")
    public ResponseEntity<Object> recognizedUser(@RequestParam @Valid long userId) throws ParseException {

        IndexAndName indexAndName = recognizedUserService.verifyRecognizedClient(userId);

        if (indexAndName != null) {
            System.out.println("The user is a client! - Id: " + indexAndName.getId() + ", Name: " + indexAndName.getName());

            Optional<ClientModel> clientModelOptional = clientService.findById(userId);
            if (clientModelOptional.isPresent()) {
                ClientSellerModel clientSellerModel = new ClientSellerModel();
                clientSellerModel.setStartTime(LocalDateTime.now(ZoneId.of("UTC")));
                clientSellerModel.setClientModel(clientModelOptional.get());
                System.out.println("The user was registered in entity of attendance... Now, notify a seller available to server the client!");
                return ResponseEntity.status(HttpStatus.CREATED).body(clientSellerService.save(clientSellerModel));
            }
        } else {
            indexAndName = recognizedUserService.verifyRecognizedSeller(userId);
            if (indexAndName != null) {
                System.out.println("The user is a seller! - Id: " + indexAndName.getId() + ", Name: " + indexAndName.getName());
                System.out.println("Disregard recognition!");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unregistered user or the user is a store seller!");
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
